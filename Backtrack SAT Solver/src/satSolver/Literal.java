package satSolver;

/**
 * The Literal class is a simple class of getters and setters, used to 
 * 	represent each individual literal in the formula.
 */
public class Literal 
{
	private int value, assignment = 0, removedLevel = 0;
	//value = the number representing the variable.
	//assignment = -1 for false,
	//				0 for unnassigned, 
	//				1 for true
	//removedLevel = the recursive level that the literal was removed at.
	//	removedLevel 0 means that the literal has yet to be removed.
	
	
	
	/**
	 * Constructor for a new Literal.
	 * @param value is the number representing the literal
	 */
	public Literal(int value)
	{
		this.value = value;
	}	
	
	
	
	/**
	 * This will set the recursive level for a literal that was removed.
	 * 	If the program runs 3 times, the recursive level will be on level 3.
	 * 	Any literals that should be removed at that point, will have their levels
	 * 	set to 3.
	 * @param level is the number passed into the method representing the level fo recursion
	 * 	that the program is on.
	 */
	public void setLevel(int level)
	{
		removedLevel = level;
	}
	
	
	
	/**
	 * @return The level of recursion this literal was removed at.
	 * 	If it was removed during the 3rd level of recursion, it will return 3.
	 */
	public int getLevel()
	{
		return removedLevel;
	}
	
	
	
	/**
	 * @return The assignment of either positive or negative will be returned. 
	 * The assignment is determined throughout the program and is represented by 
	 * 	-1 for false, 0 for unnasigned, or 1 for true.
	 */
	public int getAssignment()
	{
		return assignment;
	}
	
	
	/**
	 * @param assigned has a value of -1,0,or 1.
	 * 	In the case of -1, it will be assigned False.
	 * 	In the case of 0, it will be Unnasigned.
	 * 	In the case of 1, it will be assigned True.
	 */
	public void assign(int assigned)
	{
		assignment = assigned;
	}
	
	
	/**
	 * @return the number associated with this literal.
	 */
	public int getValue()
	{
		return value;
	}
}