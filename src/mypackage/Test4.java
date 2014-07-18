package mypackage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test4 {

	public static void method1() {
		System.out.println("method1");
		method2();
		
	}
	public static void method2() {
		System.out.println("method2");
		//method1();
		new Test4().sayHello(); 
	}
	public void sayHello() {
		System.out.println("hello");
	}
	public static void main(String[] args) {
//		Test4.method1();
		testFindSubStrings();
	}
	
	public static int[] findSubStrings(String input, String subString) {
		int[] result = new int[input.length()];
		Pattern p = Pattern.compile(subString);
		Matcher m = p.matcher(input);
		int i = 0;
		while (m.find()) {
			int position = m.start();
			result[i ++] = position;
			System.out.println(position);
		}
		return result;
	}
	
	public static void testFindSubStrings() {
		String input = "yuabcbaoabcquanabc";
		String subString = "abc";
		findSubStrings(input, subString);
	}
}
