package satSolver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Formula 
{ 
	Clause clause; 	//Used for for extracting info from the file.
	int numClauses;	//Used for for extracting info from the file.
	int numVars;	//Used for for extracting info from the file.
	int[] output;	//This will hold the assignments throughout the program.
	List<Clause> clauses = new ArrayList<Clause>();	//All of the clauses in the formula.
	
	
	public Formula(String fileName)
	{
		readFile(fileName);
	}

	public void readFile(String fileName)
	{
		try
		{
			File file = new File(fileName);
			Scanner scan = new Scanner(file);
			Literal vars;

			while(scan.hasNext())
			{
				String s = scan.next();
				if(s.equals("c"))
				{
					scan.nextLine();
				}
				else if(s.equals("p"))
				{
					scan.skip(" cnf ");
					numVars = scan.nextInt();
					output = new int[numVars];
					numClauses = scan.nextInt();
					clauses = new ArrayList<Clause>(numClauses);
					clause = new Clause();
				}
				else
				{
					int temp = Integer.parseInt(s);
					vars = new Literal(temp);
					if(vars.getValue() != 0)
					{
						clause.addLiteral(vars);
					}
					else
					{
						clauses.add(clause);
						clause = new Clause();
					}
				}
			}
			scan.close();
		}
		catch(IOException e)
		{
			System.out.println("No file found");
		}
	}
	
	/**
	 * This method adds/replaces assignments for the final output.
	 * It takes in an index, which correlates with the literals, and it
	 * 	changes the value to the value passed in with assignment.
	 * 
	 * @param index	the index that we want to add to.
	 * @param assignment the value that we want to add.
	 */
	private void addToOutput(int index, int assignment)
	{
		output[index] = assignment;
	}
	
	/**
	 * This will go through output, and switch any -1's into 0's.
	 * @return the final output of 0's and 1's of the program.
	 */
	public String getOutput()
	{
		String o = "";
		for (int i = 0; i < output.length; i++)
		{
			if (output[i] == 1)
			{
				o += "1";
			}
			else if (output[i] == -1) 
			{
				o += "0";
			}
			else
				o += "0";
		}
		return o;
	}
	
	/**
	 * This will got through the formula and find the first clause with a literal in it.
	 * Then it will assign the first literal in that clause to the assignment passed in.
	 */
	public void setAssignment(int assign,int var)
	{
		int x = 0;
		while (x < clauses.size())
		{
			Clause tmp = clauses.get(x);
			if (tmp.isSatisfied() == false && tmp.isEmpty() == false)
			{
				tmp.setAssignment(assign);
				x = clauses.size();	//Quits the loop
				int varTMP = var;
				if (varTMP < 0)	//Incase it is neagtive, this fixes it.
				{varTMP *= -1;}
				addToOutput(varTMP-1,assign);	//Adds to the final output.
			}
			x++;
		}
}
	
	/**
	 * Goes through each clause in order until it finds one that is not empty.
	 * It then calls the getFirstValue method which returns the first value
	 * in that clause.
	 */
	public int selectBranchVar()
	{
		int x = 0;
		while (x < clauses.size())
		{
			Clause tmp = clauses.get(x);
			if (tmp.isSatisfied() == false && tmp.isEmpty() == false)
			{
				return clauses.get(x).getFirstValue();
			}
			x++;
		}
		return 0;
	}
	
	
	/**
	 * Loops through the clauses and returns true if it ever finds a clause that is empty.
	 * Returns false if otherwise.
	 */
		boolean hasEmptyClause ( Formula f ) 
		{
			for (int c = 0; c < clauses.size(); c++)
			{
				if (clauses.get(c).isSatisfied() == false && clauses.get(c).isEmpty() == true)
				{
					return true;
				}
			}
			return false;
		}
		
		/**
		 * Loops through and calls each clauses isSatisfied method, which checks if they 
		 * have been satisfied or not. If it reaches the end without finding one unsatisfied,
		 * it returns true.
		 * Returns false otherwise.
		 */
		boolean isEmpty ( Formula f ) {
			for (int c = 0; c < clauses.size(); c++)
			{
				if (clauses.get(c).isSatisfied() == false)
				{
					return false;
				}
			}
			return true;
		}
		
		
		/**
		 * This will recover any literals with the same value as int var.
		 * They will be added from the clauses trash list, and added into its list of literals
		 */
		public void recover(int var, int level)
		{
			for (int x = 0; x < clauses.size(); x++)
			{
				clauses.get(x).recoverLits(level);
			}
		}
		

		/**
		 * This removes the first variable in the whole formula that can be found
		 * This occurs when the branch var does not match the assignment given in BTSolver
		 */
		public void removeFirstLit(int level)
		{
			int x = 0;
			while (x < clauses.size())
			{
				if (clauses.get(x).isEmpty() == false)
				{
					clauses.get(x).removeFirst(level);
					x = clauses.size();
				}
				x++;
			}
		}
		
		
		/**
		 * This method removes all clauses with int value in them, and any instances of 
		 * NOT value in any other clauses.
		 */
		public void removeLits(int value, int level)
		{
			for (int clause = 0; clause < clauses.size(); clause++)
			{
				if (clauses.get(clause).isSatisfied() == false)
				{
					clauses.get(clause).removeLiterals(value,level);
				}
			}
		}
}