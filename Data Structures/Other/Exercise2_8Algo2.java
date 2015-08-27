public class Exercise2_8Algo2 {
	

	public static void main(String[] args) { 
	
		int[] inputSize = {25000, 50000, 100000, 200000};
		for(int k = 0; k < inputSize.length; k++){
			System.out.println();
			int sum = 0; //reset duration sum to 0 for each input size 
			int iterations = 10; 
			for(int l = 0; l < iterations; l++) {	
				sum += Algorithm2(inputSize[k]);
			}
			int average = sum/iterations;
			System.out.println("Input Size: " + inputSize[k] +
								"\nTotal Time: " + sum +
								"\tAverage Time: " +average);
		}
	}

	private static long Algorithm2(int size) {
		int[] numbers = new int[size];	//array of length: size
		boolean[] used = new boolean[size + 1];
		
		final long startTime = System.nanoTime(); //timerA
			for(int i = 0; i<size; i++){
				
				int n = randInt(0, size);
				
				if(used[n] == true)
					i--;
				if(used[n] == false)
					numbers[i] = n;
					used[n] = true;
			}
			final long duration = System.nanoTime() - startTime;
			System.out.println(duration);
			return duration;
		}
	
	private static int randInt(int min, int max) { 
		int minRand = min + 1;
		int n = (int) (Math.random()*max + minRand);
		return n; 
	}
}	
	
