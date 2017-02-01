package sudoku;

import java.util.ArrayList;
import java.util.List;

/**
 * The purpose of the decoder class is to recognize satisfiable variables 
 * given from the 'Encoder' class and arrange them properly in a new board object 
 * and display the final answer if satisfiable
 */
public class Decoder {

 List<Variable> variables;
 List<String> result;
 int dimension, boxSize;
 

 public Decoder(List<Variable> variables, int dimension, int boxSize)
 {
  this.variables = variables;
  this.dimension = dimension;
  this.boxSize = boxSize;
  result = new ArrayList<String>();

  for(int i = 0; i < (dimension * dimension); i++)
  {
   result.add("0");
  }
 }
 
 /**
  * This method prepares the final answer based off the value provided by Sat4J
  * if the given value is greater than zero(True) then add it to the final result
  * @param String values
  * @return The final product in the form of a 'Board'
  */

 public String decode(String values)
 {
  String board = "";

  String[] variableValues = values.split(" "); 

  int numToBeAdded = 0;

  for(int i = 0; i < variableValues.length; i++)
  {
   int temp = Integer.parseInt(variableValues[i]);
   if(i % (dimension * dimension) == 0)
   {
    numToBeAdded++;
   }
   if(temp > 0) // or num > 0 based on the output of SatSolver
   {
    result.set((i % (dimension * dimension)), "" + numToBeAdded);
   }
  }


  board += "\n ";

  for(int i = 0; i < result.size(); i++)
  {
   board += "|" + result.get(i) + "|";
   if((i + 1) % dimension == 0)
   {
    board += "\n";
   }
   if( (i + 1) % (dimension * boxSize) == 0)
   {
    board += "\n";
   }
   if((i + 1) % boxSize == 0)
   {
    board += " ";
   }
  }

  return board;
 }


}