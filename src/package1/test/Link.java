package package1.test;

import static java.lang.System.out;
public class Link {
	private Node tail;
	private Node head;
	
	private Link(int value) {
		this.head = new Node(value);
		this.tail = head;
	}
	
	private void insert(int value) {
		if (head == null) {
			head = new Node(value);
			tail = head;
		} else {
			Node temp = new Node(value);
			tail.next = temp;
			tail = tail.next;
		}
	}
	
	private void display() {
		Node temp = head;
		while (temp != null) {
			out.print(temp.value + " ");
			temp = temp.next;
		}
	}
	
	/**
	 * 方法一、从链表的第二个节点开始，保存此节点以及其前后节点，
	 * 然后将此节点的next赋值为其前节点，三个节点后移，
	 * 最后修改原head的next，并为原head赋新值
	 */
	private void revert() {
		if (head == null || head.next == null) {
			return ;
		} else if (head.next.next == null) {
			head.next.next = head;
			head = head.next;
			head.next.next = null;
			return;
		} else {
			Node tempPre, tempCurrent, tempNext;
			tempPre = head;
			tempCurrent = head.next;
			tempNext = tempCurrent.next;
			
			while (tempNext != null) {
				tempCurrent.next = tempPre;
				tempPre = tempCurrent;
				tempCurrent = tempNext;
				tempNext = tempNext.next;
			}
			tempCurrent.next = tempPre;
			head.next = null;
			head = tempCurrent;
		}
	}
	
	/**
	 * 方法2，弄一个新链表，head是旧链表的tail，从旧链表的head节点开始遍历，
	 * 每个节点插入新链表的tail,然后将新链表的head和tail赋值给旧链表，旧链表就翻转了。
	 */
	private void revert2() {
		Link tempLink = new Link(tail.value);
		Node temp = head;
		if (temp == null) {
			return;
		}
		Node currentTemp;
		while (temp != tail) {
			currentTemp = new Node(temp.value);
			currentTemp.next = tempLink.head.next;
			tempLink.head.next = currentTemp;
			temp = temp.next;
		}
		tempLink.tail = temp;
		this.head = tempLink.head;
		this.tail = tempLink.tail;
	}
	
	
	/**   网上的方法
     * 递归，在反转当前节点之前先反转后续节点   
     */  
    public static Node reverse3(Node node) {   
        if (null == node || null == node.next) {   
            return node;   
        }   
        Node reversedHead = reverse3(node.next);   
        node.next.next = node;   
        node.next = null;   
        return reversedHead;   
    } 
    
	public static void main(String[] args) {
		Link link = new Link(1);
		for (int i = 2; i < 13; i ++) {
			link.insert(i);
		}
		link.display();
		out.println();
		link.revert2();
		link.display();
	}
	
	private static class Node {
		public Node next;
		private int value;
		
		private Node(int value) {
			this.value = value;
			this.next = null;
		}
	}
}
