package com.example.demo.mapper;

import com.example.demo.model.entity.GameAccountPO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.opencv.core.Core;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Classname GameAccountMapperTest
 * @Description GameAccountMapperTest
 * @Date 2023/1/29 0:24
 * @Created by orcakill
 */
@SpringBootTest
@Log4j2
class GameAccountMapperTest {
	
	@Autowired
	private  GameAccountMapper gameAccountMapper;
	@Test
	void  test1(){
		log.info ("测试开始");
		System.setProperty ("java.awt.headless", "false");
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		GameAccountPO gameAccountPO=gameAccountMapper.selectById ("5561731");
		log.info (gameAccountPO);
		log.info ("测试结束");
		
	}
	
}