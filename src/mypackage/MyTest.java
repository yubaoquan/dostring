package mypackage;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import javax.swing.JOptionPane;

/**
 * �������ڲ��Է���API�������û��������ȫ·���� �ҵ��������еĳ�Ա�����ͳ�Ա����
 */
public class MyTest {
	/**
	 * ���췽��
	 */
	public MyTest() {
		String className = JOptionPane.showInputDialog(null, "please input the class full name");// Ҫ���û��������ȫ·��
		try {
			Class<?> cla = Class.forName(className);
			Method[] method = cla.getDeclaredMethods();
			for (Method me : method) {
				System.out.println(me.toString());
			}
			System.out.println("********");
			Field[] field = cla.getDeclaredFields();
			for (Field me : field) { 
				System.out.println(me.toString());
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new MyTest();
	}
}