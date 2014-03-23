package mypackage;

public class A {

	public static int a = 6;
	public int value;
	public String str = "hello";
	public void sayValue() {
		System.out.println("value: " + value + " str: " + str);
	}
	public static void method(A a) {
		a.value = 100;
		a.str = "byebye";
		a = new A(8);
		
	}
	A(int value) {
		this.value = value;
	}
	public static void main(String[] args) {
		A a = new A(1);
		a.sayValue();
		A.method(a);
		a.sayValue();
		System.out.println(1&0);
		//a.a++;
		//System.out.println(a.a);
	}
}
