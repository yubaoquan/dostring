package mypackage;

public class Test4 {

	public static void method1() {
		System.out.println("method1");
		method2();
		
	}
	public static void method2() {
		System.out.println("method2");
		//method1();
		new Test4().sayHello(); 
	}
	public void sayHello() {
		System.out.println("hello");
	}
	public static void main(String[] args) {
		Test4.method1();
	}
}
