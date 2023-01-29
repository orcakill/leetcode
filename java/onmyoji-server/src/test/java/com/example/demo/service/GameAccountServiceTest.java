package com.example.demo.service;

import com.example.demo.model.entity.GameAccountPO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.opencv.core.Core;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname GameAccountServiceTest
 * @Description GameAccountServiceTest
 * @Date 2023/1/29 21:00
 * @Created by orcakill
 */
@SpringBootTest
@Log4j2
class GameAccountServiceTest {
	@Autowired
	private  GameAccountService gameAccountService;
	@Test
	public void findById (){
		long a=System.currentTimeMillis ();
		log.info ("测试开始");
		System.setProperty ("java.awt.headless", "false");
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		GameAccountPO gameAccountPO=gameAccountService.findById ("1");
		log.info (gameAccountPO);
		log.info ("测试结束");
		long b=System.currentTimeMillis ();
		log.info ("用时{}毫秒",b-a);
	}
	
	@Test
	public void findList (){
		long a=System.currentTimeMillis ();
		log.info ("测试开始");
		System.setProperty ("java.awt.headless", "false");
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		Map<String,Object> map=new HashMap<> ();
		List<String> idList=new ArrayList<> ();
		idList.add ("1");
		map.put ("id",idList);
		List<GameAccountPO> gameAccountPOList=gameAccountService.findList (map);
		log.info (gameAccountPOList);
		log.info ("测试结束");
		long b=System.currentTimeMillis ();
		log.info ("用时{}毫秒",b-a);
	}
}