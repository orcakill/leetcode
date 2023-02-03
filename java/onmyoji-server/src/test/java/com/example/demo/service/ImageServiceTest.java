package com.example.demo.service;

import com.example.demo.model.entity.PictureCollectionPO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.opencv.core.Core;
import org.springframework.boot.test.context.SpringBootTest;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * @Classname ImageServiceTest
 * @Description ImageServiceTest
 * @Date 2023/1/28 15:59
 * @Created by orcakill
 */
@Log4j2
@SpringBootTest
class ImageServiceTest {
	//默认图像识别算法，RGB模板匹配
	@Test
	void imagesBack0 () throws IOException, InterruptedException, AWTException {
		System.setProperty ("java.awt.headless", "false");
		log.info ("测试开始");
		boolean b = ImageService.imagesBack0 ("首页\\编号\\5561731");
		log.info (b);
	}
	
	@Test
	void imagesBack () throws IOException, InterruptedException, AWTException {
		System.setProperty ("java.awt.headless", "false");
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		log.info ("测试开始");
		boolean b = ImageService.imagesBack ("1", 2);
		log.info (b);
	}
	
	@Test
	void imagesBackSingleHide () throws IOException, InterruptedException, AWTException {
		System.setProperty ("java.awt.headless", "false");
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		log.info ("测试开始");
		boolean b = ImageService.imagesBackSingleHide ("1", 2, 1, true);
		log.info (b);
	}
	
	@Test
	void imagesBackSingleHideIsEmpty () throws IOException, InterruptedException, AWTException {
		System.setProperty ("java.awt.headless", "false");
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		log.info ("测试开始");
		boolean b = ImageService.imagesBackSingleHideIsEmpty ("1", 2, 1, true);
		log.info (b);
	}
	
	@Test
	//初始化图片
	void  bufferedImageTest() throws IOException {
		long a=System.currentTimeMillis ();
		log.info ("测试开始");
		System.setProperty ("java.awt.headless", "false");
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		String path = null;
		//Map<String,List<BufferedImagePO>> map=new HashMap<String,List<BufferedImagePO>>();
		String path1="D:\\study\\Project\\leetcode\\java\\onmyoji-server\\src\\main" +
		            "\\resources\\static\\scenario\\";
		String path2="D:\\project\\leetcode\\java\\onmyoji-server\\src\\main\\resources\\static\\scenario\\";
		File file=new File (path1);
		File file1=new File (path2);
		if(file.exists ()){
			path=path1;
		}
		if(file1.exists ()){
			path=path2;
		}
		Map<String, List<PictureCollectionPO>> map =test (path);
		log.info (map.size ());
		Map<String, List<PictureCollectionPO>> map1 =test1 (path);
		log.info (map1.size ());
		log.info ("测试结束");
		long b=System.currentTimeMillis ();
		log.info ("用时{}毫秒",b-a);
	}
	
	private static Map<String, List<PictureCollectionPO>> test (String path) throws IOException {
		List<File> files =new ArrayList<> ();
		try (Stream<Path> paths = Files.walk (Paths.get (path))){
			files = paths.filter (Files::isRegularFile).map(l -> new File (l.toString ())).collect (Collectors.toList ());
		} catch (IOException e) {
			e.printStackTrace();
		}
		//创建map
		Map<String, List<PictureCollectionPO>> result = new TreeMap<> ();
		Map<String,Integer> map=new TreeMap<> ();
		//遍历所有信息，添加map
		for (File file : files) {
			String name=file.getParent ().replace(path,"");
			PictureCollectionPO pictureCollectionPO =new PictureCollectionPO (1, name,ImageIO.read (file), null);
			int width = pictureCollectionPO.getImage ().getWidth ();
			int height = pictureCollectionPO.getImage ().getHeight ();
			int [][]img = new int[width][height];
			for (int w = 0; w < width; w++) {
				for (int h = 0; h < height; h++) {
					img[w][h] = pictureCollectionPO.getImage ().getRGB (w, h);
				}
			}
			pictureCollectionPO.setTwoArray (img);
			if (result.containsKey(name)) { //包含相同编号，
				int num=map.get(name);
				pictureCollectionPO.setImageNumber (num + 1);
				result.get(name).add (pictureCollectionPO); //相同编号追加到集合中
				map.put(name, num+1);
			}
			else { //每组中只有一个的
				ArrayList<PictureCollectionPO> list = new ArrayList<>();
				pictureCollectionPO.setImageNumber (0);
				list.add (pictureCollectionPO);
				map.put (name,0);
				result.put(name, list);//直接添加到集合中
			}
		}
		
		return result;
	}
	
	private static Map<String, List<PictureCollectionPO>> test1 (String path) throws IOException {
		List<File> files =new ArrayList<> ();
		try (Stream<Path> paths = Files.walk (Paths.get (path))){
			files = paths.filter (Files::isRegularFile).map(l -> new File (l.toString ())).collect (Collectors.toList ());
		} catch (IOException e) {
			e.printStackTrace();
		}
		//创建map
		Map<String, List<PictureCollectionPO> >result = new TreeMap<> ();
		//遍历所有信息，添加map
		for (File file : files) {
			String name=file.getParent ().replace(path,"");
			PictureCollectionPO pictureCollectionPO =new PictureCollectionPO (1, name,ImageIO.read (file), null);
			int width = pictureCollectionPO.getImage ().getWidth ();
			int height = pictureCollectionPO.getImage ().getHeight ();
			int [][]img = new int[width][height];
			for (int w = 0; w < width; w++) {
				for (int h = 0; h < height; h++) {
					img[w][h] = pictureCollectionPO.getImage ().getRGB (w, h);
				}
			}
			pictureCollectionPO.setTwoArray (img);
			if (result.containsKey(name)) { //包含相同编号，
				result.get(name).add (pictureCollectionPO); //相同编号追加到集合中
			}
			else { //每组中只有一个的
				ArrayList<PictureCollectionPO> list = new ArrayList<>();
				pictureCollectionPO.setImageNumber (0);
				list.add (pictureCollectionPO);
				result.put(name, list);//直接添加到集合中
			}
		}
		Set<Map.Entry<String, List<PictureCollectionPO>>> entries = result.entrySet ();
		for (Map.Entry<String, List<PictureCollectionPO>> entry : entries) {
			List<PictureCollectionPO> pictureCollectionPOList =entry.getValue ();
			for(int i=0; i < pictureCollectionPOList.size ();i++){
				PictureCollectionPO pictureCollectionPO = pictureCollectionPOList.get (i);
				pictureCollectionPO.setImageNumber (i);
				pictureCollectionPOList.set (i, pictureCollectionPO);
			}
			result.put (entry.getKey (), pictureCollectionPOList);
		}
		return result;
	}
}