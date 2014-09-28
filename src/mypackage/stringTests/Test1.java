package mypackage.stringTests;

public class Test1 {

	public static void testIntern() {
		String str1 = "hello world";
		String str2 = str1.intern();
		str2 = str1.replace("l", "t");
		System.out.println(str2);
	}
	
	private static void testNull() {
		String str = null;
		String nullStr = String.valueOf(str);
		System.out.println(nullStr.length());
	}
	public static void main(String[] args) {
		testNull();
	}
}
