
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
	
	public static int CountNodes(TreeNode root, TreeNode a, TreeNode b) {
		if( root == null )
			return 0;
		int LeftNum = CountNodes(root.left, a, b);
		int RightNum = CountNodes(root.right, a, b);
		if( root == a || root == b )
			return 1 + LeftNum + RightNum;
		else
			return LeftNum + RightNum;			
	}
	
	public static TreeNode LCA2(TreeNode root, TreeNode a, TreeNode b){
		if(root == null || a == null || b == null ) 
			return null;
		if(root == a || root == b)	return root;
		int LeftNum = CountNodes(root.left, a, b);
		if( LeftNum == 1 )
			return root;
		else if( LeftNum == 2 )
			return LCA2(root.left, a, b);
		else // LeftNum == 0
			return LCA2(root.right, a, b); 
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
		
		TreeNode res2_1 = LCA2(A, D, F);
		TreeNode res2_2 = LCA2(A, C, G);
		TreeNode res2_3 = LCA2(A, C, C);
		TreeNode res2_4 = LCA2(A, B, A);
		System.out.println("Top Down First Common Ancestor of D and F is: " + res2_1.c);
		System.out.println("Top Down First Common Ancestor of C and G is: " + res2_2.c);
		System.out.println("Top Down First Common Ancestor of C and C is: " + res2_3.c);
		System.out.println("Top Down First Common Ancestor of B and A is: " + res2_4.c);
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
