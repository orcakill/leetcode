package other.scenario;

import other.scenario.map.ExeAddress;
import other.scenario.util.PlanTimerTask;
import other.scenario.util.StartUpExeUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

//	启动模拟器，需要调整成全屏后隐藏到后台
public class ScenarioApp {
	public static void main (String[] args) throws Exception {
//	    启动程序
		StartUpExeUtils.startUpExe ("CMD /C " + ExeAddress.exeAddress (), "Nox.exe");
	}
}

//	小号做协战，检测到登录图标后，自动登录，完成每日任务（协战拒接）
class ScenarioApp1 {
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

//	大号刷御魂，检测到登录图标后，自动登录，自动御魂150次（协战拒接）
class ScenarioApp2 {
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

//	大号刷御魂，检测到登录图标后，自动登录，自动御魂（协战拒接），自动个人结界，自动阴阳寮结界
class ScenarioApp3 {
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

//	大号刷御灵，检测到登录图标后，自动登录，自动刷御灵->自动刷结界->自动刷御灵（协战拒接）
class ScenarioApp4 {
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















