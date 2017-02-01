package sudoku;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import org.sat4j.BasicLauncher;
import org.sat4j.pb.SolverFactory;
import org.sat4j.reader.DimacsReader;
import org.sat4j.reader.InstanceReader;
import org.sat4j.reader.ParseFormatException;
import org.sat4j.reader.Reader;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.IProblem;
import org.sat4j.specs.ISolver;
import org.sat4j.specs.TimeoutException;


public class TestDriver {

	@SuppressWarnings("deprecation")
	public static void main( final String[] args) throws IOException {
		String fileLocation = "src" + File.separatorChar
                + "sudoku" + File.separatorChar
                + "solvable49by49";	//Change this to test different files.
		
		//Examples to run
		//  solvable2by2
		//	TESTFORSUDOKU
		//	sudokuEmptyBoard
		//	sudokuTestFromBook
		//	sudokuTest3x3
		//	unsolvableBox
		//	unsolvableCol
		//	unsolvableRow
		//	solvable49by49		<-- This may take about 9 minutes or so
		
		Encoder en = new Encoder(fileLocation);
		
		
		Decoder dec = new Decoder(en.variables, Encoder.DIMENSION, Encoder.BOARD_LENGTH);
		
		
		en.writeFile("SudokuTest.cnf");
		
		ISolver solver = SolverFactory . newDefault ();
		solver.setVerbose(true);
		solver.setTimeout (3600); // 1 hour timeout
		Reader reader = new DimacsReader ( solver );
		reader.setVerbosity(true);
		// CNF filename is given on the command line
		try {
		final IProblem problem = reader.parseInstance ( "SudokuTest.cnf" );
		if ( problem.isSatisfiable ()) {
		System.out.println(" Satisfiable !");
		reader.decode ( problem.model(), new PrintWriter(System.out));
		String solvedLiterals = "";
		for(int i = 0; i < solver.model().length; i++)
		{
			solvedLiterals += solver.model()[i] + " ";
		}
		System.out.println(dec.decode(solvedLiterals));
		} else {
		System.out.println (" Unsatisfiable !");
		}
		} catch ( FileNotFoundException e) {
		// TODO Auto - generated catch block
		} catch ( ParseFormatException e) {
		// TODO Auto - generated catch block
		} catch ( IOException e) {
		// TODO Auto - generated catch block
		} catch ( ContradictionException e) {
		System.out.println (" Unsatisfiable ( trivial )!");
		} catch ( TimeoutException e) {
		System.out.println (" Timeout , sorry !");
		}
		
		
		System.out.println(en.board.numClauses);


	}

}
