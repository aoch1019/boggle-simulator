
import java.util.*;

public class AllWords {

	Board board;
	String[][] letters;
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

		if(!words.contains(currWord) && dict.contains(currWord)) {
//			System.out.println(currWord + " is a word!");
			words.add(currWord);
		}
		else if(!sequenceExistsInDict(currWord)){
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
	
	boolean sequenceExistsInDict(String sequence){
		for(String word : dict) {
			if(word.startsWith(sequence)) {
				return true;
			}
		}
		return false;
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
		AllWords aw = new AllWords();
//		aw.board.display();
		aw.printBoard();
		aw.beginPathsStart();
		aw.printWords();
	}

}
