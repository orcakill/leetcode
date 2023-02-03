package com.example.demo.model.map;

import com.example.demo.model.entity.PictureCollectionPO;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.example.demo.utils.ReadFileUtils.readPictureMap;

public class FolderPathMap {
	
	public  static String folderPath (String key) throws IOException {
		if(key.equals ("模拟器路径")){
			return exeAddress ();
		}
		if(key.equals ("图片总路径")){
			return  photoPath ();
		}
		return  key;
	}
	
	
	public  static String exeAddress(){
		String address="";
		List<String> list=new ArrayList<> ();
		list.add ("D:\\software\\Nox\\bin");
		
		for (String s : list) {
			File file = new File (s);
			if (file.exists ()) {
				address = s + "\\Nox.exe";
				break;
			}
		}
		return address;
	}
	
	public static String photoPath () throws IOException {
		String path = null;
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
		return path;
	}
	
}
