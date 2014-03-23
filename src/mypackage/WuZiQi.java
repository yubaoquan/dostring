package mypackage;

import java.util.Scanner;

public class WuZiQi {
	// 定义棋盘的大小
	static int SIZE = 15;
	// 用二维数组表示棋盘
	static String[][] BROAD = new String[SIZE][SIZE];

	// 初始化棋盘
	public static void init() {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				BROAD[i][j] = "十";
			}
		}
	}

	// 绘画棋盘
	public static void paintBroad() {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				// 输出一行，不要换行
				System.out.print(BROAD[i][j]);
			}
			// 换行输出下一行
			System.out.println();
		}
	}

	// 判断输入坐标的合法性
	public static boolean isLegal(int i, int j) {
		// 输入不能超出棋盘范围
		if (i >= SIZE || i < 0 || j >= SIZE || j < 0) {
			return false;
		}
		// 坐标位置必须是还未下子
		if (BROAD[i][j] != "十") {
			return false;
		}
		return true;
	}

	// 生成随机数模拟电脑下子
	public static int[] computer() {
		int[] coordinate = new int[2];
		coordinate[0] = (int) (Math.random() * 15 + 1);
		coordinate[1] = (int) (Math.random() * 15 + 1);
		while (!isLegal(coordinate[0], coordinate[1])) {
			coordinate[0] = (int) (Math.random() * 15 + 1);
			coordinate[1] = (int) (Math.random() * 15 + 1);
		}
		return coordinate;
	}

	/**
	 * 判断输赢 返回值： 1:黑子赢了 0:白字赢了 -1：未分胜负
	 */
	public static int isWin() {
		// 横向判断
		// 纵向判断
		// 反斜杠判断
		// 斜杠判断
		for (int i = 0; i < SIZE - 4; i++) {
			for (int j = 0; j < SIZE - 4; j++) {
				if (BROAD[i][j] == "●" && BROAD[i + 1][j + 1] == "●" && BROAD[i + 2][j + 2] == "●" && BROAD[i + 3][j + 3] == "●" && BROAD[i + 4][j + 4] == "●")
					return 1;
				if (BROAD[i][j] == "●" && BROAD[i][j + 1] == "●" && BROAD[i][j + 2] == "●" && BROAD[i][j + 3] == "●" && BROAD[i][j + 4] == "●")
					return 1;
				if (BROAD[i][j] == "●" && BROAD[i + 1][j] == "●" && BROAD[i + 2][j] == "●" && BROAD[i + 3][j] == "●" && BROAD[i + 4][j] == "●")
					return 1;
				
				if (BROAD[i][j] == "○" && BROAD[i + 1][j + 1] == "○" && BROAD[i + 2][j + 2] == "○" && BROAD[i + 3][j + 3] == "○" && BROAD[i + 4][j + 4] == "○")
					return 0;
				if (BROAD[i][j] == "○" && BROAD[i][j + 1] == "○" && BROAD[i][j + 2] == "○" && BROAD[i][j + 3] == "○" && BROAD[i][j + 4] == "○")
					return 0;
				if (BROAD[i][j] == "○" && BROAD[i + 1][j] == "○" && BROAD[i + 2][j] == "○" && BROAD[i + 3][j] == "○" && BROAD[i + 4][j] == "○")
					return 0;
				//判断反斜线,一部分
				if (i >= 4) {
					if (BROAD[i][j] == "●" && BROAD[i - 1][j + 1] == "●" && BROAD[i - 2][j + 2] == "●" && BROAD[i - 3][j + 3] == "●" && BROAD[i - 4][j + 4] == "●")
						return 1;
					if (BROAD[i][j] == "○" && BROAD[i - 1][j + 1] == "○" && BROAD[i - 2][j + 2] == "○" && BROAD[i - 3][j + 3] == "○" && BROAD[i - 4][j + 4] == "○")
						return 0;
				}
			}
		}
		
		for (int i = SIZE - 4; i < SIZE; i++) {
			for (int j = 0; j < SIZE - 4; j++) {
				//判断反斜线,另一部分
				if (i >= 4) {
					if (BROAD[i][j] == "●" && BROAD[i - 1][j + 1] == "●" && BROAD[i - 2][j + 2] == "●" && BROAD[i - 3][j + 3] == "●" && BROAD[i - 4][j + 4] == "●")
						return 1;
					if (BROAD[i][j] == "○" && BROAD[i - 1][j + 1] == "○" && BROAD[i - 2][j + 2] == "○" && BROAD[i - 3][j + 3] == "○" && BROAD[i - 4][j + 4] == "○")
						return 0;
				}
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		init();
		paintBroad();
		do {
			System.out.println("请输入下子的坐标:");
			Scanner sn = new Scanner(System.in);
			int i = sn.nextInt();
			int j = sn.nextInt();
			while (!isLegal(i, j)) {
				System.out.println("输入非法，请重新输入下子的坐标:");
				sn = new Scanner(System.in);
				i = sn.nextInt();
				j = sn.nextInt();
			}
			BROAD[i][j] = "●";
			paintBroad();
			int[] coordinate = computer();
			int c1 = coordinate[0];
			int c2 = coordinate[1];
			BROAD[c1 - 1][c2 - 1] = "○";
			System.out.println("电脑下子: " + c1 + " " + c2);
			paintBroad();
			if (isWin() == 1) {
				System.out.println("恭喜您赢了");
				break;
			}
			if (isWin() == 0) {
				System.out.println("很遗憾，您输了");
				break;
			}
		} while (isWin() == -1);
	}
}