package exceptionLearning;

import implenentsAndExtends.SweetShop;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Practice {

	/**
	 * @param args
	 */
	public static void practice1() {
		try {
			throw new Exception("yubaoquan exception");
		} catch (Exception e){
			System.err.println(e.getMessage());
		} finally {
			System.out.println("finally block");
		}
	}
	
	public static void practice2() {
		String nullString = null;
		try {
			System.out.println(nullString.length());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("exception:" + e.getMessage());
		} finally {
			
		}
	}
	public static void practice15() {
		System.out.println("invoke method 15 succeed!");
	}
	
	static class VeryImportantException extends Exception {
		@Override
		public String toString() {
			return "a very important exception";
		}
	}
	
	static class HoHumException extends Exception {
		@Override
		public String toString() {
			return "a trivial exception";
		}
	}
	
	public static void practice3() {
		int[] array = {1,2,3};
		try {
			System.out.println(array[3]);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("caught the exception!");
		} finally {
			
		}
	}
	
	static class MyException extends Exception {
		public String name;
		public MyException(){}
		MyException(String msg) {
			super(msg);
			this.name = msg;
		}
	}
	
	public static void practice4() {
		try {
			throw new MyException("myexceptino hahahaa");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void practice5() {
		String str = "abc";
		while(true) {
			try {
				System.out.println(str.charAt(10));
				return;
			} catch (Exception e) {
				System.out.println("exception");
				str += "w";
			}
		}
		
	}
	
	static class LoggingException extends Exception {
		private static Logger logger = Logger.getLogger("LoggingException");
		public LoggingException() {
			StringWriter trace = new StringWriter();
			printStackTrace(new PrintWriter(trace));
			logger.severe(trace.toString());
		}
	}
	
	public static void practice6() {
		try {
			throw new LoggingException();
		} catch(LoggingException e) {
			System.out.println("Caught:" + e);
		}
		
		try {
			throw new LoggingException();
		} catch(LoggingException e) {
			System.out.println("Caught:" + e);
			
		}
	}
	
	static class LostMessage {
		void f() throws VeryImportantException{
			throw new VeryImportantException();
		}
		
		void dispose() throws HoHumException {
			throw new HoHumException();
		}
		
		void third() throws MyException{
			throw new MyException("3rd exception");
		}
		
		public LostMessage(){}
		
		public LostMessage(int number) throws Exception {
			if(number == 1) {
				throw new Exception("exception in constructor");
			}
			
		}
	}
	
	public static void practice7() {
		try {
			LostMessage lm = new LostMessage();
			try {
				lm.f();
			} catch(Exception e) {
				System.out.println(e);
			} finally {
				try {
					lm.dispose();
				}  catch(Exception e) {
					System.out.println(e);
				} finally {
					lm.third();
				}
			}
		} catch(Exception e) {
			System.out.println(e);
		} 
	}
	
	public static void practice8() throws Exception{
		try {
			throw new FileNotFoundException();
		} catch(FileNotFoundException e) {
			System.out.println("catch 1");
			throw e;
		} catch(Exception e) {
			System.out.println("catch 2");
		}
	}
	
	public static void practice9() {
		try {
			practice8();
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public static void practice10() {
		LostMessage lm1 = null;
		LostMessage lm2 = null;
		try {
			lm1 = new LostMessage(2);
			lm2 = new LostMessage(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("" + lm1 + " " + lm2);
		}
	}
	
	public static void practice11() {
		int T = 0;
	}
	
	public static void practice12() {
		System.out.println("this is practice 14");
	}
	
	public static class MyComparator implements Comparator<String> {

		@Override
		public int compare(String arg0, String arg1) {
			int firstNumber = Integer.parseInt(arg0.replace("practice", ""));
			int secondNumber = Integer.parseInt(arg1.replace("practice", ""));
			if (firstNumber > secondNumber) {
				return 1;
			} else if (firstNumber == secondNumber) {
				return 0;
			} else {
				return -1;
			}
			
		}

		@Override
		public Comparator<String> reversed() {
			return null;
		}

		@Override
		public Comparator<String> thenComparing(Comparator<? super String> other) {
			return null;
		}

		@Override
		public <U> Comparator<String> thenComparing(Function<? super String, ? extends U> keyExtractor, Comparator<? super U> keyComparator) {
			return null;
		}

		@Override
		public <U extends Comparable<? super U>> Comparator<String> thenComparing(Function<? super String, ? extends U> keyExtractor) {
			return null;
		}

		@Override
		public Comparator<String> thenComparingInt(ToIntFunction<? super String> keyExtractor) {
			return null;
		}

		@Override
		public Comparator<String> thenComparingLong(ToLongFunction<? super String> keyExtractor) {
			return null;
		}

		@Override
		public Comparator<String> thenComparingDouble(ToDoubleFunction<? super String> keyExtractor) {
			return null;
		}

		
		
	}
	
	public static void runTheLatestMethod() {
		Method[] methods = Practice.class.getMethods();
		Pattern p = Pattern.compile("practice*");
		int index = 0;
		Method latestMethod = null;
		for(Method method : methods) {
			Matcher m = p.matcher(method.getName());
			if(m.find()) {
				index ++;
			}
		}
		try {
			latestMethod = Practice.class.getMethod("practice" + index);
			latestMethod.invoke(Practice.class);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	private static void runMethodN(int n) {
		Method latestMethod = null;
		try {
			latestMethod = Practice.class.getMethod("practice" + n);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		try {
			latestMethod.invoke(Practice.class);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	public static void practice13() {
		MyComparator mc = new MyComparator();
		System.out.println(mc.compare("practice1", "practice2"));
		System.out.println(mc.compare("practice11", "practice2"));
		System.out.println(mc.compare("practice2", "practice2"));
	}
	
	public static void practice14() {
		SweetShop ss = new SweetShop();
		try {
			Method g = ss.getClass().getDeclaredMethod("privateMethod");
			g.setAccessible(true);
			try {
				g.invoke(ss);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		runMethodN(10);
		
	}
}
