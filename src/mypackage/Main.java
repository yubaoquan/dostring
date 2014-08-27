package mypackage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.script.Bindings;
import javax.script.Invocable;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import exceptionLearning.Practice;

public class Main {

	private static void branch() {
		
	}
private static void master() {
		
	}
	public static void practice1() {
		int a = 0;
		if (a == 0) {
			System.out.println("if");
			a ++;
		} else if (a == 1) {
			System.out.println("else if");
			a ++;
		} else {
			System.out.println("else");
		}
	}
	
	public static void func1(int[] array) {
		int[] newArray = new int[100];
		for (int i = 0;i < 100;i++) {
			newArray[i] = i;
		}
		array = newArray;
	}
	
	public static void practice2() {
		int[] array = new int[10];
		System.out.println(array.length);
		func1(array);
		System.out.println(array.length);
	}
	
	public static void runTheLatestMethod() {
		Class<Main> classObject = Main.class;
		Method[] methods = classObject.getMethods();
		Pattern p = Pattern.compile("practice*");
		int index = 0;
		Method latestMethod = null;
		for(Method method : methods) {
			Matcher m = p.matcher(method.getName());
			if(m.find()) {
				index ++;
			}
		}
		try {
			latestMethod = classObject.getMethod("practice" + index);
			latestMethod.invoke(classObject);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public static void practice3() {
		ArrayList<String> strList = new ArrayList<String>();
		strList.add("hello");
		strList.add("I");
		strList.add("world");
		strList.add("a");
		List<String> filtered = strList.stream().filter(x -> x.length()> 2).collect(Collectors.toList()); 
		 System.out.printf("Original List : %s, filtered list : %s %n", strList, filtered);
	}
	
	public static void practice4() {
		//获得一个javascript的执行引擎
				ScriptEngine engine=new ScriptEngineManager().getEngineByName("javascript");
				//建立上下文变量
				Bindings bind=engine.createBindings();
				bind.put("factor", 1);
				//绑定上下文，作用域是当前引擎范围 
				engine.setBindings(bind,ScriptContext.ENGINE_SCOPE);
				Scanner input = new Scanner(System.in);
				while(input.hasNextInt()){
					int first = input.nextInt();
					int sec = input.nextInt();
					System.out.println("输入参数是："+first+","+sec);
					//执行js代码
					try {
						engine.eval(new FileReader("E:/model.js"));
					} catch (FileNotFoundException | ScriptException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//是否可调用方法
					if(engine instanceof Invocable){	
						Invocable in=(Invocable)engine;
						//执行js中的函数
						Double result = null;
						try {
							result = (Double)in.invokeFunction("formula",first,sec);
						} catch (NoSuchMethodException | ScriptException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println("运算结果："+result.intValue());
					}
				}	
	}
	public static void main(String[] args) {
		runTheLatestMethod();
		
	}
}
