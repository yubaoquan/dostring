package mypackage;

public class Test11 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		test1();

	}
	
	private static void test1() {
		String string = "123,234,345,";
		String[] strings = string.split(",");
		System.out.println(strings.length);
		for (String temp : strings) {
			System.out.println(temp);
		}
	}

}
