package com.example.demo.model.thread;

import com.example.demo.config.BeanContextConfig;
import com.example.demo.model.entity.GameThreadPO;
import com.example.demo.model.map.FolderPathMap;
import com.example.demo.service.GameThreadService;
import com.example.demo.service.OnmyojiService;
import com.example.demo.utils.StartUpExeUtils;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

import static com.example.demo.service.MailService.sendMail;


@Log4j2
@Component
public class SecondThread extends Thread {
	private  String threadId;
	
	private  int type;
	private  int round;
	
	public void setThreadId (String threadId) {
		this.threadId = threadId;
	}
	
	public void setType (int type) {
		this.type = type;
	}
	
	public void setRound (int round) {
		this.round = round;
	}
	
	@SneakyThrows
	public void run () {
		GameThreadService gameThreadService = BeanContextConfig.getApplicationContext ().getBean (GameThreadService.class);
		OnmyojiService onmyojiService = BeanContextConfig.getApplicationContext ().getBean (OnmyojiService.class);
		int threadState=gameThreadService.findById (threadId).getThreadState ();
		while (threadState!=2){
			//启动游戏
			log.info ("运行线程，检查模拟器");
			if (!StartUpExeUtils.checkProcessOnly ("Nox.exe")) {
				StartUpExeUtils.startUpExeOnly ("CMD /C " + FolderPathMap.exeAddress ());
			}
			//	阴阳师自动化
			log.info ("项目 {},轮次 {}",type,round);
			onmyojiService.autoActivity(type,round);
			// 进程结束,保存进程信息
			log.info ("运行线程，游戏进程状态更新");
			log.info ("运行线程，游戏进程ID{}",threadId);
			GameThreadPO gameThreadPO=gameThreadService.findById (threadId);
			gameThreadPO.setThreadState (2);
			InetAddress inetAddress = InetAddress.getLocalHost ();
			gameThreadPO.setCreateUser (inetAddress.getHostName ());
			gameThreadService.save (gameThreadPO);
			log.info ("运行线程，游戏进程状态为已完成");
			threadState=gameThreadService.findById (threadId).getThreadState ();
		}
		log.info ("运行线程结束");
		//  邮件发送
		log.info ("运行线程，邮件发送");
		sendMail ();
	}
}
