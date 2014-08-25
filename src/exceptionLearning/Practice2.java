package exceptionLearning;

public class Practice2 {

	@SuppressWarnings("unused")
	private static void test1(String[] args) throws Exception {
		if (args.length == 0) {
			throw new Exception("this is the exception !");
		} else {
			System.out.println("there is no exception ,the argument is " + args[0] + ".");
				Thread.sleep(1500);
		}
	}
	
	public static void main(String[] args) {
		try {
			test1(new String[]{});
			test1(new String[]{""});
			test1(new String[]{"hello"});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
