package com.example.controller;

import com.example.service.ImageService;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.example.App.ThreadSecondIsAlive;
import static com.example.App.logger;
import static com.example.service.MailService.sendMail;

public class ThreadFirstController extends Thread {
	public  static  final Logger logger = LogManager.getLogger ("ThreadFirstController");
	@SneakyThrows
	public void run () {
		System.out.println ("拒接协战");
		String file1 = "scenario/御魂/拒接协战";
		String file2 = "scenario/登录/阴阳师图标";
		boolean b;
		while (ThreadSecondIsAlive){
			ImageService.imagesClickBack (file1,1,false);
			Thread.sleep (60 * 1000);
			b = ImageService.imagesClickBackIsEmpty (file2,1,false);
			if (b) {
				logger.info ("异常退出");
				sendMail();
				System.exit (0);
			}
			
		}
	}
}
