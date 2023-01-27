package com.example.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Classname Timer
 * @Description TODO
 * @Date 2023/1/26 17:11
 * @Created by orcakill
 */
public class Timer {
	
	    public static final Logger logger = LogManager.getLogger ("Timer");
		public static void main(String[] args) {
			logger.info ("开始");
			final int[] num = {0};
			// 创建定时器任务
			TimerTask timerTask = new TimerTask() {
				@Override
				public void run() {
					logger.info (num[0]);
					num[0] = num[0] + 1;
					if (num[0] > 11) {
						this.cancel ();
					}
				}
			};
			ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool (2);
			scheduledThreadPool.scheduleAtFixedRate(timerTask, 1000, 1000, TimeUnit.MILLISECONDS);
			while (!scheduledThreadPool.isShutdown ()){
			}
			logger.info ("结束");
		}
}
