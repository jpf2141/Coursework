import java.io.File;
import java.io.IOException;

import java.util.Scanner;

public class SpellCheckTest { 
	final static char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

public static void main(String[] args){ 
	
		boolean again=true;
		while(again){
			try{
				File textFile1 = new File(args[0]);	//command line argument 0
				Scanner bigDict1 = new Scanner(textFile1);
				File textFile2 = new File(args[1]);	//command line argument 1
				Scanner smallDict = new Scanner(textFile2);
				File textFile3 = new File(args[2]);	//command line argument 2
				Scanner spellCheckFile = new Scanner(textFile3);

				adder(bigDict1, smallDict, spellCheckFile);
				again = false;
				}
		  catch(IOException e){
			    System.out.println("Please try again with correct input "
			    						+ "file name");
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

/**
 * adder method
 * This method builds the hashtable using the words in the dictionaries 
 * supplied the big dictionary and the personal dictionary are passed 
 * into the same hashtable, so that each word checked can be compared 
 * with the elements in only 1 hashtable 
 * in order to prevent collisions, the hashtable is has a load factor of 
 * less than .50; using quadratic probing (see DictHashTable.java), there
 * are 0 collisions that remain unresolved when load factor < .50
 */
public static void adder(Scanner dict1, Scanner dict2, Scanner checkFile) { 
		
		//hash table for the words in the big dictionary
	
		int size = 200003; //next prime number after 200000
		
		//using 200,003 as the table size for this bigDictionary
		//hashtable because the dictionary has approximately 
		//100,000 words, and I want to keep the load factor 
		//less .50 (~100,000 * 2 = 200,000)
		//200003 is the next prime number after 200,003
		DictHashtable dictionaryTable = new DictHashtable(size);
		
		//inserts from the big dictionary into the hashtable
		while(dict1.hasNext()) {
			String initial = dict1.nextLine();
			String bigDictInput = initial.toLowerCase();
			dictionaryTable.insert(bigDictInput);	
		}
		//inserts from the personal dictionary into the hashtable
		while(dict2.hasNext()) {
			String initial = dict2.nextLine();
			String smallDictInput = initial.toLowerCase();
			dictionaryTable.insert(smallDictInput);	
		}
		checker(checkFile, dictionaryTable);
	}

/**
 * checker method
 * This method checks to see if the words in the file are in the dictionary 
 * (ie, if they are "real" words). If they are not, it passes them to the 
 * suggester() method; it checks to see if the word is possibly misspelled 
 * with a typo. 
 */
public static void checker(Scanner checkFile, DictHashtable dictionaryTable) {
		int lineNumber = 0; 
		while(checkFile.hasNext()) {	//parses line by line 
		
			lineNumber++;	//increment the current line counter
			String lineInitial = checkFile.nextLine();	
			String lineAdjusted;	
			//line with no punctuation except apostrophes 
			lineAdjusted = lineInitial.toLowerCase().replaceAll
										("[^\\w' ]", "");
			String[] words = lineAdjusted.split(" ");
			for(int i = 0; i < words.length; i++) { 
				if(!dictionaryTable.contains(words[i])) {
					int lineOfWord = lineNumber;
					suggester(words[i], lineOfWord, dictionaryTable);
				}
			}
		}
	}

/**
 * This method checks to see if the word is possibly misspelled 
 * with a typo. It adds a A-Z at each index of the word, removes one character 
 * at a time, and swaps. The result of each change is checked against the 
 * dictionaryTable; if there's a match (ie, dictionaryTable.contains(theNewWord)
 *is true, the suggester() method prints it as a possible correction.
 */
public static void suggester(String badWord, int lineNumber, DictHashtable dictionaryTable) {
	System.out.print("\nMisspelled '"+badWord+"' at line "+lineNumber+ "\nPossible Corrections: ");
	for(int i = 0, k = 1; i < badWord.length(); i++, k++) {
		String subWord = badWord.substring(0, i+1);
		String subWordEnd = badWord.substring(i +1, badWord.length());
		
		//adding one character to the word
		for(int j = 0; j <alphabet.length; j++) { //loops through alphabet 
			String newWord = subWord +  Character.toString(alphabet[j]) + subWordEnd;
			if(dictionaryTable.contains(newWord)) { //if matched
				System.out.print(newWord + "\t");
			}		
		}
		
		//removing a character from the word
		String subWordRemove = badWord.substring(0, k-1);
		String subWordEndRemove = badWord.substring(k);
		String concatenatedRemove = subWordRemove + subWordEndRemove;
			if(dictionaryTable.contains(concatenatedRemove)) { //if matched
				System.out.print(concatenatedRemove + "\t");
			}
		//prints the Swapping method in allignment with the removal and adding
		System.out.print(swapChars(badWord, dictionaryTable));
		}
	}
	
	//swapping characters
	public static String swapChars(String badWord, DictHashtable dictionaryTable) {	
		char[] badWordArray = badWord.toCharArray();
		for(int l = 0; l < badWordArray.length-1; l++) {
			char subWordTemp = badWordArray[l];
			badWordArray[l] = badWordArray[l+1];
			badWordArray[l+1] = subWordTemp;
		}
			//makes charArray into a String
			String swapWord = new String(badWordArray);	
			if(dictionaryTable.contains(swapWord)) {
				return swapWord + "\t";
			}
			else {
				return "";
			}
		}
	}
	

		
	