import java.util.Scanner;

public class PalindromeDetector {
	public Scanner text;
	
	public PalindromeDetector(Scanner words) {
		this.text = words; 
	} 
	
	public void stackAdder(){ 
		while(text.hasNext())		//goes through text file line by line
		{	
			MyCharStack builder = new MyCharStack();	//instantiate new stack
			String lineInitial = text.nextLine();	//get raw text from file
			String lineAdjusted;	//raw line with no punctuation or spaces
			lineAdjusted = lineInitial.toLowerCase().replaceAll("\\W", "");
			//System.out.println(lineAdjusted);
			
			//parse through adjusted line, pushing each character into 
			//the stack of char's in the MyStringStack class
			for(int i = 0; i < lineAdjusted.length(); i++) { 
				char letter = lineAdjusted.charAt(i);
				builder.push(letter);	//push letter onto stack	
			}
			
			
			boolean palindrome = true;	//assumes palindrome until made false
			//parse through lineAdjusted from front to back (left to right)
			//while at the same time, popping from the stack, so that the 
			//characters being compared are the first and last, 2nd 
			//and 2nd to last, etc
			for(int j = 0; j < lineAdjusted.length(); j++) {
				char fromFront = lineAdjusted.charAt(j);
				char fromStack = builder.pop();
				//System.out.println("Front: " + fromFront + "\tStack: " + fromStack);
				//as soon as the if statement finds a letter as it is parsing
				//from the front that is not equal to the letter that was 
				//popped (from the "back"), it declared palindrome false
				if(fromFront != fromStack) {
					 palindrome = false;	//not a palindrome, check next line
					}	
				//**to run as it was submitted, remove 2nd brace**
				}
				//if palindrome remains true for the entire string, print 
				//out the palindrome, followed by "is a palindrome"
				//then make false, so it can check the next line
				if(palindrome){	
						System.out.println("'"+ lineInitial 
								+ "' is a palindrome."); 
						palindrome = false; 
				}		
			} 	
		}	
	}	
	
	


