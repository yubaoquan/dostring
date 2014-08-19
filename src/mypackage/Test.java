package mypackage;

import java.util.ArrayList;
import static java.lang.System.*;

public class Test {
	
	private final int a = 9;

	@SuppressWarnings("finally")
	public static int func() {
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

	/**
	 * 输入一个字符串,一个List,将字符串中字符的所有组合填入List中
	 * 
	 * @param str 输入的字符串
	 * @param result 作为结果输出的List
	 * @return 同上
	 */
	public static ArrayList<String> getResult(String str,ArrayList<String> result) {
		if (str.length() < 2) {
			String sb = new String(str);
			result.add(sb);
			return result;
		}
		
		String temp = null;
		
		result = getResult(str.substring(1), result);
		int originSize = result.size();
		for (int index = 0; index != originSize; index++) {
			temp = result.get(0);
			Test.insertCharToString(str.charAt(0), temp,result);
			result.remove(0);
		}
		return result;
	}

	private static void insertCharToString(char c,String str,ArrayList<String> result) {
		String temp = null;
		for (int i = 0; i <= str.length(); i ++) {
			temp = str.substring(0,i) + c + str.substring(i);
			//System.out.println(temp);
			result.add(temp);
		}
	}
	public static void main(String[] args) {
//		testPailiezuhe();
		out.println(func());
	}

	private static void testPailiezuhe() {
		String s = "我爱你";// 原字符串
		ArrayList<String> result = new ArrayList<String>();
		result = getResult(s,result);
		for (int i = 0; i < result.size(); i ++) {
			System.out.println(result.get(i));
		}
	}
}
