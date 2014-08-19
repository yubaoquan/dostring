package mypackage.stringTests;

public class TestStringNStringBuffer {
	public static void main(String[] args) {
		testEquals();
	}

	private static void testEquals() {
		String str1 = "hello";
		String str2 = str1;
		
		StringBuffer sb1 = new StringBuffer("world");
		StringBuffer sb2 = sb1;
		
		boolean flag1 = (str1 == str2);
		boolean flag2 = (sb1 == sb2);
		System.out.println("str1: " + str1);
		System.out.println("str2: " + str2);
		System.out.println("sb1: " + sb1);
		System.out.println("sb2: " + sb2);
		
		System.out.println(flag1 + " | " + flag2 );
		
		str1 += "x";
		sb1.append("x");
		
		System.out.println("str1: " + str1);
		System.out.println("str2: " + str2);
		System.out.println("sb1: " + sb1);
		System.out.println("sb2: " + sb2);
		
		flag1 = (str1 == str2);
		flag2 = (sb1 == sb2);
		System.out.println(flag1 + " | " + flag2 );
		
		//System.out.println("|" + str1 == str2 + "|");
		//System.out.println( "|" + (sb1 == sb2));
	}
	
	int a, _a;
	int $a;

	public static void change(String str) {
		str = "hello";
	}

	public static void changeBuf(StringBuffer sb) {
		sb.append("xxx");
		sb = new StringBuffer("changed");
	}

	public static boolean foo(char c) {
		System.out.print(c);
		return true;
	}

	private static void test1() {
		String str = new String("world");
		change(str);
		System.out.println(str);
		StringBuffer sb = new StringBuffer("yubaoquan");
		changeBuf(sb);
		System.out.println(sb);
		/*
		 * int i = 0; for (foo('A');foo('B') && i < 2;foo('C')) { i ++; foo('D'); } new
		 * ChangeString().change(null);
		 */
	}
}
