package com.example.controller;

import com.example.service.ImageService;
import lombok.SneakyThrows;

public class ThreadFirstController extends Thread {
	@SneakyThrows
	public void run () {
		System.out.println ("拒接协战");
		String file1 = "scenario/御魂/拒接协战";
		String file2 = "scenario/登录/阴阳师图标";
		boolean b;
		while (true){
			ImageService.imagesClickBack (file1,1,false);
			Thread.sleep (60 * 1000);
			b = ImageService.imagesClickBackIsEmpty (file2,1);
			if (b) {
				System.out.println ("异常退出");
				System.exit (0);
			}
			
		}
	}
}
