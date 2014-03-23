package mypackage;

public abstract class Test2 {
	private String str;
	public void setStr(String str) {
		this.str = str;
	}
	public String getStr() {
		return this.str;
	}
	public void printStr(String str) {
		System.out.println(str);
	}
	public void printInt(int a) {
		System.out.println(a);
	}
	public static void main(String[] args) {
		System.out.println(args.length);
		if (args.length != 0) {
			for (String str : args) {
				System.out.print(str + " ");
				
			}
		}
	}
}
