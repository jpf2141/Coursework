public class Exercise2_7b6 { 
	public static void main(String[] args) {
		final long startTime = System.nanoTime();
		int sum = 0; 
		int n = 10;
		for(int i = 0; i < n; i++)
			for(int j = 0; j < i * i; j++) 
				if(j% i == 0)
					for(int k = 0; k < j; k++)
						sum++; 	
		
		
		final long duration = System.nanoTime() - startTime;
		System.out.println(duration);
		System.out.print(sum);
	
	}
}