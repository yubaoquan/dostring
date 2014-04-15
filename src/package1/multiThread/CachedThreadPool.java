package package1.multiThread;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPool {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i ++) {
			exec.execute(new LiftOff());
		}
		exec.shutdown();
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		//Runtime.getRuntime().exec("cmd /c start cls ");
		System.out.println("clear" + str);

	}

}
