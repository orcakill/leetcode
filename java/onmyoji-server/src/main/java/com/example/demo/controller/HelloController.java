package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname HelloController
 * @Description 测试端口是否正常访问
 * @Date 2022/12/27 16:51
 * @Created by orcakill
 */
@RestController
public class HelloController {
	
	@GetMapping
	public String hello(){
		return "hello";
	}
}
