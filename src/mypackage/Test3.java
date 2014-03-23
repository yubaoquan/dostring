package mypackage;

public class Test3 {

	static int a = 0;
	int b = 0;
	//Test3 innerInstance = new Test3();
	static {
		System.out.println("in static block");
		a ++;
	}
	public static void func() {
		System.out.println("static method");
	}
	public Test3() {
		System.out.println("initial method");
	}
	{
		int c = 100;
		System.out.println("in no static block");
		a ++;
	}
	
	public static void main(String[] args) {
		Test3 t3 = new Test3();
		System.out.println(Test3.a);
		Test3 t4 = new Test3();
		System.out.println(Test3.a);
		Test3 t5 = new Test3();
		System.out.println(Test3.a);
		Test3.func();
		System.out.println(t3.b);
	}
}
