package package4.studyTimerTask;

import static java.lang.System.out;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TestTimerTask extends TimerTask {

	private String id;

	public TestTimerTask(String id) {
		super();
		this.id = id;
	}

	public static void main(String[] args) {
		test();

	}

	private static void test() {
		Timer timer = new Timer();
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + 1);
		Date date = calendar.getTime();
		TimerTask timerTask = new TestTimerTask("1");
		TimerTask timerTask2 = new TestTimerTask("2");
		timer.schedule(timerTask, date, 30000);
		timer.schedule(timerTask2, 120000);
	}

	@Override
	public void run() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		out.println("I am task " + id + ", now is " + sdf.format(new Date()));

	}

}
