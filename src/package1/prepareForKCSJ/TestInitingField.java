package package1.prepareForKCSJ;

import java.io.UnsupportedEncodingException;

public class TestInitingField {

	private Tree tree = new Tree(1); 
	{
		System.out.println("dynamic: " + tree.getHeight());
	}
	
	static {
		System.out.println("static block ");
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		test2();
		

	}

	public TestInitingField(int treeHeight) {
		this.tree = new Tree(treeHeight);
	}
	
	private static void test2() {
		TestInitingField test = new TestInitingField(2);
		System.out.println(test.tree);
	}

}
