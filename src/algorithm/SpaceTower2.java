package algorithm;

import java.util.Arrays;

/**
 * 原题:有一群牛要上太空。他们计划建一个太空梯-----用一些石头垒。 
 * 他们有K种不同类型的石头，每一种石头的高度为h_i，数量为c_i,并且由于会受到太空辐射， 
 * 每一种石头不能超过这种石头的最大建造高度a_i。帮助这群牛建造一个最高的太空梯。
 * 
 * @author http://poj.org/problem?id=2392
 *
 */
public class SpaceTower2 {

	boolean[] heightArray = new boolean[400001];
	int typeSize;
	Block[] blocks;

	public static void main(String[] args) {
		Block block1 = new Block(7, 40, 3);
		Block block2 = new Block(5, 23, 8);
		Block block3 = new Block(2, 52, 6);
		Block[] blocks = { block1, block2, block3 };
		int height = new SpaceTower2(3, blocks).makeTower();
		System.out.println(height);

	}

	SpaceTower2(int typeSize, Block[] blocks) {
		this.typeSize = typeSize;
		this.blocks = blocks;
	}

	private int makeTower() {
		Arrays.sort(blocks);
		int maxHeight = 0;
		heightArray[0] = true;
		
		for (Block block : blocks) {
			int tmp = maxHeight;
			for (int j = maxHeight; j >= 0; j--) {
				if (heightArray[j]) {
					for (int h = 1; h <= block.amount; h++) {
						int x = h * block.unitHeight + j;
						if (x > block.destoryHeight) {
							break;
						}
						heightArray[x] = true;
						if (tmp < x) {
							tmp = x;
						}
					}
				}
			}
			maxHeight = tmp;
		}
		return maxHeight;
	}

	private static class Block implements Comparable {
		int unitHeight;
		int destoryHeight;
		int amount;

		public Block(int unit, int destory, int amount) {
			this.unitHeight = unit;
			this.destoryHeight = destory;
			this.amount = amount;
		}

		@Override
		public int compareTo(Object o) {
			if (o instanceof Block) {
				Block bl = (Block) o;
				return destoryHeight - bl.destoryHeight;
			} else {
				return 0;
			}
		}
	}
}
