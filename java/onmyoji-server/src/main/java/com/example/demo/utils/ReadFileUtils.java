package com.example.demo.utils;

import com.example.demo.model.entity.BufferedImagePO;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
	public static List<BufferedImagePO> readFilesBufferedImagePO (String FolderName) {
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
				List<BufferedImagePO> files = new ArrayList<> ();
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
						BufferedImagePO bufferedImagePO = new BufferedImagePO (i, s, img);
						files.add (bufferedImagePO);
					}
				}
				return files;
			}
		} catch (Exception e) {
			e.getStackTrace ();
			
		}
		return null;
	}
}
