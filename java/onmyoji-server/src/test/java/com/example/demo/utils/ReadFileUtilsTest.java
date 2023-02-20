package com.example.demo.utils;

import com.example.demo.model.entity.PictureCollectionPO;
import com.example.demo.model.map.FolderPathMap;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

/**
 * @Classname ReadFileUtilsTest
 * @Description ReadFileUtilsTest
 * @Date 2023/2/5 18:14
 * @Created by orcakill
 */
@SpringBootTest
@Log4j2
class ReadFileUtilsTest {
	
	@Test
	void readPictureCollectionPOList () throws IOException {
		long start=System.currentTimeMillis ();
		System.setProperty ("java.awt.headless", "false");
		String path= FolderPathMap.folderPath ("图片总路径");
		String folder="首页\\编号\\5561731";
		List<PictureCollectionPO> pictureCollectionPOList=ReadFileUtils.readPictureCollectionPOList (path,folder,"TM_SQDIFF_NORMED");
		log.info (pictureCollectionPOList);
		log.info ("图片识别，用时{}毫秒",System.currentTimeMillis ()-start);
	}
}