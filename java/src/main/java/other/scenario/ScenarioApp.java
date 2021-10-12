package other.scenario;

import other.scenario.util.PlanTimerTask;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class ScenarioApp {
	public static void main (String[] args) throws Exception {
		Calendar calendar = Calendar.getInstance ();
		TimerTask task = new PlanTimerTask ();
		Date firstTime = calendar.getTime ();
		//间隔：1分钟
		long period = 1000 * 60;
		
		Timer timer = new Timer ();
		timer.schedule (task, firstTime, period);
		
	}
}














