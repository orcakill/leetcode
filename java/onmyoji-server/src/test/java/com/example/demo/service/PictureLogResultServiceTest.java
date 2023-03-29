package com.example.demo.service;

import com.example.demo.model.entity.PictureLogResultPO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

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
	
	}
	
	@Test
	void deleteById () {
		pictureLogResultService.deleteById ("1640639951211167746");
	}
}