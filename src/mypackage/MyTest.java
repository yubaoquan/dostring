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
		String classInfo = JOptionPane.showInputDialog(null, "������ȫ·��");// Ҫ���û��������ȫ·��
		try {
			Class cla = Class.forName(classInfo);// ������ȫ·����������أ����ظ����Class����
			Method[] method = cla.getDeclaredMethods();// ���õõ���Class��������󣬷��ط������󼯺�
			for (Method me : method) {// ������෽���ļ���
				System.out.println(me.toString());// ��ӡ������Ϣ
			}
			System.out.println("********");
			Field[] field = cla.getDeclaredFields();// ���õõ���Class��������󣬷������Զ��󼯺�
			for (Field me : field) { // ����������Եļ���
				System.out.println(me.toString());// ��ӡ������Ϣ
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new MyTest();
	}
}