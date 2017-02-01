package sudoku;


/**
 * @author Sean Dale, Dave Diienno, Tom Miller
 * This class defines what a basic variable is.  A variable has a column, row, and a value.
 * Contains getters and setters for each of these fields.
 */
public class Variable {
	
	public int row, col, val;

	public Variable(int val, int row, int col)
	{
		this.row = row;
		this.col = col;
		this.val = val;
	}
	
	public Variable(int val)
	{
		this.val = val;
	}
	
	public int getRow()
	{
		return row;
	}
	
	public int getCol()
	{
		return col;
	}
	
	public int getVal()
	{
		return val;
	}
	
	public String toString()
	{
		return "" + val;
	}
	
	public String toStringFull()
	{
		return "[" + val + "," + row + "," + col + "]";
	}
	
}
