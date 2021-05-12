package application;

public class SortingClass {

	public static void bubbleSort(int[] vals) {
		boolean changed = true; //false means sorted; true means unsorted;
		while (changed == true) {
			changed = false;
			for (int pos = 1; pos < vals.length; pos++) {
				if (vals[pos] < vals[pos-1]) {
					swap(vals, pos, pos-1);
					changed = true;
				}
			}
			printNumbers(vals);
		}
	}
	
	public static void selectionSort(int[] vals) {
		int indexMin;
		for (int sortedIndex = 0; sortedIndex < vals.length-1; sortedIndex++) {
			indexMin = sortedIndex;
			for (int arrayIndex = sortedIndex; arrayIndex < vals.length; arrayIndex++) {
				if(vals[arrayIndex] < vals[indexMin]) swap(vals, indexMin, arrayIndex);
			}
		}
	}

	public static void insertionSort(int[] vals) {
		int currIndex;
		for (int pos = 1; pos < vals.length; pos++) {
			currIndex = pos - 1;
			while (currIndex >= 0 && vals[currIndex+1] < vals[currIndex]) {
				swap(vals, currIndex, currIndex+1);
				currIndex--;
			}
		}
	}
	
	public static void quickSort() {
		;
	}
	
	public static void mergeSort() {
		;
	}
	
	private static void swap (int[] array, int index1, int index2) {
		int temp;
		temp = array[index1];
		array[index1] = array[index2];
		array[index2] = temp;
	}
	
	private static void printNumbers(int[] input) {
        
        for (int i = 0; i < input.length; i++) {
            System.out.print(input[i] + ", ");
        }
        System.out.println("\n");
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] input = { 4, 2, 9, 6, 23, 12, 34, 0, 1 };
		bubbleSort(input);
//		printNumbers(input);
	}

}
