package test;

public class Plus implements Runnable{   
    
    private long num = 0;       
    public void run(){   //线程的任务
        a();   
    }   
    private void a() {   
        for(int i=0;i< 100;i++){//将num每次加1,加100次
            num++;   
               
            if(num%1000==0)   
                System.out.println("num:"+num);   
        }   
        System.out.println("num:"+num);
    }       
    public static void main(String args[]){
         Plus p=new Plus();
         Thread t=new Thread(p);
         t.start();
   }
} 