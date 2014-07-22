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
//		Test4.testRegex();
	}
	
	public static int[] findSubStrings(String input, String subString) {
		int[] result = new int[input.length()];
		Pattern p = Pattern.compile(subString);
		Matcher m = p.matcher(input);
		int i = 0;
		while (m.find()) {
			int position = m.start();
			result[i ++] = position;
			//System.out.println(position);
		}
		return result;
	}
	
	public static void testFindSubStrings() {
		String input = "abc06139fg1360613998706139125ffkjdf06139fff10613901664";
		String subString = "06139";
		int[] result = findSubStrings(input, subString);
		for (int i = 0; i < result.length; i ++) {
			System.out.println(result[i]);
		}
	}
	
	public static void testRegex() {
		String reg = "^1[0-9]{10}$";
		Pattern p = Pattern.compile(reg);
		String phoneNumber = "10123556789";
		Matcher m = p.matcher(phoneNumber);
		if (m.find()) {
			System.out.println("ok");
		} else {
			System.out.println("not ok");
		}
	}
}
