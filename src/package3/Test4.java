package package3;

import java.text.SimpleDateFormat;
import java.util.Date;
import static java.lang.System.*;

public class Test4 {

	public static void main(String[] args) throws Exception {
		// test1();
		/*for (int i = 0; i < 10; i++) {
			int result = triangleNumber(i + 1);
			System.out.println(result);
		}*/
		time();
	}

	private static int triangleNumber(int n) {
		int result = 0;
		for (int i = 1; i <= n; i++) {
			result += i;
		}
		return result;
	}
	
	private static void time(){
		String strBeginDate = "2000-01-01 00:00:00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try
        {
            Date beginDate = sdf.parse(strBeginDate);
            long time = beginDate.getTime();
            out.println(time);
        } catch (Exception e){
        	
        }
	}
}
