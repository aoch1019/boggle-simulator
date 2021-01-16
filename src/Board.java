import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Board {

	Cube[][] cubes;
	String[] cubeOptions = {"aaafrs",
			"aaeeee",
			"aafirs",
			"adennn",
			"aeeeem",
			"aeegmu",
			"aegmnn",
			"afirsy",
			"bjkqxz",
			"ccenst",
			"ceiilt",
			"ceilpt",
			"ceipst",
			"ddhnot",
			"dhhlor",
			"dhlnor",
			"dhlnor",
			"eiiitt",
			"emottt",
			"ensssu",
			"fiprsy",
			"gorrvw",
			"iprrry",
			"nootuw",
			"ooottu"};
	
	Board(){
		Cube[][] createCubes = new Cube[5][5];
		int[] order = getRandomOrder();
		int k = 0;
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				createCubes[i][j] = new Cube(this.cubeOptions[order[k]]);
				k++;
			}
		}
		this.cubes = createCubes;
	}
	
	public void display() {
		for(Cube[] row : cubes) {
			for(Cube c : row) {
				System.out.print(c.getSelection() + " ");
			}
			System.out.println("");
		}
	}
	
	public Cube[][] getCubes() {
		return cubes;
	}

	public void setCubes(Cube[][] cubes) {
		this.cubes = cubes;
	}

	int[] getRandomOrder() {
		Integer[] numbers = new Integer[25];
		for (int i = 0; i < 25; i++) {
			numbers[i] = i;
		}
		List<Integer> numList = Arrays.asList(numbers);
		Collections.shuffle(numList);
		int[] array = numList.stream().mapToInt(i->i).toArray();
		return array;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Board b = new Board();
		b.display();
//		System.out.println(b.getCubes()[3][1].getSelection());
	}

}
