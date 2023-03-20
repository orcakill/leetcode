package com.example.demo;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Objects;

@SpringBootApplication
@EnableAsync
@Log4j2
public class OnmyojiServerApplication {
	public static void main (String[] args) {
		//SpringApplication.run (OnmyojiServerApplication.class, args);
		
		SpringApplicationBuilder builder = new SpringApplicationBuilder(OnmyojiServerApplication.class);
		builder.headless(false).web (WebApplicationType.SERVLET).run (args);
		Environment env = builder.run (args).getEnvironment ();
		String  port=env.getProperty("server.port");
		String  active=env.getProperty("spring.profiles.active");
		String  url="http://localhost:"+port+"/api/v1/doc.html";
		if(Objects.equals (active, "pro")){
			try {
				Runtime.getRuntime().exec("cmd   /c   start "+url);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
		}
		log.info (url);
	}
	
}
