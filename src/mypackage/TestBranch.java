package mypackage;

public class TestBranch {
	
	public static void main(String[] args) {
		int a = 0;
		FILTER_LOOP:
//			int a = 0;
		    while(a != 4) {
		      switch(a) {
		      case 1: return;
		      case 2: break FILTER_LOOP;
		      case 3:return;
		      }
		    }
	}
}
