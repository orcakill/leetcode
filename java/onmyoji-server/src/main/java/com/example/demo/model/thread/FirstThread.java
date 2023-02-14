package com.example.demo.model.thread;

import com.example.demo.model.entity.GameThreadPO;
import com.example.demo.service.GameThreadService;
import com.example.demo.service.ImageService;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import static com.example.demo.model.enums.GameEnum.comm_JJXZ;


@Component
@Log4j2
public class FirstThread extends Thread {
	
	private final GameThreadService gameThreadService;
	
	public FirstThread(GameThreadService gameThreadService) {
		this.gameThreadService = gameThreadService;
	}
	
	
	private  String  threadId;


	public void setThreadId(String threadId) {
		this.threadId = threadId;
	}

	@SneakyThrows
	public void run () {
		int threadState=gameThreadService.findById (threadId).getThreadState ();
		GameThreadPO gameThreadPO;
		log.info ("监控线程开始运行");
		//每间隔1分钟运行一次，共运行一天
		while (threadState!=2){
			//检查是否有悬赏封印，有则点击
			ImageService.imagesBackSingleHide (comm_JJXZ.getValue (),0, 1, false);
			threadState=gameThreadService.findById (threadId).getThreadState ();
			//等待一分钟
			Thread.sleep (10 * 1000);
		}
		log.info ("监控线程结束");
	}
}
