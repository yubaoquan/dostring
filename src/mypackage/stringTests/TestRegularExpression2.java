package mypackage.stringTests;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRegularExpression2 {

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
	
	private static void test1() {
		String target = "yUdaoquaNyUbaoquaNyubaoquaTL";
		String regular = "(\\w*(.))dao(\\w*).";
		Pattern pattern = null;
		pattern = Pattern.compile(regular);
		Matcher matcher = pattern.matcher(target);

		if (matcher.matches()) {
			String group1 = matcher.group(1);
			String group2 = matcher.group(2);
			String group3 = matcher.group(3);
			System.out.println(group1);
			System.out.println(group2);
			System.out.println(group3);
		} else {
			System.out.println("not match");
		}
	}

	private static void test3() {
		String regex = ".*(\\.\\./upFiles/infoImg/\\d*\\.jpg).*";
		Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
		StringBuffer content = new StringBuffer(
				"=xg\n\n\r\tfgn  =../upFiles/infoImg/2014011225050969.jpg\nxbfg a");
		Matcher matcher = pattern.matcher(content);
		if (matcher.matches()) {
			System.out.println(matcher.group(1));
		}
	}
}
