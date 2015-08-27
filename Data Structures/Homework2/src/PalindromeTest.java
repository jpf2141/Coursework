import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class PalindromeTest { 
	
	public static void main(String[] args){ 

		boolean again=true;
		while(again){
			try{
				File textFile = new File(args[0]);	//command line argument 
				Scanner words = new Scanner(textFile);
				PalindromeDetector detector = new PalindromeDetector(words);
				detector.stackAdder();
				again = false;
				}
		  catch(IOException e){
			    System.out.println("Please try again with correct input file name");
			    Scanner scan = new Scanner(System.in);
			    args[0]=scan.next();
			  	}
		  catch(ArrayIndexOutOfBoundsException e) {
			  	System.out.println("Please enter the file name in "
			  			+ "the command line!");
			    again=false;
		  		}
			}
		}
	}
	