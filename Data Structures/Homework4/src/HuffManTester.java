import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author joshfram
 *
 */
public class HuffManTester { 

	public static void main(String[] args) throws IOException{ 

		boolean again=true;
		while(again){
			try{
				//file encoding
				File textFile = new File(args[0]);	//command line argument
				Scanner words = new Scanner(textFile);
				Encoder fileEncoder = new Encoder();
				fileEncoder.encodeScanner(words);		//encodes file 
				words.close();

				userPrompt(fileEncoder);

				

				again = false;
			}
			catch(IOException e ){
				System.out.println("Please enter a correct input value:");
				Scanner userChoice = new Scanner(System.in);
				int pick = userChoice.nextInt();
				//Encoder fileEncoderChoice = new Encoder();
				main(args);
				again = false;
			}
			catch(ArrayIndexOutOfBoundsException e) {
				System.out.println("Please enter the file name in "
						+ "the command line!");
				again=false;
			}
		}
	}

	
	private static void userPrompt(Encoder fileEncoder) throws IOException {
		//user pro1mpting 
		Scanner userChoice = new Scanner(System.in);
		System.out.println("\n\nPress 1 to Get Character Sequence From "
				+ "Huffman Code");
		System.out.println("Press 2 to Get Huffman Codes "
				+ "from Entered Characters");
		System.out.println("Press 3 to Enter a String and Encode "
				+ "new Huffman Frequencies");
		System.out.println("Press 4 to Quit");
		int pick = 0;
		try {
			pick = userChoice.nextInt();
			userChoice(pick, fileEncoder);
		} catch (InputMismatchException e) {
			System.out.println("Please enter a correct input value:"); 
			userPrompt(fileEncoder);
		}
		
		
	}

	
	/**
	 * @param pick
	 * @param fileEncoder
	 * @throws IOException
	 * calls method based on user choice 
	 */
	private static void userChoice(int pick, Encoder fileEncoder) 
													throws IOException { 
		if(pick == 1) { 
			System.out.println("Enter a Huffman Code:");
			Scanner input = new Scanner(System.in);
			String inputString = input.nextLine();
			
			fileEncoder.decoder(inputString, fileEncoder);
			userPrompt(fileEncoder);
		}

		else if(pick == 2) { 
			System.out.println("Enter a String:");
			Scanner input = new Scanner(System.in);
			String inputString = input.nextLine();
			char[] charArray = inputString.toCharArray();
			fileEncoder.stringEncoder(charArray, fileEncoder);
			userPrompt(fileEncoder);

		}
		
		else if(pick == 3) {
			Encoder.nextLineCount = 0;
			System.out.println("Enter a String:");
			//Encoder inputEncoder = new Encoder();
			Scanner input = new Scanner(System.in);
			String inputString = input.nextLine();


			//triggers exit of scanner
			inputString.concat("\nEXIT_SCANNER_OBJECT");
			Scanner inputConcat = new Scanner(inputString);
			fileEncoder.encodeScanner(inputConcat);
			userPrompt(fileEncoder);
		}
		
		else if(pick == 4) { 
			return;
		}
		
		else {
			throw new IOException();
		}
		{
		}
	}
}