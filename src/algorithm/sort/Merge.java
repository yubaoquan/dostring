package algorithm.sort;

import static java.lang.System.out;

import java.util.Arrays;

public class Merge {

	private static Integer[] tempArray;
	/**
	 * 我写的归并算法
	 * @param array 待排序的数组
	 * @param begin 待排序的开始位置
	 * @param end 待排序的结束位置
	 */
	@SuppressWarnings("unused")
	private static void mergeSort(Integer[] array, int begin, int end) {
		int length = end - begin;
		if (length > 1) {
			int middle = (begin + end) / 2;
			mergeSort(array, begin, middle);
			mergeSort(array, middle, end);
			int leftIndex = begin;
			int rightIndex = middle;
			int tempIndex = begin;
			while (leftIndex < middle && rightIndex < end) {
				int a = array[leftIndex];
				int b = array[rightIndex];
				if (a < b) {
					tempArray[tempIndex] = a;
					leftIndex++;
				} else {
					tempArray[tempIndex] = b;
					rightIndex++;
				}
				tempIndex ++;
			}
			while (leftIndex < middle) {
				tempArray[tempIndex ++] = array[leftIndex ++];
			}
			while (rightIndex < end) {
				tempArray[tempIndex ++] = array[rightIndex ++];
			}
			System.arraycopy(tempArray, begin, array, begin, length);
		}
	}

	public static void sort(Object[] arr) {
		// 创建临时数组
		Object[] tempArr = arr.clone();
		msort(arr, tempArr, 0, arr.length);
	}

	public static <T extends Comparable<? super T>> void sort(T[] arr) {
		// 创建临时数组
		T[] tempArr = (T[]) arr.clone();
		msort(arr, tempArr, 0, arr.length);
	}

	/**
	 * 归并排序
	 * 网站上标准代码
	 * @param arr 待排序数组
	 * @param tempArr 中间数组
	 * @param first 待排序的开始位置
	 * @param last 待排序的结束位置
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static void msort(Object[] arr, Object[] tempArr, int first, int last) {
		if (first + 1 < last) {

			int midpt = (last + first) / 2;
			msort(arr, tempArr, first, midpt);
			msort(arr, tempArr, midpt, last);

			if (((Comparable) arr[midpt - 1]).compareTo(arr[midpt]) <= 0)
				return;

			int indexA, indexB, indexC;
			indexA = first;
			indexB = midpt;
			indexC = first;
			while (indexA < midpt && indexB < last) {
				if (((Comparable) arr[indexA]).compareTo(arr[indexB]) < 0) {
					tempArr[indexC] = arr[indexA]; // copyto tempArr
					indexA++;
				} else {
					tempArr[indexC] = arr[indexB]; // copyto tempArr
					indexB++;
				}
				indexC++;
			}
			// copy the tail of the sublist that is not exhausted
			while (indexA < midpt) {
				tempArr[indexC++] = arr[indexA++];
			}
			while (indexB < last) {
				tempArr[indexC++] = arr[indexB++];
			}

			for (int i = first; i < last; i++)
				arr[i] = tempArr[i];
		}
	}
	
	/**
	 * 归并算法的测试方法
	 */
	private static void testMergeSort() {
		int arraySize = 10;
		// int[] array = new int[arraySize];
		// tempArray = new int[arraySize];
		Integer[] array = new Integer[arraySize];
		tempArray = new Integer[arraySize];

		// Random random = new Random(47);
		for (int i = 0; i < array.length; i++) {
			array[i] = (int) (Math.random() * 10);
		}
		out.println(Arrays.toString(array));
		mergeSort(array, 0, array.length);
		// msort(array, tempArray, 0, arraySize);
		out.println(Arrays.toString(tempArray));
	}
}
