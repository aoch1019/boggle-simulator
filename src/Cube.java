
import java.util.*;

public class Cube {

	String[] options;
	String selection;
	
	Cube(String options){
		String[] createOptions = new String[6];
		for(int i = 0; i < 6; i++) {
			String curr = Character.toString(options.charAt(i));
			if(curr.equals("q")) {
				curr = "qu";
			}
			createOptions[i] = curr;
		}
		
		this.options = createOptions;
		this.selection = selectOption();
	}
	
	public String selectOption() {
		Random rand = new Random();
		return this.options[rand.nextInt(6)];
	}
	
	public void printCube() {
		System.out.print("This cube contains the letters: ");
		for(String option : options) {
			System.out.print(option);
		}
		System.out.println("");
	}
	
	public String getSelection() {
		return selection;
	}

	public void setSelection(String selection) {
		this.selection = selection;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
