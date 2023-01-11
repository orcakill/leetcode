package com.example.test;

import com.example.model.entity.BufferedImagePO;
import com.example.model.entity.PictureIdentifyWorkPO;
import com.example.util.ImagesOpenCVSIFT;
import com.example.util.RandomUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.opencv.calib3d.Calib3d;
import org.opencv.core.*;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.Features2d;
import org.opencv.features2d.SIFT;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

import static com.example.util.ImagesOpenCVSIFT.getMatify;

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
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool ();
		List<Future<Integer>> resultList = new ArrayList<> ();
		for (Integer integer : list) {
			FactorialCalculator factorialCalculator = new FactorialCalculator (integer);
			Future<Integer> res = executor.submit (factorialCalculator);
			resultList.add (res);
		}
		do {
			for (Future<Integer> integerFuture : resultList) {
				logger.info (integerFuture.get ());
			}
		} while (executor.getCompletedTaskCount () < resultList.size ());
		executor.shutdownNow ();
		long end = System.currentTimeMillis ();
		logger.info ("doParallelStream cost:" + (end - start) / 1000);
		
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
			futureList.add (executor.submit (() -> execute (list.get (finalI), countDownLatch)));
		}
		countDownLatch.await ();
		logger.info ("--------------------");
		for (Future<?> future : futureList) {
			future.cancel (true);
		}
		for(int i=0;i<futureList.size ();i++){
			if(futureList.get (i).get ()!=null){
				logger.info ("结果{}",futureList.get (i).get ());
				break;
			}
		}
		long end = System.currentTimeMillis ();
		logger.info ("总耗时 :{}秒", (end - start) / 1000);
	}
	
	private static  Integer execute (int val, CountDownLatch countDownLatch) {
		try {
			Thread.sleep (1000);
		} catch (InterruptedException e) {
			throw new RuntimeException (e);
		}
		logger.info (val);
		if (val == 6) {
			countDownLatch.countDown ();
			return val;
		}
		return null;
	}
	
	@Test
	public void test5 () throws ExecutionException, InterruptedException {
		long start = System.currentTimeMillis ();
		ExecutorService executor = Executors.newFixedThreadPool (5);
		CountDownLatch countDownLatch = new CountDownLatch (1);
		List<Future> futureList = new ArrayList<> ();
		List<Integer> list = new ArrayList<> ();
		for (int i = 0; i < 10; i++) {
			list.add (i);
		}
		for (Integer integer : list) {
			FactorialCalculator1 factorialCalculator1 = new FactorialCalculator1 (integer, countDownLatch);
			futureList.add (executor.submit (factorialCalculator1));
		}
		countDownLatch.await ();
		logger.info ("--------------------");
		for (Future<?> future : futureList) {
			future.cancel (true);
		}
		for(int i=0;i<futureList.size ();i++){
			if(futureList.get (i).get ()!=null){
				logger.info ("结果{}",futureList.get (i).get ());
				break;
			}
		}
		long end = System.currentTimeMillis ();
		logger.info ("总耗时 :{}秒", (end - start) / 1000);
	}
	
	public static class FactorialCalculator1 implements Callable<Integer> {
		
		private final Integer number;
		
		private final CountDownLatch countDownLatch;
		
		public FactorialCalculator1 (Integer number, CountDownLatch countDownLatch) {
			this.number = number;
			this.countDownLatch = countDownLatch;
		}
		
		public Integer call () {
			try {
				Thread.sleep (1000);
			} catch (InterruptedException e) {
				throw new RuntimeException (e);
			}
			logger.info (number);
			if (number == 4) {
				countDownLatch.countDown ();
				return number;
			}
			return null;
		}
	}
	public  void  test() throws IOException {
		long start = System.currentTimeMillis ();
		String str_src = "D:/a.png";
		List<BufferedImagePO> ImagesData = ImagesOpenCVSIFT.readFiles ("scenario/阴阳寮/阴阳寮图标");
		File file_src = new File (str_src);
		BufferedImage bufferedImageSrc = ImageIO.read (file_src);
		long end = System.currentTimeMillis ();
		logger.info ("总耗时 :{}秒", (end - start) / 1000);
	}
	
	@Test
	public  void  test7() throws IOException {
		long start = System.currentTimeMillis ();
		ExecutorService executor = Executors.newFixedThreadPool (5);
		CountDownLatch countDownLatch = new CountDownLatch (1);
		List<Future> futureList = new ArrayList<> ();
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		String str_src = "D:/a.png";
		List<BufferedImagePO> ImagesData = ImagesOpenCVSIFT.readFiles ("scenario/阴阳寮/阴阳寮图标");
		File file_src = new File (str_src);
		BufferedImage bufferedImageSrc = ImageIO.read (file_src);
	}
	
	public static class FactorialCalculator2 implements Callable<PictureIdentifyWorkPO> {
		private final BufferedImage originalImageB;
		
		private final BufferedImage templateImageB;
		
		private final Double coefficient;
		
		private final Boolean printOrNot;
		private final int characteristicPoint;
		private final  CountDownLatch countDownLatch;
		
		public FactorialCalculator2 (BufferedImage originalImageB, BufferedImage templateImageB, Double coefficient,
		                             Boolean printOrNot, int characteristicPoint,CountDownLatch countDownLatch) {
			this.originalImageB = originalImageB;
			this.templateImageB = templateImageB;
			this.coefficient = coefficient;
			this.printOrNot = printOrNot;
			this.characteristicPoint = characteristicPoint;
			this.countDownLatch=countDownLatch;
		}
	
		
		public PictureIdentifyWorkPO call () {
			PictureIdentifyWorkPO pictureIdentifyWorkPO=ImagesOpenCVSIFT.matchImage (originalImageB,templateImageB,coefficient,
			                                                                         printOrNot,characteristicPoint);
			if (pictureIdentifyWorkPO.getX ()!=null&&pictureIdentifyWorkPO.getY ()!=null) {
				countDownLatch.countDown ();
				return pictureIdentifyWorkPO;
			}
			return pictureIdentifyWorkPO;
		}
		
	}

}
