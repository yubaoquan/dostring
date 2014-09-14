package package1.multiThread;

public class StaticMethod implements Runnable{
	
	public static void staticMethod() {
		System.out.println("static method invoke");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private int ID;
	private static long[] endTimes = new long[5];
	
	public StaticMethod(int id) {
		this.ID = id;
	}
	
	public static void main(String[] args) {
		long begin = System.currentTimeMillis();
		for (int i = 0; i < 5; i ++) {
			new Thread(new StaticMethod(i)).start();
		}
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long end = getLongestEndTime();
		long period = end - begin;
		System.out.println("totally " + period +" miliseconds.");

	}

	public static long getLongestEndTime() {
		long longestEndTime = endTimes[0];
		for (int i = 1; i < endTimes.length; i ++) {
			if (endTimes[i] > longestEndTime) {
				longestEndTime = endTimes[i];
			}
		}
		return longestEndTime;
	}
	
	@Override
	public void run() {
		System.out.println("Thread " + ID);
		staticMethod();
		System.out.println( ID + " finish");
		endTimes[ID] = System.currentTimeMillis();
		// TODO Auto-generated method stub
		
	}

}
