
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
				beginPaths2("", location, new ArrayList<Integer>());
			}
		}
	}
	
	void beginPaths2(String currWord, int location, ArrayList<Integer> seen) {
		
		String toAdd = "";
		
		if(currWord.length() > 0) {
			toAdd = currWord.substring(0, currWord.length() - 1);
		}

		if(dict.contains(toAdd) && !words.contains(toAdd)) {
			words.add(toAdd);
		}
		if(currWord.length() > 8) {
			return;
		}
		if(seen.contains(location)) {
			return;
		}
		
		seen.add(location);
		
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
				boolean valid = r >= 0 && r < letters.length && c >= 0 && c < letters.length;
				if(valid) {
					int newLocation = (r * 5) + c;
					if(newLocation == location) {
						continue;
					}
					ArrayList<Integer> seenCopy = (ArrayList)seen.clone();
					beginPaths2(currWord + letters[r][c], newLocation, seenCopy);
				}
			}
		}
	}
	
	void printWords() {
		for(String str : this.words) {
			System.out.println(str);
		}
	}
	
	void printFakeBoard() {
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
		aw.printFakeBoard();
		aw.beginPathsStart();
		aw.printWords();
	}

}
