package com.example.demo.controller;

import com.example.demo.model.param.ProjectParam;
import com.example.demo.model.param.ProjectsParam;
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
import java.util.ArrayList;
import java.util.List;

import static com.example.demo.model.var.ProjectVar.*;
import static com.example.demo.model.var.ProjectVar.project_HSY;

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
			@RequestParam (required = false, defaultValue = "1") Integer round,
			@ApiParam ("模拟器")
			@RequestParam (required = false, defaultValue = "夜神模拟器") String process
	                              ) throws InterruptedException, UnknownHostException {
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		List<ProjectParam> projectParams=new ArrayList<> ();
		ProjectsParam projectsParam=new ProjectsParam ("测试 大号寄养功能", process, round, projectParams);
		//当前状态初始化
		projectParams.add (new ProjectParam (project_CSH,"1"));
		//寄养检查
		projectParams.add (new ProjectParam (project_JYJC,"1",null,false,null,
		                                     null));
		projectsParam.setProjectParams (projectParams);
		onmyojiService.onmyojiService (projectsParam);
		return Results.ok ("任务已启动");
	}
	
	@ApiOperation ("测试 大号阴阳寮突破")
	@ApiOperationSupport (order = 2)
	@GetMapping ("/dealTaskTest2")
	public Results<?> dealTaskTest2(
			@ApiParam ("轮次")
			@RequestParam (required = false, defaultValue = "1") Integer round,
			@ApiParam ("模拟器")
			@RequestParam (required = false, defaultValue = "夜神模拟器") String process
	                           ) throws InterruptedException, UnknownHostException {
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		List<ProjectParam> projectParams=new ArrayList<> ();
		ProjectsParam projectsParam=new ProjectsParam ("测试 大号阴阳寮突破", process, round, projectParams);
		//当前状态初始化
		projectParams.add (new ProjectParam (project_CSH,"1"));
		//阴阳寮突破
		projectParams.add (new ProjectParam (project_YYLTP,"1",null,false,null,
		                                     null));
		projectsParam.setProjectParams (projectParams);
		onmyojiService.onmyojiService (projectsParam);
		return Results.ok ("任务已启动");
	}
	
	@ApiOperation ("测试 个人突破")
	@ApiOperationSupport (order = 3)
	@GetMapping ("/dealTaskTest3")
	public Results<?> dealTaskTest3(
			@ApiParam ("轮次")
			@RequestParam (required = false, defaultValue = "1") Integer round,
			@ApiParam ("模拟器")
			@RequestParam (required = false, defaultValue = "夜神模拟器") String process
	                               ) throws InterruptedException, UnknownHostException {
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		List<ProjectParam> projectParams=new ArrayList<> ();
		ProjectsParam projectsParam=new ProjectsParam ("测试 个人突破", process, round, projectParams);
		//当前状态初始化
		projectParams.add (new ProjectParam (project_CSH,"1"));
		//个人突破
		projectParams.add (new ProjectParam (project_GRTP,"1",null,false,null,
		                                     null));
		projectsParam.setProjectParams (projectParams);
		onmyojiService.onmyojiService (projectsParam);
		return Results.ok ("任务已启动");
	}
	
	@ApiOperation ("测试 魂十一")
	@ApiOperationSupport (order = 4)
	@GetMapping ("/dealTaskTest4")
	public Results<?> dealTaskTest4(
			@ApiParam ("轮次")
			@RequestParam (required = false, defaultValue = "1") Integer round,
			@ApiParam ("模拟器")
			@RequestParam (required = false, defaultValue = "夜神模拟器") String process
	                               ) throws InterruptedException, UnknownHostException {
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		List<ProjectParam> projectParams=new ArrayList<> ();
		ProjectsParam projectsParam=new ProjectsParam ("测试 魂十一", process, round, projectParams);
		//当前状态初始化
		projectParams.add (new ProjectParam (project_CSH,"1"));
		//魂十一
		projectParams.add (new ProjectParam (project_HSY,"1",1,true,null,
		                                     null));
		projectsParam.setProjectParams (projectParams);
		onmyojiService.onmyojiService (projectsParam);
		return Results.ok ("任务已启动");
	}
	
	@ApiOperation ("测试 业原火")
	@ApiOperationSupport (order = 5)
	@GetMapping ("/dealTaskTest5")
	public Results<?> dealTaskTest5(
			@ApiParam ("轮次")
			@RequestParam (required = false, defaultValue = "1") Integer round,
			@ApiParam ("模拟器")
			@RequestParam (required = false, defaultValue = "夜神模拟器") String process
	                               ) throws InterruptedException, UnknownHostException {
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		List<ProjectParam> projectParams=new ArrayList<> ();
		ProjectsParam projectsParam=new ProjectsParam ("测试 业原火", process, round, projectParams);
		//当前状态初始化
		projectParams.add (new ProjectParam (project_CSH,"1"));
		//业原火
		projectParams.add (new ProjectParam (project_YYH,"1",1,false,null,
		                                     null));
		projectsParam.setProjectParams (projectParams);
		onmyojiService.onmyojiService (projectsParam);
		return Results.ok ("任务已启动");
	}
	

}
