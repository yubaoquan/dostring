package package1.test;

import java.util.Scanner;

public class TestClearCMD {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String input = "";
		while (!input.equalsIgnoreCase("exit")) {
			System.out.print("input:");
			input = sc.nextLine();
			System.out.println(input);
		}
		
	}

}
