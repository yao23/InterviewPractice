import java.util.HashSet;
import java.util.Set;

public class FirstCommonAncestor {
	public static Node commonAncestor(Node one, Node two) {
		if( one == null || two == null )
	    	return null;
		Set<Node> nodes = new HashSet<Node>();
		
		while( one != null || two != null ) {
			if( one != null ) {
				if( nodes.add(one) ) 
					one = one.parent;
				else 
					return one;
			}
			if( two != null ) {
				if( nodes.add(two) )
					two = two.parent;
				else
					return two;
			}
		}
		
		return null;
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
		System.out.println("Bottom Up First Common Ancestor of D and F is: " + res1.c);
		System.out.println("Bottom Up First Common Ancestor of C and G is: " + res2.c);
		System.out.println("Bottom Up First Common Ancestor of C and C is: " + res3.c);
		System.out.println("Bottom Up First Common Ancestor of B and A is: " + res4.c);
		
		TreeNode res2_1 = LCA2(A, D, F);
		TreeNode res2_2 = LCA2(A, C, G);
		TreeNode res2_3 = LCA2(A, C, C);
		TreeNode res2_4 = LCA2(A, B, A);
		System.out.println("Top Down First Common Ancestor of D and F is: " + res2_1.c);
		System.out.println("Top Down First Common Ancestor of C and G is: " + res2_2.c);
		System.out.println("Top Down First Common Ancestor of C and C is: " + res2_3.c);
		System.out.println("Top Down First Common Ancestor of B and A is: " + res2_4.c);
		
		Node A2 = new Node('A');
		Node B2 = new Node('B');
		Node C2 = new Node('C');
		Node D2 = new Node('D');
		Node E2 = new Node('E');
		Node G2 = new Node('F');
		Node F2 = new Node('G');
		A2.left = B2; A2.right = C2; A2.parent = null;
		B2.left = D2; B2.right = F2; B2.parent = A2; C2.parent = A2;
		D2.left = G2; D2.right = E2; D2.parent = B2; E2.parent = D2;
		F2.parent = B2; G2.parent = D2;
		Node res3_1 = commonAncestor(D2, F2);
		Node res3_2 = commonAncestor(C2, G2);
		Node res3_3 = commonAncestor(C2, C2);
		Node res3_4 = commonAncestor(B2, A2);
		System.out.println("With parent pointer, First Common Ancestor of D and F is: " + res3_1.c);
		System.out.println("With parent pointer, First Common Ancestor of C and G is: " + res3_2.c);
		System.out.println("With parent pointer, First Common Ancestor of C and C is: " + res3_3.c);
		System.out.println("With parent pointer, First Common Ancestor of B and A is: " + res3_4.c);
	}
}
 
class Node {
 
	char c;
	Node parent;
	Node left;
	Node right;
 
	public Node(char c) {
    	this.c = c;
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
