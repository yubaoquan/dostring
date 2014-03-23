package package3;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test4 {

	public static void main(String[] args) throws Exception {
		test3();

	}

	private static void test4() {
		String str = "yu.bao.quan";
		String[] array = str.split("\\.");
		for (String temp : array) {
			System.out.println(temp);
		}
		str = str.replaceFirst("yu", "123");
		
		System.out.println(str);
	}
	
	private static void test3() {
		String regex = ".*(\\.\\./upFiles/infoImg/\\d*\\.jpg).*";
		Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
		StringBuffer content = new StringBuffer("=xg\n\n\r\tfgn  =../upFiles/infoImg/2014011225050969.jpg\nxbfg a");
		Matcher matcher = pattern.matcher(content);
		if (matcher.matches()) {
			System.out.println(matcher.group(1));
		}
	}

	private static void test2() throws Exception {
		InputStream in = new FileInputStream(new File("testPicture.txt"));
		testCast(in);
	}

	private static void testCast(Object obj) {
		if (obj instanceof InputStream) {
			System.out.println("is");
		} else if (obj instanceof OutputStream) {
			System.out.println("os");
		} else {
			System.out.println("obj");
		}
	}

	private static void test1() {
		String target = "yUdaoquaNyUbaoquaNyubaoquaTL";
		String regular = "(\\w*(.))dao(\\w*).";
		Pattern pattern = null;
		pattern = Pattern.compile(regular);
		Matcher matcher = pattern.matcher(target);

		if (matcher.matches()) {
			String group1 = matcher.group(1);
			String group2 = matcher.group(2);

			System.out.println(group1);
			System.out.println(group2);
		} else {
			System.out.println("not match");
		}
	}
}
