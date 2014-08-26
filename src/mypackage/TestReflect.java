package mypackage;

import static java.lang.System.out;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.JOptionPane;


public class TestReflect {
	
	public static void say() {
		System.out.println("hello");
	}
	public int add(int a,int b) {
		int c = a + b;
		System.out.println("" + a + " + " + b + " is " + c);
		return c;
	}
	
	public void divide(float a,float b) {
		float c = a / b;
		System.out.println("" + a + " / " + b + " is " + c);
	}
	public static void main(String[] args) {
		MethodType mt = MethodType.methodType(int.class,int.class,int.class);
		MethodType mt2 = MethodType.methodType(void.class,float.class,float.class);
		MethodHandles.Lookup lk = MethodHandles.lookup();
		MethodHandle mh = null;
		
		TestReflect tr = new TestReflect();
		try {
			mh = lk.findVirtual(tr.getClass(), "add",mt);
			mh.invoke(tr,1,2);
			mh = lk.findVirtual(tr.getClass(),"divide",mt2);
			mh.invoke(tr,1,2);
		} catch (Exception e) {
			e.printStackTrace();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public static void showMethodNField() {
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
}
