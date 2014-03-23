package prepareForKCSJ;

import java.io.UnsupportedEncodingException;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String str = "=?GB2312?B?Q2xvdWQgRm91bmRyedbQufrIutOiu+GhqmlQYWQgQWlyoaK7+tC1vPzFzLXIt+G68cDxxre1yMTj wLTEw6Oh?=";
		try {
			String encoding = "GB2312";
			byte[] bytes = str.getBytes(encoding);
			String s = new String(bytes,encoding);
			System.out.println(s);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] array = str.split("\\?");
		

	}

}
