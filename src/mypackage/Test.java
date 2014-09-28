package mypackage;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import static java.lang.System.*;

public class Test {
	private static void test1() {
		String str = "yubaoquannnn?sid=123";
		String str2 = str.replace("n?s\\.d=", "");
		out.println(str2);
	}
	
	private static void test2() {
		String str1 = "yubaoquan";
		String str2 = "baoquanyu";
		String str3 = "baoquan";
		out.println(str1.indexOf(str2));
		out.println(str1.indexOf(str3));
	}
	
	private static String method1() {
		 try {
			 return "in try";
		 } catch (Exception e) {
			 System.out.println("exceptino");
		 } finally {
			 return "in finally";
		 }
	}
	public static void main(String[] args) {
		System.out.println(method1());
	}
}
