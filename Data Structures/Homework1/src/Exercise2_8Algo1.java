public class Exercise2_8Algo1 { 
	
	public static void main(String[] args) { 
	
		int[] inputSize = {250, 500, 1000, 2000};
		for(int k = 0; k < inputSize.length; k++){
			System.out.println();
			int sum = 0; //reset duration sum to 0 for each input size 
			int iterations = 10; 
			for(int l = 0; l < iterations; l++) {	
				sum += Algorithm1(inputSize[k]);
			}
			int average = sum/iterations;
			System.out.println("Input Size: " + inputSize[k] +
								"\nTotal Time: " + sum +
								"\tAverage Time: " +average);
		}	
	}

	private static long Algorithm1(int size) {
		int[] numbers = new int[size];	//array of length: size
		final long startTime = System.nanoTime(); //timerA
			for(int i = 0; i<size; i++){
				int n = randInt(0, size);
		
				boolean inNumbers = false; 
				for(int j = 0; j < size; j++)
					if(numbers[j] == n)
					inNumbers = true; 
					if (inNumbers == true)
						i--;
					if (inNumbers == false)
						numbers[i] = n; 	
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