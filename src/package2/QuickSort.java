package package2;

public class QuickSort {

	public static void quickSortOuter(int[] array,int head,int tail) {
		if (head < tail) {
			int middle = quickSortInner2(array,head,tail);
			quickSortOuter(array,head,middle - 1);
			quickSortOuter(array,middle + 1,tail);
		}
	}
	
	/**
	 * version 1
	 * @param array
	 * @param head
	 * @param tail
	 * @return
	 */
	public static int quickSortInner(int[] array,int head,int tail) {
		int temp = array[tail];
		int i = head;
		int j = tail;
		
		while (i < j) {
			while (i < j && array[i] <= temp) {
				i ++;
			}
			if (i < j) {
				array[j] = array[i];
				j --;
			}
			while (i < j && array[j] >= temp) {
				j --;
			}
			if (i < j) {
				array[i] = array[j];
				i ++;
			}
		}
		array[i] = temp;
		return i;
	}
	
	/**
	 * version 2
	 * @param array
	 * @param head
	 * @param tail
	 * @return
	 */
	public static int quickSortInner2(int[] array,int head,int tail) {
		int temp = array[tail];
		int i = head;
		int j = tail;
		
		while (i < j) {
			while (i < j && array[i] <= temp) {
				i ++;
			}
			while (i < j && array[j] >= temp) {
				j --;
			}
			if (i < j) {
				swap(array,i,j);
			} else {
				break;
			}
		}
		array[tail] = array[i];
		array[i] = temp;
		return i;
	}
	
	private static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
		
	}
	public static void printArray(int[] a) {
		for (int i : a) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
	
	public static int[] getArray() {
		int[] array = new int[10];
		for (int i = 0;i < 10;i++) {
			array[i] = (int)(Math.random() * 10);
		}
		return array;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//int[] array = getArray();
		int[] array = {8 ,0 ,9 ,6 ,0 ,7 ,0 ,2 ,3 ,6 };
		printArray(array);
		quickSortOuter(array,0,array.length - 1);
		printArray(array);

	}

}
