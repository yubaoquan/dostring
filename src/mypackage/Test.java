package mypackage;

import java.util.ArrayList;
import java.util.List;

/*��������*/
public class Test {
	/*
	 * public static void main(String[] args) { int width = 400;
	 * 
	 * int height = 300;
	 * 
	 * // ����BufferedImage���� BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	 * 
	 * // ����Graphics2D Graphics2D g2d = image.createGraphics();
	 * 
	 * // ---------- �������������������������� ----------------- image = g2d.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT); g2d.dispose(); g2d = image.createGraphics();
	 * 
	 * // ---------- ���������������� -----------------
	 * 
	 * // ���� g2d.setColor(new Color(255,0,0)); g2d.setStroke(new BasicStroke(1)); g2d.draw(new Rectangle(10,10));
	 * 
	 * //�������� g2d.dispose();
	 * 
	 * // �������� try { ImageIO.write(image, "png", new File("E:/test.png")); } catch (IOException e) { // TODO Auto-generated catch block e.printStackTrace(); } }
	 */
	private final int a = 9;

	@SuppressWarnings("finally")
	public static int func() {
		try {
			System.out.println("try");
			return 1;
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			System.out.println("finally");
			return 2;
		}
	}

	/**
	 * 输入一个字符串,一个List,将字符串中字符的所有组合填入List中
	 * 
	 * @param str 输入的字符串
	 * @param result 作为结果输出的List
	 * @return 同上
	 */
	public static ArrayList<String> getResult(String str,ArrayList<String> result) {
		if (str.length() < 2) {
			String sb = new String(str);
			result.add(sb);
			return result;
		}
		
		String temp = null;
		
		result = getResult(str.substring(1), result);
		int originSize = result.size();
		for (int index = 0; index != originSize; index++) {
			temp = result.get(0);
			Test.insertCharToString(str.charAt(0), temp,result);
			result.remove(0);
		}
		return result;
	}

	private static void insertCharToString(char c,String str,ArrayList<String> result) {
		String temp = null;
		for (int i = 0; i <= str.length(); i ++) {
			temp = str.substring(0,i) + c + str.substring(i);
			//System.out.println(temp);
			result.add(temp);
		}
	}
	public static void main(String[] args) {

		List<Object> objectList;
		List<String> stringList;
		     
		//objectList = stringList;  //compilation error incompatible types
		
		String s = "我爱你";// 原字符串
		ArrayList<String> result = new ArrayList<String>();
		result = getResult(s,result);
		for (int i = 0; i < result.size(); i ++) {
			System.out.println(result.get(i));
		}
		//insertCharToString('o',"apple",null);

	}
}
