package other.scenario.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import other.scenario.dao.OnmyojiInfoMapper;
import other.scenario.map.ExeAddress;
import other.scenario.util.ImageRecognition;
import other.scenario.util.StartUpExeUtils;

import java.awt.*;
import java.awt.event.InputEvent;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class LoginService {
	private static final Logger logger = LogManager.getLogger (LoginService.class);
	
	public static void loginService () throws Exception {
//	    启动程序
		StartUpExeUtils.startUpExe ("CMD /C " + ExeAddress.exeAddress (), "Nox.exe");
//      模拟器全屏
//      单击阴阳师图标
		Thread.sleep (30000);
		logger.info ("开始单击阴阳师图标");
		File file1=new File ("D:\\Study\\Project\\leetcode\\java\\src\\main\\resources\\image\\scenario\\阴阳师图标.png");
		if(file1.exists ()){
			ImageRecognition.imageRecognition (file1);
		}
		else{
			logger.info ("图标路径不存在");
		}
//		跳过开场动画
//		跳过公告

	}
}
