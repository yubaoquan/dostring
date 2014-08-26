package algorithm.sort;

import algorithm.tools.Tools;

public class MaopaoNInsert {

	public static int[] maopaoSort(int[] inputArray ) {
		int[] resultArray = new int[inputArray.length];
		for (int i = 0; i < inputArray.length; i ++) {
			boolean swaped = false;
			for (int j = 0; j < inputArray.length - 1 - i; j ++) {
				if (inputArray[j] > inputArray [j + 1]) {
					Tools.swap(inputArray, j, j + 1);
					swaped = true;
				}
			}
			if (!swaped) {
				break;
			}
		}
		System.arraycopy(inputArray, 0, resultArray, 0, inputArray.length);
		return resultArray;
	}
	
	public static int[] insertSort(int[] inputArray) {
		int[] resultArray = new int[inputArray.length];
		resultArray[0] = inputArray[0];
		int sortedNumber = 0;
		int insertPoint = 0;
		
		for (sortedNumber = 1; sortedNumber < inputArray.length; sortedNumber ++) {
			int temp = inputArray[sortedNumber];
			for (insertPoint = 0; temp >= resultArray[insertPoint] && insertPoint < sortedNumber; insertPoint ++) {
				;
			}
			for (int i = sortedNumber; i > insertPoint; i --) {
				resultArray[i] = resultArray[i - 1];
			}
			resultArray[insertPoint] = temp;
		}
		return resultArray;
	}
	
	private static void testSortMethods() {
		int[] array = Tools.produceArray(10);
		Tools.printArray(array);
		array = maopaoSort(array);
//		array = insertSort(array);
		Tools.printArray(array);
	
	}
	
	public static void main(String[] args) {
		testSortMethods();
	}
}
