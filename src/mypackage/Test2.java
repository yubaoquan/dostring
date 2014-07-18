package mypackage;

import static java.lang.System.out;
import java.util.Arrays;

public abstract class Test2 {
	
	public static void main(String[] args) {
		int[] phoneNumber = {3,1,6,8,6,7,6};
		translatePhoneNumberToEnglishWord(phoneNumber);
	}
	
	private static void translatePhoneNumberToEnglishWord(int[] phoneNumber) {
		int[] total = {0,0,3,3,3,3,3,4,3,4};//how many letters on each button
		//int[] phoneNumber = {3,1,6,8,6,7,6};//telephone number
		int telLength = phoneNumber.length;
		int[] answer = new int[telLength];
		
		char[][] c = {
				{' '},//
				{'-'},//1
				{'a','b','c'},//2
				{'d','e','f'},//3
				{'g','h','i'},//4
				{'j','k','l'},//5
				{'m','n','o'},//6
				{'p','q','r','s'},//7
				{'t','u','v'},//8
				{'w','x','y','z'}//9
			};
		while (true) {
			for (int i = 0; i < telLength; i ++) {
				System.out.print(c[phoneNumber[i]][answer[i]]);
			}
			System.out.println();
			int phoneNumberIndex = telLength - 1;
			while (phoneNumberIndex >= 0) {
				if (answer[phoneNumberIndex] < total[phoneNumber[phoneNumberIndex]] - 1) {
					answer[phoneNumberIndex] ++;
					break;
				} else {
					answer[phoneNumberIndex] = 0;
					phoneNumberIndex --;
				}
			}
			if (phoneNumberIndex < 0) {
				break;
			}
		}
	}
	
	private static void arrayAssignAndPrint() {
		int[][] array = new int[2][3];
		for (int[] a: array) {
			Arrays.fill(a, 4);
		}
		System.out.println(Arrays.deepToString(array));
	}
	
	private static void testArrayCopy() {
		int[] src = {1,2,3,4,5};
		int[] des = new int[10];
		System.arraycopy(src, 2, des, 3, 3);
		for (int i : des) {
			System.out.print(i + " ");
		}
	}
	
	
}
