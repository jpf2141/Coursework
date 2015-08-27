
// BinarySearchTree class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// boolean contains( x )  --> Return true if x is present
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order



/**
 * Implements an unbalanced binary search tree.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss ***with changes by Josh Fram*** 
 */
public class BinarySearchTree_LAZY<AnyType extends Comparable<? super AnyType>>
{
	
    public BinaryNode<AnyType> root;	//root of tree
    public BinarySearchTree_LAZY()		//constructor for tree
	{
		root = null;
	}
    
    // Basic BinaryNode 
    public static class BinaryNode<AnyType>
    {
    	//BinaryNode class variables 
        AnyType element;            // The data in the node
        BinaryNode<AnyType> left;   // Left child
        BinaryNode<AnyType> right;  // Right child
        public boolean active; 	//added for LazyDelete

        // Constructors
        BinaryNode(AnyType theElement)
        {
            this(theElement, null, null);	
        }

        BinaryNode(AnyType theElement, BinaryNode<AnyType> leftTree, BinaryNode<AnyType> rightTree)
        {
        	Activate(true);	//added for LazyDelete 
        					//(when constructing a node, make it active)
            element = theElement;
            left = leftTree;
            right = rightTree;
            
        }
        
        //sets node active 
        public void Activate(boolean active) {	//added for LazyDelete
        	this.active = active; 
        }
        
        //checks if node is active
        public boolean checkIfActive() { 	//added for LazyDelete
        	return active; 
        }
    }
  

    /*********************************findMin*********************************
     * done
     * Find the smallest item in the tree.
     * @return smallest item or null if empty.
     */
    public AnyType findMin()
    {
        if (isEmpty(root) == true)	//added for Lazy Delete 
            System.out.println("Tree is Empty!") ;
        return findMin(root, root).element;
    }

    public BinaryNode<AnyType> findMin(BinaryNode<AnyType> t, 
    		BinaryNode<AnyType> Node)
    {
    	BinaryNode <AnyType >minVal;
    	//if left is active, smallest, so continue that way
        if (t.left != null && t.left.checkIfActive()) {
           Node = t.left;
                return findMin(t.left, Node);
        }
        //if left is innactive, we still need to go right because there
        //could still be active variables below it 
        else if (t.checkIfActive()) {
        	Node = t; 
        	if (t.left != null) {	//checks this subtree 
        		return findMin(t.left, Node); 	//dont need to consider right
												//because t is active, != null
        	}
        	else return Node; //this is the max
        }
        else {
        	if(t.right != null && t.right.checkIfActive()) {
        		Node = t.right;
        	}
        	if(t.left != null) {
        		minVal = findMin(t.left, Node);
        		if (minVal == Node && t.right != Node) {
        			return findMin(t.right, Node);	//right subtree because 
        											//no new mins in left
        		}
        		else return minVal;
        	}
        	else return Node; 	
        	}
    	}

    /******************************findMax************************************
     * done
     * Find the largest item in the tree.
     * @return the largest item of null if empty.
     * we go right in this method because we are finding the max (max > right)
     */
    public AnyType findMax()
    {
        if (isEmpty(root))
            System.out.println("Tree is empty!");
        return findMax(root, root).element;
    }
    
    public BinaryNode<AnyType> findMax(BinaryNode<AnyType> t, 
    		BinaryNode<AnyType> Node)
    {
    	BinaryNode <AnyType >maxVal;
    	//if right is innactive, we still need to go left because there
        //could still be active variables below it 
        if (t.right != null && t.right.checkIfActive()) {
           Node = t.right;
                return findMax(t.right, Node);
        }
        else if (t.checkIfActive()) {
        	Node = t; 
        	if (t.right != null) {	//checks this subtree 
        		return findMax(t.right, Node); 	//dont need to consider left
        										//because t is active, != null
        	}
        	else return Node; //this is the max
        }
        else {
        	if(t.left != null && t.left.checkIfActive()) {
        		Node = t.left;
        	}
        	if(t.right != null) {
        		maxVal = findMax(t.right, Node);
        		if (maxVal == Node && t.left != Node) {
        			return findMax(t.left, Node);
        		}
        		else return maxVal;
        	}
        	else return Node; 	
        	}
    	}

    
    /*******************************contains**********************************
     * done
     * Find an item in the tree.
     * @param x the item to search for.
     * @return true if not found.
     * RETURNS TRUE IFF THE NODE IS FOUND && ACTIVE
     */
    public boolean contains(AnyType x)
    {
        return contains(x, root);
    }
    
    public boolean contains(AnyType x, BinaryNode<AnyType> t)
    { 	//checks to see if values exists AND if they are active
        if (t == null)
            return false;
            
        int compareResult = x.compareTo(t.element);	//value at that node
            
        if (compareResult < 0)
            return contains(x, t.left);		//check left tree
        else if (compareResult > 0)
            return contains(x, t.right);	//check right tree 
        else
            return t.checkIfActive();    //returns true
    }

   
    /******************************height*************************************
     * done
     * Internal method to compute height of a subtree.
     * @param t the node that roots the subtree.
     */
    
    public int height(BinaryNode<AnyType> t)	//did not change this method 
    											//as per P.Blaer's instructions
    {
        if (t == null)
            return -1;
        else
            return 1 + Math.max(height(t.left ), height( t.right));    
    }
    
    
    
    /*******************************printTree*********************************
     * Done
     * Methods to print a subtree in sorted order.
     * @param t the node that roots the subtree.
     */
    public void printTree()	//same logic as makeEmpty, but prints instead
    						//of deactivates 
    {
        if (isEmpty(root))
            System.out.println("Tree is Empty!");
        else
            printTree(root);	//overloads method 
    }
    
    //same except that only prints out element when it is active 
    public void printTree(BinaryNode<AnyType> t)
    {
        if (t != null) {
            printTree(t.left);	//more recursion! prints left tree
            if(t.checkIfActive()) {	//added for LazyDelete
            System.out.println(t.element);
            }
            printTree(t.right);	//MORE recursion! prints right tree
        }
    }
    
    
    /********************************isEmpty**********************************
     * done
     * Test if the tree is logically empty.
     * @param root2 
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty(BinaryNode<AnyType> root2) 
    {
        if(root2.checkIfActive()) {	//changed for Lazy Delete
        	return false;	//the tree is not empty, because there is one
        					//active node at least, so isEmpty = false
        }
        //changed for Lazy Delete
        else if(root2.right == null && root2.left == null) {
        	return true;	//the tree is empty because weve reached the 
        					//bottom row through recursion
        					//and there are no active nodes
        }
        else if (root2.right == null) {
        	return isEmpty(root2.left);	//checks the left tree
        }
        else if (root2.left == null) {
        	return isEmpty(root2.right);//checks the right tree
        }
        else return isEmpty(root2.right) && isEmpty(root2.left);
        
    }
    
    

    /******************************makeEmpty**********************************
     * done
     * Make the tree empty (deactivate all nodes).
     * same logic as print tree, but instead of printing
     * it deactivates all the nodes
     */ 
    
    public void makeEmpty(BinaryNode<AnyType> t)	
    {
        if(t != null) {
        	makeEmpty(t.right);	//recursion!
        	t.Activate(false);	//decative nodes
        	makeEmpty(t.left);
        }
    }
    
    
    /********************************remove***********************************
     * done
     * Remove from the tree. Nothing is done if x is not found.
     * @param x the item to remove.
     */
    public void remove(AnyType x)	//stays the same (remove(x, root) changes)
    {
        root = remove(x, root); //***overloads the remove method***
    }

    public BinaryNode<AnyType> remove(AnyType x, BinaryNode<AnyType> t)
    {
        if (t == null)
            return t;   // Item not found; do nothing :D 
            
        int compareResult = x.compareTo(t.element);
            
        if (compareResult < 0)
            t.left = remove(x, t.left);
        else if (compareResult > 0) {
            t.right = remove(x, t.right);
        }
        else  
        {
            t.Activate(false);	//changed for lazy delete 
        }
        return t;
    }
    
    
    /*********************************Insert**********************************
     * done
     * Insert into the tree; duplicates are ignored.
     * @param x the item to insert.
     */
    public void insert(AnyType x)	//stays the same (insert(x, root) changes)
    {
        root = insert(x, root);	//***overloads the insert method***
    }

    public BinaryNode<AnyType> insert(AnyType x, BinaryNode<AnyType> t)
    {
        if (t == null)
            return new BinaryNode<AnyType>(x, null, null);
        
        int compareResult = x.compareTo(t.element);
            
        if (compareResult < 0)
            t.left = insert(x, t.left);
        else if (compareResult > 0)
            t.right = insert(x, t.right);
        else
            if(t.checkIfActive() == false) { // Duplicate; do nothing
            	t.Activate(true);
            }
        return t;
    }
 
    // Test program
    public static void main(String [ ] args)
    {
        BinarySearchTree_LAZY<Integer> t = new BinarySearchTree_LAZY<Integer>();
        
        //inserts
        t.insert(2);
        t.insert(3);
        t.insert(6);
        t.insert(5);
        t.insert(10);
        t.insert(11);
        t.insert(12);
        t.insert(20);
        t.insert(31);
        t.remove(31);
        t.insert(30);
        t.remove(29);
        t.remove(10);
      
        
        //returning methods
        System.out.println("Printed Tree in Ascending Order: ");
        t.printTree();
        System.out.println("Contains 3?: " +t.contains(3));
        System.out.println("Max: " +t.findMax());
        System.out.println("Min: " +t.findMin());
        System.out.println("Height: " +t.height(t.root));
        System.out.println("\nEmpty (pre-makeEmpty() call)?: "
        					+t.isEmpty(t.root));
        t.makeEmpty(t.root);
        System.out.println("Empty (post-makeEmpty() call)?: "
        					+t.isEmpty(t.root));
        System.out.println("\nPrinting tree after makeEmpty() call:");
        t.printTree();
    }
}
