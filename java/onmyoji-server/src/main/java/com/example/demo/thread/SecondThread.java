package com.example.demo.thread;

import com.example.demo.model.entity.GameThreadPO;
import com.example.demo.model.map.ExeAddress;
import com.example.demo.service.GameThreadService;
import com.example.demo.utils.StartUpExeUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.example.demo.service.MailService.sendMail;

@EqualsAndHashCode (callSuper = true)
@Slf4j
@Component
@Data
public class SecondThread extends Thread {

	private final GameThreadService gameThreadService;
	
	private  String threadId;
	
	private  int a;
	
	private  int b;
	
	public SecondThread (GameThreadService gameThreadService) {
		this.gameThreadService = gameThreadService;
	}
	
	@SneakyThrows
	public void run () {
		//启动游戏
		if (!StartUpExeUtils.checkProcessOnly ("Nox.exe")) {
			StartUpExeUtils.startUpExeOnly ("CMD /C " + ExeAddress.exeAddress ());
		}
		//	阴阳师自动化
		//  邮件发送
		sendMail ();
		// 进程结束,保存进程信息
		GameThreadPO gameThreadPO=gameThreadService.findById (threadId);
		gameThreadPO.setThreadState (1);
		gameThreadService.save (gameThreadPO);
	}
}
