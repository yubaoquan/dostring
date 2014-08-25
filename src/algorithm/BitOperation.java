package algorithm;

import static java.lang.System.*;

public class BitOperation {

	private static String operate(int hexNum, int x, int y) {
		Integer hex = Integer.parseInt(String.valueOf(hexNum), 16);
		out.println(hex);
		int bit = 1;
		bit <<= x;
		hex &= ~bit;
		
		hex &= ~(0x1 << y - 2);	//	0
		hex |= 0x1 << y - 1;			//	1
		hex |= 0x1 << y;				//	1
		
		out.println("hex: " + hex);
		return Integer.toHexString(hex);
	}
	public static void main(String[] args) {
		out.println(operate(12345678, 0, 3));

	}

}
