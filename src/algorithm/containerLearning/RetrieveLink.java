package algorithm.containerLearning;

public class RetrieveLink {

	MyList list = new MyList(null);
	public static void display(MyList list) {
		Node temp = list.head;
		while (temp != null) {
			System.out.print(temp.value + " ");
			temp = temp.next;
		}
	}
	public static Node retrieve(MyList list) {
		Node preHead = null;
		Node pNode = list.head;
		Node pPre = null;//save pNode
		
		  while(pNode != null) {
	            // get the next node, and save it at pNext
	             Node pNext = pNode.next;
	            // if the next node is null, the currect is the end of original 
	            // list, and it's the head of the reversed list
	            if (pNext == null) {
	            	preHead = pNode;
	            }

	            // reverse the linkage between nodes
	             pNode.next = pPre;

	            // move forward on the the list
	             pPre = pNode;
	             pNode = pNext;
	       }

	      return preHead;
	}
	
	public static void main(String[] args) {
		RetrieveLink rl = new RetrieveLink();
		rl.list.add(new Node(0,null)).add(new Node(1,null)).add(new Node(2,null)).add(new Node(3,null));
		display(rl.list);
		rl.list.add(new Node(4,null));
		display(rl.list);
		rl.list.head = retrieve(rl.list);
		display(rl.list);
	}
//}

static class Node {
	public int value;
	public Node next;
	
	Node(int value,Node next) {
		this.value = value;
		this.next = next;
	}
}

static class MyList {
	Node head;
	MyList(Node head) {
		this.head = head;
	}
	public MyList add(Node node) {
		if (this.head == null) {
			this.head = node;
		} else {
			Node current = this.head;
			while (current.next != null) {
				current = current.next;
			}
			current.next = node;
		}
		return this;
	}
}
}