package com.example.demo.thread;

import com.example.demo.model.thread.SecondThread;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.opencv.core.Core;
import org.springframework.boot.test.context.SpringBootTest;



/**
 * @Classname SecondThreadTest
 * @Description SecondThreadTest
 * @Date 2023/1/28 15:30
 * @Created by orcakill
 */
@Log4j2
@SpringBootTest
class SecondThreadTest {

	@Test
	void test1() throws InterruptedException {
		System.setProperty ("java.awt.headless", "false");
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		log.info ("测试开始");
		SecondThread t1=new SecondThread ();
		t1.setThreadId ("63f64f8260a59767118e6300");
		t1.start ();
		boolean b=t1.isAlive ();
		while (b){
			Thread.sleep (1000);
			b=t1.isAlive ();
		}
		log.info ("测试结束");
		
	}
}