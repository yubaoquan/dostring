package mypackage;

public class ChangeString {

	int a,_a;
	int $a;
	
	public static void change(String str) {
		str = "hello";
	}
	public static void changeBuf(StringBuffer sb) {
		sb = new StringBuffer("changed");
	}
	public static boolean foo(char c) {
		System.out.print(c);
		return true;
	}
	public static void main(String[] args) {
		String str = new String("world");
		ChangeString.change(str);
		System.out.println(str);
		StringBuffer sb = new StringBuffer("yubaoquan");
		ChangeString.changeBuf(sb);
		System.out.println(sb);
		/*int i = 0;
		for (foo('A');foo('B') && i < 2;foo('C')) {
			i ++;
			foo('D');
		}
		new ChangeString().change(null);*/
	}
 }
