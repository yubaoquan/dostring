package mypackage;

public class TestStringNStringBuffer {
	public static void main(String[] args) {
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
}
