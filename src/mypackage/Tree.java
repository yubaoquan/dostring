package mypackage;

import java.util.ArrayList;

public class Tree {

	private TreeNode root;
	private Tree leftTree,rightTree;
	
	Tree(TreeNode root) {
		this.root = root;
	}

	/**
	 * get the maximum distance of two leaves in the tree
	 * @param tree
	 * @return
	 */
	public static Result getMaxDistance(TreeNode root) {
		if (root == null) {
			return new Result(0, -1);
		}
		Result leftResult = getMaxDistance(root.left);
		Result rightResult = getMaxDistance(root.right);

		Result result = new Result(0, 0);
		result.setMaxDepth(Math.max(leftResult.getMaxDepth() + 1, rightResult.getMaxDepth() + 1));
		result.setMaxDistance(
				Math.max(
						Math.max(leftResult.getMaxDistance(), rightResult.getMaxDistance()), 
								leftResult.getMaxDepth() + rightResult.getMaxDepth() + 2
				)
		);
		return result;
	}

	/**
	 * rebuild the tree with the given preOrder display result and inOrder display result
	 * 
	 * @param preOrder
	 *            : preOrder display result
	 * @param inOrder
	 *            : inOrder display result
	 * @param treeLen
	 *            : length of the tree
	 * @param root
	 *            : root of the tree
	 */
	public void rebuild(String preOrder, String inOrder, int treeLen, TreeNode root) {
		if (preOrder == null || inOrder == null) {
			return;
		}
		TreeNode pTemp = new TreeNode();
		pTemp.value = preOrder.charAt(0);
		pTemp.left = null;
		pTemp.right = null;

		if (root == null) {
			root = pTemp;
		}

		if (treeLen == 1) {
			return;
		}

		String pOrgInOrder = inOrder;
		String pLeftEnd = inOrder;
		int nTempLen = 0;

		while (preOrder.charAt(0) != pLeftEnd.charAt(0)) {
			if (preOrder == null || pLeftEnd == null) {
				return;
			}
			nTempLen++;

			if (nTempLen > treeLen) {
				break;
			}
			pLeftEnd = pLeftEnd.substring(1);
		}
		// get the length of left tree
		int nLeftLen = 0;
		nLeftLen = inOrder.indexOf(pLeftEnd) - nLeftLen - 1;

		// get the length of right tree
		int nRightLen = 0;
		nRightLen = treeLen - nLeftLen - 1;

		// rebuild left tree
		if (nLeftLen > 0) {
			rebuild(preOrder.substring(1), inOrder, nLeftLen, root.left);
		}

		// rebuild right tree
		if (nRightLen > 0) {
			rebuild(preOrder.substring(nLeftLen + 1), inOrder.substring(nLeftLen + 1), nRightLen, root.right);
		}
	}

	/**
	 * display the tree by first order :root,left,right
	 * 
	 * @param root
	 */
	public static void firstOrderDisplay(TreeNode root) {
		System.out.print("" + root.value + " ");
		if (root.left != null) {
			firstOrderDisplay(root.left);
		}
		if (root.right != null) {
			firstOrderDisplay(root.right);
		}
	}

	/**
	 * 
	 * @param root
	 * @return the depth of the tree
	 */
	public static int getLength(TreeNode root) {
		if (root == null) {
			return 0;
		} else if (root.left == null && root.right == null) {
			return 1;
		} else {
			return getLength(root.left) > getLength(root.right) ? getLength(root.left) + 1 : getLength(root.right) + 1;
		}

	}

	/**
	 * display the tree floor by floor
	 * 
	 * @param tree
	 *            :the tree to display
	 * @param floorToDisplay
	 *            : the certain floor in the tree to display
	 * 
	 *            the root of the tree is floor 0
	 */
	public static void display(Tree tree, int floorToDisplay) {
		if (tree.root == null) {
			return;
		}
		ArrayList<TreeNode> array = new ArrayList<TreeNode>();
		array.add(tree.root);
		int cur = 0;
		int last = 1;
		int floor = 0;
		while (cur < array.size()) {
			last = array.size();

			while (cur < last) {

				TreeNode temp = array.get(cur);
				if (floor == floorToDisplay) {
					System.out.print("" + temp.value + " ");
				}

				if (temp.left != null) {
					array.add(temp.left);
				}
				if (temp.right != null) {
					array.add(temp.right);
				}
				cur++;
			}
			floor++;
			if (floor > floorToDisplay) {
				break;
			}
			// System.out.println();//finish a floor enter the next floor
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(5);
		TreeNode n6 = new TreeNode(6);
		TreeNode n7 = new TreeNode(7);
		TreeNode n8 = new TreeNode(8);

		n1.left = n2;
		n1.right = n3;
		n2.left = n4;
		n2.right = n5;
		n3.right = n6;
		n5.left = n7;
		n5.right = n8;

		Tree tree = new Tree(n1);
		// Tree.display(tree,2);
		// Tree.firstOrderDisplay(tree.root);
		// System.out.println(tree.root.right == null);
		System.out.println("length: " + Tree.getLength(tree.root));
		Result result = Tree.getMaxDistance(tree.root);
		System.out.println("maxDistance: " + Tree.getMaxDistance(tree.root).getMaxDistance());
	}

	static class TreeNode {
		public TreeNode left, right;
		public int value;

		TreeNode() {
			this(0);
		}

		TreeNode(int value) {
			this.value = value;
			this.left = null;
			this.right = null;
		}

	}
}



class Result {
	private int maxDistance;
	private int maxDepth;

	Result(int maxDistance, int maxDepth) {
		this.maxDistance = maxDistance;
		this.maxDepth = maxDepth;
	}

	public int getMaxDistance() {
		return maxDistance;
	}

	public void setMaxDistance(int maxDistance) {
		this.maxDistance = maxDistance;
	}

	public int getMaxDepth() {
		return maxDepth;
	}

	public void setMaxDepth(int maxDepth) {
		this.maxDepth = maxDepth;
	}
}