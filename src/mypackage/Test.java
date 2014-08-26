package mypackage;

import static java.lang.System.*;

public class Test {
	
	private final int a = 9;

	@SuppressWarnings("finally")
	private static int testFinally() {
		try {
			System.out.println("try");
			return 1;
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			System.out.println("finally");
			return 2;
		}
	}

	private static void test1() {
		String string = "123,234,345,";
		String[] strings = string.split(",");
		System.out.println(strings.length);
		for (String temp : strings) {
			System.out.println(temp);
		}
	}
	
	public static void main(String[] args) {
		out.println(testFinally());
	}

}
