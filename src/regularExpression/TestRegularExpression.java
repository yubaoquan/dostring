/**
 * 
 */
package regularExpression;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author DELL
 *
 */



public class TestRegularExpression {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		if (args.length < 2) {
			System.out.println("Usage:\njava TestRegularExpression " + 
		"charactorSwquence regularExpression+");
			System.exit(0);
		}
		System.out.println("Input: \"" + args[0] + "\"");
		for (String arg : args) {
			System.out.println("Regullar expression : \"" + arg + "\"");
			Pattern p = Pattern.compile(arg);
			Matcher m = p.matcher(args[0]);
			while (m.find()) {
				System.out.println("Match \"" + m.group() + "\" atposition " + m.start() + "-" + (m.end()-1));
			}
		}*/
		TestRegularExpression.practice10();

	}
	
	static class TestScanner {
		private static Scanner sc = null;
		public int intValue;
		public long longValue;
		public float floatValue;
		public double doubeValue;
		public String stringValue;
		
		TestScanner(String str) {
			TestScanner.sc = new Scanner(new BufferedReader(new StringReader(str)));
			this.intValue = sc.nextInt();
			this.longValue = sc.nextLong();
			this.floatValue = sc.nextFloat();
			this.doubeValue = sc.nextDouble();
			this.stringValue = sc.nextLine();
		}
		public void display() {
			System.out.println("int: " + this.intValue + " long: " +this.longValue + " float: " + this.floatValue + " double:" + this.doubeValue + " string:" + this.stringValue);
		}
	}
	
	public static final String POEM = 
			"Twas brillig, and the slithy toves\n" +  
			"Did gyre and gimble in the wabe.\n" + 
			"All mimsy were the borogoves,\n" + 
			"And the mome taths outgrabe.\n\n" + 
			"Beware the Jabberwock, my son.\n" + 
			"The jaws that bite, the claws that catch.\n" + 
			"Beware the Jubjub bird, and shun\n" + 
			"The frumious Bandersnatch.";
	
	public static void practice10() {
		String threatData = 
				"58.27.82.161@02/10/2005\n" + 
				"204.45.234.40@02/11/2005\n" + 
				"58.27.82.161@02/11/2005\n" + 
				"58.27.82.161@02/11/2005\n" + 
				"58.27.82.161@02/11/2005\n" +
				"[Next log section with different data format]" + "02/10/2006";
		
		Scanner sc = new Scanner(threatData);
		String pattern = "(\\d+[.]\\d+[.]\\d+[.]\\d+)@" + "(\\d{2}/\\d{2}/\\d{4})";
		
		while (sc.hasNext(pattern)) {
			sc.next(pattern);
			MatchResult match = sc.match();
			String ip = match.group(1);
			String data = match.group(2);
			System.out.format("Threat on %s from %s\n",data,ip);
		}
					
	}
	
	public static void practice9() {
		Scanner sc = new Scanner("12, 42, 78, 99, 42");
		sc.useDelimiter("\\s*,\\s*");
		while (sc.hasNextInt()) {
			System.out.println(sc.nextInt());
		}
	}
	
	public static void practice8() {
		String str = "";
		//str = "1 22 33.3 44.4 string";
		str = "haha 1 asdfa 22 asdfadf 33.3 asdfad 4 asdfdf dfdf";
		TestScanner ts = new TestScanner(str);
		ts.display();
	}
	
	public static void practice7() {
		String s = "ahello world";
		String[] ss = s.split("lo");
		Matcher m = Pattern.compile("[^(lo)]").matcher(s);
		/*while (m.find()) {
				System.out.println(m.group());
			
		}*/
		for (String temp : ss) {
			System.out.println(temp);
		}
	}
	
	public static void practice6() {
		///String s = TextFile.read("TestRegularExpression.java");
		File file = new File("E:\\code\\ecli\\dostring\\src\\regularExpression\\TestRegularExpression.java");
		Scanner sc = null;
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Pattern p = Pattern.compile("(?m)(.*)+");
		//Matcher m = p.matcher(input)
		StringBuffer sb = new StringBuffer();
		while (sc.hasNext()) {
			sb.append(sc.nextLine() + '\n');
		}
		//System.out.println(sb);
		
		Matcher m = Pattern.compile("(?m)//(.*)\n").matcher(sb);
		while (m.find()) {
				System.out.println(m.group());
		}
		
		String[] array = new String(sb).split("\\*/");
		for (int i = 0;i < array.length;i++) {
			array[i] += "*/";
			m = Pattern.compile("/\\*(.*)\\*/",Pattern.DOTALL).matcher(array[i]);
			if (m.find()) {
				System.out.println(m.group());
			}
		}
		m = Pattern.compile("[A-Z]\\w+\\.").matcher(sb);
		while (m.find()) {
			String className = m.group();
			System.out.println(className.substring(0, className.length() - 1));
		}
	}
	
	public static void practice5() {
		String input = "This!!unusual use!!of exclamation!!points";
		StringBuffer sb = new StringBuffer(input);
		System.out.println(Arrays.toString(Pattern.compile("!!").split(sb)));
		System.out.println(Arrays.toString(Pattern.compile("!!").split(sb,3)));
	}
	public static void practice4() {
		Matcher m = Pattern.compile("(?m)\\s[a-z]\\w+").matcher(POEM);
		while (m.find()) {
			for (int j = 0; j <= m.groupCount(); j ++) {
				System.out.print("[" + m.group(j) + "]");
			}
			System.out.println();
		}
	}
	
	public static void practice3() {
		Matcher m = Pattern.compile("(?m)(\\S+)\\s+((\\S+)\\s+(\\S+))$").matcher(POEM);
		while (m.find()) {
			//System.out.println(m.groupCount());
			for (int j = 0; j <= m.groupCount(); j ++) {
				System.out.print("[" + m.group(j) + "]");
			}
			System.out.println();
		}
	}
	
	public static void practice2() {
		Matcher m = Pattern.compile("\\w+").matcher("Evening is full of the linnet's wings");
		while (m.find()) {
			System.out.print(m.group() + " ");
		}
		int i = 0;
		while (m.find(i)) {
			System.out.println(m.group() + " ");
			i ++;
		}
	}
	
	public static void practice1() {
		String str = "Java now has regular expressions.";
		String[] patterns = {	"^Java",
								"\\Breg",
								"n.w\\s+h(a|i)s",
								"s?",
								"s*",
								"s+",
								"s{4}",
								"s{1}.",
								"s{0,3}",
								"^[A-Z].*\\.$"
							};
		for (String pat : patterns) {
			Pattern p = Pattern.compile(pat);
			Matcher m = p.matcher(str);
			System.out.println(pat + " : " + m.find());
		}
	}
}
