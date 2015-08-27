public class MyCharStack {
	
	int topOfStack;
	final int LENGTH = 200; //array of arbitary length
	char[] charStack;	//stack of characters 
	
	public MyCharStack() {
		this.topOfStack = 0;	//stack starts empty
		this.charStack = new char[LENGTH];	
	}
	
	public void push (char letter) {	//adds an item to the top of the stack                                                                                                                       
		topOfStack++;
		charStack[topOfStack] =  letter; 
	}
	
	public char pop() {		//returns and removes topOfStack
		char returnLetter = charStack[topOfStack];
		topOfStack--;
		return returnLetter;
	}
	
	public char peek() { 	//peeks at topOfStack 
		return charStack[topOfStack];
	}
	
	public boolean isEmpty() { 	//checks if stack is empty
		if(topOfStack == -1) 
			return true;
		else return false; 				
	}
	
	public void printStack() { 
		for(int i = 0; i < charStack.length; i++) {
		System.out.println(charStack[i]);
		}
	}	
}
	
	