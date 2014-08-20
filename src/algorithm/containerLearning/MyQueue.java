package algorithm.containerLearning;


public class MyQueue {

	MyQueue() {
		this.stackA = new Stack();
		this.stackB = new Stack();
	}
	private Stack stackA,stackB;
	private int maxValue(int x,int y) {
		return x > y ? x:y;
	}
	
	public int max() {
		return this.maxValue(this.stackA.max(), this.stackB.max());
	}
	public void enQueue(int v) {
		this.stackB.push(v);
	}
	public int deQueue() {
		if (this.stackA.isEmpty()) {
			while (!this.stackB.isEmpty()) {
				try {
					this.stackA.push(this.stackB.pop());
				} catch (StackEmptyException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		try {
			return this.stackA.pop();
		} catch (StackEmptyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	static class Stack {
		private final int MAX_SIZE = 100;
		private int stackTop = -1;
		private int maxStackItemIndex = -1;
		private int[] stackItem = new int[MAX_SIZE];
		private int[] link2NextMaxItem = new int[MAX_SIZE];
		
		public boolean isEmpty() {
			return this.stackTop < 0;
		}
		public void push(int x) {
			this.stackTop ++;
			if (this.stackTop >= this.MAX_SIZE) {
				;
			} else {
				this.stackItem[this.stackTop] = x;
				if (x > max()) {
					this.link2NextMaxItem[this.stackTop] = this.maxStackItemIndex;
					this.maxStackItemIndex = this.stackTop;
				} else {
					this.link2NextMaxItem[this.stackTop] = -1;
				}
			}
		}
		
		public int pop() throws StackEmptyException {
			int ret;
			if (this.stackTop < 0) {
				throw new StackEmptyException();
			} else {
				ret = this.stackItem[this.stackTop];
				if (this.stackTop == this.maxStackItemIndex) {
					this.maxStackItemIndex = this.link2NextMaxItem[this.stackTop];
				}
				this.stackTop --;
			}
			return ret;
		}
		
		public int max() {
			if (this.maxStackItemIndex > 0) {
				return this.stackItem[this.maxStackItemIndex];
			} else {
				return Integer.MIN_VALUE;
			}
		}
	}
	
	public static void main(String[] args) {
		MyQueue q = new MyQueue();
		for (int i = 0;i < 5;i ++) {
			q.enQueue(i);
		}
		for (int i = 0;i < 7;i ++) {
			System.out.print("" + q.deQueue() + " ");
		}
	}
}
