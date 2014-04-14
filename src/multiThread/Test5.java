package multiThread;

public class Test5 implements Runnable{

	public static int j;
	@Override
	public void run() {
		while(true) {
			Test5.j ++;
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public static void main(String[] args) {
		new Thread(new Test5()).start();
		new Test6().start();
	}
}

class Test6 extends Thread{
	public void run() {
		while(true) {
			Test5.j --;
			System.out.println(Test5.j);
			try {
				this.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}