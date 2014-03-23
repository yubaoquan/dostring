package mypackage;

public class Test10 {

	public int value;
	public String name;
	Test10(int value) {
		this.value = value;
	}
	
	Test10() {
		this.value = 0;
	}
	public static void print(Object o) {
		System.out.println(o.toString());
	}

	public static void changeObject(final Test10 t) {
		Test10 temp = t;
		t.setValue(200);
		// float a = (float) 1.1;
		temp.value = 100;
		// t = temp;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public static boolean isOdd(int i) {
		return i % 2 == 0;
	}
	public static void main(String[] args) {

		for (int i = Integer.MIN_VALUE;i < Integer.MAX_VALUE;++i) {
			boolean isOdd = isOdd(i);
			System.out.println(String.format("i = %d, isOdd=%b", i,isOdd));
		}
		//byte.MAX_VALUE==127
		for (byte b = Byte.MIN_VALUE;b < Byte.MAX_VALUE;b++) {
			if (b == 0x90)
				System.out.println("joy");
		}
		System.out.println(Byte.MAX_VALUE);
		//没加L
		/*final long MICROS_PER_DAY = 24 * 60 * 60 * 1000 * 1000L;
		final long MILLIS_PER_DAY = 24 * 60 * 60 * 1000L;
		System.out.println(MICROS_PER_DAY);
		System.out.println(MILLIS_PER_DAY);
		System.out.println(MICROS_PER_DAY / MILLIS_PER_DAY);*/
		/*Set<Test10> set = new HashSet<Test10>();
		List<Test10> list = new LinkedList<Test10>();
		for (int i = 0; i < 10; i++) {
			list.add(new Test10(i + 1));
			set.add(list.get(i));
		}

		Iterator<Test10> iter = set.iterator();
		while (iter.hasNext()) {
			Test10 temp = iter.next();
			print(temp.getValue());
		}
		Test10.print(iter.equals(list.get(8)));
		Test10.print(iter.equals(list.get(9)));
		System.out.println(set.contains(list.get(0)));*/
		/*Test10 t = new Test10();
		t.setName("摩羯");
		System.out.println(t.name == "摩羯");*/
	}

}
