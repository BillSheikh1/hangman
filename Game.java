package hangman;

import java.util.LinkedList;

public class Game {
	
	private String word;
	private char[] finalWord;
	//private char[] validGuesses;
	private LinkedList<Character> usedGuesses;
	private LinkedList<String> guessedWords;
	private int lives;
	private boolean finished;
	private Input input;
	
	public Game(String word) {
		this.word = word;
		finalWord = word.toCharArray();
		//validGuesses = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		usedGuesses = new LinkedList<>();
		guessedWords = new LinkedList<>();
		lives = 10;
		input = new Input();
		prepareWord();
	}
	
	public void run() {
		finished = false;
		while(!finished) {
			printWord();
			printLives();
			String word = input.getInput();
			if(input.valid(word)) {
				if(word.length() > 1) {
					guessWord(word);
				}
				else {
					char guess = word.charAt(0);
					guessLetter(guess);
				}
			}
			else {
				System.out.println("Please enter a valid input.");
			}
		}
	}
	
	private void prepareWord() {
		for(int i=0; i<finalWord.length; i++) {
			finalWord[i] = '_';
		}
	}
	
	private void printWord() {
		String word = "";
		for(int i=0; i<finalWord.length; i++) {
			if(i != finalWord.length-1) {
				word += finalWord[i] + " ";
			}
			else {
				word += finalWord[i];
			}
		}
		
		System.out.println(word);
	}
	
	private void win() {
		finalWord = word.toCharArray();
		System.out.println("Congratulations you win!");
		printWord();
		end();
	}
	
	private void lose() {
		finalWord = word.toCharArray();
		System.out.println("You lose.");
		printWord();
		end();
	}
	
	private void end() {
		finished = true;
	}
	
	private void loseLife() {
		if(lives > 0) {
			lives -= 1;
		}
		else {
			lose();
		}
	}
	
	private void printLives() {
		System.out.println("Lives: " + lives);
	}
	
	private boolean checkWordStatus() {
		char[] theWord = word.toCharArray();
		int counter = 0;
		for(int i=0; i<finalWord.length; i++) {
			if(theWord[i] == finalWord[i]) {
				counter++;
			}
		}
		
		if(counter == theWord.length) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void guessLetter(char letter) {

		if(!usedGuesses.contains(letter)) {
			boolean correct = false;
			char[] originalWord = word.toCharArray();
			for(int i=0; i<finalWord.length; i++) {
				if(letter == originalWord[i]) {
					finalWord[i] = letter;
					correct = true;
					if(checkWordStatus()) {
						win();
					}
				}
			}
			usedGuesses.push(letter);
			if(!correct) {
				loseLife();
			}
		}
		else {
			System.out.println("Letter already used.");
		}

		
	}
	
	public void guessWord(String guess) {
		if(!guessedWords.contains(guess)) {
			if(guess.equals(word)) {
				win();
			}
			else {
				loseLife();
				guessedWords.push(guess);
			}
		}
		else {
			System.out.println("Already guessed that word.");
		}
		
	}
}
