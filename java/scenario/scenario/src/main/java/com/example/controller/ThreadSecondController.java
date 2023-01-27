package com.example.controller;

import com.example.model.map.ExeAddress;
import com.example.util.StartUpExeUtils;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.example.App.ThreadSecondIsEnd;
import static com.example.controller.LoginController.loginGame;
import static com.example.service.MailService.sendMail;

public class ThreadSecondController extends Thread {
	public static final Logger logger = LogManager.getLogger ("ThreadSecondController");
	
	private final int a;
	
	private final int b;
	
	public ThreadSecondController (int a, int b) {
		this.a=a;
		this.b=b;
	}
	
	@SneakyThrows
	public void run () {

		//启动游戏
		if (!StartUpExeUtils.checkProcessOnly ("Nox.exe")) {
			StartUpExeUtils.startUpExeOnly ("CMD /C " + ExeAddress.exeAddress ());
		}
		loginGame ();
		//	开启轮次、选项循环
		FightController.fightGame (a, b);
		sendMail ();
		// 进程结束
		ThreadSecondIsEnd=true;
	}
}
