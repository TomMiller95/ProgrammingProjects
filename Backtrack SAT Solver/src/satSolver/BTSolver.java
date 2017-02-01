package satSolver;

public class BTSolver {
	
	Formula formula;
	int level = 0;
	
	void readFormula ( String fileName ) {
		formula = new Formula(fileName);
	}
	
	// Set given variable to given true/false value
	// Variable value may be positive or negative
	void setVar (int var, Formula f, boolean tf) {
		
		boolean matched = false;
		
		//This will determine what to set the assignment to, and also 
		//	if the assignment matches the vars value in negativity.
		if (tf == true)
		{
			if (var > 0)
			{matched = true;}
			formula.setAssignment(1,var); //sets branch var to 1 for true.
		}
		else	//tf == false
		{
			if (var < 0)
			{matched = true;}
			formula.setAssignment(-1,var); //sets branch var to -1 for false.
		}
		
		//This will determine whether to remove the first literal, or all of the occurences of the var
		if (matched == true)
		{
			formula.removeLits(var,level);  //Removes all clauses with var in it, and any instance of NOT var
			level++;
		}
		else  //matched == false
		{
			formula.removeFirstLit(level); //removes the first literal in the current formula.
			level++;
		}
	}
	
	// Set given variable to "unassigned" in the given formula
	void unset (int var, Formula f) {
		level--;
		formula.recover(var,level);
		//formula.unassign(var);
	}
	
	// Formula is satisfiable
	void success (Formula f) {
		System.out.println ("Formula is satisfiable");	
		System.out.println(f.getOutput());
	}
	
	// Formula is unsatisfiable
	void failure (Formula f) {
		System.out.println ("Formula is unsatisfiable");
	}
	
 	public void solve ( String fileName ) {
		readFormula ( fileName );
		if (dp ( formula ) )
			success ( formula );
		else
			failure ( formula );
	}
	
 	// Recursive backtracking solution
	boolean dp ( Formula formula ) {
		if (formula.isEmpty(formula)) // First base case: solution found
			return true;
		else if (formula.hasEmptyClause (formula)) // Second base case: dead end found
			return false;
		else {
			
			// Pick a branch variable
			int var = formula.selectBranchVar(); 
			
			setVar ( var, formula, true );
			
			if (dp(formula)) 
				return true;
			else {
				
				// Unset var in the formula 
				unset ( var, formula );
				int tmp = var;
				if (tmp < 0)
				{
					tmp *= -1;
				}
				// Setting var to true did not work. 
				// Now try var = false
				
				setVar ( var, formula, false );
				
				if (dp (formula))
					return true;
				else {
					// Neither true nor false worked, so unset the branch 
					// variable and head back
					unset ( var, formula );
					int TMP = var;
					if (TMP < 0)
					{
						TMP *= -1;
					}
					return false;			
				}
			}
		}	
	}
	

	public static void main(String[] args) {
		
		if (args.length < 1) {
			System.err.println ("Usage: java dpsolver_skeleton cnf-formula");
			System.exit(0);
		}
		Timer t = new Timer();
		t.start();
		new BTSolver().solve ( args[0] );
		t.stop();
		System.out.println("Time: " + t.getDuration() + "ms");
	}
}