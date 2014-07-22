package avlTree;

public class AVLNode implements Comparable {

	int element;
	AVLNode left;
	AVLNode right;
	int height;
	int depth;

	AVLNode(int theElement) {
		this(theElement, null, null);
	}

	AVLNode(int theElement, AVLNode lt, AVLNode rt) {
		element = theElement;
		left = lt;
		right = rt;
	}

	@SuppressWarnings("rawtypes")
	public static int height(AVLNode t) {
		return t == null ? -1 : t.height;
	}

	/**
	 * Internal method to insert into a subtree
	 * 
	 * @param x
	 *            the item to insert
	 * @param t
	 *            the node that roots the subtree
	 * @return the new root of the subtree
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static AVLNode insert(int x, AVLNode t) {
		System.out.println("insert " + x + " into the tree");
		if (t == null) {
			return new AVLNode(x, null, null);
		}
		int compareResult = x - t.element;
		if (compareResult < 0) {
			t.left = insert(x, t.left);
			if (height(t.left) - height(t.right) == 2) {
				if (x - t.left.element < 0) {
					t = rotateWithLeftChild(t);
				} else {
					t = doubleWithLeftChild(t);
				}
			}
		} else if (compareResult > 0) {
			t.right = insert(x, t.right);
			if (height(t.right) - height(t.left) == 2) {
				if (x - t.right.element > 0) {
					t = rotateWithRightChild(t);
				} else {
					t = doubleWithRightChild(t);
				}
			}
		} else
			;
		t.height = Math.max(height(t.left), height(t.right)) + 1;

		return t;
	}

	public static void refreshDepth(AVLNode root, int depth) {
		root.depth = depth + 1;
		if (root.left != null) {
			refreshDepth(root.left, depth + 1);
		}
		if (root.right != null) {
			refreshDepth(root.right, depth + 1);
		}
	}

	@SuppressWarnings("unchecked")
	public static AVLNode rotateWithLeftChild(AVLNode k2) {
		AVLNode k1 = k2.left;
		k2.left = k1.right;
		k1.right = k2;
		k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
		k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
		return k1;
	}

	@SuppressWarnings("unchecked")
	public static AVLNode rotateWithRightChild(AVLNode k2) {
		AVLNode k1 = k2.right;
		k2.right = k1.left;
		k1.left = k2;
		k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
		k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
		return k1;
	}

	public static AVLNode doubleWithLeftChild(AVLNode k3) {
		k3.left = rotateWithRightChild(k3.left);
		return rotateWithLeftChild(k3);
	}

	public static AVLNode doubleWithRightChild(AVLNode k3) {
		k3.right = rotateWithLeftChild(k3.right);
		return rotateWithRightChild(k3);
	}

	public String toString() {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < height; i++) {
			result.append("- ");
		}
		result.append(element);
		result.append("\n");
		if (left != null) {
			result.append(left.toString());
		}
		if (right != null) {
			result.append(right.toString());
		}
		return result.toString();
	}

	private static void printTree(AVLNode root) {
		for (int i = 0; i <= root.height + 1; i++) {
			printNthTree(root, i);
			System.out.println();
		}
	}

	private static void printNthTree(AVLNode root, int depth) {
		if (root == null) {
			return;
		}
		if (root.depth == depth) {
			for (int i = 0; i <= root.height; i++) {
				System.out.print("\t");
			}
			System.out.print(root.element);
			for (int i = 0; i <= root.height; i++) {
				System.out.print("\t");
			}
		} else if (root.depth <= depth) {
			printNthTree(root.left, depth);// left
			// System.out.print("-");
			printNthTree(root.right, depth);// right
		}
	}

	private static void testPrintintree() {
		AVLNode[] nodes = new AVLNode[16];
		for (int i = 0; i < nodes.length; i++) {
			nodes[i] = new AVLNode(i + 1);
		}
		AVLNode root = new AVLNode(3);
		for (int i = 2; i > 0; i--) {
			root = insert(i, root);
		}
		printTree(root);
		for (int i = 4; i <= 7; i++) {
			root = insert(i, root);
		}
		printTree(root);
		for (int i = 16; i >= 10; i--) {
			root = insert(i, root);
		}
		printTree(root);
		for (int i = 8; i < 9; i++) {
			root = insert(i, root);
		}
		refreshDepth(root, 0);
		printTree(root);
	}

	public static void main(String[] args) {
		testPrintintree();
	}

	@Override
	public int compareTo(Object o) {
		AVLNode temp = (AVLNode) o;
		int result = element - temp.element;
		return result;
	}
}
