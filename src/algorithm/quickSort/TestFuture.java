package algorithm.quickSort;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestFuture implements Callable<Integer>{

	public static ExecutorService exec = Executors.newCachedThreadPool();
	private int a,b;
	public TestFuture(int a,int b) {
		this.a = a;
		this.b = b;
	}
	@Override
	public Integer call() throws Exception {
		// TODO Auto-generated method stub
		return this.a + this.b;
	}

	
	
	public static void main(String[] args) {
		TestFuture tf = new TestFuture(1,2);
		Future<Integer> future = exec.submit(tf);
		try {
			System.out.println(future.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
