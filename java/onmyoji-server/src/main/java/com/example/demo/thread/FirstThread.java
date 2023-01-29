package com.example.demo.thread;

import com.example.demo.model.entity.GameThreadPO;
import com.example.demo.service.GameThreadService;
import com.example.demo.service.ImageService;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import static com.example.demo.model.enums.GameEnum.comm_JJXZ;
import static com.example.demo.model.enums.GameEnum.login_YYSTB;


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
		boolean b;
		GameThreadPO gameThreadPO;
		log.info ("监控线程开始运行");
		//每间隔1分钟运行一次，共运行一天
		for(int i=0;i<3600;i++){
			gameThreadPO=gameThreadService.findById(threadId);
			//检查当前运行线程是否结束，运行线程结束，监控线程跳出
			if(gameThreadPO.getThreadState()==2){
				log.info ("运行线程已结束，监控线程中断");
				break;
			}
			//检查是否有悬赏封印，有则点击
			ImageService.imagesBackSingleHide (comm_JJXZ.getValue (),0, 1, false);
			//检查是否有阴阳师图标
			b = ImageService.imagesBackSingleHideIsEmpty (login_YYSTB.getValue(),0,1,
			                                              false                                                                                                                                                                                                                                                                                                                                                                                                                                          );
			if (b) {
				log.info("检查到阴阳师图标，重启运行线程，当前运行线程数加一");
				gameThreadPO.setThreadNumber(gameThreadPO.getThreadNumber()+1);
				gameThreadService.save(gameThreadPO);
				//等待5分钟
				Thread.sleep (10*1000);
			}
			//等待一分钟
			Thread.sleep (10 * 1000);
		}
		log.info ("监控线程结束");
	}
}
