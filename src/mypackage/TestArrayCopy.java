package mypackage;

public class TestArrayCopy {

	public static void main(String[] args) {
		int[] src = {1,2,3,4,5};
		int[] des = new int[10];
		System.arraycopy(src, 2, des, 3, 3);
		for (int i : des) {
			System.out.print(i + " ");
		}
	}
}
