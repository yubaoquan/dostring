package mypackage;

import java.util.Arrays;

public class Test12 {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		int[][] array = new int[2][3];
		for (int[] a: array) {
			Arrays.fill(a, 4);
		}
		System.out.println(Arrays.deepToString(array));
	}

}
