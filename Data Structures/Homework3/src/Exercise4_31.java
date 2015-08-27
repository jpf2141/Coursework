public class Exercise4_31<T> { 
public class BinaryNode<T> {
	public BinaryNode<T> left;
	public BinaryNode<T> right;
}
//Part A) Finds Number of nodes in tree t 
//O(N) runtime 
public int nodes(BinaryNode<T> t){
	if (t!=null){
		return 1 + nodes(t.left) + nodes(t.right);
	} else return 0;
}
//Part B) Finds number of leaves in tree t  
//O(N) runtime 
public int leaves(BinaryNode<T> t){
	if (t.left==null && t.right==null) 
		return 1;
	else if (t.left!=null && t.right!= null) 
		return leaves(t.left)+leaves(t.right); 
	else if (t.left!=null) 
		return leaves(t.left);
	else return leaves(t.right);
}
//Part C) Finds number of full nodes in tree t 
//O(N) runtime 
public int fullNodes(BinaryNode<T> t){ 
	if (t!=null){
		if (t.left!=null && t.right!=null)
			return 1 + fullNodes(t.left) + fullNodes(t.right);
		else
			return fullNodes(t.left) + fullNodes(t.right);
	} else return 0;
	}
}
