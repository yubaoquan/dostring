package typeinfo;

import java.util.Arrays;
import java.util.Scanner;

class Candy {
	static {
		System.out.println("Loading Candy");
	}
}

class Gum {
	static {
		System.out.println("Loading Gum");
	}
}

class Cookie {
	static {
		System.out.println("Loading Cookie");
	}
}

public class SweetShop {

	public static void practice0() {
		System.out.println("inside main");
		new Candy();
		System.out.println("After creating Candy");
		try {
			new Gum();
			Class.forName("typeinfo.Gum");
		} catch(ClassNotFoundException e) {
			//System.out.println("Could not find Gum");
			e.printStackTrace();
		}
		System.out.println("After Class.forName(\"typeinfo.Gum\")");
		new Cookie();
		System.out.println("After creating cookie");
	}
	
	public static void practice1() {
		Scanner sc = new Scanner(System.in);
		System.out.println("which class do you want to init?");
		String name = sc.nextLine();
		try {
			Class.forName(name);
		} catch(ClassNotFoundException e) {
			System.out.println("Could not find " + name);
		}
	}
	
	public static void practice2(Object obj) {
		Class c = obj.getClass();
		System.out.println(c.getName());
		System.out.println(Arrays.toString(c.getFields()));
		do {
			Class up = c.getSuperclass();
			System.out.println(up.getName());
			System.out.println(Arrays.toString(up.getFields()));
			c = up;
		}while(!c.getSimpleName().equals("Object"));
	}
	
	public static void practice3() {
		practice2(new Circle());
	}
	
	public static void practice4() {
		char[] array = new char[2];
		int a = 1;
		Class c = char.class;
		Class cc = array.getClass();
		System.out.println(c.getName() + " " + c.getSuperclass());
		System.out.println(cc.getName() + " " + cc.getSuperclass());
		Class<? extends Number> intClass = int.class;
		intClass = double.class;
		intClass = float.class;
		//intClass = char.class;
	}
	
	private static void privateMethod() {
		System.out.println("this is a private method in SweetShop.java");
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		practice4();
		
	}

}
