package algorithm.aStarAlgorithm;

import java.util.*;

//A*算法
public class AStar {
	private int[][] map;// 地图(1可通过 0不可通过)
	private List<Node> openList = new ArrayList<Node>();// 开启列表
	private List<Node> closeList = new ArrayList<Node>();// 关闭列表
	private final int COST_STRAIGHT = 10;// 垂直方向或水平方向移动的路径评分
	private final int COST_DIAGONAL = 14;// 斜方向移动的路径评分
	private int row;// 行
	private int column;// 列
	/**
	 * 上，下，左，右，左上，左下，右上，右下
	 */
	private static int[][] directions = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 },
			{ -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 } };

	public AStar(int[][] map, int row, int column) {
		this.map = map;
		this.row = row;
		this.column = column;
	}

	// 查找坐标（-1：错误，0：没找到，1：找到了）
	public int search(int x1, int y1, int x2, int y2) {
		if (nodeIllegal(x1, y1) || nodeIllegal(x2, y2)) {
			return -1;
		}

		Node startNode = new Node(x1, y1, null);
		Node destinationNode = new Node(x2, y2, null);
		openList.add(startNode);
		List<Node> resultList = search(startNode, destinationNode);
		if (resultList.size() == 0) {
			return 0;
		}
		for (Node node : resultList) {
			// 2 is the result path
			map[node.getX()][node.getY()] = 2;
		}
		return 1;
	}

	private boolean nodeIllegal(int x, int y) {
		return !nodeLegal(x, y);
	}

	private boolean nodeLegal(int x, int y) {
		return ((isInTheMap(x, y)) && (!isBlindAlley(x, y)));
	}

	private boolean isInTheMap(int x, int y) {
		return ((x >= 0) && (x < row)) && ((y >= 0) && (y < column));
	}

	private boolean isBlindAlley(int x, int y) {
		return map[x][y] == 0;
	}

	// 查找核心算法
	private List<Node> search(Node startNode, Node destination) {
		List<Node> resultList = new ArrayList<Node>();
		boolean isFind = false;
		Node node = null;
		while (openList.size() > 0) {
			// System.out.println(openList);
			// 取出开启列表中最低F值，即第一个存储的值的F为最低的
			node = openList.get(0);
			// 判断是否找到目标点
			// node.getX() == destinationNode.getX() && node.getY() == destinationNode.getY()
			if (node.equals(destination)) {
				isFind = true;
				break;
			}
			 for (int i = 0;i < directions.length;i ++) {
				 checkPath(node,directions[i][0],directions[i][1],destination);
			 }
			// 从开启列表中删除
			// 添加到关闭列表中
			closeList.add(openList.remove(0));
			// 开启列表中排序，把F值最低的放到最底端
			Collections.sort(openList);
			// System.out.println(openList);
		}
		if (isFind) {
			getPath(resultList, node);
		}
		return resultList;
	}

	private void checkPath(Node node, int x, int y, Node destination) {
		if (isInTheMap(node.getX() + x, node.getY() +y)) {
			checkPath(node.getX() + x, node.getY() +y, node, destination,
					COST_DIAGONAL);
		}
	}
	// 查询此路是否能走通
	private boolean checkPath(int x, int y, Node parentNode, Node destinationNode,
			int cost) {
		Node node = new Node(x, y, parentNode);
		// 查找地图中是否能通过
		if (isBlindAlley(x, y)) {
			closeList.add(node);
			return false;
		}
		// 查找关闭列表中是否存在
		if (isListContains(closeList, x, y) != -1) {
			return false;
		}
		// 查找开启列表中是否存在
		int index = -1;
		if ((index = isListContains(openList, x, y)) != -1) {
			// G值是否更小，即是否更新G，F值
			if ((parentNode.getG() + cost) < openList.get(index).getG()) {
				node.setParentNode(parentNode);
				countG(node, destinationNode, cost);
				countF(node);
				openList.set(index, node);
			}
		} else {
			// 添加到开启列表中
			node.setParentNode(parentNode);
			count(node, destinationNode, cost);
			openList.add(node);
		}
		return true;
	}

	// 集合中是否包含某个元素(-1：没有找到，否则返回所在的索引)
	private int isListContains(List<Node> list, int x, int y) {
		for (int i = 0; i < list.size(); i++) {
			Node node = list.get(i);
			if (node.getX() == x && node.getY() == y) {
				return i;
			}
		}
		return -1;
	}

	// 从终点往返回到起点
	private void getPath(List<Node> resultList, Node node) {
		if (node.getParentNode() != null) {
			getPath(resultList, node.getParentNode());
		}
		resultList.add(node);
	}

	// 计算G,H,F值
	private void count(Node node, Node destinationNode, int cost) {
		countG(node, destinationNode, cost);
		countH(node, destinationNode);
		countF(node);
	}

	// 计算G值
	private void countG(Node node, Node destinationNode, int cost) {
		if (node.getParentNode() == null) {
			node.setG(cost);
		} else {
			node.setG(node.getParentNode().getG() + cost);
		}
	}

	// 计算H值
	private void countH(Node node, Node eNode) {
		node.setF((Math.abs(node.getX() - eNode.getX()) + Math.abs(node.getY()
				- eNode.getY())) * 10);
	}

	// 计算F值
	private void countF(Node node) {
		node.setF(node.getG() + node.getH());
	}

}