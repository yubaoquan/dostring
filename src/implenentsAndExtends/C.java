package implenentsAndExtends;

public class C extends B {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		A a = new B();
		if (a instanceof A) {
			System.out.println("A");
		}
		if (a instanceof B) {
			System.out.println("B");
		}
		B b = new B();
		if (b instanceof A) {
			System.out.println("A");
		}
		if (b instanceof B) {
			System.out.println("B");
		}
	}

}
