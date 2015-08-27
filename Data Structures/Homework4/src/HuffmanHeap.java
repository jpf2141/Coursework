
// BinaryHeap class
//
// CONSTRUCTION: with optional capacity (that defaults to 100)
//               or an array containing initial items
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// Comparable deleteMin( )--> Return and remove smallest item
// Comparable findMin( )  --> Return smallest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// ******************ERRORS********************************
// Throws UnderflowException as appropriate

/**
 * Implements a binary heap.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */
public class HuffmanHeap<AnyType extends Comparable<? super AnyType>>
{

	/**
     * Construct the binary heap.
     */
    public HuffmanHeap( )
    {
        this( 500 );
    }
    
    /**
     * Construct the binary heap.
     * @param capacity the capacity of the binary heap.
     */
    public HuffmanHeap( int capacity )
    {
        currentSize = 0;
        array = new Encoder.HuffmanNode[ capacity + 1 ];
    }
	 
    /**
     * Construct the binary heap given an array of items.
     */
    public HuffmanHeap( Encoder.HuffmanNode [ ] items )
    {
        currentSize = items.length;
        System.out.println(currentSize);
        array = new Encoder.HuffmanNode[ ( currentSize + 2 ) * 11 / 10 ];
        
        int i = 1;
        for( Encoder.HuffmanNode item : items )
            array[ i++ ] = item;
        buildHeap( );
    }
    

    /**
     * Find the smallest item in the priority queue.
     * @return the smallest item, or throw an UnderflowException if empty.
     */
    public Encoder.HuffmanNode findMin( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( );
        return array[ 1 ];
    }
    
    /**
     * Remove the smallest item from the priority queue.
     * @return the smallest item, or throw an UnderflowException if empty.
     */
    public Encoder.HuffmanNode deleteMin( )
    {
        if( isEmpty( ) ) 
            throw new UnderflowException( );
        
        Encoder.HuffmanNode minItem = findMin( );
        array[ 1 ] = array[ currentSize-- ];
        percolateDown( 1 );
        
        return minItem;
    }
    
    
    /**
     * Establish heap order property from an arbitrary
     * arrangement of items. Runs in linear time.
     */
    private void buildHeap( )
    {
        for( int i = currentSize / 2; i > 0; i-- )
            percolateDown( i );
    }
    
    
    
    /**
     * Insert into the priority queue, maintaining heap order.
     * Duplicates are allowed.
     * @param x the item to insert.
     */
    public void insert( Encoder.HuffmanNode x )
    {
        if( currentSize == array.length - 1 )
            enlargeArray( array.length * 2 + 1 );
        
        // Percolate up
        int hole = ++currentSize;
        for( array[ 0 ] = x; x.compareTo( array[ hole / 2 ] ) < 0; hole /= 2 )
            array[ hole ] = array[ hole / 2 ];
        array[ hole ] = x;
    }
    
    
    private void enlargeArray( int newSize )
    {
        Encoder.HuffmanNode [] old = array;
        array = new Encoder.HuffmanNode[ newSize ];
        for( int i = 0; i < old.length; i++ )
            array[ i ] = old[ i ];
    }
    
    /**
     * Test if the priority queue is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return currentSize == 0;
    }
    
    /**
     * Make the priority queue logically empty.
     */
    public void makeEmpty( )
    {
        currentSize = 0;
    }
    
    public int currentSize;      // Number of elements in heap
    public Encoder.HuffmanNode[] array; // The heap array
    
    /**
     * Internal method to percolate down in the heap.
     * @param hole the index at which the percolate begins.
     */
    private void percolateDown( int hole )
    {
        int child;
        Encoder.HuffmanNode tmp = array[ hole ];
        
        for( ; hole * 2 <= currentSize; hole = child )
        {
            child = hole * 2;
            if( child != currentSize &&
               array[ child + 1 ].compareTo( array[ child ] ) < 0 )
                child++;
            if( array[ child ].compareTo( tmp ) < 0 )
                array[ hole ] = array[ child ];
            else
                break;
        }
        array[ hole ] = tmp;
    }
}