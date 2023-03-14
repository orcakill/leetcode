package com.example.demo;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@Log4j2
public class OnmyojiServerApplication {
	public static void main (String[] args) {
		//SpringApplication.run (OnmyojiServerApplication.class, args);
		
		SpringApplicationBuilder builder = new SpringApplicationBuilder(OnmyojiServerApplication.class);
		builder.headless(false).web (WebApplicationType.SERVLET).run (args);
		log.info ("http://localhost:8000/api/v1/doc.html");
	}
	
}
