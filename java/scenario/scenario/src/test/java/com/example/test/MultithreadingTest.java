package com.example.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author orcakill
 * @version 1.0.0
 * @ClassName MultithreadingTest.java
 * @Description 多线程测试
 * @createTime 2023年01月09日 08:37:00
 */
public class MultithreadingTest {
	
	private static final Logger logger = LogManager.getLogger ("MultithreadingTest");

	
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
	public void test3 () throws ExecutionException, InterruptedException {
		long start = System.currentTimeMillis ();
		List<Integer> list = new ArrayList<> ();
		for (int i = 0; i < 100; i++) {
			list.add (i);
		}
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
		List<Future<Integer>> resultList = new ArrayList<> ();
		for (int i = 0; i < list.size (); i ++) {
			FactorialCalculator factorialCalculator = new FactorialCalculator (list.get (i));
			Future<Integer> res = executor.submit (factorialCalculator);
			resultList.add(res);
		}
		do {
			for (int i = 0; i < resultList.size(); i++) {
				logger.info(resultList.get (i).get ());
			}
		} while (executor.getCompletedTaskCount() < resultList.size());
		executor.shutdownNow ();
		long end = System.currentTimeMillis ();
		logger.info ("doParallelStream cost:" + (end - start) / 1000);
		
	}
	
	
	private static String execute (int i, int val, CountDownLatch countDownLatch) {
		try {
			Thread.sleep (1000);
		} catch (InterruptedException e) {
			throw new RuntimeException (e);
		}
		logger.info (val);
		if (val == 6) {
			countDownLatch.countDown ();
			return String.valueOf (val);
		}
		return null;
	}
	
	@Test
	public void test4 () throws ExecutionException, InterruptedException {
		long start = System.currentTimeMillis ();
		ExecutorService executor = Executors.newFixedThreadPool (5);
		CountDownLatch countDownLatch = new CountDownLatch (1);
		List<Future> futureList = new ArrayList<> ();
		List<Integer> list = new ArrayList<> ();
		for (int i = 0; i < 10; i++) {
			list.add (i);
		}
		for (int i = 0; i < list.size (); i++) {
			int finalI = i;
			futureList.add (executor.submit (() -> execute (finalI, list.get (finalI), countDownLatch)));
		}
		countDownLatch.await ();
		logger.info ("--------------------");
		for (Future<?> future : futureList) {
			future.cancel (true);
		}
		logger.info ("结果{}", futureList.get (0).get ());
		long end = System.currentTimeMillis ();
		logger.info ("总耗时 :{}秒", (end - start) / 1000);
	}
	
	public static class FactorialCalculator implements Callable<Integer> {
		
		private final Integer number;
		
		public FactorialCalculator (Integer number) {
			this.number = number;
		}
		
		public Integer call () throws Exception {
			Thread.sleep (1000);
			return number;
		}
	}
}
