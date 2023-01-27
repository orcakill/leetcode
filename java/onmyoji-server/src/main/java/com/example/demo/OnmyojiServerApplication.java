package com.example.demo;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@Log4j2
public class OnmyojiServerApplication {
	public static void main (String[] args) {
		SpringApplication.run (OnmyojiServerApplication.class, args);
		log.info ("http://localhost:9000/api/v1/doc.html");
	}
	
}
