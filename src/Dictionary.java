import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

// https://rapidapi.com/dpventures/api/wordsapi?endpoint=54b6b054e4b0b51cad11a1bb

public class Dictionary {

	ArrayList<String> dict = new ArrayList<String>();
	
	Dictionary(){
		File f = new File("engDictionary.txt");
		try {
			Scanner fileScanner = new Scanner(f);
			while (fileScanner.hasNext()) {
				String currWord = fileScanner.next();
				if(currWord.length() >= 4) {
					dict.add(currWord);	
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> getDict() {
		return dict;
	}

	public void setDict(ArrayList<String> dict) {
		this.dict = dict;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Dictionary d = new Dictionary();
		System.out.println(d.getDict());
	}

}
