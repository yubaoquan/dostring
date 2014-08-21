package algorithm.sort;

import java.util.Random;

public class QuickSort {

	public static final int CUTOFF = 11;

	/**
	 * quick sort algorithm. <br />
	 * 
	 * @param arr
	 *            an array of Comparable items. <br />
	 */
	public static void quicksort(int[] arr) {
		quickSort(arr, 0, arr.length - 1);
	}

	/**
	 * get the median of the left, center and right. <br />
	 * order these and hide the pivot by put it the end of of the array. <br />
	 * 
	 * @param arr
	 *            an array of Comparable items. <br />
	 * @param left
	 *            the most-left index of the subarray. <br />
	 * @param right
	 *            the most-right index of the subarray.<br />
	 * @return T
	 */
	public static int median(int[] arr, int left, int right) {

		int center = (left + right) / 2;

		if (arr[left] > arr[center])
			swap(arr, left, center);
		if (arr[left] > arr[right])
			swap(arr, left, right);
		if (arr[center] > arr[right])
			swap(arr, center, right);

		swap(arr, center, right - 1);
		return arr[right - 1];
	}

	/**
	 * internal method to sort the array with quick sort algorithm. <br />
	 * 
	 * @param arr
	 *            an array of Comparable Items. <br />
	 * @param left
	 *            the left-most index of the subarray. <br />
	 * @param right
	 *            the right-most index of the subarray. <br />
	 */
	private static void quickSort(int[] arr, int left, int right) {
		if (left + CUTOFF <= right) {
			// find the pivot
			int pivot = median(arr, left, right);

			// start partitioning
			int i = left, j = right - 1;
			for (;;) {
				while (arr[++i] < pivot)
					;
				while (arr[--j] > pivot)
					;
				if (i < j)
					swap(arr, i, j);
				else
					break;
			}

			// swap the pivot reference back to the small collection.
			swap(arr, i, right - 1);

			quickSort(arr, left, i - 1); // sort the small collection.
			quickSort(arr, i + 1, right); // sort the large collection.

		} else {
			// if the total number is less than CUTOFF we use insertion sort
			// instead (cause it much more efficient).
			insertionSort(arr, left, right);
		}
	}

	/**
	 * method to sort an subarray from start to end with insertion sort
	 * algorithm. <br />
	 * 
	 * @param arr
	 *            an array of Comparable items. <br />
	 * @param start
	 *            the begining position. <br />
	 * @param end
	 *            the end position. <br />
	 */
	public static void insertionSort(int[] arr, int start, int end) {
		int i;
		for (int j = start + 1; j <= end; j++) {
			int tmp = arr[j];
			for (i = j; i > start && tmp < arr[i - 1]; i--) {
				arr[i] = arr[i - 1];
			}
			arr[i] = tmp;
		}
	}

	public static void quickSortOuter(int[] array, int head, int tail) {
		if (head < tail) {
			int middle = quickSortInner(array, head, tail);
			quickSortOuter(array, head, middle - 1);
			quickSortOuter(array, middle + 1, tail);
		}
	}

	/**
	 * version 1
	 * 
	 * @param array
	 * @param head
	 * @param tail
	 * @return
	 */
	public static int quickSortInner(int[] array, int head, int tail) {
		int middle = (head + tail) / 2;
		// int middle = tail;
		if (array[head] > array[middle]) {
			swap(array, head, middle);
		}
		if (array[head] > array[tail]) {
			swap(array, head, tail);
		}
		if (array[middle] > array[tail]) {
			swap(array, middle, tail);
		}
		swap(array, middle, tail - 1);
		int temp = array[tail - 1];
		int i = head + 1;
		int j = tail - 2;

		while (i < j) {
			while (i < j && array[i] <= temp) {
				i++;
			}
			if (i < j) {
				array[j] = array[i];
				j--;
			}
			while (i < j && array[j] >= temp) {
				j--;
			}
			if (i < j) {
				array[i] = array[j];
				i++;
			}
		}
		array[tail - 1] = array[i];
		array[i] = temp;
		return i;
	}

	/**
	 * version 2
	 * 
	 * @param array
	 * @param head
	 * @param tail
	 * @return
	 */
	public static int quickSortInner2(int[] array, int head, int tail) {
		int temp = array[tail];
		int i = head;
		int j = tail;

		while (i < j) {
			while (i < j && array[i] <= temp) {
				i++;
			}
			while (i < j && array[j] >= temp) {
				j--;
			}
			if (i < j) {
				swap(array, i, j);
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
		for (int i = 0; i < 10; i++) {
			array[i] = (int) (Math.random() * 10);
		}
		return array;
	}

	public static int[] produceArray(int length) {
		int[] array = new int[length];
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			array[i] = random.nextInt(length);
		}
		return array;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// int[] array = getArray();
		int[] array = produceArray(15);
		printArray(array);
		quickSortOuter(array, 0, array.length - 1);
//		quicksort(array);
		printArray(array);

	}

}
