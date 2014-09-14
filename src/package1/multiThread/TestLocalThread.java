package package1.multiThread;

import java.util.ArrayList;

public class TestLocalThread {

	private ThreadLocal<Integer> myTl;
	public TestLocalThread(ThreadLocal<Integer> myTl) {
		super();
		this.myTl = myTl;
	}
	 
	private static void testAddToArrayList() {
		ArrayList<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(0, 5);
		System.out.println(list.get(0));
	}
	public static void main(String[] args) {
		testAddToArrayList();
//		testLocalThread();
		
	}

	private static void testLocalThread() {
		ThreadLocal<Integer> tl = new ThreadLocal<>();
		tl.set(2);
		TestLocalThread tlt = new TestLocalThread(tl);
		System.out.println(tlt.myTl.get());
	}
}
