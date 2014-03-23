package mypackage;

public class Test9 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int[] total = {0,0,3,3,3,3,3,4,3,4};//how many letters on each button
		int[] number = {3,1,6,8,6,7,6};//telephone number
		int telLength = number.length;
		int[] answer = new int[telLength];
		
		char[][] c = {
				{' '},
				{' '},
				{'a','b','c'},
				{'d','e','f'},
				{'g','h','i'},
				{'j','k','l'},
				{'m','n','o'},
				{'p','q','r','s'},
				{'t','u','v'},
				{'w','x','y','z'}
			};
		while (true) {
			for (int i = 0; i < telLength; i ++) {
				System.out.print(c[number[i]][answer[i]]);
			}
			System.out.println();
			int k = telLength - 1;
			while (k >= 0) {
				if (answer[k] < total[number[k]] - 1) {
					answer[k] ++;
					break;
				} else {
					answer[k] = 0;
					k --;
				}
			}
			if (k < 0) {
				break;
			}
		}
	}

}
