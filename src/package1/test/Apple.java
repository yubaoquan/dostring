package package1.test;

import java.util.HashMap;

public class Apple {
    private String color;
 
    public Apple(String color) {
        this.color = color;
    }
 
    public boolean equals(Object obj) {
        if (!(obj instanceof Apple))
            return false;   
        if (obj == this)
            return true;
        return this.color == ((Apple) obj).color;
    }
 
    public static void main(String[] args) {
        Apple a1 = new Apple("green");
        Apple a2 = new Apple("red");
        Apple a3 = new Apple("graan");
 
        //hashMap stores apple type and its quantity
        HashMap<Apple, Integer> m = new HashMap<Apple, Integer>();
        m.put(a1, 10);
        m.put(a2, 20);
        m.put(a3, 30);
        System.out.println(m.get(new Apple("green")));
    }
    
    public int hashCode(){
    	  return this.color.length(); 
    	 }
}