package mypackage;

import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptionLearning.Practice;

public class Main {

	public static void practice1() {
		int a = 0;
		if (a == 0) {
			System.out.println("if");
			a ++;
		} else if (a == 1) {
			System.out.println("else if");
			a ++;
		} else {
			System.out.println("else");
		}
	}
	
	public static void func1(int[] array) {
		int[] newArray = new int[100];
		for (int i = 0;i < 100;i++) {
			newArray[i] = i;
		}
		array = newArray;
	}
	
	public static void practice2() {
		int[] array = new int[10];
		System.out.println(array.length);
		func1(array);
		System.out.println(array.length);
	}
	
	public static void runTheLatestMethod() {
		Class<Main> classObject = Main.class;
		Method[] methods = classObject.getMethods();
		Pattern p = Pattern.compile("practice*");
		int index = 0;
		Method latestMethod = null;
		for(Method method : methods) {
			Matcher m = p.matcher(method.getName());
			if(m.find()) {
				index ++;
			}
		}
		try {
			latestMethod = classObject.getMethod("practice" + index);
			latestMethod.invoke(classObject);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public static void main(String[] args) {
		runTheLatestMethod();
		
	}
}
