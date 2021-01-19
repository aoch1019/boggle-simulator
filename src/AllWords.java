
import java.util.*;

public class AllWords {

	Board board;
	String[][] letters;
	public String[][] getLetters() {
		return letters;
	}

	public void setLetters(String[][] letters) {
		this.letters = letters;
	}

	public ArrayList<String> getWords() {
		return words;
	}

	public void setWords(ArrayList<String> words) {
		this.words = words;
	}

	ArrayList<String> words;
	ArrayList<String> dict;
	
	AllWords(){
		this.board = new Board();
//		this.letters = cubesToStrings();
//		//creating a temporary fake board...
//		this.letters = generateFakeBoard();
		this.letters = cubesToStrings();
		this.words = new ArrayList<String>();
		Dictionary d = new Dictionary();
		this.dict = d.getDict();
	}
	
	String[][] generateFakeBoard(){
		String[][] str = {{"0","1","2","3","4"}, {"5","6","7","8","9"}, {"a","b","c","d","e"}, {"f","g","h","i","j"}, {"k","l","m","n","o"}};
		return str;
	}
	
	String[][] cubesToStrings(){
		String[][] letters = new String[5][5];
		
		for(int i = 0; i < board.getCubes().length; i++) {
			for(int j = 0; j < board.getCubes()[i].length; j++) {
				letters[i][j] = board.getCubes()[i][j].getSelection();
			}
		}
		
		return letters;
	}
	
	void beginPathsStart() {
		for(int i = 0; i < letters.length; i++) {
			for(int j = 0; j < letters[i].length; j++) {
				int location = (i * 5) + j;
				beginPathsRecursion("", location, new ArrayList<Integer>());
			}
		}
	}
	
	void beginPathsRecursion(String currWord, int location, ArrayList<Integer> seen) {
			
		boolean sequenceExistsInDict = false;
		
			// Go through the dictionary. For each word check if it's equal to the current word (and not already found via another path).
		    // If it's not equal, check if the current dictionary word starts with currWord. If this second condition is never met, then we want to stop the branch.
		    // If we have passed the word we are looking for alphabetically, we can exit the loop.
		for(String word : dict) {
			if(word.equals(currWord)) {
				if(!words.contains(currWord)) {
					words.add(currWord);	
				}
			}
			// If this condition is met, we know we have alphabetically passed currWord if it exists in the dictionary.
			else if(word.startsWith(currWord)) {
				sequenceExistsInDict = true;
				break;
			}
			// If we have not shown the sequence exists and we have passed currWord's starting letter,
			// then we know the word does not exist so we can stop this branch.
			else if(word.charAt(0) > currWord.charAt(0)) {
				return;
			}
		}
		
		// This is to cover the edge case of the word starting with z AND not having a matching sequence in the dictionary.
		if(!sequenceExistsInDict) {
			return;
		}
		
		int currRow = (int)Math.floor(location / 5);
		int currCol = (location % 5);
		
		int bottomRow = currRow + 1;
		int topRow = currRow - 1;
		
		int leftCol = currCol - 1;
		int rightCol = currCol + 1;
		
		int[] rows = {bottomRow, currRow, topRow};
		int[] cols = {leftCol, currCol, rightCol};
		
		for(int r : rows) {
			for(int c : cols) {
				if(r >= 0 && r < letters.length && c >= 0 && c < letters.length) {
					int newLocation = (r * 5) + c;
					if(newLocation == location || seen.contains(newLocation)) {
						continue;
					}
					ArrayList<Integer> seenCopy = (ArrayList)seen.clone();
					seenCopy.add(newLocation);
					beginPathsRecursion(currWord + letters[r][c], newLocation, seenCopy);
				}
			}
		}
	}
	
	void printWords() {
		System.out.println("\nHere are all possible words...");
		String longest = "";
		for(String str : this.words) {
			System.out.println(str);
			if(str.length() > longest.length()) {
				longest = str;
			}
		}
		
		System.out.println("\nThere are a total of " + words.size() + " valid words.");
		System.out.println("Fun fact: The longest possible word is " + longest);
	}
	
	void printBoard() {
		System.out.println("\nHere is a randomly generated Boggle board...\n");
		for(String[] row : letters) {
			for(String str : row) {
				System.out.print(str + " ");
			}
			System.out.println("");
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		HashMap<String, Integer> wordCount = new HashMap<String, Integer>();
		
		for(int i = 0; i < 1; i++) {
			AllWords aw = new AllWords();
//			String[][] hardcodedBoard = {{"c", "n", "r", "i", "t"}, {"l", "s", "x", "a", "e"}, {"t", "m", "l", "r", "v"}, {"f", "l", "o", "t", "a"}, {"o", "c", "a", "p", "y"}};
//			String[][] hardcodedBoard2 = {{"r", "u", "o", "f", "p"}, {"t", "s", "o", "u", "g"}, {"x", "p", "c", "e", "n"}, {"e", "a", "r", "r", "o"}, {"h", "h", "e", "e", "t"}};			
//			aw.setLetters(hardcodedBoard2);
//			aw.board.display();
			aw.printBoard();
			aw.beginPathsStart();
			aw.printWords();	
			for(String word : aw.getWords()) {
				if(wordCount.containsKey(word)) {
					wordCount.put(word, wordCount.get(word) + 1);
				}
				else {
					wordCount.put(word, 1);
				}
			}
		}
		
		for(String key : wordCount.keySet()) {
			if(key.length() >= 6 && wordCount.get(key) > 1) {
				System.out.println(key + " appears " + wordCount.get(key) + " times");	
			}
		}
	}

}
