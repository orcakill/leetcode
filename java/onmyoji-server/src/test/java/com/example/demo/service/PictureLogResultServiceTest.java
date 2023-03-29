package com.example.demo.service;

import cn.hutool.core.date.DateUtil;
import com.example.demo.model.entity.PictureLogResultPO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

/**
 * @author orcakill
 * @version 1.0.0
 * @ClassName PictureLogResultServiceTest.java
 * @Description PictureLogResultService 测试类
 * @createTime 2023年03月28日 16:50:00
 */
@SpringBootTest
@Log4j2
class PictureLogResultServiceTest {
	@Autowired
	private  PictureLogResultService pictureLogResultService;
	
	@Test
	void save () {
		PictureLogResultPO pictureLogResultPO=new PictureLogResultPO ();
		pictureLogResultPO.setLogId ("6421b15fde0bd666549d7363");
		pictureLogResultPO.setRecognizeResult ("结界挑战劵");
		pictureLogResultService.save (pictureLogResultPO);
	}
	
	@Test
	void findList () {
		HashMap<String,Object> map=new HashMap<> ();
		List<PictureLogResultPO> pictureLogResultPOList=new ArrayList<> ();
		map.put ("resultId", Arrays.asList ("1640640068508995586,1640640141896814594".split (",")));
		pictureLogResultPOList=pictureLogResultService.findList (map);
		log.info (pictureLogResultPOList);
		map.put ("logId",Arrays.asList ("6421b15fde0bd666549d7363".split (",")));
		pictureLogResultPOList=pictureLogResultService.findList (map);
		log.info (pictureLogResultPOList);
		map.put ("recognizeResult",Arrays.asList ("结界挑战劵".split (",")));
		pictureLogResultPOList=pictureLogResultService.findList (map);
		log.info (pictureLogResultPOList);
		String  dateS1="2023-03-27";
		String  dateS2="2023-03-29";
		Date date1= DateUtil.parseDate (dateS1);
		Date  date2= DateUtil.parseDate (dateS2);
		map.put ("createStartTime",date1);
		map.put ("createEndTime",date2);
		pictureLogResultPOList=pictureLogResultService.findList (map);
		log.info (pictureLogResultPOList);
	}
	
	@Test
	void deleteById () {
		pictureLogResultService.deleteById ("1640639951211167746");
	}
}