package satSolver;

import java.io.File;

public class TestDriver {
	public static void main(String args[]){
		Timer t = new Timer();
		t.start();
		BTSolver tester = new BTSolver();
		
		String fileLocation = "src" + File.separatorChar
                + "satSolver" + File.separatorChar
                + "u32.cnf";	//Change this to test different files.
		
		//Some examples to choose from
		//	s20.cnf
		//	s28.cnf
		//	Simple File
		//	u15.cnf
		//	u27.cnf
		//	u29.cnf
		//	u30.cnf
		//	u32.cnf
		
		tester.solve(fileLocation);
		//tester.solve("/Users/Tom/Desktop/ANewHope/backtrack/backtrack/s28.cnf");
		t.stop();
		System.out.println("Time: " + t.getDuration() + "ms");
		System.out.println("Time: About " + t.getDuration()/1000 + "." + t.getDuration()%1000 + " second(s)");
	}
}