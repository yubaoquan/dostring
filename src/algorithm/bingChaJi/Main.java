package algorithm.bingChaJi;

import java.util.Scanner;

public class Main{
  private int n; 
  private int m;
  private int father[];
  private int other[]; //other[x]表示x的另一半是谁
  private int x[];
  private int y[];
  private boolean mark;

  public Main(int n,int m,int[] x,int[] y){
     this.n=n;
     this.m=m;
     this.x=x;
     this.y=y;
      father=new int[n+1];
        other=new int[n+1];
      
        for(int i=0;i< n+1;i++){
            father[i]=i;
            other[i]=0;
        }
        mark=false;
  }

   private int Find_Set(int x){ //找x的父节点
     int k,root; 
     root=x; 
     while(root!=father[root])  //循环找x的根      
         root=father[root]; 
     while(x!=root)//本循环修改查找路径中的所有节点使其指向根节点---压缩 
     { 
         k=father[x]; 
         father[x]=root;//指向根节点 
         x=k; 
     } 
     return x; 
    } 



  private void Union(int x,int y){//合并
    int fx=Find_Set(x);
    int fy=Find_Set(y);
    father[fx]=fy;
}

 public void go(int d){
      
       int fx,fy,xl,yl;
        for(int l=1;l<=m;l++){
            xl=x[l];
            yl=y[l];
            if(!mark){
                fx=Find_Set(xl);
                fy=Find_Set(yl);
                if(fx==fy){//出现同性恋
                    mark=true;
                    break;
                }else{//没出现同性恋，合并。公的在一个集合，母的在另一个集合
                 
                  if(other[xl]!=0){
                        Union(other[xl],yl);
                  } else if(other[yl]!=0){
                        Union(other[yl],xl);
                  } else {
                   
                        other[xl]=yl;
                        other[yl]=xl;
                  }
                }
            }
        }
        System.out.println("Scenario #"+d+":");
        if(mark)
            System.out.println("Suspicious bugs found!");
        else
            System.out.println("No suspicious bugs found!");
        System.out.println();
     
  }

    public static void main(String[] args){
          Scanner in=new Scanner(System.in);
      int t,n,m;
      int[] x;
      int[] y;
      t=in.nextInt();
      for(int d=1;d<=t;d++){
        n=in.nextInt();
        m=in.nextInt();
        x=new int[m+1];
        y=new int[m+1];
        for(int l=1;l<=m;l++){
           x[l]=in.nextInt();
           y[l]=in.nextInt();
        }

       Main ma=new Main(n,m,x,y);
       ma.go(d);
     }
   }
}