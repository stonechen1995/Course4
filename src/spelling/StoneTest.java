package spelling;

public class StoneTest {

	public static void main(String[] args) {
		
		//example of StoneTree
		StoneTree st;
		StoneTree left = new StoneTree (new Integer(2), null, null); 
		StoneTree right = new StoneTree (new Integer(3), null, null); 
		StoneTree root = new StoneTree (new Integer(1), left, right);
		st = root;
		StoneTree.print(st);
		
		//example of StoneNewTree
		System.out.println("\nTest for StoneNewTree");
		StoneNewTree snt = new StoneNewTree();
		int newRoot = 1;
		snt.setCargo(newRoot, "cargo for root");
		snt.setCargo(snt.left(newRoot),"cargo for left");
		snt.setCargo(snt.right(newRoot),"cargo for right");
		System.out.println("print whole tree:");
		snt.print();
		
		
	}

}
