package spelling;

import java.util.Vector;

public class StoneNewTree {
	
	Vector<Object> array;
	
	public StoneNewTree() {
		array = new Vector<>(10);
	}
	
	//define parent & children
	public int left (int i) {
		return 2*i;
	} 
    public int right (int i) {
    	return 2*i + 1;
    } 
    public int parent (int i) {
    	return i/2;
    } 
	
	
	public boolean isEmpty() {
		return (array.get(1) == null);
	}
	
	public Object getCargo(int i) {
		if (i < 0 || i > array.size()) return null;
		return array.get(i);
	}
	
	public void setCargo(int i, Object obj) {
		array.setSize(7);
//		System.out.println("size: " + array.size());
//		System.out.println("capacity: " + array.capacity());
		if (i < 0 || i > array.capacity()) {
			throw new ArrayIndexOutOfBoundsException("out of capacity");
		}
		array.add(i, obj);
	}

	public void print(int i) {
		if (getCargo(i) == null) {
			return;
		}
		System.out.println(getCargo(i));
		print(left(i));
		print(right(i));
	}
	
	public void print() {
		if (getRoot() == -1) {
			System.out.println("null root");
			return;
		}
		print(getRoot());
	}
	
	private int getRoot() {
		for (int i = 0; i < array.size(); i++) {
			if (getCargo(i) != null) return i;
		}
		return -1;
	}
	
	public boolean find(String str) {
		str.compareToIgnoreCase(str);
		return false;
	}
	
}
