package algorithm.dataStructure;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

public class Main {

	static class TwoTuple<A,B> {
		public final A first;
		public final B second;
		
		public TwoTuple(A a,B b) {
			first = a;
			second = b;
		}
		
		@Override
		public String toString() {
			return "(" + first + ", " + second + ")";
		}
	}
	
	static class ThreeTuple<A,B,C> extends TwoTuple<A,B>{
		public final C third;
		public ThreeTuple(A a,B b,C c) {
			super(a,b);
			third = c;
		}
		
		@Override
		public String toString() {
			return "(" + first + ", " + second + ", " + third + ")";
		}
	}
	
	static class Tuple {
		public static <A,B> TwoTuple<A,B> tuple(A a,B b) {
			return new TwoTuple<A,B>(a,b);
		}
		
		public static <A,B,C> ThreeTuple<A,B,C> tuple(A a,B b,C c) {
			return new ThreeTuple<A,B,C>(a,b,c);
		}
	}
	
	static class TupleTest2 {
		static TwoTuple<String,Integer> f() {
			return Tuple.tuple("hi",47);
		}
		
		static TwoTuple f2() {
			return Tuple.tuple("hi",47);
		}
	}
	
	public static ArrayList func1() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("yubaoquan");
		return list;
	}
	
	public static ArrayList<String> func2() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("yubaoquan");
		return list;
	}
	
	static class Sets {
		public static <T> Set<T> union(Set<T> a,Set<T> b) {
			Set<T> result = new HashSet<T>(a);
			result.retainAll(b);
			return result;
		}
		
		public static <T> Set<T> intersection(Set<T> a,Set<T> b) {
			Set<T> result = new HashSet<T>(a);
			result.retainAll(b);
			return result;
		}
		
		public static <T> Set<T> difference(Set<T> superset,Set<T> subset) {
			Set<T> result = new HashSet<T>(superset);
			result.removeAll(subset);
			return result;
		}
		
		public static <T> Set<T> complement(Set<T> a,Set<T> b) {
			return difference(union(a,b),intersection(a,b));
		}
	}
	
	static class ContainerMethodDifferences {
		static Set<String> methodSet(Class<?> type) {
			Set<String> result = new TreeSet<String>();
			for(Method m : type.getMethods()) {
				result.add(m.getName());
			}
			return result;
		}
		
		static void interfaces(Class<?> type) {
			System.out.println("Interfaces in " + type.getSimpleName() + ": ");
			List<String> result = new ArrayList<String>();
			for(Class<?> c : type.getInterfaces()) {
				result.add(c.getSimpleName());
			}
			System.out.println(result);
		}
		
		static Set<String> object = methodSet(Object.class);
		static {
			object.add("clone");
		}
		static void difference(Class<?> superset,Class<?> subset) {
			System.out.println(superset.getSimpleName() + " extends " 
								+ subset.getSimpleName() + ", adds: ");
			Set<String> comp = Sets.difference(methodSet(superset), methodSet(subset));
			comp.removeAll(object);
			System.out.println(comp);
			interfaces(superset);
			System.out.println("----------------------------------------------------");
		}
		
		
		public static void practice1() {
			System.out.println("Collection: " + methodSet(Collection.class));
			interfaces(Collection.class);
			difference(Set.class,Collection.class);
			difference(HashSet.class,Set.class);
			difference(LinkedHashSet.class,Set.class);
			difference(TreeSet.class,Set.class);
			difference(List.class,Collection.class);
			difference(ArrayList.class,List.class);
			difference(LinkedList.class,List.class);
			difference(Queue.class,Collection.class);
			difference(PriorityQueue.class,Collection.class);
			
			System.out.println("Map: " + methodSet(Map.class));
			difference(HashMap.class,Map.class);
			difference(LinkedHashMap.class,HashMap.class);
			difference(SortedMap.class,Map.class);
			difference(TreeMap.class,Map.class);
		}
	}
	
	public static void practice1() {
		ContainerMethodDifferences.practice1();
	}
	
	

	static interface Generator<T> {
		public T next();
	}
	
	static class BigFish{
		private static int counter = 0;
		private int id = counter ++;
		private BigFish(){}
		
		@Override
		public String toString() {
			return "big fish " + this.id;
		}
		
		public static Generator<BigFish> generator() {
			return new Generator<BigFish>(){
				public BigFish next() {
					return new BigFish();
				}
			};
		}
	}
	
	public static class SmallFish{
		private static int counter = 0;
		private int id = counter ++;
		private SmallFish(){}
		
		@Override
		public String toString() {
			return "Small fish " + this.id;
		}
		
		public static Generator<SmallFish> generator() {
			return new Generator<SmallFish>(){
				public SmallFish next() {
					return new SmallFish();
				}
			};
		}
	}
	
	public static class Generators{
		public static <T> Collection<T> fill(Collection<T> coll,Generator<T> gen,int n) {
			for(int i = 0;i < n;i++) {
				coll.add(gen.next());
			}
			return coll;			
		}
	}
	
	public static void bigFishEatSmallFish(BigFish bf,SmallFish sf) {
		System.out.println(bf + " eat " + sf);
	}
	
	public static void practice2() {
		List<BigFish> bigFishList = new ArrayList<BigFish>();
		List<SmallFish> smallFishList = new ArrayList<SmallFish>();
		Generators.fill(bigFishList, BigFish.generator(), 10);
		Generators.fill(smallFishList, SmallFish.generator(), 10);
		for(BigFish bf : bigFishList) {
			bigFishEatSmallFish(bf,smallFishList.get((int)(Math.random() * 10)));
		}
	}
	public static BigFish[] function1(int n) {
		BigFish[] array = new BigFish[n];
		for(int i = 0;i < array.length;i++) {
			array[i] = new BigFish();
		}
		return array;
	}
	public static void practice3() {
		BigFish[] array = function1(5);
		System.out.println(Arrays.toString(array));
	}
	
	public static void practice4() {
		int[][][] array = new int[3][][];
		System.out.println(Arrays.deepToString(array));
	}
	
	public static BigFish[][] fillBigFishArray(int a,int b) {
		BigFish[][] array = new BigFish[a][b];
		for(int i = 0;i < a;i++) {
			for(int j = 0;j < b;j++) {
				array[i][j] = new BigFish();
			}
		}
		return array;
	}
	
	public static BigFish[][][] fillBigFishArray(int a,int b,int c) {
		BigFish[][][] array = new BigFish[a][b][c];
		for(int i = 0;i < a;i++) {
			array[i] = fillBigFishArray(b,c);
		}
		return array;
	}
	
	public static void practice5() {
		BigFish[][] array1 = fillBigFishArray(1,2);
		BigFish[][][] array2 = fillBigFishArray(1,2,3);
		System.out.print("array1: ");
		System.out.println(Arrays.deepToString(array1));
		System.out.print("array2: ");
		System.out.println(Arrays.deepToString(array2));
	}
	
	public static void practice6() {
		int[] array1 = {1,2,3};
		int[] array2 = new int[3];
		System.arraycopy(array1, 0, array2, 0, array1.length);
		System.out.println(Arrays.toString(array1));
		System.out.println(Arrays.toString(array2));
		array1[1] = 99;
		System.out.println(Arrays.toString(array1));
		System.out.println(Arrays.toString(array2));
		
		BigFish[] array3 = {new BigFish(),new BigFish(),new BigFish()};
		BigFish[] array4 = new BigFish[3];
		System.arraycopy(array3, 0, array4, 0, array3.length);
		
		System.out.println(Arrays.toString(array3));
		System.out.println(Arrays.toString(array4));
		array3[1] = new BigFish();
		System.out.println(Arrays.toString(array3));
		System.out.println(Arrays.toString(array4));
	}
	
	public static class SmallClass {
		private int value;
		public SmallClass(int value) {
			this.value = value;
		}
		
		@Override
		public boolean equals(Object sc) {
			return this.value == ((SmallClass)sc).value;
		}
	}
	
	public static void practice7() {
		SmallClass[] array1 = {new SmallClass(1),new SmallClass(2)};
		SmallClass[] array2 = {new SmallClass(1),new SmallClass(2)};
		System.out.println(Arrays.equals(array1, array2));
	}
	
	public static void main(String[] args) {
		practice7();
	}

}
