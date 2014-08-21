package algorithm.containerLearning;

public class MyQueue {

	MyQueue() {
		stackA = new Stack();
		stackB = new Stack();
	}

	private Stack stackA, stackB;

	public int max() {
		return Math.max(stackA.max(), stackB.max());
	}

	public void enQueue(int v) {
		stackB.push(v);
	}

	public int deQueue() {
		if (stackA.isEmpty()) {
			while (!stackB.isEmpty()) {
				try {
					stackA.push(stackB.pop());
				} catch (StackEmptyException e) {
					e.printStackTrace();
				}
			}
		}
		try {
			return stackA.pop();
		} catch (StackEmptyException e) {
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
			return stackTop < 0;
		}

		public void push(int x) {
			stackTop++;
			if (stackTop >= MAX_SIZE) {
				;
			} else {
				stackItem[stackTop] = x;
				if (x > max()) {
					link2NextMaxItem[stackTop] = maxStackItemIndex;
					maxStackItemIndex = stackTop;
				} else {
					link2NextMaxItem[stackTop] = -1;
				}
			}
		}

		public int pop() throws StackEmptyException {
			int ret;
			if (stackTop < 0) {
				throw new StackEmptyException();
			} else {
				ret = stackItem[stackTop];
				if (stackTop == maxStackItemIndex) {
					maxStackItemIndex = link2NextMaxItem[stackTop];
				}
				stackTop--;
			}
			return ret;
		}

		public int max() {
			if (maxStackItemIndex > 0) {
				return stackItem[maxStackItemIndex];
			} else {
				return Integer.MIN_VALUE;
			}
		}
	}

	public static void main(String[] args) {
		MyQueue q = new MyQueue();
		for (int i = 0; i < 5; i++) {
			q.enQueue(i);
		}
		for (int i = 0; i < 5; i++) {
			System.out.print("" + q.deQueue() + " ");
		}
	}
}
