/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import java.awt.Canvas;

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {

/** Resets the display so that only the scaffold appears */
	public void reset() {
		removeAll();
		GLine scaffold = new GLine (36, 31, 36, 31 +SCAFFOLD_HEIGHT);
		GLine beam = new GLine (36, 31, 36 + BEAM_LENGTH, 31);
		GLine rope = new GLine (36 + BEAM_LENGTH, 31, 36 + BEAM_LENGTH, 31 + ROPE_LENGTH);
		add(scaffold);
		add(beam);
		add(rope);
		temp = "";
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		label.setLabel(word);
		label.setFont("SanSerif-36");
		label.setLocation((350 - label.getWidth())/2, 435);
		remove(label);
		add(label);
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(int guessCount, char letter) {
		updateWrongGuessesLabel(letter);
		
		switch (guessCount){
		case 1:
			head();
			break;
		case 2:
			body();
			break;
		case 3:
			leftArm();
			break;
		case 4:
			rightArm();
			break;
		case 5:
			leftLeg();
			break;
		case 6:
			rightLeg();
			break;
		case 7:
			leftFoot();
			break;
		case 8:
			rightFoot();
			break;
		}
	}
	
	private void head(){
		GOval head = new GOval(36 + BEAM_LENGTH - HEAD_RADIUS, 
				31 + ROPE_LENGTH, 2*HEAD_RADIUS, 2*HEAD_RADIUS);
		add(head);
	}
	
	private void body(){
		GLine body = new GLine(36 + BEAM_LENGTH, 31 + ROPE_LENGTH + (2*HEAD_RADIUS),
				36 + BEAM_LENGTH, 31 + ROPE_LENGTH + (2*HEAD_RADIUS) + BODY_LENGTH);
		add(body);
	}
	
	private void leftArm(){
		GLine arm = new GLine(36 + BEAM_LENGTH, 
				31 + ROPE_LENGTH + (2*HEAD_RADIUS) + ARM_OFFSET_FROM_HEAD,
				36 + BEAM_LENGTH - UPPER_ARM_LENGTH, 
				31 + ROPE_LENGTH + (2*HEAD_RADIUS) + ARM_OFFSET_FROM_HEAD);
		GLine hand = new GLine(36 + BEAM_LENGTH - UPPER_ARM_LENGTH, 
				31 + ROPE_LENGTH + (2*HEAD_RADIUS) + ARM_OFFSET_FROM_HEAD, 
				36 + BEAM_LENGTH - UPPER_ARM_LENGTH, 
				31 + ROPE_LENGTH + (2*HEAD_RADIUS) + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH);
		add(arm);
		add(hand);
	}

	private void rightArm(){
		GLine arm = new GLine(36 + BEAM_LENGTH, 
				31 + ROPE_LENGTH + (2*HEAD_RADIUS) + ARM_OFFSET_FROM_HEAD,
				36 + BEAM_LENGTH + UPPER_ARM_LENGTH, 
				31 + ROPE_LENGTH + (2*HEAD_RADIUS) + ARM_OFFSET_FROM_HEAD);
		GLine hand = new GLine(36 + BEAM_LENGTH + UPPER_ARM_LENGTH, 
				31 + ROPE_LENGTH + (2*HEAD_RADIUS) + ARM_OFFSET_FROM_HEAD, 
				36 + BEAM_LENGTH + UPPER_ARM_LENGTH, 
				31 + ROPE_LENGTH + (2*HEAD_RADIUS) + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH);
		add(arm);
		add(hand);
	}
	
	private void leftLeg(){
		GLine hip = new GLine(36 + BEAM_LENGTH, 
				31 + ROPE_LENGTH + (2*HEAD_RADIUS) + BODY_LENGTH,
				36 + BEAM_LENGTH - HIP_WIDTH, 
				31 + ROPE_LENGTH + (2*HEAD_RADIUS) + BODY_LENGTH);
		GLine leg = new GLine(36 + BEAM_LENGTH - HIP_WIDTH, 
				31 + ROPE_LENGTH + (2*HEAD_RADIUS) + BODY_LENGTH, 
				36 + BEAM_LENGTH - HIP_WIDTH, 
				31 + ROPE_LENGTH + (2*HEAD_RADIUS) + BODY_LENGTH + LEG_LENGTH);
		add(hip);
		add(leg);
	}
	
	private void rightLeg(){
		GLine hip = new GLine(36 + BEAM_LENGTH, 
				31 + ROPE_LENGTH + (2*HEAD_RADIUS) + BODY_LENGTH,
				36 + BEAM_LENGTH + HIP_WIDTH, 
				31 + ROPE_LENGTH + (2*HEAD_RADIUS) + BODY_LENGTH);
		GLine leg = new GLine(36 + BEAM_LENGTH + HIP_WIDTH, 
				31 + ROPE_LENGTH + (2*HEAD_RADIUS) + BODY_LENGTH, 
				36 + BEAM_LENGTH + HIP_WIDTH, 
				31 + ROPE_LENGTH + (2*HEAD_RADIUS) + BODY_LENGTH + LEG_LENGTH);
		add(hip);
		add(leg);
	}
	
	private void leftFoot(){
		GLine foot = new GLine(36 + BEAM_LENGTH - HIP_WIDTH, 
				31 + ROPE_LENGTH + (2*HEAD_RADIUS) + BODY_LENGTH + LEG_LENGTH, 
				36 + BEAM_LENGTH - HIP_WIDTH - FOOT_LENGTH,
				31 + ROPE_LENGTH + (2*HEAD_RADIUS) + BODY_LENGTH + LEG_LENGTH);
		add(foot);
	}
	
	private void rightFoot(){
		GLine foot = new GLine(36 + BEAM_LENGTH + HIP_WIDTH, 
				31 + ROPE_LENGTH + (2*HEAD_RADIUS) + BODY_LENGTH + LEG_LENGTH, 
				36 + BEAM_LENGTH + HIP_WIDTH + FOOT_LENGTH,
				31 + ROPE_LENGTH + (2*HEAD_RADIUS) + BODY_LENGTH + LEG_LENGTH);
		add(foot);
	}
	
	private void updateWrongGuessesLabel(char letter){
		ch = Character.toString(letter);
		if(!temp.contains(ch)){
			temp = temp + " " + ch;
		}
		wrongGuesses.setLabel(temp);
		wrongGuesses.setFont("SanSerif-24");
		wrongGuesses.setLocation((350 - wrongGuesses.getWidth())/2, 500 - 36);
		remove(wrongGuesses);
		add(wrongGuesses);
	}
	
/* Constants for the simple version of the picture (in pixels) */
	private GLabel label = new GLabel("");
	private GLabel wrongGuesses = new GLabel ("");
	private String ch;
	String temp = "";
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;
}
