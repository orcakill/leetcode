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

//      单击阴阳师图标
		File file1=new File ("java/src/main/resources/image/scenario/阴阳师图标.png");
		logger.info ("单击阴阳师图标，进入游戏");
		ImageService.imageClick (file1);
		logger.info ("进入游戏成功");
//      模拟器全屏
		File file2=new File ("java/src/main/resources/image/scenario/模拟器全屏.png");
		logger.info ("单击窗口最大化");
		ImageService.imageClick (file2);
		logger.info ("窗口最大化最大化成功");

	}
}
