
public class FirstCommonAncestor {
	static Node commonAncestor(Node one, Node two) {
		if( one == null )
	    	return two;
		if( two == null )
	    	return one;
		if( FindInChild(one, two) )
			return one;
		if( FindInChild(two, one) )
		return two;
		Node OneParent = one, TwoParent = two;
		
		while( OneParent != null && TwoParent != null ) {
			if( OneParent == TwoParent )
				return OneParent;
			OneParent = OneParent.parent;
			TwoParent = TwoParent.parent;
		}

		return null;
	}

	private static boolean FindInChild(Node root, Node target) {
		if( root == null )
			return false;
		if( root == target )
			return true;
		if( root.left != null || root.right != null )
			return FindInChild(root.left, target) || FindInChild(root.right, target);
		return false;
	}
	
	public static TreeNode LCA(TreeNode root, TreeNode a, TreeNode b){
		TreeNode left = null, right = null;
		if(root == null) return root;
		if(root == a || root == b)	return root;
		left = LCA(root.left, a, b);
		right = LCA(root.right, a, b);
		if( left != null && right != null)	return root;
		return ( left != null ) ? left : right; 
	}
	
	public static void main(String[] args) {
		TreeNode A = new TreeNode('A');
		TreeNode B = new TreeNode('B');
		TreeNode C = new TreeNode('C');
		TreeNode D = new TreeNode('D');
		TreeNode E = new TreeNode('E');
		TreeNode G = new TreeNode('F');
		TreeNode F = new TreeNode('G');
		A.left = B; A.right = C;
		B.left = D; B.right = F;
		D.left = G; D.right = E;
		TreeNode res1 = LCA(A, D, F);
		TreeNode res2 = LCA(A, C, G);
		TreeNode res3 = LCA(A, C, C);
		TreeNode res4 = LCA(A, B, A);
		System.out.println("First Common Ancestor of D and F is: " + res1.c);
		System.out.println("First Common Ancestor of C and G is: " + res2.c);
		System.out.println("First Common Ancestor of C and C is: " + res3.c);
		System.out.println("First Common Ancestor of B and A is: " + res4.c);
	}
}
 
class Node {
 
	final char c;
	final Node parent;
	final Node left;
	final Node right;
 
 
	public Node(char c, Node parent, Node left, Node right) {
    	this.c = c;
		this.parent = parent;
    	this.left = left;
    	this.right = right;
	}
 
	boolean isRoot() {
    	return parent == this;
	}
}

class TreeNode {	 

	char c;
	TreeNode left;
	TreeNode right;
 
	public TreeNode(char c) {
    	this.c = c;
	}
 
}
