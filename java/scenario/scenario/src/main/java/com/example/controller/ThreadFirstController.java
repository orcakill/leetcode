package com.example.controller;

import com.example.service.ImageService;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.example.App.ThreadSecondIsEnd;
import static com.example.App.mobileDesktop;

public class ThreadFirstController extends Thread {
	public  static  final Logger logger = LogManager.getLogger ("ThreadFirstController");
	@SneakyThrows
	public void run () {
		String file1 = "scenario/御魂/拒接协战";
		String file2 = "scenario/登录/阴阳师图标";
		boolean b;
		while (!ThreadSecondIsEnd){
			ImageService.imagesClickBack (file1,1,false);
			Thread.sleep (60 * 1000);
			b = ImageService.imagesClickBackIsEmpty (file2,1,false);
			if (b) {
				logger.info ("检查到异常退出");
				mobileDesktop = true;
				Thread.sleep (5 * 60 * 1000);
			}
			
		}
		logger.info ("结束监控线程");
	}
}
