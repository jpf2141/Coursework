import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class WordListTester { 
	


public static void main(String[] args){ 
	int lineNumber;
	
		boolean again=true;
		while(again){
			try{
				lineNumber = 0; 
				File textFile = new File(args[0]);	//command line argument 
				Scanner lines = new Scanner(textFile);
				AvlTree index = new AvlTree(); 
				while(lines.hasNext()) {	//parses line by line 
					
					lineNumber++;	//increment the current line counter
					String lineInitial = lines.nextLine();	
					String lineAdjusted;	//line with no punctuation 
					lineAdjusted = lineInitial.toLowerCase().replaceAll
														("\\p{Punct}+", "");
					String[] words = lineAdjusted.split(" ");
					//inserts the I'th word in each line, along with the 
					//current line number. This for loop is not a function 
					//of N, but rather a function of the length of each line
					for(int i = 0; i < words.length; i++) { 
								index.insert(words[i], lineNumber); 
					}
				again = false;
				}
				index.printTree();	//print tree method 
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