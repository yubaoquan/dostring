package mypackage;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import javax.swing.JOptionPane;
import static java.lang.System.*;
/**
 * �������ڲ��Է���API�������û��������ȫ·���� �ҵ��������еĳ�Ա�����ͳ�Ա����
 */
public class ShowMethodNField {
	/**
	 * ���췽��
	 */
	public ShowMethodNField() {
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
			out.println("no such class defined: " + className);
			System.exit(-1);
		}
	}

	public static void main(String[] args) {
		new ShowMethodNField();
	}
}