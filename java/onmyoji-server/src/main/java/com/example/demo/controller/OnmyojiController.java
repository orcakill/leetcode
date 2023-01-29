package com.example.demo.controller;

import com.example.demo.model.vo.Results;
import com.example.demo.service.OnmyojiService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	
	@ApiOperation ("大号每日任务，寮突+个突+魂十一")
	@ApiOperationSupport (order = 1)
	@GetMapping ("/dailyTask")
	public Results<?> getArticles (
			@ApiParam ("轮次")
			@RequestParam (required = false, defaultValue = "1") Integer round
	                              ) throws InterruptedException {
		onmyojiService.onmyojiService (1,round);
		return Results.ok ("任务已启动");
	}
}
