package spelling;

public class StoneTree {
	Object obj;
	StoneTree left, right;
	
	// constructor
	public StoneTree(Object cargo, StoneTree left, StoneTree right) {
		this.obj = cargo;
		this.left = left;
		this.right = right;
	}
    public static int total (StoneTree tree) {
    	if (tree == null) return 0;
    	if (!(tree.obj instanceof Integer)) throw new IllegalArgumentException();
    	
    	int cargo = (Integer) tree.obj;
    	return cargo + total(tree.left) + total(tree.right);
    }
    
    public static void print(StoneTree tree) {
		if (tree == null) return;		
		System.out.println(tree.obj.toString() + " ");
		print(tree.left);
		print(tree.right);
	}
	
	public static void printPostorder (StoneTree tree) { 
        if (tree == null) return; 
        printPostorder (tree.left); 
        printPostorder (tree.right); 
		System.out.println(tree.obj.toString() + " ");
    } 
	
	public static void printInorder (StoneTree tree) { 
        if (tree == null) return; 
        printInorder (tree.left); 
		System.out.println(tree.obj.toString() + " ");
        printInorder (tree.right); 
    }

	
	
}