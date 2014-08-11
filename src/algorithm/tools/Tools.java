package algorithm.tools;

import java.util.Random;

public class Tools {

	/**
	 * 交换数组中两个元素
	 * 
	 * @param array
	 * @param i
	 * @param j
	 */
	public static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	/**
	 * 打印数组内容
	 * 
	 * @param a
	 */
	public static void printArray(int[] a) {
		for (int i : a) {
			System.out.print(i + " ");
		}
		System.out.println();
	}

	/**
	 * 生成length长的整数数组，数组中每个数随机产生
	 * 
	 * @param length
	 * @return
	 */
	public static int[] produceArray(int length) {
		int[] array = new int[length];
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			array[i] = random.nextInt(length);
		}
		return array;
	}
}
