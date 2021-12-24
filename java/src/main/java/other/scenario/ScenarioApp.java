package other.scenario;

import other.scenario.map.ExeAddress;
import other.scenario.service.ImageService;
import other.scenario.util.PlanTimerTask;
import other.scenario.util.StartUpExeUtils;

import java.awt.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

//	启动模拟器，需要调整成全屏后隐藏到后台
public class ScenarioApp {
	public static void main (String[] args) throws Exception {
		//启动程序
		StartUpExeUtils.startUpExeOnly ("CMD /C " + ExeAddress.exeAddress ());
		/*拒接协战*/
		refuse ();
	}
	
	public static void refuse () throws InterruptedException, AWTException {
		String file1 = "scenario/御魂/拒接协战";
		for (int i = 0; i <= 300; i++) {
			Thread.sleep (30*1000);
			ImageService.imagesClickBackNumber (file1, 3, false);
		}
	}
}
















