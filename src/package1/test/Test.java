package package1.test;

import static java.lang.System.out;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Test {
	private static Integer[] tempArray;

	public static void main(String[] args) throws Exception {
		testMergeSort();
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
	private static void testParallelStream() {
		long t0 = System.nanoTime();
		// 初始化一个范围100万整数流,求能被2整除的数字，toArray（）是终点方法
		int a[] = IntStream.range(0, 1000000).filter(p -> p % 2 == 0).toArray();
		long t1 = System.nanoTime();
		// 和上面功能一样，这里是用并行流来计算
		int b[] = IntStream.range(0, 1000000).parallel().filter(p -> p % 2 == 0).toArray();
		long t2 = System.nanoTime();
		// 我本机的结果是serial: 0.06s, parallel 0.02s，证明并行流确实比顺序流快
		System.out.printf("serial: %.2fs, parallel %.2fs%n", (t1 - t0) * 1e-9, (t2 - t1) * 1e-9);
	}

	@SuppressWarnings("unused")
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

	@SuppressWarnings("unused")
	private static void testList() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(new Integer(1));
		list.add(new Integer(1));
		list.add(new Integer(1));

		for (Iterator<Integer> iter = list.iterator(); iter.hasNext();) {
			System.out.println(iter.next());
		}
	}

	@SuppressWarnings("unused")
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

	@SuppressWarnings("unused")
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

	@SuppressWarnings("unused")
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

	@SuppressWarnings("unused")
	private static void findSSOID(String response) {
		String ssotitle = "SSOSID:";
		int index = response.indexOf(ssotitle);
		String sid = response.substring(index + ssotitle.length());
		System.out.println(sid);
	}

	@SuppressWarnings("unused")
	private static void testSubString() {
		String string = "1 username password";
		int index = string.indexOf(" ");
		String leftString = string.substring(0, index);
		String rightString = string.substring(index);
		System.out.println(leftString);
		System.out.println(rightString);
	}

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

	private static void testMergeSort() {
		int arraySize = 10;
		// int[] array = new int[arraySize];
		// tempArray = new int[arraySize];
		Integer[] array = new Integer[arraySize];
		tempArray = new Integer[arraySize];

		// Random random = new Random(47);
		for (int i = 0; i < array.length; i++) {
			array[i] = (int) (Math.random() * 10);
		}
		out.println(Arrays.toString(array));
		mergeSort(array, 0, array.length);
		// msort(array, tempArray, 0, arraySize);
		out.println(Arrays.toString(tempArray));
	}

	@SuppressWarnings("unused")
	private static void mergeSort(Integer[] array, int begin, int end) {
		int length = end - begin;
		if (length > 1) {
			int middle = (begin + end) / 2;
			mergeSort(array, begin, middle);
			mergeSort(array, middle, end);
			int leftIndex = begin;
			int rightIndex = middle;
			int tempIndex = begin;
			while (leftIndex < middle && rightIndex < end) {
				int a = array[leftIndex];
				int b = array[rightIndex];
				if (a < b) {
					tempArray[tempIndex] = a;
					leftIndex++;
				} else {
					tempArray[tempIndex] = b;
					rightIndex++;
				}
				tempIndex ++;
			}
			while (leftIndex < middle) {
				tempArray[tempIndex ++] = array[leftIndex ++];
			}
			while (rightIndex < end) {
				tempArray[tempIndex ++] = array[rightIndex ++];
			}
			System.arraycopy(tempArray, begin, array, begin, length);
		}
	}

	private static void swap(Integer[] inputArray, int i, int j) {
		int temp = inputArray[0];
		inputArray[0] = inputArray[1];
		inputArray[1] = temp;

	}

	public static void sort(Object[] arr) {
		// 创建临时数组
		Object[] tempArr = arr.clone();
		msort(arr, tempArr, 0, arr.length);
	}

	public static <T extends Comparable<? super T>> void sort(T[] arr) {
		// 创建临时数组
		T[] tempArr = (T[]) arr.clone();
		msort(arr, tempArr, 0, arr.length);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static void msort(Object[] arr, Object[] tempArr, int first, int last) {
		if (first + 1 < last) {

			int midpt = (last + first) / 2;
			msort(arr, tempArr, first, midpt);
			msort(arr, tempArr, midpt, last);

			if (((Comparable) arr[midpt - 1]).compareTo(arr[midpt]) <= 0)
				return;

			int indexA, indexB, indexC;
			indexA = first;
			indexB = midpt;
			indexC = first;
			while (indexA < midpt && indexB < last) {
				if (((Comparable) arr[indexA]).compareTo(arr[indexB]) < 0) {
					tempArr[indexC] = arr[indexA]; // copyto tempArr
					indexA++;
				} else {
					tempArr[indexC] = arr[indexB]; // copyto tempArr
					indexB++;
				}
				indexC++;
			}
			// copy the tail of the sublist that is not exhausted
			while (indexA < midpt) {
				tempArr[indexC++] = arr[indexA++];
			}
			while (indexB < last) {
				tempArr[indexC++] = arr[indexB++];
			}

			for (int i = first; i < last; i++)
				arr[i] = tempArr[i];
		}
	}
}
