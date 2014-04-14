package quickSort;

public class QuickSortAgain {

	private static void swap(int[] a, int i, int j) {
		int t = a[i];
		a[i] = a[j];
		a[j] = t;
		return;
	}
	
	public static void sort(int[] array,int head,int tail) {
		if (head < tail) {
			int middle = quickSort(array,head,tail);
			System.out.println("head:" + head + " middle:" + middle + " tail:" + tail);
			printArray(array);
			sort(array,head,middle - 1);
			sort(array,middle + 1,tail);
		}
		
	}
	public static int quickSort(int[] array,int head,int tail) {
		//int middle = (head + tail) / 2;
		int temp = array[head];
		int i = head;
		int j = tail - 1;
		while (true) {
			while (i < j && array[i] <= temp) {
				i ++;
			}
			while (j > i && array[j] >= temp) {
				j --;
			}
			if (i < j) {
				swap(array,i,j);
			} else {
				swap(array,i,head);
				break;
			}
		}
		return i;
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
		int[] array = {2, 8, 0, 5, 0, 2, 3, 2, 0, 3}; 
		System.out.println("before");
		
		printArray(array);
		
		sort(array,0,array.length);
		System.out.println("after");
		printArray(array);

	}

}
