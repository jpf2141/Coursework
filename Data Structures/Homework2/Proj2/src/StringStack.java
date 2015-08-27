public class StringStack {
	
	int topOfStack;
	final int LENGTH = 100; //array of arbitary length
	String[] stringStack;	//stack of integers 
	
	public StringStack() {
		this.topOfStack = -1;	//stack starts empty
		this.stringStack = new String[LENGTH];	
	}
	
	public void push (String input) {	//adds an item to the top of the stack
		topOfStack++;
		stringStack[topOfStack] =  input;
	}
		
	public String pop() {		//returns and removes topOfStack
		String returnNum = stringStack[topOfStack];
		topOfStack--;
		return returnNum;
	}
	
	public String peek() { 	//peeks at topOfStack 
		return stringStack[topOfStack];
	}
	
	public boolean isEmpty() { 	//checks if stack is empty
		if(topOfStack == -1) 
			return true;
		else return false; 				
	}
	
	public void printStack() { 
		for(int i = 0; i < stringStack.length; i++) {
		System.out.println(stringStack[i]);
		}
	}
}