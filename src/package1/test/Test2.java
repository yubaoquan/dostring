package package1.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test2 {

	public static int[] maopaoSort(int[] inputArray ) {
		int[] resultArray = new int[inputArray.length];
		for (int i = 0; i < inputArray.length; i ++) {
			boolean swaped = false;
			for (int j = 0; j < inputArray.length - 1; j ++) {
				if (inputArray[j] > inputArray [j + 1]) {
					swap(inputArray, j, j + 1);
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
	
	private static void swap(int[] array, int indexA, int indexB) {
		int temp = array[indexA];
		array[indexA] = array[indexB];
		array[indexB] = temp;
	}
	
	private static int[] produceRadomArray(int arrayLength) {
		int[] resultArray = new int[arrayLength];
		for (int i = 0; i < arrayLength; i ++) {
			resultArray[i] = (int)(Math.random() * 100);
		}
		return resultArray;
	} 
	
	private static void printArray(int[] inputArray) {
		for (int temp : inputArray) {
			System.out.print(temp + " ");
		}
		System.out.println();
	}
	
	private static void testSortMethods() {
		Class thisClass = Test2.class;
		Method[] methods = thisClass.getDeclaredMethods();
		for (Method method : methods) {
			System.out.println(method.getName());
			if (method.getName().equals("cast")) {
				try {
					method.invoke(null, 2);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		/*
		int[] array = produceRadomArray(10);
		printArray(array);
		//array = maopaoSort(array);
		array = insertSort(array);
		printArray(array);
	*/
	}
	
	/**
	 * 测试将对象转型
	 * @param a
	 */
	private static void cast(int a) {
		Object obj;
		obj = (int) 11;
		System.out.println(obj);
		obj = "中文";
		System.out.println(obj);
		System.out.println(a);
	}
	
	public static void main(String[] args) {
		testSortMethods();
	}
}
