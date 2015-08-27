import java.util.Scanner;

public class ExpressionTree { 
	
	public static void main(String[] args){
		try { 
		System.out.println("Please enter a PostFix Expression: ");
    	Scanner input = new Scanner(System.in); 
    	ExpressionTreeFiller(input);
		}
		catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Did not enter valid combination of"
					+ "operands and operators!");
		}
	}
	
	
    	public static void ExpressionTreeFiller(Scanner input) { 
    		StringStack builder1 = new StringStack();	//new stack object 
    		String postFix = input.nextLine(); //makes input into a string
    		String[] forParsing = postFix.split(" "); 	//separate the string
    													//at whitespace 
    		for(int i = 0; i < forParsing.length; i++){	//iterate through 
    			String number = forParsing[i];
    			if(Character.isDigit(number.charAt(0))){ //check if digit
    				builder1.push(number);	//push the number onto the stack 
    			}
    			
    			else {	//if not a digit (input dictates it will be an operand)
    				char operation = number.charAt(0);	
    				String operand1 = builder1.pop();	//pop the first number
    				String operand2 = builder1.pop();	//pop the second number
    				//push the new expression onto the stack (with parentheses)
    				builder1.push("(" + operand2 + operation + operand1 + ")");	
    			}	
    		}
    		System.out.println(builder1.peek());
    	}
	}