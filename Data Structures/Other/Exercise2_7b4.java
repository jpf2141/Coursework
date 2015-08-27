public class Exercise2_7b4 { 
	public static void main(String[] args) {
		final long startTime = System.nanoTime();
		int n = 3000; 
		double sum = 0; 
		
		for(int i = 0; i < n; i++){
			
			for(int j = 0; j < i; j++)	
				sum++;
			
		}
		
		
		final long duration = System.nanoTime() - startTime;
		System.out.println(sum);
		System.out.println(duration);
		
	}
}