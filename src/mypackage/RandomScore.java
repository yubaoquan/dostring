package mypackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RandomScore {
	static Map<String,Integer> scoreInfo = new HashMap<String,Integer>();
	static List<String> nameList = new ArrayList<String>();
	static List<Integer> scoreList = new ArrayList<Integer>();
	static {
		nameList.add("≥…Ïœ");
		nameList.add("¿Ó≥Â");
		nameList.add("Œ‚Àß");
		nameList.add("¡∫∂¨Û„");
	}
	public static void giveScore(){
		
		for (int i = 0; i < 4; ) {
			int score = (int)(Math.random() * 10);
			if (! intExists(score)) {
				scoreList.add(score);
				scoreInfo.put(nameList.get(i), scoreList.get(i));
				i ++;
			}
			
		}
		
	}
	private static boolean intExists(int t) {
		for (int i = 0; i < scoreList.size(); i ++) {
			if (scoreList.get(i).intValue() == t) {
				return true;
			}
		}
		return false;
	}
	public static void main(String[] args){
		giveScore();
		for (int i = 0; i < nameList.size(); i ++) {
			System.out.println(nameList.get(i) + ": " + scoreList.get(i));
		}
	}
}
