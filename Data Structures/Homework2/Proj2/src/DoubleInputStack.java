public class DoubleInputStack {
	
	int topOfStack;
	int bottomOfStack; 
	final int LENGTH = 20; //array of arbitary length
	String[] stringStack;	//stack of integers 
	
	public DoubleInputStack() {
		this.topOfStack = -1;	//stack starts empty
		this.bottomOfStack = LENGTH;
		this.stringStack = new String[LENGTH];	
	}
	
	public void push (String input) {	//adds an item to the top of the stack
		topOfStack++;
		if(stringStack[topOfStack] ==  null) {
			stringStack[topOfStack] =  input;
		}
		else {
			System.out.println("Stack is full. "
					+ "There was a collision at index: "+topOfStack);
			return;
		}
	}
		
	public void pushBottom (String inputBottom) {
		bottomOfStack--; 
		if(stringStack[bottomOfStack] == null) {
			stringStack[bottomOfStack] = inputBottom;
		}
		else {
			System.out.println("Stack is full. "
					+ "There was a collision at index: "+bottomOfStack);
			return;
		} 
	}
	
	public String pop() {		//returns and removes topOfStack
		String returnNum = stringStack[topOfStack];
		stringStack[topOfStack] = null; //makes null upon removal
		topOfStack--;
		return returnNum;
	}
	
	public String peek() { 	//peeks at topOfStack 
		return stringStack[topOfStack];
	}
	
	public boolean isEmpty() { 	//checks if stack is empty
		if(topOfStack == -1 && bottomOfStack == LENGTH) 
			return true;
		else return false; 				
	}
	
	public void printStack() { 
		for(int i = 0; i < stringStack.length; i++) {
		System.out.println(stringStack[i]);
		}
	}
}