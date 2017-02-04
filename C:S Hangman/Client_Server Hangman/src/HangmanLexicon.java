/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import acm.util.*;
import java.io.*;
import java.util.*;

public class HangmanLexicon {

	public HangmanLexicon(){
		BufferedReader reader = openFile();
		
		while(true){
			try{
				String word = reader.readLine();
				if(word == null) break;
				wordList.add(word);
			} catch(IOException ex) {
				throw new ErrorException(ex);
			}
		}
	}
	
	private BufferedReader openFile(){
		BufferedReader rd = null;
		try {
			rd = new BufferedReader(new FileReader("HangmanLexicon.txt"));
		} catch(IOException ex) {
			throw new ErrorException(ex);
		}
		return rd;
	}
	
/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return wordList.size();
	}

/** Returns the word at the specified index. */
	public String getWord(int index) {
		return wordList.get(index);
	}
	
	ArrayList<String> wordList = new ArrayList<String>();
}
