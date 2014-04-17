package algorithm.bingChaJi;

import static java.lang.System.out;
import java.util.HashSet;
import java.util.Set;

/**
 * 以下这种算法比较笨，该问题属于并查集问题，不建议此方法。
 * @author yubaoquan
 *
 */
public class BugMakeLove {

	public static void main(String[] args) {
		int testTime = 2;
		Test test1 = new Test(3, 3);
		test1.setPair(1, 2);
		test1.setPair(2, 3);
		test1.setPair(1, 3);
		test1.setSex();
		
		Test test2 = new Test(4, 2);
		test2.setPair(1, 2);
		test2.setPair(3, 4);
		test2.setSex();

		test1.showPairs();
		test1.findSuspiciousBugs();
		test2.showPairs();
		test2.findSuspiciousBugs();

	}

	private static class Test {
		private int bugAmount;
		private int makeLoveTime;
		private int[][] pairs;
		int pairInitIndex = 0;
		private Set<Integer> maleBugs = new HashSet<>();
		private Set<Integer> femaleBugs = new HashSet<>();

		private Test(int bugAmount, int makeLoveTime) {
			this.bugAmount = bugAmount;
			this.makeLoveTime = makeLoveTime;
			pairs = new int[makeLoveTime][];
		}

		private void setPair(int a, int b) {
			pairs[pairInitIndex] = new int[] { a, b };
			pairInitIndex++;
		}

		private void showPairs() {
			for (int[] pair : pairs) {
				out.println(pair[0] + " " + pair[1]);
			}
			out.println();
		}

		private void findSuspiciousBugs() {
			for (int[] pair : pairs) {
				if (maleAndFemale(pair[0])) {
					out.println("Suspicious bugs found! -->" + pair[0]);
				} else if (maleAndFemale(pair[1])) {
					out.println("Suspicious bugs found! -->" + pair[1]);
				}
			}
		}

		private void setSex() {
			for (int[] pair : pairs) {
				int a = pair[0];
				int b = pair[1];
				setSex(a, b);
			}
		}

		private void setSex(int a, int b) {
			if (unknownSex(a) && unknownSex(b)) {
				maleBugs.add(a);
				femaleBugs.add(b);
			} else if (unknownSex(b)) {
				if (maleBugs.contains(a)) {
					femaleBugs.add(b);
				} else {
					maleBugs.add(b);
				}
			} else {
				if (maleBugs.contains(b)) {
					femaleBugs.add(a);
				} else {
					maleBugs.add(a);
				}
			}
		}

		private boolean maleAndFemale(int bug) {
			return maleBugs.contains(bug) && femaleBugs.contains(bug);
		}

		private boolean unknownSex(int bug) {
			return (!maleBugs.contains(bug) && !femaleBugs.contains(bug));
		}
	}

}
