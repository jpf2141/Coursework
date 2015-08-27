import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JFrame;


public class Exercise2_8 { 
	
	public static void main(String[] args) { 
		
		//graphing GUI 
		//x value = input size; y value = time 
		JFrame frame = new JFrame("Basic line grapher");	//title 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int xwindow = 800;
        int ywindow = 700; 
        int xaxis = 6400000;
        int yaxis = 500; 
        BasicLineGrapher lineGraph = new BasicLineGrapher(new Dimension(xwindow,
                ywindow), xaxis, yaxis);
        
    	//inputs & graphing for algorithm 1
        LineGraph lg1 = new LineGraph(Color.GREEN);
        int[] inputSize = {250, 500, 1000, 2000};
		for(int k = 0; k < inputSize.length; k++){
			System.out.println();
			int sum = 0; //reset duration sum to 0 for each input size 
			int iterations = 10; 	//10 trials for good average 
			for(int l = 0; l < iterations; l++) {	
				sum += Algorithm1(inputSize[k]);
			}
			//divide by 1000000 to get milliseconds 
			int MilliAverage1 = (sum/iterations)/1000000;	
			lg1.addPoint(new Point(inputSize[k], MilliAverage1));	
			//System.out.println("\tAverage Time: "
									//+ MilliAverage1+ " "+inputSize[k]);
		}
			lineGraph.addLineGraph(lg1);
			
		//inputs & graphing for algorithm 2 
		LineGraph lg2 = new LineGraph(Color.RED);
		int[] inputSize2 = {25000, 50000, 100000, 200000};
		for(int k = 0; k < inputSize2.length; k++){
			System.out.println();
			int sum = 0; //reset duration sum to 0 for each input size 
			int iterations = 10;	//10 trials for good average 
			for(int l = 0; l < iterations; l++) {	
				sum += Algorithm2(inputSize2[k]);
			}
			//divide by 1000000 to get milliseconds 
			int MilliAverage2 = (sum/iterations)/1000000;
			lg2.addPoint(new Point(inputSize2[k], MilliAverage2));
			//System.out.println("\tAverage Time: "
								//+MilliAverage2+ " " +inputSize2[k]);
			}
				lineGraph.addLineGraph(lg2);
		
		
		//inputs & graphing for algorithm 2 
		LineGraph lg3 = new LineGraph(Color.BLUE);
		int[] inputSize3 = {100000, 200000, 400000, 1600000, 6400000}; 
		for(int k = 0; k < inputSize3.length; k++){
			System.out.println();
			long sum = 0; //reset duration sum to 0 for each input size 
			int iterations = 10; 	//10 trials for good average 
			for(int l = 0; l < iterations; l++) {	
				sum += Algorithm3(inputSize3[k]);
			}
			//the sum of times & average time must be computed as 
			//longs; ints are not of sufficient size for such large
			//inputs; input size 6400000 returns unexpectedly long 
			//results due to the increased time casting from such a 
			//large long to an int takes when inputting graphing values
			//
			//divide by 1000000 to get milliseconds
			long MilliAverage3 = ((sum/iterations)/1000000);
			lg3.addPoint(new Point(inputSize3[k], (int) MilliAverage3));
			//System.out.println("Average Time: "
								//+MilliAverage3+ " "+inputSize3[k]);
			}
				lineGraph.addLineGraph(lg3);
	
				
		frame.getContentPane().add(lineGraph, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
	}
	
	private static long Algorithm1(int size) {	//pass input size as parameter
		int[] numbers = new int[size];	//array of length: size
		final long startTime = System.nanoTime(); //start timer (nanoseconds)
		//fill array with numbers between 0 and size 
			for(int i = 0; i<size; i++){
				int n = randInt(0, size);
		
				//parses through entire array to check if the randomly 
				//generated number is present
				//if it is, it decrements and tries again
				//if not, it ads it to the array
				boolean inNumbers = false; 
				for(int j = 0; j < size; j++)
					if(numbers[j] == n)
					inNumbers = true; 
					if (inNumbers == true)
						i--;	//decrement and try again
					if (inNumbers == false)
						numbers[i] = n; //set index = to n
			}
			final long duration = System.nanoTime() - startTime;	//stop time
			return duration;
		
		}
	
	private static long Algorithm2(int size) {
		int[] numbers = new int[size];	//array of length: size
		boolean[] used = new boolean[size + 1];
		
		final long startTime = System.nanoTime(); //start timer
		//parses through used array to see if the number has already 
		//been used
		//if it has been, decrement and regenerate to try again 
			for(int i = 0; i<size; i++){	
				int n = randInt(0, size);
				if(used[n] == true)
					i--;
				if(used[n] == false)
					numbers[i] = n;
					used[n] = true;
			}
			final long duration = System.nanoTime() - startTime;
			return duration;
		}
	
	//fills array 
	private static long Algorithm3(int size) {
		int[] numbers = new int[size];	//array of length: size
		final long startTime = System.nanoTime(); //timerA
		//fill array with value i+1
		for(int i = 0; i < size; i++)
			numbers[i] = i + 1;
			
		for(int i = 1; i < size; i++){
			//swap array values
			int temp = numbers[i];
			int n = randInt(0, i);
			numbers[i] = numbers[n];
			numbers[n] = temp; 
		}
		final long duration = System.nanoTime() - startTime;
		return duration;	
	}
	//random number generator 
	private static int randInt(int min, int max) { 
		int minRand = min + 1;
		int n = (int) (Math.random()*max + minRand);
		return n; 
	}
}