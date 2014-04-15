package test;

import java.util.GregorianCalendar;
import java.util.*;
public class EmployeeTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Employee[] staff = new Employee[3];
		
		staff[0] = new Employee("olive",10000,2004,2,28);
		staff[1] = new Employee("dora",20100,2001,3,18);
		staff[3] = new Employee("hellokitty",20000,2013,3,2);
		
		for(Employee e :staff){
			e.raiseSalary(5);
		}
		
		for(Employee e: staff){
			System.out.println("name:" + e.getName() + "salary:" + e.getSalary() + "hireDay:" + e.getHireday());
		}
	}

}

class Employee{
	public Employee(String n, double s, int year, int month, int day){
		GregorianCalendar calendar = new GregorianCalendar(year,month-1,day);
		hireday = calendar.getTime();
		name = n;
		salary = s;
		
	}
	
	public String getName(){
		return name;
	}
	
	public double getSalary(){
		return salary;
	}
	
	public Date getHireday(){
		return hireday;
	}
	
	public void raiseSalary(double byPercent){
		salary = salary * (100 + byPercent) / 100;
	}
	private String name;
	private double salary;
	private Date hireday;
}