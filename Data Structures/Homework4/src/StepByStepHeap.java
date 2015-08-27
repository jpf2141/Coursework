import java.util.Random;

/**
 * A class that contains several sorting routines,
 * implemented as static methods.
 * Arrays are rearranged with smallest item first,
 * using compareTo.
 * @author Mark Allen Weiss
 */
public final class StepByStepHeap
{

    /**
     * Internal method for heapsort.
     * @param i the index of an item in the heap.
     * @return the index of the left child.
     */
    private static int leftChild( int i )
    {
        return 2 * i + 1;
    }
    
    /**
     * Internal method for heapsort that is used in deleteMax and buildHeap.
     * @param a an array of Comparable items.
     * @index i the position from which to percolate down.
     * @int n the logical size of the binary heap.
     */
    private static <AnyType extends Comparable<? super AnyType>>
    void percDown( AnyType [ ] a, int i, int n )
    {
        int child;
        AnyType tmp;

        for( tmp = a[ i ]; leftChild( i ) < n; i = child )
        {
            child = leftChild( i );
            if( child != n - 1 && a[ child ].compareTo( a[ child + 1 ] ) < 0 )
                child++;
            if( tmp.compareTo( a[ child ] ) < 0 )
                a[ i ] = a[ child ];
            else
                break;
        }
        a[ i ] = tmp;
    }
    
    /**
     * Standard heapsort.
     * @param a an array of Comparable items.
     */
    public static <AnyType extends Comparable<? super AnyType>>
    void heapsort( AnyType [ ] a )
    {
    	System.out.println("\nOriginal Array\n");
    	printArray(a);
        for( int i = a.length / 2 - 1; i >= 0; i-- ) {  /* buildHeap */
            percDown( a, i, a.length );
        	
        }
        System.out.println("\nBuilt Heap:\n");
    	printArray(a);
    	System.out.println("\n\n");
        
        	
        for( int i = a.length - 1; i > 0; i-- )
        {
            swapReferences( a, 0, i );                /* deleteMax */
            percDown( a, 0, i );
            printArray(a);
        }
    }

    public static <AnyType extends Comparable<? super AnyType>>
    void printArray( AnyType [ ] a ) {
    	for (int i = 0; i < a.length; i++) {
    		System.out.print(a[i] + ", ");
    	}
    	System.out.println("\n");
    }
 
    
    
    public static <AnyType> void swapReferences( AnyType [ ] a, int index1, int index2 )
    {
        AnyType tmp = a[ index1 ];
        a[ index1 ] = a[ index2 ];
        a[ index2 ] = tmp;
    }
    


  
    

    private static final int NUM_ITEMS = 1000;
    private static int theSeed = 1;



    public static void main( String [ ] args )
    {
        Integer [ ] a = {142, 543, 123, 65, 453, 879, 572, 434, 111, 242, 811, 102};
          
            
            StepByStepHeap.heapsort( a );
        
        
       
        
       
    }
}

