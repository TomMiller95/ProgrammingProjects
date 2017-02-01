package sudoku;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sean Dale, Dave Diienno, Tom Miller
 * This class will handle all of the data management, and has methods for accessing the values from the board.
 * Boards contain a List of List of variables, where the inner list is each row of the sudoku board, and the outer list
 * contains each of those rows.
 *
 */
public class Board {

	public List<List<Variable>> board;
	int numClauses = 0;

	public Board(int dim)
	{
		board = new ArrayList<List<Variable>>(dim);
	}

	public void add(List<Variable> list)
	{
		board.add(list);
	}

	public String toString()
	{
		String s = "";
		for(int idx = 0; idx < board.size(); idx++)
		{
			List<Variable> temp = board.get(idx);
			for(int var = 0; var < temp.size(); var++)
			{
				s += temp.get(var) + " ";
			}
			s += "\n";
		}
		return s;
	}

	//*************************************  ROW METHODS  *******************************************
	

	/**
	 * This method will go through the list of variables, and will generate part A of the row clauses
	 * for each row.
	 *  
	 * @param List<Variables> variables
	 * @param int dimension
	 * @return String clause
	 */
	public String generateClauseRow_A(List<Variable> variables, int dimension)
	{
		String clause = "";
		
		for(int idx = 0; idx < variables.size(); idx++)
		{
			clause += variables.get(idx) + " ";
			
			if((idx + 1) % dimension == 0) // If index + 1 (because dimension is not 0 based) % dimension
			{							   // is equivalent to 0, then we have looped through a whole row.
				clause += "0\n"; 		   // If we have looped through a whole row, add a 0 to the string.
				numClauses++;
			}
		}

		return clause;
	}

	/**
	 * This method will create the clauses for part B of the row constraints. It will use the base and the start to loop through each row.
	 * This method calls the helper method, and only generates the next starting position so the helper method can create the rest of the clause.
	 * 
	 * @param List<Variable> variables
	 * @param int dimension
	 * @return String clause
	 */
	public String generateClauseRow_B(List<Variable> variables, int dimension)
	{
		String clause = "";

		int bound = (dimension * (dimension - 1)) / 2; // The two is because we are always choosing by 2 (ie dimension choose 2)
		int base = 0;								   // Bound is just the simplified n C r.
		int other = 1;
		int start = 0;
		int numLoops = (dimension * dimension);

		for(int idx = 0; idx < numLoops; idx++) //we are looping through each set of Dimension sized chunks from the list of all possible variables
		{
			clause += ConstraintRowBHelper(base, other, bound, start, dimension, variables); // and iterating through it 
			base = base + dimension;														 // (dimension! / (dimension - 2)! * 2!)
			other = base + 1;
		}

		return clause;
	}

	/**
	 * Helper method for generateRowClause_B method.  This will generate a single clause based on the starting index provided.
	 * 
	 * @param int base
	 * @param int other
	 * @param int bound
	 * @param int start
	 * @param int dimension
	 * @param List<Variable> variables
	 * @return String clause
	 */
	private String ConstraintRowBHelper(int base, int other, int bound, int start, int dimension, List<Variable> variables)
	{
		String clause = "";
		Variable temp1, temp2;

		for(int i = start; i < bound; i++)
		{
			temp1 = variables.get(base);
			temp2 = variables.get(other);
			if(((temp2.col + 1) % dimension) == 0)
			{
				base++;
				other = base + 1;
			}
			else
			{
				other++;
			}
			clause += "-" + temp1 + " -" + temp2 + " 0\n";
			numClauses++;
		}

		return clause;
	}

	//*************************************  COL METHODS  *******************************************
	
	/**
	 * This method will generate all the constraint A clauses for columns.
	 * Specifically, it will 
	 * 
	 * @param List<Variable> variables
	 * @param int dimension
	 * @return String clause
	 */
	public String generateClauseCol_A(List<Variable> v, int dimension)
	{
		String clause = "";
		int idx = 0;
		int nextIdx = 1;
		for(int ctr = 1; ctr <= v.size(); ctr++)
		{
			clause += v.get(idx) + " ";
			idx = idx + dimension; // increment idx by size of dimension to get the element in the next row, same col
			if(ctr % dimension == 0)
			{
				clause += "0\n";
				numClauses++;

				if(nextIdx % dimension == 0)
				{
					nextIdx += ((dimension * dimension) - dimension) + 1; // This formula gets the first position
					idx = nextIdx - 1;					// of the next set of dimension * dimension positions
				}
				else
				{
					idx = nextIdx;
					nextIdx++;
				}
			}
		}
		return clause;
	}

	/**
	 * This method will generate all of the constraint B methods for col.
	 * Specifically it will generate all of the starting positions and pass them into the helper function.
	 * 
	 * @param List<Variable> variables
	 * @param int dimension
	 * @return String clause
	 */
	public String generateClauseCol_B(List<Variable> variables, int dimension)
	{
		String clause = "";

		int bound = (dimension * (dimension - 1)) / 2; // the two is because we are always choosing by 2 (ie dimension choose 2)
		int base = 0;
		int other = dimension; // Start it at the position of the next element in the column.
		int start = 0;
		int numLoops = (dimension * dimension);

		for(int ctr = 0; ctr < numLoops; ctr++) //we are looping through each set of Dimension sized chunks from the list of all possible variables
		{
			clause += ConstraintCol_BHelper(base, other, bound, start, dimension, variables); // and iterating through it 
			base++;																	  // (dimension! / (dimension - 2)! * 2!)
			if(base % dimension == 0)
			{
				base += ((dimension * dimension) - dimension); // Same as above, will get the starting position of the first column in the
			}												   // next set of values.
			other = base + dimension;
		}

		return clause;
	}

	/**
	 * This method will, given the starting position, create a string of all the col clauses for constraint B
	 * 
	 * @param int base
	 * @param int other
	 * @param int bound
	 * @param int start
	 * @param int dimension
	 * @param List<Variable> variables
	 * @return String clause
	 */
	private String ConstraintCol_BHelper(int base, int other, int bound, int start, int dimension, List<Variable> variables)
	{
		String clause = "";
		Variable temp1, temp2;

		for(int i = start; i < bound; i++)
		{
			temp1 = variables.get(base);
			temp2 = variables.get(other);
			if(((temp2.row + 1) % dimension) == 0) // If the row mod dimension (+ 1 cause its 0 based) equals 0, increment
			{									   // the base by the size of the dimension to get the next element down.
				base += dimension;
				other = base + dimension;
			}
			else
			{
				other += dimension;
			}
			clause += "-" + temp1 + " -" + temp2 + " 0\n";
			numClauses++;
		}
		return clause;
	}

	//*************************************  BOX METHODS  *******************************************

	/**
	 * This method will generate all of the starting positions for the helper method for constraint A for boxes.
	 * 
	 * @param List<Variable> variables
	 * @param int boxSize
	 * @return String clause
	 */
	public String generateClauseBox_A(List<Variable> variables, int boxSize)
	{
		String clause = "";
		int numLoops = (boxSize * boxSize);
		int nextStart = (boxSize * boxSize * boxSize); // This int will be used to calculate the starting position of the next box
		int start = 0;								   // in the next row of boxes.
		int boxCtr = 1;

		for(int ctr = 1; ctr <= (numLoops * numLoops); ctr++)
		{
			clause += generateClauseBox_AHelper(variables, boxSize, start);
			start += boxSize; // increment position by size of box

			if(start % numLoops == 0)
			{
				start = nextStart * boxCtr; // the boxCtr is there only to navigate to the next level of boxes.
				boxCtr++;
			}
		}
		return clause;
	}

	/**
	 * This helper method will create a string of clauses for the individual boxes given the starting input provided
	 * by the generateClauseBox_A method.
	 * 
	 * @param List<Variable> variables
	 * @param int boxSize
	 * @param int start
	 * @return String clause
	 */
	private String generateClauseBox_AHelper(List<Variable> variables, int boxSize, int start)
	{
		String clause = "";

		int count = 0;
		int colStart = start;
		for(int i = 1; i <= (boxSize * boxSize); i++) // each box will have boxSize * boxSize elements
		{
			if(count != boxSize) // Still iterating through the current row
			{
				clause += variables.get(start) + " ";
				count++;
				start++;
			}
			else // completed the current row of the box
			{
				count = 0;
				colStart += (boxSize * boxSize); // Used to get to the index of the start of the next row
				start = colStart;
				clause += variables.get(start) + " ";
				count++;
				start++;
			}
		}
		clause += "0\n";
		numClauses++;

		return clause;
	}

	/**
	 * This method will generate  Constraint B clauses for boxes.
	 * Specifically it will generate the starting position for the helper method.
	 * 
	 * @param List<Variable> variables
	 * @param int boxSize
	 * @return String clause
	 */
	public String generateClauseBox_B(List<Variable> variables, int boxSize)
	{
		String clause = "";

		int base = 0;
		int start = 0;
		int numLoops = (boxSize * boxSize); // dimension
		int bound = (numLoops * (numLoops - 1)) / 2; // the two is because we are always choosing by 2 (ie dimension choose 2)
		int startCount = 1;							 // dimension * dimension - 1 because when simplified, it is thusly
		int nextStart = (boxSize * boxSize * boxSize);

		for(int i = 0; i < (numLoops * numLoops); i++) //we are looping through each set of Dimension sized 
		{												// chunks from the list of all possible variables.
			clause += ConstraintBox_BHelper(base, bound, start, boxSize, variables); // and iterating through it 
			base += boxSize;														//(dimension! / (dimension - 2)! * 2!) 
			if(base % numLoops == 0)
			{
				base = nextStart * startCount;
				startCount++;
			}
		}

		return clause;
	}

	/**
	 * This method will generate the group of clauses for a single box given the starting position.
	 * 
	 * @param int base
	 * @param int bound
	 * @param int start
	 * @param int boxSize
	 * @param List<Variable> variables
	 * @return String clause
	 */
	private String ConstraintBox_BHelper(int base, int bound, int start, int boxSize, List<Variable> variables)
	{
		String clause = "";
		Variable temp1, temp2;
		int other = base + 1;

		for(int i = start; i < bound; i++)
		{
			temp1 = variables.get(base);
			temp2 = variables.get(other);

			if(((temp2.getRow() + 1) % boxSize == 0) && ((temp2.getCol() + 1) % boxSize == 0)) // If the row and col % boxSize == 0
			{																				   // we are at the last element of the box
				if((base + 1) % boxSize == 0) // If the base % boxSize == 0, we need to put the base in the next row of the box
					base += ((boxSize * boxSize) - boxSize) + 1;
				else // We just increment base by 1
					base++;
				other = base + 1;
				if(other % boxSize == 0) // if we are at the last position in the row for the box, our other needs to be in the next row.
					other += (boxSize * boxSize) - boxSize;
			}
			else if(!((temp2.getRow() + 1) % boxSize == 0) && ((temp2.getCol() + 1) % boxSize == 0)) // If only the col (and not the row)
			{																			// mod boxSize == 0, we need to go to the next row.
				other += ((boxSize * boxSize) - boxSize) + 1; // This will increment the position of other to the starting pos of next row.
			}
			else 
			{
				other++;
			}
			clause += "-" + temp1 + " -" + temp2 + " 0\n";
			numClauses++;
		}

		return clause;
	}

	//*************************************  C4 METHODS  *******************************************

	/**
	 * This will generate the constraint A methods of Constraint 4, which is that every cell must have
	 * at least one variable in it.
	 * 
	 * @param List<Variables> variables
	 * @param int dimension
	 * @return String clause
	 */
	public String generateClauseC4A(List<Variable> variables, int dimension)
	{
		String clause = "";
		int numLoops = dimension * dimension; // for the number of cells
		int increment = dimension * dimension; // increment by the total num of cells of one board
		int index; // the actual index of the element

		for(int i = 0; i < numLoops; i++)
		{
			index = i; // every loop, reset index to i
			for(int j = 0; j < dimension; j++)
			{
				clause += variables.get(index) + " ";
				index += increment; // increment by the size of the board to get the next variable up (ie: 1 to 2)
			}
			clause += "0\n";
			numClauses++;
		}

		return clause;
	}

	/**
	 * This method will create all of the starting positions for the helper method
	 * 
	 * @param List<Variable> variables
	 * @param int dimension
	 * @return String clause
	 */
	public String generateClauseC4B(List<Variable> variables, int dimension)
	{
		String clause = "";
		int bound = (dimension * (dimension - 1)) / 2;

		for(int i = 0; i < dimension * dimension; i++)
		{
			clause += generateClauseC4BHelper(variables, dimension, i, bound);
		}

		return clause;
	}

	/**
	 * This method will create all the constraint B clausess for Constraint 4
	 * 
	 * @param List<Variable> variables
	 * @param int dimension
	 * @param int start
	 * @param int bound
	 * @return String clause
	 */
	public String generateClauseC4BHelper(List<Variable> variables, int dimension, int start, int bound)
	{
		String clause = "";
		Variable temp1;
		Variable temp2;
		int increment = dimension * dimension; // incrementing by the size of the board
		int last = start + (increment * (dimension - 1)); // index of the last element to check for out of bounds arrays
		int base = start;
		int other = start + increment;

		for(int i = 0; i < bound; i++)
		{
			temp1 = variables.get(base);
			temp2 = variables.get(other);

			if(other < last)
			{
				other += increment;
			}
			else
			{
				base += increment;
				other = base + increment;
			}
			clause += "-" + temp1 + " -" + temp2 + " 0\n";
			numClauses++;
		}

		return clause;
	}

	//*************************************  PRESET METHODS  *******************************************

	/**
	 * This method will loop through the preset values read from the Encoder class 
	 * and generate clauses for each variable given.
	 * 
	 * @param int dimension
	 * @return String clause
	 */
	public String generatePresetClauses(int dimension)
	{
		String clause = "";
		
		for(int idx = 0; idx < board.size(); idx++)
		{
			List<Variable> temp = board.get(idx);
			for(int varPos = 0; varPos < temp.size(); varPos++)
			{
				Variable tempVar = temp.get(varPos);
				if(tempVar.getVal() != 0)
				{
					clause += (((tempVar.getVal() - 1) * (dimension * dimension)) 				// this will generate the correct value
							+ (dimension * tempVar.getRow()) + tempVar.getCol() + 1) + " 0\n";	// for the variable of that position on the board
					numClauses++;																// that will correspond to the list of variables
				}
			}
		}
		
		return clause;
	}

}
