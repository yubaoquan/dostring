package algorithm.dataStructure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class TestContainer2 {

	private static void test1() {
		List<Integer> integers = Collections.emptyList();
		Map<String, Object> extraContext = Collections.emptyMap();
		extraContext.put("hello", "world");
//		integers.add(1);
		System.out.println(integers.getClass());
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test1();

	}

}
