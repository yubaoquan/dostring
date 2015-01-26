package implenentsAndExtends;

public class A2 extends A1 {

	public static void staticMethod(A1 a1) {
		a1.dosth();
		Math.abs(1.2);
	}
	{
		System.out.println("I am A2");
	}
	static {
		System.out.println("I am static A2");
	}

	public void dosth(int a) {
		return;
	}

	public void dosth() {

	}

	@Override
	public void method2() {
		// TODO Auto-generated method stub

	}

	public int dosth(int a, int b) {
		return 0;
	}

	public static void main(String[] args) {
		new A2();
		Base base = new Son();
        base.method();
        base.methodB();
	}

	@Override
	public int method() {
		// TODO Auto-generated method stub
		return 0;
	}

	class Base {
		public void method() {
			System.out.println("Base");
		}
	}

	class Son extends Base {
		public void method() {
			System.out.println("Son");
		}

		public void methodB() {
			System.out.println("SonB");
		}
	}
}
