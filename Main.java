package hangman;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BufferedReader br = null;
		ArrayList<String> words = new ArrayList<>();
		String location = args[0];
		
		try {
			br = new BufferedReader(new FileReader(location));
			String line;
			while((line = br.readLine()) != null) {
				words.add(line);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		Integer size = words.size();
		Random rand = new Random();
		int number = rand.nextInt(size) + 1;
		
		
		Game game = new Game(words.get(number));
		game.run();
		
	}

}
 