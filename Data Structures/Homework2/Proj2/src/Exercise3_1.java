import java.util.ArrayList;
import java.util.Iterator;

//Because we iterate through the ArrayList P of length N once, 
//the time complexity is O(N)

public abstract class Exercise3_1 implements Iterator<Integer> { 
	static ArrayList<Integer> L = new ArrayList<Integer>(); 
	static ArrayList<Integer> P = new ArrayList<Integer>(); 
	
	
	public static void main(String[] args){
		try{
			L.add(0); L.add(-1); L.add(3); L.add(45); L.add(5); L.add(6); 
			P.add(1); P.add(2); P.add(3); P.add(5);
			iterator(L,P);
		}
		catch(IndexOutOfBoundsException e) {
		  	System.out.println("Some array indices to be printed were "
		  			+ "not in the array 'L' ");
		}	
	}

	private static void iterator(ArrayList<Integer> l1, ArrayList<Integer> p1){
		Iterator<Integer> itr = p1.iterator();
		while(itr.hasNext()) {
			Integer element = itr.next();
			System.out.println(l1.get(element));
		}	
	}
}