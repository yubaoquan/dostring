package mypackage;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


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

}
