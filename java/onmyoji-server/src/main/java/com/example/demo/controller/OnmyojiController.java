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

import static com.example.demo.model.var.ProjectVar.project_CSH;

/**
 * @Classname FightController
 * @Description 自动战斗
 * @Date 2023/1/25 20:12
 * @Created by orcakill
 */
@Api (tags = "阴阳师自动化")
@ApiSort (value = 3)
@RestController ("/fight")
public class OnmyojiController {
	private final OnmyojiService onmyojiService;
	
	public OnmyojiController (OnmyojiService onmyojiService) {
		this.onmyojiService = onmyojiService;
	}
	
	@ApiOperation ("正式  大号每日任务，寮突+个突+魂十一")
	@ApiOperationSupport (order = 1)
	@GetMapping ("/dealTask1")
	public Results<?> dealTask1 (
			@ApiParam ("轮次")
			@RequestParam (required = false, defaultValue = "1") Integer round,
			@ApiParam ("模拟器")
			@RequestParam (required = false, defaultValue = "夜神模拟器") String process
	                              ) throws InterruptedException, UnknownHostException {
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		ProjectsParam projectsParam=new ProjectsParam ();
		projectsParam.setProjectsName ("大号每日任务，寮突+个突+魂十一");
		projectsParam.setProcess (process);
		projectsParam.setRound (round);
		List<ProjectParam> projectParams=new ArrayList<> ();
		projectParams.add (new ProjectParam (project_CSH,"1",null,))
		onmyojiService.onmyojiService (process,1,round);
		return Results.ok ("任务已启动");
	}
	
	@ApiOperation ("正式  大号业原火（阴阳寮+个人结界）")
	@ApiOperationSupport (order = 3)
	@GetMapping ("/dealTask3")
	public Results<?> dealTask3 (
			@ApiParam ("轮次")
			@RequestParam (required = false, defaultValue = "1") Integer round,
			@ApiParam ("模拟器")
			@RequestParam (required = false, defaultValue = "夜神模拟器") String process
	                              ) throws InterruptedException, UnknownHostException {
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		onmyojiService.onmyojiService (process,3,round);
		return Results.ok ("任务已启动");
	}
}
