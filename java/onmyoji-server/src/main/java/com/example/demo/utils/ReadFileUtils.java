package com.example.demo.utils;

import com.example.demo.model.entity.PictureCollectionPO;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Classname ReadFileUtils
 * @Description 文件读取工具类
 * @Date 2023/1/28 16:49
 * @Created by orcakill
 */
public class ReadFileUtils {
	/**
	 * 本方法根据文件夹名从当前项目中找文件夹并且返回文件夹所有照片文件
	 * @param FolderName - 指定的文件夹名字
	 * @return - 返回指定文件夹内的所有照片文件
	 */
	public static List<BufferedImage> readFilesBufferedImage (String FolderName) {
		
		try {
			// 获取当前目录下的指定文件夹
			File Folder = new File (
					"D:/project/leetcode/java/onmyoji-server/src/main/resources/static/scenario/" + FolderName);
			if (!Folder.exists ()) {
				Folder = new File (
						"D:/study/Project/leetcode/java/onmyoji-server/src/main/resources/static/scenario/" +
						FolderName);
			}
			if (Folder.isDirectory ()) {
				List<BufferedImage> files = new ArrayList<> ();
				String[] fileList = Folder.list ();
				// 将所有照片存储并且返回
				assert fileList != null;
				
				for (String s : fileList) {
					File file = new File (Folder + File.separator + s);
					// 判断是否为照片文件
					String[] strArray = file.getName ()
					                        .split ("\\.");
					int suffixIndex = strArray.length - 1;
					// 存储照片文件
					if (!file.isDirectory ()
					    && (strArray[suffixIndex].equals ("png") || strArray[suffixIndex].equals ("jpg"))) {
						BufferedImage img = ImageIO.read (file);
						files.add (img);
					}
					
				}
				return files;
			}
		} catch (Exception e) {
			
			e.getStackTrace ();
			
		}
		
		return null;
		
	}
	
	/**
	 * 本方法根据文件夹名从当前项目中找文件夹并且返回文件夹所有照片文件
	 * @param FolderName - 指定的文件夹名字
	 * @return - 返回指定文件夹内的所有照片文件
	 */
	public static List<PictureCollectionPO> readFilesBufferedImagePO (String FolderName) {
		try {
			// 判断当前目录下的指定文件夹是否存在
			File Folder = new File (
					"D:/project/leetcode/java/onmyoji-server/src/main/resources/static/scenario/" + FolderName);
			if (!Folder.exists ()) {
				Folder = new File (
						"D:/study/Project/leetcode/java/onmyoji-server/src/main/resources/static/scenario/"
						+ FolderName);
			}
			if (Folder.isDirectory ()) {
				List<PictureCollectionPO> files = new ArrayList<> ();
				String[] fileList = Folder.list ();
				// 将所有照片存储并且返回
				for (int i = 0; i < Objects.requireNonNull (fileList).length; i++) {
					String s = fileList[i];
					File file = new File (Folder + File.separator + s);
					// 判断是否为照片文件
					String[] strArray = file.getName ().split ("\\.");
					int suffixIndex = strArray.length - 1;
					// 存储照片文件
					if (!file.isDirectory () && (
							strArray[suffixIndex].equals ("png")
							|| strArray[suffixIndex].equals ("jpg"))) {
						BufferedImage img = ImageIO.read (file);
						PictureCollectionPO pictureCollectionPO = new PictureCollectionPO (i, s, img, null);
						files.add (pictureCollectionPO);
					}
				}
				return files;
			}
		} catch (Exception e) {
			e.getStackTrace ();
			
		}
		return null;
	}
	
	public static Map<String, List<PictureCollectionPO>> readPictureMap (String path) throws IOException {
		List<File> files =new ArrayList<> ();
		try (Stream<Path> paths = Files.walk (Paths.get (path))){
			files = paths.filter (Files::isRegularFile).map(l -> new File (l.toString ())).collect (
					Collectors.toList ());
		} catch (IOException e) {
			e.printStackTrace();
		}
		//创建map
		Map<String, List<PictureCollectionPO> >result = new TreeMap<> ();
		//遍历所有信息，添加map
		for (File file : files) {
			String name=file.getParent ().replace(path,"");
			PictureCollectionPO pictureCollectionPO =new PictureCollectionPO (1, name, ImageIO.read (file), null);
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