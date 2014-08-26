package algorithm.sort;

import static java.lang.System.*;
import algorithm.tools.Tools;

public class JiOuSort {

	public static void sort(int[] array) {
		boolean swaped = false;
		while (true) {
			swaped = false;
			for (int i = 0; i + 1< array.length; i += 2) {
				if (array[i] > array[i + 1]) {
					Tools.swap(array, i, i + 1);
					swaped = true;
				}
			}
			for (int i = 1; i + 1 < array.length; i += 2) {
				if (array[i] > array[i + 1]) {
					Tools.swap(array, i, i + 1);
					swaped = true;
				}
			}
			if (!swaped) {
				break;
			}
		}
	}
	
	private static void testJOSort(int arraySize) {
		int[] array = Tools.produceArray(arraySize);
		Tools.printArray(array);
		sort(array);
		Tools.printArray(array);
	}
	
	private static void testJOSort() {
		for (int i = 0; i < 5; i ++) {
			out.println("-----------------------");
			testJOSort(i + 8);
		}
	}
	
	public static void main(String[] args) {
		testJOSort();

	}

}
