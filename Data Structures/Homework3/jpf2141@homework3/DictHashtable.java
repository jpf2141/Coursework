
public class DictHashtable {
	
	public int size;
	public String[] dictHashTable;
		
	public DictHashtable(int size) { 
		this.size = size;
		this.dictHashTable = new String[size];	
		
		
	/**
	 * hash function (parent method)		 
	*/
	}
	public int hash(String toBeHashed) {
		return hash(toBeHashed, size);
	}
	
	/**
	 * hash function (nested method)
	 *uses an odd, prime multiple of 31
	 *the hash function is simply multiplying the ASCII value of the char
	 *by the primeMultiple, and adding the result onto itself
	 *this is then modded, and the hashVal is returned
	 */
	public int hash(String toBeHashed, int tableSize) {
		final int primeMultiple = 31; 
		int hashVal = 0;
		
		for(int i = 0; i < toBeHashed.length(); i++) {
			hashVal += toBeHashed.charAt(i) * primeMultiple;
		}
		return hashVal % tableSize; 
	}

	//method to insert Strings into the hashtable
	public void insert(String toBePlaced) {
		int currentPos = findPos(toBePlaced);
	
		if(isUsed(currentPos))
			return;					//do nothing, toBePlaced is a duplicate
		else { 
			dictHashTable[currentPos] = toBePlaced;
		}
	}
	
	public boolean contains (String toBeChecked) { 
		int currentPos = findPos(toBeChecked);
		return isUsed(currentPos);
	}
	
	
	
	/**
	 * this is the method that does the actual "finding" of the 
	 * open positions, as well as find the position for elements we 
	 * are seeking (depending on the method that calls it). the while loop
	 * probes until the currentPos index is unused and not equal to toBeFound
	 */
	
	public int findPos(String toBeFound) {
		int offset = 1; 
		int currentPos = hash(toBeFound);
		
		//probes for open array index
		while(dictHashTable[currentPos] != null &&
				!dictHashTable[currentPos].equals(toBeFound)) { 
			currentPos += offset;
			offset += 2;
			if(currentPos >= dictHashTable.length) { //wraps around
				currentPos -= dictHashTable.length;
			}
		}
		return currentPos;	
	}
	
	//we do not have to account for "non-active" cells because in this case 
	//we will be hashing only twice (once for the big dictionary, once for 
	//the small dictionary, with no removes necessary. Thus, the only thing we
	//need to check for is whether there is an element in the cell. 
	public boolean isUsed (int currentPos) { 
		return dictHashTable[currentPos] != null;
	}
}
