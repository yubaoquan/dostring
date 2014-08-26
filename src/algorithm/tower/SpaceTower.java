package algorithm.tower;

import java.util.Scanner;
import java.util.Arrays;

public class SpaceTower {
	private int typeSize;// 石头的类型数
	private Block blocks[];// 对每种类型石头的记录
	private boolean heightArray[] = new boolean[400001]; // 标记已经凑出来的各种高度

	public SpaceTower(int k, Block block[]) {
		this.typeSize = k;
		this.blocks = block;
	}

	private int doIt() {
		int maxHeight = 0; // 存放最后的最高的高度
		heightArray[0] = true;// 0高度肯定能凑出来
		Arrays.sort(blocks);// 先把block按照它所能忍受的高度从小到大排序

		for (int i = 0; i < typeSize; i++)// 枚举每一种类型
		{
			int t = 0;
			int tmp = maxHeight;
			for (int j = maxHeight; j >= t; j--)// 寻找最大值
			{
				System.out.println();
				if (heightArray[j])
					for (int h = 1; h <= blocks[i].amount; h++)// 遍取每一种类型的数量，磊一块石头，二块石头...
					{
						int x = h * blocks[i].unitHeight + j;// 凑出一种新的高度
						System.out.print(x + " ");
						if (x > blocks[i].destoryHeight)
							break; // 如果大于此种block所能忍受的最大高度则跳出
						if (tmp < x)
							tmp = x; // 是否大于当前已知的最大高度
						heightArray[x] = true; // 标记这种高度已经凑出来
					}
			}
			maxHeight = tmp;
		}
		return maxHeight;
	}

	public static void main(String args[]) {
		int k = 3;
		Block block1 = new Block(7, 40, 3);
		Block block2 = new Block(5, 23, 8);
		Block block3 = new Block(2, 52, 6);
		Block[] blocks = { block1, block2, block3 };
		SpaceTower m = new SpaceTower(k, blocks);
		System.out.println(m.doIt());
	}
}

class Block implements Comparable<Block>{// 石头类型
	int unitHeight;// 石头的高度
	int destoryHeight;// 最大建造高度
	int amount;// 这种石头的数量

	public Block() {
		unitHeight = 0;
		destoryHeight = 0;
		amount = 0;
	}

	public Block(int h, int a, int c) {
		this.unitHeight = h;
		this.destoryHeight = a;
		this.amount = c;
	}

	public String toString() {
		return "( " + unitHeight + "-" + destoryHeight + "-" + amount + " )";
	}

	@Override
	public int compareTo(Block o) {
		return this.destoryHeight - o.destoryHeight;
	}
}