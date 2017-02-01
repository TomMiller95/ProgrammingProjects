package sudoku;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * The purpose of this class is to read a non-CNF file that
 * contains the parameters for a potential sudoku board and
 * generate all appropriate rows and columns and then 
 * further encoded into the appropriate number of clauses.
 * 
 * The clauses are then written into a CNF file which will be passed
 * to an external SAT solver.
 */
public class Encoder {

 public static int BOARD_LENGTH = 0;
 public static int BOARD_WIDTH = 0;
 public static int DIMENSION;
 public static int NUM_VARS;
 public Board board;
 public List<Variable> variables;
 public File cnfFile;

 public Encoder(String filename)
 {
  readFile(filename);
  DIMENSION = BOARD_LENGTH * BOARD_WIDTH;
  generateVars();

 }


 /**
  * Reads a non-CNF file and creates the board dimensions based off the first 
  * two beginning non-commented integers and adds appropriate rows and columns 
  * to the 'Board' object for further encoding.
  * @param filename
  */
 public void readFile(String filename)
 {
  int col = 0;
  int row = 0;

  try
  {
   File file = new File(filename);
   Scanner scan = new Scanner(file);
   int preset, dim, ctr = 0;
   List<Variable> tempRow = new ArrayList<Variable>(BOARD_LENGTH * BOARD_LENGTH);

   while(scan.hasNext())
   {

    String s = scan.next();
    if(s.equals("c"))
    {
     scan.nextLine();
    }
    else if(BOARD_LENGTH != 0 && BOARD_WIDTH != 0)
    {
     preset = Integer.parseInt(s);
     tempRow.add(new Variable(preset, row, col));
     ctr++;
     col++;
     if(ctr == BOARD_LENGTH * BOARD_LENGTH)
     {
      board.add(tempRow);
      ctr = 0;
      tempRow = new ArrayList<Variable>(BOARD_LENGTH * BOARD_LENGTH);
      col = 0;
      row++;
     }
    }
    else
    {
     dim = Integer.parseInt(s);
     BOARD_LENGTH = dim;
     dim = scan.nextInt();
     BOARD_WIDTH = dim;
     board = new Board(BOARD_WIDTH * BOARD_WIDTH);
     NUM_VARS = (int) Math.pow((double)(DIMENSION), 3.0);
     variables = new ArrayList<Variable>(NUM_VARS);
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
  * The writeFile method is used to write the row, box, and column clauses to
  * an appropriate CNF file, which will be later used in a SAT solver
  * @param String filename
  */
 public void writeFile(String filename)
 {
  String fileData = "";
  fileData += rowClausestoFile();
  fileData += colClausestoFile();
  fileData += boxClausestoFile();
  fileData += c4ClausestoFile();
  fileData += presetClausestoFile();

  BufferedWriter writer = null;
  try
  {
   writer = new BufferedWriter( new FileWriter( filename));
   writer.write( "p cnf " + variables.size() + " " + board.numClauses + "\n");
   writer.write(fileData);
  }
  catch ( IOException e)
  {
  }
  finally
  {
   try
   {
    if ( writer != null)
     writer.close( );
   }
   catch ( IOException e)
   {
   }
  }
 }

 /**
  * A void method that creates the correct number of variables based
  * off of the file previously read. The dimensions are used to place 
  * the variable in their respected row and column, while also retaining the
  * correct Integer value.
  */
 public void generateVars() // changed to increment value by 1 every time
 {
  int v = 1;
  for(int val = 1; val <= (DIMENSION); val++)
  {
   for(int r = 0; r < (BOARD_LENGTH * BOARD_LENGTH); r++)
   {
    for(int c = 0; c < (BOARD_WIDTH * BOARD_WIDTH); c++)
    {
     variables.add(new Variable(v, r, c));
     v++;
    }
   }
  }
 }
 /**
  * This method creates the appropriate clauses for the rows
  * based off the constraints provided to ensure a properly 
  * solved sudoku board.
  * @return String clauses
  */
 public String rowClausestoFile()
 {
  String rowClauses = "";
  rowClauses += board.generateClauseRow_A(variables, DIMENSION);
  rowClauses += board.generateClauseRow_B(variables, DIMENSION);
  return rowClauses;
 }
 /**
  * This method creates the appropriate clauses for the columns
  * based off the constraints provided to ensure a properly 
  * solved sudoku board.
  * @return String clauses
  */
 public String colClausestoFile()
 {
  String colClauses = "";
  colClauses += board.generateClauseCol_A(variables, DIMENSION);
  colClauses += board.generateClauseCol_B(variables, DIMENSION);
  return colClauses;
 }
 /**
  * This method creates the appropriate clauses for the individual
  * box cells based off the constraints provided to ensure a properly 
  * solved sudoku board.
  * @return String clauses
  */
 public String boxClausestoFile()
 {
  String boxClauses = "";
  boxClauses += board.generateClauseBox_A(variables, BOARD_WIDTH);
  boxClauses += board.generateClauseBox_B(variables, BOARD_WIDTH);
  return boxClauses;
 }
 /**
  * Method for the fourth constraint which ensures that every cell
  * in the sudoku board has a variable
  * @return String Clauses
  */
 public String c4ClausestoFile()
 {
  String c4Clauses = "";
  c4Clauses += board.generateClauseC4A(variables, DIMENSION);
  c4Clauses += board.generateClauseC4B(variables, DIMENSION);
  return c4Clauses;
 }
 /**
  * This method creates the clauses based off (if given) the preset integer
  * values provided in the File.
  * @return String clauses
  */
 public String presetClausestoFile()
 {
  String presetClauses = "";
  presetClauses += board.generatePresetClauses(DIMENSION);
  return presetClauses;
 }

}