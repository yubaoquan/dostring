package algorithm.sort;

public class BaseSort {
	static class Node {
		private int value;
		private Node next;
		Node() {
			this.value = 0;
			this.next = null;
		}
		Node(int value) {
			this.value = value;
			this.next = null;
		}
		public int getValue() {
			return value;
		}
		public void setValue(int value) {
			this.value = value;
		}
		public Node getNext() {
			return next;
		}
		public void setNext(Node next) {
			this.next = next;
		}
	}
	static class Link {
		private Node head;
		private Node tail;
		
		Link() {
			this.head = null;
			this.tail = null;
		}
		
		Link(Node head,Node tail) {
			this.head = head;
			this.tail = tail;
		}
		
		public Node getHead() {
			return head;
		}
		public void setHead(Node head) {
			this.head = head;
		}
		public Node getTail() {
			return tail;
		}
		public void setTail(Node tail) {
			this.tail = tail;
		}
		
		/**
		 * add a node to the tail of the current link
		 * @param node
		 */
		public void addNode(Node node) {
			if (this.head == null) {
				this.head = node;
				this.tail = node;
			} else {
				this.tail.next = node;
				this.tail = node;
			}
		}
		
		/**
		 * add a link to the tail of the current link
		 * @param link
		 */
		public void addLink(Link link) {
			if (link.head == null) {
				return;
			}
			this.tail.next = link.head;
			this.tail = link.tail;
		}
		
		public void display() {
			Node node = this.head;
			System.out.println("display link:");
			while (node != null) {
				System.out.print("" + node.value + " ");
				node = node.next;
			}
			System.out.print("\n");
		}
	} 
	private static Link[] links = new Link[10];
	static {
		for (int i = 0;i < links.length; i++) {
			links[i] = new Link();
		}
	}
	private static int getCertainNumber(int a,int index) {
		int ret = 0;
		for (int i = 0;i < index; i++) {
			ret = a % 10;
			a = a / 10;
		}
		return ret;
	}

	private static void displayArray(int[] array) {
		System.out.println("display array");
		for (int i = 0;i < array.length;i++) {
			System.out.print("" + array[i] + " ");
			if (i % 10 == 0 && i >0) {
				System.out.println();
			}
		}
		System.out.println();
	}
	

	/**
	 * collect the values from 10 links to the array and clean the links
	 * @param array
	 */
	private static void collect(int[] array) {
		int pos = 0;
		
		//link all the links to one link
		for (int i = 1;i < BaseSort.links.length;i++) {
			BaseSort.links[0].addLink(BaseSort.links[i]);
		}
		
		//copy the values in the links to the array
		Node currentNode = BaseSort.links[0].head;
		while (currentNode != null && pos < array.length) {
			array[pos] = currentNode.value;
			pos ++;
			currentNode = currentNode.next;
		}
		
		//clean the links
		for (int i = 0;i < BaseSort.links.length;i++) {
			BaseSort.links[i] = new Link();
		}
	}
	/**
	 * the main base sort function
	 * @param weiShu maximum number of numbers in the target array
	 * @param array
	 * @return
	 */
	private static int[] sort(int[] array,int weiShu) {
		for (int i = 0;i < weiShu;i ++) {//totally weiShu times collecting
			for (int j = 0;j <array.length;j++) {
				int pos = BaseSort.getCertainNumber(array[j], i + 1);//find the 'posth' link
				int value = array[j];
				//System.out.println("pos: " + pos + " value: " + value );
				BaseSort.links[pos].addNode(new Node(value));
			}
			BaseSort.collect(array);
		}
		return array;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int[] array = new int[100];
		for (int i = 0;i < array.length;i++) {
			array[i] = (int)(Math.random() * 100);
			//link.add(new Node(array[i]));
		}
		//int[] array = {5, 6 ,2, 3 ,7 ,8 ,3, 0 ,0, 1 };
		BaseSort.displayArray(array);
		array = BaseSort.sort(array,2);
		System.out.println("\n------------------------------");
		BaseSort.displayArray(array);
		
		
	}
}
