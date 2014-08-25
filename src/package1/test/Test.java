package package1.test;

import static java.lang.System.out;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Test {

	public static void main(String[] args) throws Exception {
		testConcurrent();
	}

	@SuppressWarnings("unused")
	private static void testConcurrent() {
		Test test = new Test();
		// 根据类名得到实现Runnable接口的对象
		Runnable rb = test.getRunnable("Plus");
		// 并发执行
		test.runInGroup(rb, 4000); // 启动4000个线程执行同一任务
	}

	/**
	 * 将同一个Runnable对象产生多个线程，并发执行
	 * 
	 * @param rb
	 *            Runnable对象
	 * @param num
	 *            线程的数量
	 */
	public void runInGroup(Runnable rb, int num) {
		ThreadGroup thg = new ThreadGroup("TestGroup");
		for (int i = 0; i < num; i++) {
			Thread th = new Thread(thg, new Test().getRunnable("Plus"));
			th.start();
		}
	}

	/**
	 * 根据类的名字获得线程实例
	 * 
	 * @param className
	 * @return 线程对象，如发生异常，则返回null
	 */
	public Runnable getRunnable(String className) {
		className = "test." + className;
		try {
			Runnable rb = (Runnable) Class.forName(className).newInstance();
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
	private static void testPutListIntoMap() {
		Map<String, List<String>> map = new HashMap<>();
		List<String> list = new ArrayList<>();
		map.put("list", list);
		System.out.println(map.get("list").size());
		list.add("hello");
		System.out.println(map.get("list").size());
		list = null;
		System.out.println(map.get("list").size()); // newxxx
	}

	/**
	 * 画菱形
	 */
	@SuppressWarnings("unused")
	private static void drawDiamond() {
		int height = 9;
		int width = 9;
		int middle = width / 2;
		int[] array = new int[width];
		Arrays.fill(array, 0);

		for (int i = 0; i < height; i++) {
			Arrays.fill(array, 0);
			if (i < 5) {
				for (int x = middle - i; x <= middle + i; x++) {
					array[x] = 1;
				}
			} else {
				for (int x = i - middle; x < width + middle - i; x++) {
					array[x] = 1;
				}
			}
			for (int j = 0; j < width; j++) {
				if (array[j] == 0) {
					out.print("+");
				} else {
					out.print("=");
				}
			}
			out.println();
		}
	}

	/**
	 * fillOrCleanSeats()方法的测试方法
	 */
	@SuppressWarnings({ "unused", "resource" })
	public static void test3667() {
		Scanner sc = new Scanner(System.in);
		int seatAmount = 10;// sc.nextInt();
		int operationTimes = 6;// sc.nextInt();
		int[][] params = { { 1, 3 }, { 1, 3 }, { 1, 3 }, { 1, 3 }, { 2, 5 }, { 1, 6 } };
		out.println(seatAmount + " " + operationTimes);

		int[] seatArray = new int[seatAmount];
		Arrays.fill(seatArray, 0);
		for (int i = 0; i < operationTimes; i++) {
			int operaterType = params[i][0] - 1;// sc.nextInt() - 1;
			int singleOperateTime = params[i][1];// sc.nextInt();
			fillOrCleanSeats(seatArray, singleOperateTime, operaterType);
		}

	}

	/**
	 * 填充或清空子序列
	 * 
	 * @param seatArray
	 *            输入序列
	 * @param amount
	 *            需要填充或清空的数量
	 * @param operator
	 *            1：清空，2：填充
	 */
	private static void fillOrCleanSeats(int[] seatArray, int amount, int operator) {
		int count = 0;
		for (int i = 0; i < seatArray.length; i++) {
			if (seatArray[i] == operator) {
				count++;
				if (count == amount) {
					out.println("found " + (i - count + 1));
					for (int j = 0; j < amount; j++) {
						seatArray[i - j] = 1 - operator;
					}
					out.println(Arrays.toString(seatArray));
					break;
				}
			} else {
				count = 0;
			}

		}
	}

	private static void testLongestSubSequence() {
		int arraySize = 10;
		Random random = new Random();
		// Integer[] array;// = new Integer[arraySize];
		/*
		 * for (int i = 0; i < array.length; i++) { array[i] = (int) (Math.random() * 10); if
		 * (random.nextBoolean()) { array[i] *= -1; } }
		 */
		Integer[] array = { -2, 11, -4, 13, -5, -2 };// {1, 2, -1, 1, 3, 2, -2, 3, -1, 5, -7, 3, 2,
														// -2, -1};
		out.println(Arrays.toString(array));
		int[] result = longestSubSequence(array);
		out.println(Arrays.toString(result));
	}

	/**
	 * 寻找数组中最大和的子序列
	 * 
	 * @param array
	 * @return
	 */
	private static int[] longestSubSequence(Integer[] array) {
		int maxSum = 0;
		int maxSumHere = 0;
		int start = 0;
		int end = 0;
		int startHere = 0;
		int endHere = 0;

		for (int i = 0; i < array.length; i++) {
			if (maxSumHere + array[i] >= 0) {
				maxSumHere = maxSumHere + array[i];
				endHere = i;
			} else {
				maxSumHere = 0;
				startHere = i + 1;
				endHere = startHere;
			}
			if (maxSumHere > maxSum
					|| ((endHere - startHere > end - start) && maxSumHere == maxSum)) {
				start = startHere;
				end = endHere;
				maxSum = maxSumHere;
			}
		}
		return new int[] { start, end, maxSum };
	}
	
	@SuppressWarnings("finally")
	private static int testFinally() {
		try {
			System.out.println("try");
			return 1;
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			System.out.println("finally");
			return 2;
		}
	}

}
