package package4.tower;

import java.util.Arrays;

public class MoveTower {

	private static final int TOWER_NUMBER = 3;
	private static final int TOWER_HEIGHT = 5;
	private static int[][] towers = new int[TOWER_NUMBER][TOWER_HEIGHT];

	public static void main(String[] args) {
		try {
			initTowers();
			showTowers();
			moveTower(TOWER_HEIGHT, 0, 1, 2);
			System.out.println("==========");
			showTowers();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	private static void initTowers() {
		for (int i = TOWER_HEIGHT; i > 0; i--) {
			towers[0][TOWER_HEIGHT - i] = i;
		}

		Arrays.fill(towers[1], 0);
		Arrays.fill(towers[2], 0);

	}

	/**
	 * 把当前塔的形状画出来，即塔上的所有砖块
	 */
	private static void showTowers() {
		System.out.println("----------------------------");
		for (int j = TOWER_HEIGHT; j > 0; j--) {
			for (int i = 0; i < TOWER_NUMBER; i++) {
				System.out.print(towers[i][j - 1] + "\t");
			}
			System.out.println();
		}
	}

	/**
	 * 把当前塔的形状画出来，即塔上的所有砖块
	 * @param tower
	 */
	private static void showTower(int[] tower) {
		for (int j = TOWER_HEIGHT; j > 0; j--) {
			System.out.println(tower[j - 1]);
		}
	}

	/**
	 * 将一个砖块放到塔顶
	 * @param tower 塔
	 * @param brickSize 砖块大小
	 */
	private static void towerAdd(int[] tower, int brickSize) {
		if (brickSize == 0) {
			return;
		}
		int i = TOWER_HEIGHT - 1;
		for (; i != 0 && tower[i] == 0; i--) {
			;
		}
		if (tower[i] > brickSize) {
			if (i + 1 < TOWER_HEIGHT) {
				tower[i + 1] = brickSize;
			}
		} else if (i == 0) {
			tower[i] = brickSize;
		}
	}

	/**
	 * 将目标位置的塔顶部的砖块移除，并返回该砖块的大小
	 * @param towerLocation 塔的位置
	 * @return 移除的砖块大小
	 */
	private static int towerRemove(int towerLocation) {
		int i = TOWER_HEIGHT - 1;
		for (; i > 0 && towers[towerLocation][i] == 0; i--) {
			;
		}
		int brickSize = towers[towerLocation][i];
		towers[towerLocation][i] = 0;
		return brickSize;
	}
/**
 * 将一个塔移动到目标位置
 * @param height 塔高
 * @param originalLocation 要移动的塔的原始位置
 * @param tempLocation 中间位置
 * @param newLocation 要移动到的位置
 */
	private static void moveTower(int height, int originalLocation, int tempLocation, int newLocation) {

		if (height == 1) {
			towerAdd(towers[newLocation], towerRemove(originalLocation));
			return;
		} else if (height > 1) {
			moveTower(height - 1, originalLocation, newLocation, tempLocation);
			showTowers();
			towerAdd(towers[newLocation], towerRemove(originalLocation));
			showTowers();
			moveTower(height - 1, tempLocation, originalLocation, newLocation);
		} else {
			System.err.println("invalid height: " + height);
		}
	}

}
