import java.io.*;
import java.util.Scanner;


/**
 * @author joshfram
 *
 */
public class Encoder {
	public int treeCount = 0;
	public static int nextLineCount = 0;
	public static int spaceCount = 0;
	public Encoder() {}
	public final int CHAR_TABLE_SIZE = 500;
	HuffmanNode[] HuffmanNodeHashArray = new HuffmanNode[CHAR_TABLE_SIZE];



	/**
	 * Static HuffMan Node class
	 * @param theElement (the character)
	 * @param frequencyCount (number of appearances)
	 */
	public class HuffmanNode implements Comparable {
		public final static int FREQUENCY_COUNT = 0;
		public final static char initChar = '\u0000';

		//called when instantiating a regular huffman node
		public HuffmanNode() {
			this(initChar, FREQUENCY_COUNT, null, null, null, false);	
		}

		//called when instantiating a treeRoot node 
		public HuffmanNode(HuffmanNode left, HuffmanNode right, int treeCount){	
			this(initChar, left.count + right.count, left, right, null, true );
			this.treeNum = treeCount;

		}

		//final huffman node 
		public HuffmanNode(char theElement, int fCount, HuffmanNode L,
				HuffmanNode R, String HuffmanCode, boolean isTree){

			character 	= theElement;
			count		= fCount;
			left 		= L;
			right 		= R;
			code 		= HuffmanCode;
			isTreeRoot	= isTree;

			//checks if leafNode is true || false 
			if(this.left == null && this.right == null) {
				this.isLeafNode = true;
			} 	else {this.isLeafNode = false;}

		}
		char printChar;
		char character;
		int count;
		int treeNum;
		HuffmanNode left;
		HuffmanNode right;
		String code;
		boolean isTreeRoot;
		boolean isLeafNode;


		public int compareTo(Object arg) {
			//less than 0 -> the object is less than the passed object 
			//more than 0 -> the object is greater than the passed object
			//equal to 0 -> the two objects are equal 
			HuffmanNode comparedCount = (HuffmanNode) arg;
			if (this.count < comparedCount.count) 
				return -1;
			else if (this.count > comparedCount.count)
				return 1;
			else return 0;
		}
	}


	///////////////////////////////////////////////////////////////////////
	//////////////////////////Build Tree & Print Codes/////////////////////
	///////////////////////////////////////////////////////////////////////

	/**
	 * Calls Method that assigns the Huffman Code 
	 * and builds the tree (instantiates left, right, parents) 
	 * @param nodeHeap 
	 */
	public void buildHuffmanTree(HuffmanHeap nodeHeap) {
		for(int i = 0; i < nodeHeap.array.length; i++) {
			if(nodeHeap.currentSize > 1) {

				HuffmanNode treeChild1 = nodeHeap.deleteMin();
				HuffmanNode treeChild2 = nodeHeap.deleteMin();
				//create new parent node with min1, min2 as children 
				HuffmanNode parentNode = 
						new HuffmanNode(treeChild1, treeChild2, ++treeCount);
				parentNode.count = treeChild1.count + treeChild2.count; 
				//adds new treeRoot to the heap
				nodeHeap.insert(parentNode);
			}
		}
		makeCodes(nodeHeap.findMin(), new StringBuffer());	//<- assigns codes
		System.out.println("\n");
		printHuffmanTree(nodeHeap);	//<- prints tree
		

	}

	
	/**
	 * @param HuffmanNode the rootNode 
	 * @param StringBuffer the encoding 
	 * prints SP when it hits a ' '
	 * prints NL when it hits a '\n'
	 */
	public void makeCodes(HuffmanNode node, StringBuffer huffmanCode) { 

										//space  vv				newLine	  vv
		if(!node.isTreeRoot && node.character != 32 && node.character != '\n'){ 
			System.out.println("Character: '" + node.character + "'" 
				+ "\tCount: " + node.count + "\tHuffman Code: " + huffmanCode);
		}
		else if((!node.isTreeRoot && node.character != 32)) {
			System.out.println("Character: " + "NL"
				+ "\tCount: " + node.count + "\tHuffman Code: " + huffmanCode);
		}
		else if((!node.isTreeRoot && node.character != '\n')) {
			System.out.println("Character: " + "SP"
				+ "\tCount: " + node.count + "\tHuffman Code: " + huffmanCode);
		}
		else if (node.isTreeRoot) {
			huffmanCode = huffmanCode.append('0');
			makeCodes(node.left, huffmanCode);
			huffmanCode = huffmanCode.deleteCharAt(huffmanCode.length()-1);
			huffmanCode = huffmanCode.append('1');
			makeCodes(node.right, huffmanCode);
			huffmanCode = huffmanCode.deleteCharAt(huffmanCode.length()-1);
		}
		assignCode(node, huffmanCode);
	}

	/**
	 * @param node
	 * @param huffmanCode
	 * assigns the final huffman code to each node
	 */
	public void assignCode(HuffmanNode node, StringBuffer huffmanCode) {
		String stringCode = huffmanCode.toString();
		node.code = stringCode;
	}

	/**
	 * @param nodeHeap
	 * public method to print the huffman tree
	 * initial height starts @ 0 
	 */
	public void printHuffmanTree(HuffmanHeap nodeHeap) {
		final int INITIAL_HEIGHT = 0;
		printHuffmanTree(nodeHeap.findMin(), INITIAL_HEIGHT);
	}

	
	/**
	 * @param rootNode
	 * @param currentHeight
	 * recursive method to print tree horizontally 
	 */
	public  void printHuffmanTree(HuffmanNode rootNode, int currentHeight){

		if(rootNode==null) {
			return;
		}
		int incrementedHeightLevel = ++currentHeight;
		printHuffmanTree(rootNode.right, incrementedHeightLevel);
		if(currentHeight != 0) { 
			//make subTree as wide as deep (formatting)
			//this specific loop prints the bars left to right
			for(int i = 1; i < currentHeight; i++) {
				System.out.print("          ||"); 
			}
			if (rootNode.character != '\u0000' && rootNode.character != 32 
								&& rootNode.character != '\n') {
				System.out.println(">---- '" + rootNode.character 
								+ "' [" + rootNode.code +"]");
			}
			else if(rootNode.character != '\u0000' && 
								rootNode.character != 32) {	
				System.out.println(">----" + "NL " 
								+ "[" + rootNode.code +"]");
			}
			else if(rootNode.character != '\u0000' 
								&& rootNode.character != '\n') {
				System.out.println(">----" + "SP " 
								+ "[" + rootNode.code +"]");
			}
			else if ((rootNode.character == '\u0000')) { 
				System.out.println(">----" + "TREE#" + rootNode.treeNum);	
			}
		}
		else{
			//prints the 
			System.out.println("'" +rootNode.character 
										+ "' [" + rootNode.code +"]"); 
		}
		printHuffmanTree(rootNode.left, incrementedHeightLevel);
	}




	/**
	 * Internal method to print a tree in sorted order.
	 * @param node the node that roots the tree.
	 */
	private void printHuffmanTree( HuffmanNode node )
	{
		if( node != null )
		{
			printHuffmanTree( node.left );
			System.out.println( node.character + " " +  node.code + " ");
			printHuffmanTree( node.right );  
		}
	}










	///////////////////////////////////////////////////////////////////////
	////////////////////////////////Encoding///////////////////////////////
	///////////////////////////////////////////////////////////////////////


	/**
	 * @param words: the file to be scanned 
	 * this method parses through the scanner object, hashes each 
	 * letter, and increments the frequency count
	 * @throws IOException 
	 */

	public void encodeScanner(Scanner input) {
		//instantiate the hash table of Huffman Nodes
		for(int i = 0; i < HuffmanNodeHashArray.length; i++) {
			HuffmanNodeHashArray[i] = new HuffmanNode(); 
		}
		//parse through textFile, hash characters & tabulate frequencies
		while(input.hasNextLine()) { 

			String currentLine = input.nextLine();
			nextLineCount++;	//counts number of new line characters
			if(currentLine.equals("EXIT_SCANNER_OBJECT")) {break;}
			char[] charArray = currentLine.toCharArray();
			for(int j = 0; j < charArray.length; j++) {
				insert(charArray[j]);
			}
		}
		encodeWithInsert(HuffmanNodeHashArray);
	}


	public void encodeWithInsert(HuffmanNode[] huffmanNodeArray) {
		HuffmanHeap nodeHeap = new HuffmanHeap();
		for(int k = 0; k < HuffmanNodeHashArray.length; k++) {
			if(HuffmanNodeHashArray[k].character != '\u0000') {
				HuffmanNode node = HuffmanNodeHashArray[k];
				nodeHeap.insert(node);
			}

		}
		//new line nodes
		nodeHeap.insert(new HuffmanNode('\n', nextLineCount, 
				null, null, null, false));
		buildHuffmanTree(nodeHeap);
	}


	public void printHeapFrequencies(HuffmanHeap heap) { 
		for(int i = 0; i < heap.array.length; i++) {
			if(heap.currentSize >= 1) {
				HuffmanNode currentMin = heap.deleteMin();
				System.out.println("Character: " + currentMin.character +
						"\tCount: " + currentMin.count);
			}
		}
	}

	public HuffmanHeap encodeWithBuild(HuffmanNode[] huffmanNodeArray) {
		int size = 0; 
		for(int p = 0; p < HuffmanNodeHashArray.length; p++) {
			if(HuffmanNodeHashArray[p].character != '\u0000') {
				size++;
			}
		}
		HuffmanNode[] nodeArray = new HuffmanNode[size];
		for(int k = 0, m = 0; k < HuffmanNodeHashArray.length; k++) {
			if(HuffmanNodeHashArray[k].character != '\u0000') {
				HuffmanNode node = HuffmanNodeHashArray[k];
				nodeArray[m++] = node;
			}
		}
		HuffmanHeap nodeHeap = new HuffmanHeap(nodeArray);
		return nodeHeap;
	}









	///////////////////////////////////////////////////////////////////////
	/////////////////////////////////Hashing///////////////////////////////
	///////////////////////////////////////////////////////////////////////

	/**
	 * @param charArray the symbol being hashed
	 * @return hash Location
	 */
	public int hash(char charArray) {
		return hash(charArray, CHAR_TABLE_SIZE);
	}

	/**
	 * hash function (nested method)
	 *uses an odd, prime multiple of 37
	 *multiplies the java generated hashCode 
	 *by the primeMultiple
	 *this is then modded, and the hashVal is returned
	 */
	public int hash(char character, int tableSize) {
		int primeMultiple = 37;
		int hashVal;


		hashVal = character * primeMultiple;
		return hashVal % tableSize;
	}


	//method to insert Strings into the hashtable
	public void insert(char character) {
		int currentPos = findPos(character);

		if(isUsed(currentPos))
			//increment count, char seen again
			++HuffmanNodeHashArray[currentPos].count;	
		else {
			HuffmanNodeHashArray[currentPos].character = character;
			//increment count, char seen for the first time
			++HuffmanNodeHashArray[currentPos].count;	
		}
	}


	/**
	 * this is the method that does the actual "finding" of the
	 * open positions, as well as find the position for elements we
	 * are seeking (depending on the method that calls it). the while loop
	 * probes until the currentPos index is unused and not equal to toBeFound
	 */

	public int findPos(char character) {
		int offset = 1;
		int currentPos = hash(character);

		//probes for open array index
		while(HuffmanNodeHashArray[currentPos].character != '\u0000' &&
				!(HuffmanNodeHashArray[currentPos].character == (character))){
			currentPos += offset;
			offset += 2;
			if(currentPos >= HuffmanNodeHashArray.length) { //wraps around
				currentPos -= HuffmanNodeHashArray.length;
			}
		}
		return currentPos;
	}


	public boolean isUsed (int currentPos) {
		return HuffmanNodeHashArray[currentPos].character != '\u0000';
	}






	//////////////////////////////////////////////////////////////////////
	//////////////////////////USER_REQUEST////////////////////////////////
	//////////////////////////////////////////////////////////////////////


	public void decoder(String inputString, Encoder fileEncoder) {

		//regenerate heap
		HuffmanHeap thisHeap = new HuffmanHeap();
		for(int k = 0; k < fileEncoder.HuffmanNodeHashArray.length; k++) {
			if(fileEncoder.HuffmanNodeHashArray[k].character != '\u0000') {
				HuffmanNode node = fileEncoder.HuffmanNodeHashArray[k];
				thisHeap.insert(node);
			}
		}
		//new line nodes
		thisHeap.insert(new HuffmanNode('\n', nextLineCount, 
				null, null, null, false));
		//regenerate HuffmanTree
		for(int i = 0; i < thisHeap.array.length; i++) {
			if(thisHeap.currentSize > 1) {
				HuffmanNode treeChild1 = thisHeap.deleteMin();
				HuffmanNode treeChild2 = thisHeap.deleteMin();
				HuffmanNode parentNode = 
							new HuffmanNode(treeChild1, treeChild2, treeCount);
				parentNode.count = treeChild1.count + treeChild2.count; 

				thisHeap.insert(parentNode);
			}
		}
		HuffmanNode root = thisHeap.findMin();
		System.out.print(decode(inputString, root, root));
	}



	public static String decode(String code, HuffmanNode root, 
										HuffmanNode current) { 
		if (code.length() == 0 && current.equals(root)) {
			return "";
		}
		else if (code.length() == 0) {
			return "\nInput Is Invalid For This Encoding";
		}
		char direction=code.charAt(0); 
		if (direction=='0'){
			if (current.left.character == '\u0000') { 	//if still @ tree node
				String cutString = code.substring(1,code.length());
				HuffmanNode recursLeft = current.left;
				return decode(cutString, root, recursLeft);
			}
			else{
				String cutString = code.substring(1,code.length());
				String decodedChar = 
						Character.toString((Character)current.left.character);
				return decodedChar + decode(cutString, root, root);
			}
		}
		if (direction=='1'){
			if (current.right.character=='\u0000') {	//if still @ tree node
				String cutString = code.substring(1,code.length());
				HuffmanNode recursRight = current.right;
				return decode(cutString, root, recursRight);
			}
			else {
				String cutString = code.substring(1,code.length());
				String decodedChar = 
						Character.toString((Character)current.right.character);
				return decodedChar + decode(cutString, root, root);
			}
		}
		else {
			return "\nInput Is Invalid For This Encoding";
		}
	}

	public void stringEncoder(char[] charArray, Encoder fileEncoder) {
		HuffmanNode[] nodeArray = fileEncoder.HuffmanNodeHashArray;
		System.out.print("Huffman Encoding: \n");
		for(int i = 0; i < charArray.length; i++) {
			int position = findPos(charArray[i]);
			if(nodeArray[position].character == 0) { 
				System.out.print(charArray[i]);	//char is not encoded 
			}
			else System.out.print(nodeArray[position].code);
		}
	}
}









