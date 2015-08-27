public class Exercise2_27 { 
	
	public static void main(String[] args) {
		
		int[][] matrix = 	{	//5x5 matrix
				{1, 2, 3, 4, 5},
				{2, 3, 4, 5, 6},
				{3, 4, 5, 6, 7},
				{4, 5, 6, 7, 8},
				{5, 6, 7, 8, 9}
			};
							
		int searchFor =9;
		int steps = 0; 			//start at bottom left corner of matrix 
		int c = 0;
		int r = matrix[c].length - 1;
			
		if(matrix[c][r]==searchFor) {	//if starting at target
			System.out.println("Column: "+(c + 1)+"\nRow: "+(r+1));
			System.out.println("Steps: "+steps);	
			return;
		}
		
		try {
			//otherwise, while current position > target, increase row
			while(matrix[c][r] > searchFor ) {
				r--;
				steps++;
				if(matrix[c][r]==searchFor){
					System.out.println("Column: "+(c + 1)+"\nRow: "+(r+1));	
					System.out.println("Steps: "+steps);	
					return;
				}	
			}
			//while current position < target, increase column 
			while(matrix[c][r] < searchFor ) {
				c++;
				steps++;
				if(matrix[c][r]==searchFor) {
					System.out.println("Column: "+(c + 1)+"\nRow: "+(r+1));
					System.out.println("Steps: "+steps);	
					return;
				}
			}
		} catch (Exception e) {
			System.out.println("Not Found");
	}	
}
}



	

	
