package com.example.controller;

import com.example.model.map.ExeAddress;
import com.example.util.StartUpExeUtils;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

import static com.example.App.ThreadSecondIsAlive;
import static com.example.controller.LoginController.loginGame;
import static com.example.service.MailService.sendMail;

public class ThreadSecondController extends Thread {
	public  static  final Logger logger = LogManager.getLogger ("ThreadSecondController");
	
	@SneakyThrows
	public void run () {
		logger.info ("1 阴阳寮结界+个人结界+魂土40次");
		logger.info ("2 个人结界+魂土60次");
		logger.info ("3 阴阳寮结界+个人结界+业原火40张");
		logger.info ("4 个人结界+日轮之陨50次");
		logger.info ("5 个人结界+永生之海30次");
		logger.info ("6 御灵60次");
		logger.info ("7 斗技10次");
		logger.info ("8 当前活动战斗");
		logger.info ("9 小号刷大号协战");
		logger.info ("10 阴阳寮结界");
		logger.info ("11 阴阳寮结界+斗技5次");
		logger.info ("12 御魂整理-速度强化-极限副属性");
		logger.info ("13 个人结界+探索20次");
		Scanner scanner=new Scanner(System.in);
		logger.info ("输入一个轮次");
		int a=scanner.nextInt();//输入一个轮次
		logger.info ("输入一个选项");
		int b=scanner.nextInt();//输入一个选项
		//启动游戏
		if(!StartUpExeUtils.checkProcessOnly ("Nox.exe")){
			StartUpExeUtils.startUpExeOnly ("CMD /C " + ExeAddress.exeAddress ());
		}
		//logger.info ("等待6小时");
		//Thread.sleep (1000*60*60*6);
		//  进入登录界面，默认直接登录，切换账号暂时无法实现
		try{
			loginGame();
			//	开启轮次、选项循环
			FightController.fightGame (a,b);
			sendMail();
		}
		catch (Exception e) {
			//  结束进程2
			ThreadSecondIsAlive=false;
			logger.info (e);
			logger.info ("异常退出");
		}
		//  结束进程2
		ThreadSecondIsAlive=false;
		
	}
}
