To run this, compile: 

HuffManTester.java
Encoder.java
HuffmanHeap.java
UnderflowException.java 

The file takes at the command line any text file. For ease of use, the file ReadMe.txt can be used as a test.
Simply run the file, and enter the file name at the command line. The program will catch errors at this step,
and ask the user to reenter a file. 

The program prints the frequency chart, and a horizontal Huffman tree. It then asks the user to:

Press 1 to Get Character Sequence From Huffman Code
Press 2 to Get Huffman Codes from Entered Characters
Press 3 to Enter a String and Encode new Huffman Frequencies
Press 4 to Quit

Pressing 1 or 2 allows the user to encode or decode using the exisiting Huffman Tree, pressing 3 allows the 
user to encode a new string he can type into the scanner. This step catches incorrect inputs using the 
InputMisMatch exception.  