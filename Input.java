package hangman;

import java.util.Scanner;

public class Input {
	
	private Scanner reader;
	
	public Input() {
		reader = new Scanner(System.in);
	}
	
	public String getInput() {
		System.out.print("> ");
		String input = reader.nextLine().trim().toLowerCase();
		return input;
	}
	
	private boolean moreThanOneWord(String word) {
		if(!word.equals("")) {
			String[] words = word.split(" ");
			if(words.length > 1) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return true;
		}
		
		
	}
	
	private boolean isAnInt(String word) {
		try {
			Integer.parseInt(word);
		}
		catch(NumberFormatException e) {
			return false;
		}
		return true;
	}
	
	public boolean valid(String word) {
		if(moreThanOneWord(word) || isAnInt(word)) {
			return false;
		}
		else {
			return true;
		}
	}

}
