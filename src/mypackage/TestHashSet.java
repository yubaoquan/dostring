package mypackage;

import java.util.HashSet;
import java.util.Iterator;

public class TestHashSet {

	public static HashSet<String> hs = new HashSet<String>();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String str1 = "hello";
		String str2 = "hello";
		System.out.println(hs.add(str1));
		System.out.println(hs.add(str2));
		hs.add("world");
		Iterator<String> it = hs.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
		
	}

}
