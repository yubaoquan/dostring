package mypackage;

public class Add {

	public void Add() {
		System.out.println("non-constructor");
	}
	public Add(int a) {
		System.out.println("public constructor");
	}
	private Add() {
		System.out.println("private constructor");
	}
	public static boolean solve(int a) {
		int sum = 0;
		for (int i = 1;i < a;i ++){
			sum = 0;
			System.out.print("" + sum);
			for (int j = i;sum < a;j ++) {
				sum += j;
				System.out.print("+" + j);
				if (sum == a) {
					System.out.println("" + "=" + a);
					return true;
				}
			}
			System.out.println();
		}
		return false;
		}
	
	public static void main(String[] args) {
		//System.out.println(Add.solve(32));
		Add a = new Add();
		
	}
}
