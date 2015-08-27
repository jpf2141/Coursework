public class Exercise2_7b2 { 
	public static void main(String[] args) {
		final long startTime = System.nanoTime();
		int n = 10; 
		int sum = 0; 
		
		for(int i = 0; i < n; i++)
			for(int j = 0; j < n; j++)
				sum++;
		
		
		final long duration = System.nanoTime() - startTime;
		System.out.println(duration);
		System.out.print(sum);
	}
}