public class IndexSearch { 
	public static void main(String[] args) { 
		final long startTime = System.nanoTime(); //timerA
		int[] IntArray = {1, 2, 1, 2, 3, 4, 5};
		
		boolean found = false; 
		int index = 0; 
		while(index < IntArray.length && !found){

			if(IntArray[index] == index)
			{
				found = true; 
				System.out.print("Match found at Index "+index);
			}
			else 
			{
				index++; 
			}	
			}
		if(!found)
			System.out.print("Not Found");
		//timerB
		final long duration = System.nanoTime() - startTime;
		System.out.println(" in "+ duration +" Nanoseconds");
	}
}