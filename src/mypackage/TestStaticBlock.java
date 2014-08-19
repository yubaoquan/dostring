package mypackage;

public class TestStaticBlock {

	static int a = 0;
	int b = 0;
	// Test3 innerInstance = new Test3();
	static {
		System.out.println("in static block");
		a++;
	}

	public static void func() {
		System.out.println("static method");
	}

	public TestStaticBlock() {
		System.out.println("initial method");
	}

	{
		int c = 100;
		System.out.println("in no static block");
		a++;
		b = 0;
	}

	public static void main(String[] args) {
		TestStaticBlock t3 = new TestStaticBlock();
		System.out.println(TestStaticBlock.a);
		TestStaticBlock t4 = new TestStaticBlock();
		System.out.println(TestStaticBlock.a);
		TestStaticBlock t5 = new TestStaticBlock();
		System.out.println(TestStaticBlock.a);
		TestStaticBlock.func();
		System.out.println(t3.b);
	}
}
