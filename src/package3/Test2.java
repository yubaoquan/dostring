package package3;

public class Test2 {

	static class Father<AnyType> {
		private AnyType nose;

		public AnyType getNose() {
			return nose;
		}

		public void setNose(AnyType nose) {
			this.nose = nose;
		}
	}

	static class Son extends Father<Integer> implements Comparable<Son> {

		@Override
		public int compareTo(Son o) {
			// TODO Auto-generated method stub
			return 0;
		}
	}

	static class Daughter extends Father {
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Father[] fathers = new Son[10];
		fathers[0] = new Son();
		fathers[1] = new Daughter();

	}

}
