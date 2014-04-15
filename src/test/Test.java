package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.*;

public class Test {

	public static void main(String[] args) throws Exception {
		testConcurrent();
	}

	private static void testConcurrent() {
    	Test test = new Test();   
        // 根据类名得到实现Runnable接口的对象            
        Runnable rb = test.getRunnable("Plus");   
        //并发执行   
        test.runInGroup(rb,4000);   //启动4000个线程执行同一任务
    }
	
	/**  
     * 将同一个Runnable对象产生多个线程，并发执行  
     * @param rb Runnable对象  
     * @param num 线程的数量  
     */  
    public void runInGroup(Runnable rb,int num){   
        ThreadGroup thg = new ThreadGroup("TestGroup");   
        for(int i=0;i< num;i++){   
            Thread th = new Thread(thg,new Test().getRunnable("Plus"));   
            th.start();
        }   
    }   
    
    /**  
     * 根据类的名字获得线程实例  
     * @param className  
     * @return 线程对象，如发生异常，则返回null  
     */  
    public Runnable getRunnable(String className){   
        className = "test."+className;   
        try {   
            Runnable rb = (Runnable)Class.forName(className).newInstance();   
            return rb;   
        } catch (InstantiationException e) {   
            e.printStackTrace();   
        } catch (IllegalAccessException e) {   
            e.printStackTrace();   
        } catch (ClassNotFoundException e) {   
            e.printStackTrace();   
        }   
           
        return null;   
    }   
    
	@SuppressWarnings("unused")
	private static void testParallelStream() {
		long t0 = System.nanoTime();
		// 初始化一个范围100万整数流,求能被2整除的数字，toArray（）是终点方法
		int a[] = IntStream.range(0, 1_000_000).filter(p -> p % 2 == 0).toArray();
		long t1 = System.nanoTime();
		// 和上面功能一样，这里是用并行流来计算
		int b[] = IntStream.range(0, 1_000_000).parallel().filter(p -> p % 2 == 0).toArray();
		long t2 = System.nanoTime();
		// 我本机的结果是serial: 0.06s, parallel 0.02s，证明并行流确实比顺序流快
		System.out.printf("serial: %.2fs, parallel %.2fs%n", (t1 - t0) * 1e-9, (t2 - t1) * 1e-9);
	}

	private static void test1(String[] args) throws Exception {
		if (args.length == 0) {
			throw new Exception("this is the exception !");
		} else {
			System.out.println("there is no exception ,the argument is " + args[0] + ".");
			while (true) {
				Thread.sleep(5000);
			}
		}
	}

	private static void testList() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(new Integer(1));
		list.add(new Integer(1));
		list.add(new Integer(1));

		for (Iterator<Integer> iter = list.iterator(); iter.hasNext();) {
			System.out.println(iter.next());
		}
	}

	private static void testSwitchString(String string) {
		switch (string) {
			case "hello":
				break;
			case "world":
				break;
			default:
				break;
		}
	}

	private static void testIfConditionOrder() {
		List<String> strings = new ArrayList<String>();
		strings = returnNullListOrNot(0);
		if (strings.size() > 0 && strings != null) {
			// if (strings != null && strings.size() >0) {
			System.out.println("ok");
		} else {
			System.out.println("not ok");
		}
	}

	private static List<String> returnNullListOrNot(int operator) {
		switch (operator) {
			case 0:
				return null;
			case 1:
				return new ArrayList<String>();
			default:
				return null;
		}
	}

	private static void testPutListIntoMap() {
		Map<String, List<String>> map = new HashMap<>();
		List<String> list = new ArrayList<>();
		map.put("list", list);
		System.out.println(map.get("list").size());
		list.add("hello");
		System.out.println(map.get("list").size());
		list = null;
		System.out.println(map.get("list").size());
	}

	private static void findSSOID(String response) {
		String ssotitle = "SSOSID:";
		int index = response.indexOf(ssotitle);
		String sid = response.substring(index + ssotitle.length());
		System.out.println(sid);
	}

	private static void testSubString() {
		String string = "1 username password";
		int index = string.indexOf(" ");
		String leftString = string.substring(0, index);
		String rightString = string.substring(index);
		System.out.println(leftString);
		System.out.println(rightString);
	}

}
