public class Exercise3_24 { 
	
	public static void main(String[] args){
		DoubleInputStack builder2 = new DoubleInputStack();
	
		
		String string1 = "a b c d e f g h i";
		String string2 = "A B C D E F G H I J";
		
		
		String[] forParsing1 = string1.split(" ");
		String[] forParsing2 = string2.split(" ");
		
		try{
		for(int i = 0; i < forParsing1.length; i++) {
			String toTop = forParsing1[i];
			builder2.push(toTop);
		}
		
		for(int i = 0; i < forParsing2.length; i++) {
			String toBottom = forParsing2[i];
			builder2.pushBottom(toBottom);
		}
		builder2.printStack();
		}
		catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Stack is ");
		}
	}
}