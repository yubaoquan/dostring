package algorithm.containerLearning;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Spliterator;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class TestContainer {

	static class MyStack {
		private LinkedList<Character> stack = new LinkedList<Character>();
		public char pop() {
			return stack.isEmpty() ? null : stack.removeFirst();
		}
		public void push(char c) {
			stack.addFirst(new Character(c));
		}
	}
	
	static class Gerbil {
		public int gerbilNumber;
		public void hop() {
			System.out.format("gerbil %d is jumping\n",this.gerbilNumber);
		}
		Gerbil(int gerbilNumber) {
			this.gerbilNumber = gerbilNumber;
		}
		@Override
		public boolean equals(Object obj) {
			return false;
		}
	}
	
	public static void practice1() {
		ArrayList<Gerbil> list = new ArrayList<Gerbil>();
		for(int i = 0;i < 10;i++) {
			list.add(new Gerbil(i+1));
		}
		for(Gerbil g : list) {
			g.hop();
		}
		Iterator<Gerbil> it = list.iterator();
		while(it.hasNext()) {
			it.next().hop();
		}
	}
	
	public static void practice2() {
		System.out.println(" hello world ".trim());
	}
	
	static class Snow{}
	static class Powder extends Snow{}
	static class Light extends Powder{}
	static class Heavy extends Powder{}
	static class Crusty extends Snow{}
	static class Slush extends Snow{}
	
	public static void practice3() {
		List<Snow> snow1 = Arrays.asList(new Crusty(),new Slush(),new Powder(),new Light(),new Heavy());
		//Type mismatch: cannot convert from List<TestContainer.Powder> to List<TestContainer.Snow>
	//	List<Snow> snow2 = Arrays.asList(/*new Slush(),*/new Light(),new Heavy());
		List<Snow> snow3 = new ArrayList<Snow>();
		Collections.addAll(snow3, new Light(),new Heavy());
		List<Snow> snow4 = Arrays.<Snow>asList(new Heavy(),new Light());
	}
	
	public static void practice4() {
		TreeMap<String,Integer> tm = new TreeMap<String,Integer>();
		tm.put("1", 1);
		tm.put("2", 3);
		tm.put("3", 2);
		tm.put("4", 5);
		tm.put("5", 4);
		
	}
	
	public static void practice5() {
		Integer a = new Integer(1);
		Integer b = new Integer(1);
		Integer c = 1;
		Integer d = 1;
		System.out.println("a == b:" + (a == b));
		System.out.println("c == d:" + (c == d));
	}
	
	public static void practice6() {
		LinkedList<Integer> ll = new LinkedList<Integer>();
		Iterator<Integer> it = ll.iterator();
		for(int i = 0;i < 20;i ++) {
			int index = ll.size() / 2;
			ll.add(index, new Integer(i));
		}
		Iterator<Integer> it2 = ll.listIterator();
		System.out.println(ll.size());
		while(it2.hasNext()) {
			Integer a = it2.next();
			System.out.print(a + " ");
		}
	}
	
	public static void practice7() {
		MyStack myStack = new MyStack();
		char[] data = "+U+n+c---+e+r+t---+a-+i-+n+y+y---+-+r+u--+l+e+s---".toCharArray();
		for(int i = 0;i < data.length;i++) {
			switch(data[i]) {
				case '+': 
					i ++;
					myStack.push(data[i]);
					break;
				case '-':
					System.out.print(myStack.pop() + "");
					break;
			}
		}
	}
	
	public static void practice8() {
		HashSet<Integer> hashSet = new HashSet<Integer>();
		
		hashSet.add(0);
		hashSet.add(new Integer(0));
		Integer integer = new Integer(0);
		System.out.println(hashSet.contains(integer));
		System.out.println(hashSet);
		
		HashSet<String> stringHashSet = new HashSet<String>();
		String str1 = new String("hello");
		String str2 = new String("hello");
		System.out.println(str1 == str2);
		stringHashSet.add(str1);
		stringHashSet.add(str2);
		System.out.println(stringHashSet);
	}
	
	public static void practice9() {
		Gerbil gerbil1 = new Gerbil(1);
		Gerbil gerbil2 = new Gerbil(2);
		System.out.println(gerbil1.equals(gerbil1));
		
		HashSet<Gerbil> hashSet = new HashSet<Gerbil>();
		hashSet.add(gerbil1);
		hashSet.add(gerbil2);
		hashSet.add(gerbil1);
		
		System.out.println(hashSet);
	}
	
	public static void practice10() {
		Map<String,String> map = new HashMap<String,String>();
		map.put("name", "yubaoquan");
		map.put("name2", "yubaoquan");
		map.put("name3", "zhaobenshan");
		System.out.println(map);
		map.put("name", "zhoujielun");
		System.out.println(map);
	}
	
	public static void practice11() {
		Map<String,Gerbil> map = new HashMap<String,Gerbil>();
		map.put("one", new Gerbil(1));
		map.put("two", new Gerbil(2));
		map.put("three", new Gerbil(3));
		
		Iterator<String> keySetIterator = map.keySet().iterator();
		while(keySetIterator.hasNext()) {
			String name = keySetIterator.next();
			Gerbil temp = map.get(name);
			System.out.println(name + ":");
			temp.hop();
			
		}
	}
	
	public static void practice12() {
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		map.put("zero",0);
		map.put("one", 1);
		map.put("two", 2);
		map.put("three", 3);
		map.put("four", 4);
		map.put("five", 5);
		System.out.println(map);
		Set<String> set = map.keySet();
		System.out.println(set);
		
		LinkedHashMap<String,Integer> linkedHashMap = new LinkedHashMap<String,Integer>();
		Iterator<String> it = set.iterator();
		
		while(it.hasNext()) {
			String name = it.next();
			linkedHashMap.put(name, map.get(name));
		}
		System.out.println(linkedHashMap);
	}
	
	static class Command {
		public Command(String str) {
			this.str = str;
		}
		private String str;
		public void operation() {
			System.out.println("option:" + this.str);
		}
	}
	
	static class SecondClass {
		public static Command fillTheQueue(Queue<Command> queue,Command command) {
			queue.offer(command);
			return command;
		}
	}
	
	static class ThirdClass {
		public static void consume(Queue<Command> queue) {
			while(true) {
				Command command = queue.poll();
				if(command == null) {
					break;
				}
				command.operation();
			}
			
		}
	}
	
	public static void practice13() {
		Command command1 = new Command("1--hujintao");
		Command command2 = new Command("2--wenjiabao");
		Command command3 = new Command("3--xijinping");
		Command command4 = new Command("4--likeqiang");
		Command command5 = new Command("5--yubaoquan");
		Queue<Command> queue = new LinkedList<Command>();
		SecondClass.fillTheQueue(queue, command1);
		SecondClass.fillTheQueue(queue, command2);
		SecondClass.fillTheQueue(queue, command3);
		SecondClass.fillTheQueue(queue, command4);
		SecondClass.fillTheQueue(queue, command5);
		ThirdClass.consume(queue);
		System.out.println(queue.isEmpty());
	}
	
	static class CollectionSequence implements Collection {

		@Override
		public boolean add(Object arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean addAll(Collection arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void clear() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean contains(Object arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean containsAll(Collection arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isEmpty() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Iterator iterator() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean remove(Object arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean removeAll(Collection arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean retainAll(Collection arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public int size() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public Object[] toArray() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object[] toArray(Object[] arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void forEach(Consumer action) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean removeIf(Predicate filter) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Spliterator spliterator() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Stream stream() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Stream parallelStream() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
	public static void main(String[] args) {
		TestContainer.practice13();
	}

}
