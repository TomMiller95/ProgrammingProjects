
import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram {   
    private HangmanCanvas canvas;
    private HangmanClient client;
    
    public void init(){
        canvas = new HangmanCanvas();
        client = new HangmanClient();
        add(canvas);
    }
    
    public void run() {
        println("Welcome to Hangman!");
        String answer = readLine("Do you want to play solo (1) or competetive (2)?  ");
        if(answer.equals("1")){
            runSingle();
        } else {
            runDouble();
        }
    }
    
    private void runSingle(){
        while(true){
            HangmanLexicon words = new HangmanLexicon();
            int numberOfWords = words.getWordCount();
            int wordNumber = rgen.nextInt(0, numberOfWords - 1);
            String newWord = words.getWord(wordNumber);
            runGame(newWord);
        
            String answer = readLine("Do you want to play again? ");
            if(!answer.equals("y")){
                println("Thanks for playing!");
                break;
            }
        }
    }

    private void runDouble(){
        
        
        while(true){
            String userName = readLine("Please enter your name: ");
            String needWord = readLine("Please enter your word: ");
            String playWord = client.processServer(userName, needWord);
            
            runGame(playWord.toUpperCase());
            
            String answer = readLine("Do you want to play again? ");
            if(!answer.equals("y")){
                println("Thanks for playing!");
                break;
            }
            
        }
    }
    
    private void runGame(String wordToGuess){
        int wrongGuessCount = 0;
        
        String newWord = wordToGuess; 
        int wordLength = newWord.length();
        String guessWord = "";
            
        canvas.reset();
        println("NAME GOES HERE");
        for(int i = 0; i < wordLength; i++){
            guessWord += "-";
        }
        println("The word now looks like this: " + guessWord);
        canvas.displayWord(guessWord);
        
        while(guessesLeft(wrongGuessCount) && (wordIsGuessed(newWord, guessWord) == false)){
            println("You have " + (wrongGuessesAllowed - wrongGuessCount) + " guesses left.");
            char ch = getLetter();
            if(checkLetter(newWord, ch)){
                println("That guess is correct.");
                guessWord = goodGuess(newWord, ch, guessWord);
                println("The word now looks like this " + guessWord);
                canvas.displayWord(guessWord);
            } else {
                wrongGuessCount = badGuess(wrongGuessCount, ch);
                println("The word now looks like this " + guessWord);
                canvas.displayWord(guessWord);
                canvas.noteIncorrectGuess(wrongGuessCount, ch);
            }
        }
        
        if(wordIsGuessed(newWord, guessWord)){
            println("That guess is correct.");
            println("You guessed the word: " + newWord);
            canvas.displayWord(newWord);
            println("You win!");
            println("Thanks for playing.");
        } else {
            println("You're completely hung.");
            println("The word was: " + newWord);
            canvas.displayWord(newWord);
            println("Sorry, you lose.");
            println("Thanks for playing.");
        }
            
    }
    
    private boolean guessesLeft(int wrongGuessCount){
        if(wrongGuessCount < wrongGuessesAllowed){
            return true;
        } else {
            return false;
        }
    }
    
    private boolean wordIsGuessed(String newWord, String guessWord){
        return newWord.equals(guessWord);
    }
    
    private char getLetter(){
        letterGuessed = readLine("Your guess: ");
        if (letterGuessed.length() != 1){
            println("Invalid guess.");
            getLetter();
        }
        
        char ch = letterGuessed.charAt(0);
        ch = Character.toUpperCase(ch);
        return ch;
    }

    private boolean checkLetter(String newWord, char ch){
        int check = newWord.indexOf(ch);
        
        if (check == -1){
            return false;
        } else {
            return true;
        }
    }

    private String goodGuess(String newWord, char ch, String guessWord){
        String tempWord = newWord;
        int check = tempWord.indexOf(ch);
        
        while (check != -1){
            guessWord = guessWord.substring(0, check) + ch + guessWord.substring(check + 1);
            tempWord = tempWord.substring(0, check) + " " + tempWord.substring(check + 1);
            check = tempWord.indexOf(ch);
        }
        return guessWord;
    }

    private int badGuess(int wrongGuessCount, char ch){
        if(!wrongLetters.contains(Character.toString(ch))){
        	wrongGuessCount += 1;
        	wrongLetters += ch;
        }
    	println("There are no " + ch + "'s in the word.");
        return wrongGuessCount;
    }
    
    private static final int wrongGuessesAllowed = 8;
    private static String letterGuessed;
    private static String wrongLetters = "";
    private static final RandomGenerator rgen = RandomGenerator.getInstance();

}