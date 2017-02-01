package satSolver;

import java.util.ArrayList;
import java.util.List;

public class Clause 
{
	List<Literal> literals = new ArrayList<Literal>();	//The list of literals for this clause.
	List<Literal> trash = new ArrayList<Literal>();	//The list of removed literals for this clause.
	boolean satisfied = false;	//boolean to know whether a clause is satisfied or not.
	
	/**
	 * Adds a literal to the list of literals that make up this clause
	 */
	public void addLiteral(Literal lit)
	{
		literals.add(lit);
	}
	
	
	/**
	 * This returns the value of the first literal in the list of literals in this clause
	 */
	public int getFirstValue()
	{
		return literals.get(0).getValue();
	}
	
	
	/**
	 * returns true if the list of literals in this clause has nothing in it
	 */
	public boolean isEmpty()
	{
		return literals.size() == 0;
	}
	
	/**
	 * @param s sets a clause to be satisfied or unsatisfied.
	 */
	public void setSatisfied(boolean s)
	{
		satisfied = s;
	}
	
	/**
	 * @return true if the clause is satisfied, false if not.
	 */
	public boolean isSatisfied()
	{
		return satisfied;
	}

	/**
	 * @param assignment This is the value of 1, -1 or 0 which will determine
	 * 	if a literal is true, false, or unnasigned
	 */
	public void setAssignment(int assignment)
	{
		literals.get(0).assign(assignment);
	}
	
	/**
	 * This will recover all the literals in a clause with a matching level.
	 * 	That means that all the literals in the trash, will go into
	 * 	the literals list. This only happens if the level passed in, matches 
	 * 	the removedLevel of the literal in the trash
	 * @param level This is the number that will determine which literals to recover.
	 * 	If it is 3, any literals with a removedLevel eqaul to 3, will be placed back into 
	 * 	the list of literals.
	 */
	public void recoverLits(int level)
	{
		for (int i = 0; i < trash.size(); i++)
		{
			if (trash.get(i).getLevel() == level)
			{
				trash.get(i).setLevel(0);	//Sets the literals being switched, to removedLevel 0.
				literals.add(trash.get(i));	
				trash.remove(i);	
				satisfied = false;
				i--;		//Used to make sure we dont skip over an item in the list
							//due to removal of another item.
			}
		}
	}
	
	
	/**
	 * This method is used when the branch variable does not match the partial 
	 * 	assignment. Example: The assignment it TRUE, but the branch var is -3.
	 * This results in only the branch variable being removed from the clause.
	 * 
	 * @param level This will change the first literal in this clause to the level passed in.
	 * 	The literal's removedLevel will equal the parameters 'int level'.
	 */
	public void removeFirst(int level)
	{
		literals.get(0).setLevel(level);	//Sets the level of the removed literal.
		trash.add(literals.get(0));	
		literals.remove(0);	
	}
	
	/**
	 * This will remove literals from the formula.
	 * There are two general cases that will determine what to remove.
	 * Case 1: The literal matches the value passed in by value and negativity.
	 * 		Example: literal: -5	value: -5
	 * Case 2: The literal matches the value passed in by value only.
	 * 		Example: literal: -5	value: 5
	 * 
	 * In Case 1, every literal in the clause would be removed.
	 * In Case 2, only that specific literal will be removed.
	 * 
	 * @param value This is the value of the branch variable passed in.
	 * 	It will be used to compare every literal in a clause.
	 * @param level	If a literal is removed, it will have its removedLevel set to this.
	 */
	public void removeLiterals(int value, int level)
	{
		for (int l = 0; l < literals.size(); l++)	
		{
			int lit = literals.get(l).getValue();	//Represents the literal matching the current index.
			if (lit == value * -1)	//This checks if value has the same number as the 
			{						//	literal, but not the same negativity.
				literals.get(l).setLevel(level);	
				trash.add(literals.get(l));	
				literals.remove(l);
			}
			else if (lit == value)	//This checks if the literal's value AND negativity
			{						// match the value passed in the parameter.
				for (int li = 0; li < literals.size(); li++)	
				{
					literals.get(li).setLevel(level);	
					trash.add(literals.get(li));	
				}
				literals.clear();	//Empties this clauses list of literals.
				satisfied = true;	
			}
		}
	}
}