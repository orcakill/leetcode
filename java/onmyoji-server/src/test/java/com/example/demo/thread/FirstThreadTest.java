package com.example.demo.thread;

import com.example.demo.service.GameThreadService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.opencv.core.Core;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Classname FirstThreadTest
 * @Description FirstThreadTest
 * @Date 2023/1/28 17:41
 * @Created by orcakill
 */
@SpringBootTest
@Log4j2
class FirstThreadTest {
	@Autowired
	private GameThreadService gameThreadService;
	@Test
	void test1() throws InterruptedException {
		System.setProperty ("java.awt.headless", "false");
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		log.info ("测试开始");
		FirstThread t1=new FirstThread (gameThreadService);
		t1.setThreadId ("63d4ebd00e25827a62f447fc");
		t1.start ();
		boolean b=t1.isAlive ();
		while (b){
			Thread.sleep (1000);
			b=t1.isAlive ();
		}
		log.info ("测试结束");
		
	}
}