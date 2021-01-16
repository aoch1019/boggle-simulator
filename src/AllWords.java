
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
	
	void beginPaths() {
		for(int i = 0; i < letters.length; i++) {
			for(int j = 0; j < letters[i].length; j++) {
				HashSet<HashMap<Integer, Integer>> seen = new HashSet<HashMap<Integer, Integer>>();
				HashMap<Integer, Integer> toAdd = new HashMap<Integer, Integer>();
				toAdd.put(i, j);
				seen.add(toAdd);
				String currStr = letters[i][j];
				navigatePaths(i, j, seen, currStr);
			}
		}
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
		
//		if(bottomRow < letters.length && leftCol >= 0) {
//			int newLocation = (bottomRow * 5) + leftCol;
//			beginPaths2(currWord + letters[bottomRow][leftCol], newLocation, seen);
//		}
		
		
		// Note -- continue making coordinates for all surrounding spots. Then check if a coordinate is valid. If it is, call beginPaths2 recursively.
		
//		if(location % 5 == 0) {
//			beginPaths2(currWord + letters[location / 5)[location / ]
//		}
	}
	
	void navigatePaths(int i, int j, HashSet<HashMap<Integer, Integer>> seen, String currStr) {
		int[] directionsI = getPossibleDirections(i, letters.length);
		int[] directionsJ = getPossibleDirections(j, letters[i].length);
		System.out.println("For the coordinates (" + i + ", " + j + ")...");
		if(directionsI.length == 3) {
           System.out.println("i can go " + directionsI[0] + " , " + directionsI[1] + " , " + directionsI[2]);
		}
		else {
	           System.out.println("i can go " + directionsI[0] + " , " + directionsI[1]);
		}
		if(directionsJ.length == 3) {
	           System.out.println("j can go " + directionsJ[0] + " , " + directionsJ[1] + " , " + directionsJ[2]);
			}
			else {
		           System.out.println("i can go " + directionsJ[0] + " , " + directionsJ[1]);
			}
		
		for (int dirI : directionsI) {
			for (int dirJ : directionsJ) {
				HashMap<Integer, Integer> toAdd = new HashMap<Integer, Integer>();
				int newI = i + dirI;
				int newJ = j + dirJ;
				if(newI == i && newJ == j) {
					continue;
				}
				toAdd.put(newI, newJ);
				if (seen.contains(toAdd) || currStr.length() >= 7) {
					return;
				}
				else {
					currStr = currStr + letters[newI][newJ];
					
					this.words.add(currStr);
					//Todo: Check if currStr is a word! If it is, add it to this.words.
					seen.add(toAdd);
					navigatePaths(newI, newJ, seen, currStr);
				}
			}
		}
	}
	
	int[] getPossibleDirections(int a, int length) {
		
		int[] possibleDirections;
		if(a == 0) {
			possibleDirections = new int[2];
			possibleDirections[0] = 0;
			possibleDirections[1] = 1;
		}
		else if(a == length - 1) {
			possibleDirections = new int[2];
			possibleDirections[0] = -1;
			possibleDirections[1] = 0;
		}
		else {
			possibleDirections = new int[3];
			possibleDirections[0] = -1;
			possibleDirections[1] = 0;
			possibleDirections[2] = 1;
		}
		
		return possibleDirections;
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
