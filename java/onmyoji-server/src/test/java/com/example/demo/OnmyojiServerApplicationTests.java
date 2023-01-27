package com.example.demo;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
class OnmyojiServerApplicationTests {
	
	@Test
	void contextLoads () {
		log.info ("程序启动");
	}
	
}
