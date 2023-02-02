package com.example.demo.service;

import com.example.demo.model.entity.BufferedImagePO;
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

import static com.example.demo.model.enums.GameEnum.home_TS;

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
		boolean b = ImageService.imagesBack0 (home_TS.getValue ());
		log.info (b);
	}
	
	@Test
	void imagesBack () throws IOException, InterruptedException, AWTException {
		System.setProperty ("java.awt.headless", "false");
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		log.info ("测试开始");
		boolean b = ImageService.imagesBack (home_TS.getValue (), 2);
		log.info (b);
	}
	
	@Test
	void imagesBackSingleHide () throws IOException, InterruptedException, AWTException {
		System.setProperty ("java.awt.headless", "false");
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		log.info ("测试开始");
		boolean b = ImageService.imagesBackSingleHide (home_TS.getValue (), 2, 1, true);
		log.info (b);
	}
	
	@Test
	void imagesBackSingleHideIsEmpty () throws IOException, InterruptedException, AWTException {
		System.setProperty ("java.awt.headless", "false");
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		log.info ("测试开始");
		boolean b = ImageService.imagesBackSingleHideIsEmpty (home_TS.getValue (), 2, 1, true);
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
		            "\\resources\\static\\scenario";
		String path2="D:\\project\\leetcode\\java\\onmyoji-server\\src\\main\\resources\\static\\scenario";
		File file=new File (path1);
		File file1=new File (path2);
		if(file.exists ()){
			path=path1;
		}
		if(file1.exists ()){
			path=path2;
		}
		Map<String, List<BufferedImagePO>> map =test (path);
		Map<String, List<BufferedImagePO>> map1 =test1 (path);
		log.info (map.size ());
		log.info (map1.size ());
		log.info ("测试结束");
		long b=System.currentTimeMillis ();
		log.info ("用时{}毫秒",b-a);
	}
	
	private static Map<String, List<BufferedImagePO>> test(String path) throws IOException {
		List<File> files =new ArrayList<> ();
		try (Stream<Path> paths = Files.walk (Paths.get (path))){
			files = paths.filter (Files::isRegularFile).map(l -> new File (l.toString ())).collect (Collectors.toList ());
		} catch (IOException e) {
			e.printStackTrace();
		}
		//创建map
		Map<String, List<BufferedImagePO>> result = new TreeMap<> ();
		Map<String,Integer> map=new TreeMap<> ();
		//遍历所有信息，添加map
		for (File file : files) {
			String name=file.getParent ().replace(path,"");
			BufferedImagePO bufferedImagePO=new BufferedImagePO (1,name,ImageIO.read (file));
			if (result.containsKey(name)) { //包含相同编号，
				int num=map.get(name);
				bufferedImagePO.setImageNumber (num+1);
				result.get(name).add(bufferedImagePO); //相同编号追加到集合中
				map.put(name, num+1);
			}
			else { //每组中只有一个的
				ArrayList<BufferedImagePO> list = new ArrayList<>();
				bufferedImagePO.setImageNumber (0);
				list.add(bufferedImagePO);
				map.put (name,0);
				result.put(name, list);//直接添加到集合中
			}
		}
		
		return result;
	}
	
	private static Map<String, List<BufferedImagePO>> test1(String path) throws IOException {
		List<File> files =new ArrayList<> ();
		try (Stream<Path> paths = Files.walk (Paths.get (path))){
			files = paths.filter (Files::isRegularFile).map(l -> new File (l.toString ())).collect (Collectors.toList ());
		} catch (IOException e) {
			e.printStackTrace();
		}
		//创建map
		Map<String, List<BufferedImagePO> >result = new TreeMap<> ();
		//遍历所有信息，添加map
		for (File file : files) {
			String name=file.getParent ().replace(path,"");
			BufferedImagePO bufferedImagePO=new BufferedImagePO (1,name,ImageIO.read (file));
			if (result.containsKey(name)) { //包含相同编号，
				result.get(name).add(bufferedImagePO); //相同编号追加到集合中
			}
			else { //每组中只有一个的
				ArrayList<BufferedImagePO> list = new ArrayList<>();
				bufferedImagePO.setImageNumber (0);
				list.add(bufferedImagePO);
				result.put(name, list);//直接添加到集合中
			}
		}
		Set<Map.Entry<String, List<BufferedImagePO>>> entries = result.entrySet();
		for (Map.Entry<String, List<BufferedImagePO>> entry : entries) {
			List<BufferedImagePO> bufferedImagePOList=entry.getValue ();
			for(int i=0;i<bufferedImagePOList.size ();i++){
				BufferedImagePO bufferedImagePO=bufferedImagePOList.get (i);
				bufferedImagePO.setImageNumber (i);
				bufferedImagePOList.set(i,bufferedImagePO);
			}
			result.put (entry.getKey (),bufferedImagePOList);
		}

		return result;
	}
}