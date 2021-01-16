
import java.util.*;

public class NextTo {

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	    HashMap<Integer, int[]> connections = new HashMap<Integer, int[]>();
	    
	    Integer a = 1;
	    Integer b = 2;
	    Integer c = 3;
	    Integer d = 4;
	    Integer e = 5;
	    Integer f = 6;
	    Integer g = 7;
	    Integer h = 8;
	    Integer i = 9;
	    
	    int[] arr1 = new int[3];
	    arr1[0] = 2;
	    arr1[1] = 4;
	    arr1[2] = 5;
	    
	    int[] arr2 = new int[5];
	    arr2[0] = 1;
	    arr2[1] = 3;
	    arr2[2] = 4;
	    arr2[3] = 5;
	    arr2[4] = 6;
	    
	    int[] arr3 = new int[3];
	    arr3[0] = 2;
	    arr3[1] = 5;
	    arr3[2] = 6;
	    
	    int[] arr4 = new int[5];
	    arr4[0] = 1;
	    arr4[1] = 2;
	    arr4[2] = 5;
	    arr4[3] = 8;
	    arr4[4] = 7;
	    
	    int[] arr5 = new int[8];
	    arr5[0] = 1;
	    arr5[1] = 2;
	    arr5[2] = 3;
	    arr5[2] = 4;
	    arr5[3] = 6;
	    arr5[4] = 7;
	    arr5[5] = 8;
	    arr5[6] = 9;
	    
	    int[] arr6 = new int[5];
	    arr6[0] = 3;
	    arr6[1] = 2;
	    arr6[2] = 5;
	    arr6[3] = 8;
	    arr6[4] = 9;
	    
	    int[] arr7 = new int[3];
	    arr7[0] = 4;
	    arr7[1] = 5;
	    arr7[2] = 8;
	    
	    int[] arr8 = new int[5];
	    arr8[0] = 7;
	    arr8[1] = 4;
	    arr8[2] = 5;
	    arr8[3] = 6;
	    arr8[4] = 9;
	    
	    int[] arr9 = new int[3];
	    arr4[0] = 6;
	    arr4[1] = 5;
	    arr4[2] = 8;
	    
	    connections.put(a, arr1);
	    connections.put(b, arr2);
	    connections.put(c, arr3);
	    connections.put(d, arr4);
	    connections.put(e, arr5);
	    connections.put(f, arr6);
	    connections.put(g, arr7);
	    connections.put(h, arr8);
	    connections.put(i, arr9);

	    System.out.println(connections.get(b)[1]);
	}

}
