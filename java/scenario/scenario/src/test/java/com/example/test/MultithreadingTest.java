package com.example.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author orcakill
 * @version 1.0.0
 * @ClassName MultithreadingTest.java
 * @Description 多线程测试
 * @createTime 2023年01月09日 08:37:00
 */
public class MultithreadingTest {
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
	public  void  test3() {
		long start = System.currentTimeMillis ();
		
		int CORE_POOL_SIZE = 5;
		int MAX_POOL_SIZE = 10;
		int QUEUE_CAPACITY = 100;
		Long KEEP_ALIVE_TIME = 1L;
		ThreadPoolExecutor executor = new ThreadPoolExecutor(
				CORE_POOL_SIZE,
				MAX_POOL_SIZE,
				KEEP_ALIVE_TIME,
				TimeUnit.SECONDS,
				new ArrayBlockingQueue<> (QUEUE_CAPACITY),
				new ThreadPoolExecutor.CallerRunsPolicy());

		List<Integer> list = new ArrayList<> ();
		for (int i = 0; i < 20; i++) {
			list.add (i);
		}
		for (int i = 0; i < list.size (); i++) {
			//创建任务
             Task task = new Task ("Task " + i, list.get (i));
			 executor.execute(task);
		}
		executor.shutdown();
		long end = System.currentTimeMillis ();
		System.out.println ("doParallelStream cost:" + (end - start));
	}
	
	public  class Task implements Runnable {
		private String name;
		
		private Integer value;
		

		public Task (String name, Integer value) {
			this.name = name;
			this.value= value;
		}
		
		public String getName() {
			return name;
		}
		
		public Integer getValue () {
			return value;
		}
		
		
		public void run() {
			try {
				//Thread.sleep (1000);
				//System.out.println("线程名 : " + name +"  值："+value);
				Long duration = (long) (Math.random() * 10);
				System.out.println("Executing : " + name+"  "+value);
				TimeUnit.SECONDS.sleep(duration);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
