package com.example.demo.service;

import cn.hutool.core.date.DateUtil;
import com.example.demo.model.entity.PictureLogPO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static com.example.demo.model.var.CommVar.soul_TCTZ;
import static com.example.demo.model.var.ProjectVar.project_HSY;

/**
 * @Classname PictureLogServiceTest
 * @Description TODO
 * @Date 2023/3/27 22:57
 * @Created by orcakill
 */
@SpringBootTest
@Log4j2
class PictureLogServiceTest {
	@Autowired
	private  PictureLogService pictureLogService;
	
	@Test
	void save () {
		PictureLogPO pictureLogPO=new PictureLogPO ();
		pictureLogPO.setLogType (project_HSY);
		pictureLogPO.setLogFolder (soul_TCTZ);
		pictureLogService.save (pictureLogPO);
	}
	
	@Test
	void findList () {
		HashMap<String, Object> map=new HashMap<> ();
		List<PictureLogPO> pictureLogPOList=pictureLogService.findList (map);
		log.info ("测试1{}",pictureLogPOList.toString ());
		String id1="6421b15fde0bd666549d7363";
		String id2="6421b20ede0bb3a707861268";
		String id3="6421c0d7de0ba219c7a4492a";
		List<String> listId=new ArrayList<> ();
		listId.add (id1);
		listId.add(id2);
		listId.add(id3);
		map.put ("logId",listId);
		pictureLogPOList=pictureLogService.findList (map);
		log.info ("测试2{}",pictureLogPOList.toString ());
		List<String> listType=new ArrayList<> ();
		String type1="魂十一";
		listType.add (type1);
		map.put ("logType",listType);
		pictureLogPOList=pictureLogService.findList (map);
		log.info ("测试3{}",pictureLogPOList.toString ());
		String  dateS1="2023-03-27";
		String  dateS2="2023-03-28";
		Date  date1= DateUtil.parseDate (dateS1);
		Date  date2= DateUtil.parseDate (dateS2);
		map.put ("createStartTime",date1);
		map.put ("createEndTime",date2);
		pictureLogPOList=pictureLogService.findList (map);
		log.info ("测试4{}",pictureLogPOList.toString ());
	}
	
	@Test
	void deleteById () {
		pictureLogService.deleteById ("6421b27bde0bac0dc52272fd");
	}
}