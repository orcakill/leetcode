package com.example.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author orcakill
 * @version 1.0.0
 * @ClassName MultithreadingTest.java
 * @Description 多线程测试
 * @createTime 2023年01月09日 08:37:00
 */
public class MultithreadingTest {
	
	private static final Logger logger = LogManager.getLogger ("MultithreadingTest");
	boolean b = false;
	
	@Test
	public void test1 () {
		long start = System.currentTimeMillis ();
		List<Integer> list = new ArrayList<> ();
		for (int i = 0; i < 10; i++) {
			list.add (i);
		}
		list.parallelStream ().forEach (x -> {
			try {
				Thread.sleep (1000);
			} catch (InterruptedException e) {
			}
			System.out.println (x.toString ());
		});
		long end = System.currentTimeMillis ();
		System.out.println ("doParallelStream cost:" + (end - start));
	}
	
	@Test
	public void test2 () throws InterruptedException {
		long start = System.currentTimeMillis ();
		List<Integer> list = new ArrayList<> ();
		for (int i = 0; i < 10; i++) {
			list.add (i);
		}
		for (Integer integer : list) {
			Thread.sleep (1000);
			System.out.println (integer);
		}
		long end = System.currentTimeMillis ();
		System.out.println ("doParallelStream cost:" + (end - start));
	}
	
	@Test
	public void test3 () {
		long start = System.currentTimeMillis ();
		b = false;
		List<Integer> list = new ArrayList<> ();
		for (int i = 0; i < 20; i++) {
			list.add (i);
		}
		ExecutorService executor = Executors.newFixedThreadPool (5);
		for (int i = 0; i < list.size (); i++) {
			Runnable worker = new WorkerThread ("" + i, list.get (i));
			executor.execute (worker);
		}
		executor.shutdown (); // This will make the executor accept no new threads and finish all existing threads in the queue
		while (!executor.isTerminated ()) { // Wait until all threads are finish,and also you can use "executor.awaitTermination();" to
			// wait
		}
		logger.info ("Finished all threads");
		long end = System.currentTimeMillis ();
		logger.info ("doParallelStream cost:" + (end - start) / 1000);
		
	}
	
	public class WorkerThread implements Runnable {
		
		private String command;
		
		private int val;
		
		public WorkerThread (String s, int m) {
			this.command = s;
			this.val = m;
		}
		
		@Override
		public void run () {
			processCommand ();
			logger.info ("{}:{}", command, val);
			if (val == 2) {
				b = true;
			}
		}
		
		private void processCommand () {
			try {
				Thread.sleep (1000);
			} catch (InterruptedException e) {
				e.printStackTrace ();
			}
		}
		
		@Override
		public String toString () {
			return this.command;
		}
	}
}
