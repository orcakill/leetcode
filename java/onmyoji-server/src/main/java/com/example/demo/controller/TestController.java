package com.example.demo.controller;

import com.example.demo.model.vo.Results;
import com.example.demo.service.OnmyojiService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.opencv.core.Core;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.UnknownHostException;

/**
 * @Classname TestController
 * @Description 测试
 * @Date 2023/3/5 13:08
 * @Created by orcakill
 */
@Api (tags = "阴阳师自动化测试")
@ApiSort (value = 4)
@RestController ("/test")
public class TestController {
	private final OnmyojiService onmyojiService;
	
	public TestController (OnmyojiService onmyojiService) {
		this.onmyojiService = onmyojiService;
	}
	
	@ApiOperation ("测试 大号寄养功能")
	@ApiOperationSupport (order = 1)
	@GetMapping ("/dealTaskTest1")
	public Results<?> dealTaskTest1 (
			@ApiParam ("轮次")
			@RequestParam (required = false, defaultValue = "100") Integer round,
			@ApiParam ("模拟器")
			@RequestParam (required = false, defaultValue = "夜神模拟器") String process
	                              ) throws InterruptedException, UnknownHostException {
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		onmyojiService.onmyojiService (process,100,round);
		return Results.ok ("任务已启动");
	}
	
	@ApiOperation ("测试 大号阴阳寮突破")
	@ApiOperationSupport (order = 2)
	@GetMapping ("/dealTaskTest2")
	public Results<?> dealTaskTest2(
			@ApiParam ("轮次")
			@RequestParam (required = false, defaultValue = "101") Integer round,
			@ApiParam ("模拟器")
			@RequestParam (required = false, defaultValue = "夜神模拟器") String process
	                           ) throws InterruptedException, UnknownHostException {
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		onmyojiService.onmyojiService (process,101,round);
		return Results.ok ("任务已启动");
	}
	
	@ApiOperation ("测试 个人突破")
	@ApiOperationSupport (order = 3)
	@GetMapping ("/dealTaskTest3")
	public Results<?> dealTaskTest3(
			@ApiParam ("轮次")
			@RequestParam (required = false, defaultValue = "102") Integer round,
			@ApiParam ("模拟器")
			@RequestParam (required = false, defaultValue = "夜神模拟器") String process
	                               ) throws InterruptedException, UnknownHostException {
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		onmyojiService.onmyojiService (process,102,round);
		return Results.ok ("任务已启动");
	}
	
	@ApiOperation ("测试 魂十一")
	@ApiOperationSupport (order = 4)
	@GetMapping ("/dealTaskTest4")
	public Results<?> dealTaskTest4(
			@ApiParam ("轮次")
			@RequestParam (required = false, defaultValue = "103") Integer round,
			@ApiParam ("模拟器")
			@RequestParam (required = false, defaultValue = "夜神模拟器") String process
	                               ) throws InterruptedException, UnknownHostException {
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		onmyojiService.onmyojiService (process,103,round);
		return Results.ok ("任务已启动");
	}
	

}
