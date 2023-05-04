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
import lombok.extern.log4j.Log4j2;
import org.opencv.core.Core;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import static com.example.demo.model.var.ProjectVar.*;

/**
 * @Classname FightController
 * @Description 自动战斗
 * @Date 2023/1/25 20:12
 * @Created by orcakill
 */
@Api (tags = "阴阳师自动化")
@ApiSort (value = 3)
@RestController ("/fight")
@Log4j2
public class OnmyojiController {
	private final OnmyojiService onmyojiService;
	
	public OnmyojiController (OnmyojiService onmyojiService) {
		this.onmyojiService = onmyojiService;
	}
	
	@ApiOperation ("正式  大号每日任务，寮突+个突+魂十一")
	@ApiOperationSupport (order = 1)
	@GetMapping ("/dealTaskMRRW")
	public Results<?> dealTaskMRRW (
			@ApiParam ("轮次")
			@RequestParam (required = false, defaultValue = "1") Integer round,
			@ApiParam ("模拟器")
			@RequestParam (required = false, defaultValue = "夜神模拟器") String process
	                              ) throws InterruptedException, UnknownHostException {
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		List<ProjectParam> projectParams=new ArrayList<> ();
		ProjectsParam projectsParam=new ProjectsParam ("大号每日任务，寮突+个突+魂十一",process,round,projectParams);
		//当前状态初始化
		projectParams.add (new ProjectParam (project_CSH,"1"));
		//寄养检查
		projectParams.add (new ProjectParam (project_JYJC,"1"));
		//阴阳寮突破
		projectParams.add (new ProjectParam (project_YYLTP,"1"));
		//个人突破
		projectParams.add (new ProjectParam (project_GRTP,"1"));
		//地域鬼王
		projectParams.add (new ProjectParam (project_DYGW,"1"));
		//魂十一
		projectParams.add (new ProjectParam (project_HSY,"1",40,true,null,
		                                     null));
		projectsParam.setProjectParams (projectParams);
		onmyojiService.onmyojiService (projectsParam);
		return Results.ok ("任务已启动");
	}
	
	@ApiOperation ("正式 大号 寮突")
	@ApiOperationSupport (order = 2)
	@GetMapping ("/dealTaskYYLTP")
	public Results<?> dealTaskYYLTP (
			@ApiParam ("轮次")
			@RequestParam (required = false, defaultValue = "1") Integer round,
			@ApiParam ("模拟器")
			@RequestParam (required = false, defaultValue = "夜神模拟器") String process
	                               ) throws InterruptedException, UnknownHostException {
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		List<ProjectParam> projectParams=new ArrayList<> ();
		ProjectsParam projectsParam=new ProjectsParam ("正式 大号 寮突",process,round,projectParams);
		//当前状态初始化
		projectParams.add (new ProjectParam (project_CSH,"1"));
		//寄养检查
		projectParams.add (new ProjectParam (project_JYJC,"1"));
		//阴阳寮突破
		projectParams.add (new ProjectParam (project_YYLTP,"1",null,false,30,
		                                     40));
		projectsParam.setProjectParams (projectParams);
		onmyojiService.onmyojiService (projectsParam);
		return Results.ok ("任务已启动");
	}
	
	@ApiOperation ("正式  大号 阴阳寮+斗技5次")
	@ApiOperationSupport (order = 3)
	@GetMapping ("/dealTaskYYLDJ")
	public Results<?> dealTaskYYLDJ (
			@ApiParam ("轮次")
			@RequestParam (required = false, defaultValue = "1") Integer round,
			@ApiParam ("模拟器")
			@RequestParam (required = false, defaultValue = "夜神模拟器") String process
	                                ) throws InterruptedException, UnknownHostException {
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		List<ProjectParam> projectParams=new ArrayList<> ();
		ProjectsParam projectsParam=new ProjectsParam ("正式  大号斗技",process,round,projectParams);
		//当前状态初始化
		projectParams.add (new ProjectParam (project_CSH,"1"));
		//阴阳寮突破
		projectParams.add (new ProjectParam (project_YYLTP,"1"));
		//斗技
		projectParams.add (new ProjectParam (project_DJ,"1",5,false,null,
		                                     null));
		projectsParam.setProjectParams (projectParams);
		onmyojiService.onmyojiService (projectsParam);
		return Results.ok ("任务已启动");
	}
	
	@ApiOperation ("正式 大号 个人突破+魂十一")
	@ApiOperationSupport (order = 4)
	@GetMapping ("/dealTaskHSY")
	public Results<?> dealTaskHSY (
			@ApiParam ("轮次")
			@RequestParam (required = false, defaultValue = "1") Integer round,
			@ApiParam ("模拟器")
			@RequestParam (required = false, defaultValue = "夜神模拟器") String process
	                            ) throws InterruptedException, UnknownHostException {
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		List<ProjectParam> projectParams=new ArrayList<> ();
		ProjectsParam projectsParam=new ProjectsParam ("正式 大号 个人突破+魂十一",process,round,projectParams);
		//当前状态初始化
		projectParams.add (new ProjectParam (project_CSH,"1"));
		//寄养检查
		projectParams.add (new ProjectParam (project_JYJC,"1"));
		//个人突破
		projectParams.add (new ProjectParam (project_GRTP,"1"));
		//魂十一
		projectParams.add (new ProjectParam (project_YYH,"1",60,true,null,
		                                     null));
		projectsParam.setProjectParams (projectParams);
		onmyojiService.onmyojiService (projectsParam);
		return Results.ok ("任务已启动");
	}
	
	@ApiOperation ("正式  大号业原火（阴阳寮+个人结界）")
	@ApiOperationSupport (order = 5)
	@GetMapping ("/dealTaskYYH")
	public Results<?> dealTaskYYH (
			@ApiParam ("轮次")
			@RequestParam (required = false, defaultValue = "1") Integer round,
			@ApiParam ("模拟器")
			@RequestParam (required = false, defaultValue = "夜神模拟器") String process
	                              ) throws InterruptedException, UnknownHostException {
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		List<ProjectParam> projectParams=new ArrayList<> ();
		ProjectsParam projectsParam=new ProjectsParam ("正式  大号业原火（阴阳寮+个人结界",process,round,projectParams);
		//当前状态初始化
		projectParams.add (new ProjectParam (project_CSH,"1"));
		//阴阳寮突破
		projectParams.add (new ProjectParam (project_YYLTP,"1"));
		//寄养检查
		projectParams.add (new ProjectParam (project_JYJC,"1"));
		//个人突破
		projectParams.add (new ProjectParam (project_GRTP,"1"));
		//业原火
		projectParams.add (new ProjectParam (project_YYH,"1",60,false,null,
		                                     null));
		projectsParam.setProjectParams (projectParams);
		onmyojiService.onmyojiService (projectsParam);
		return Results.ok ("任务已启动");
	}
	
	@ApiOperation ("正式  大号日轮之陨（个人结界）")
	@ApiOperationSupport (order = 6)
	@GetMapping ("/dealTaskRLZY")
	public Results<?> dealTaskRLZY (
			@ApiParam ("轮次")
			@RequestParam (required = false, defaultValue = "1") Integer round,
			@ApiParam ("模拟器")
			@RequestParam (required = false, defaultValue = "夜神模拟器") String process
	                            ) throws InterruptedException, UnknownHostException {
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		List<ProjectParam> projectParams=new ArrayList<> ();
		ProjectsParam projectsParam=new ProjectsParam ("正式  大号日轮之陨（个人结界）",process,round,projectParams);
		//当前状态初始化
		projectParams.add (new ProjectParam (project_CSH,"1"));
		//个人突破
		projectParams.add (new ProjectParam (project_GRTP,"1"));
		//日轮之陨
		projectParams.add (new ProjectParam (project_RLZY,"1",50,true,null,
		                                     null));
		projectsParam.setProjectParams (projectParams);
		onmyojiService.onmyojiService (projectsParam);
		return Results.ok ("任务已启动");
	}
	
	@ApiOperation ("正式  大号永生之海（个人结界）")
	@ApiOperationSupport (order = 7)
	@GetMapping ("/dealTaskYSZH")
	public Results<?> dealTaskYSZH (
			@ApiParam ("轮次")
			@RequestParam (required = false, defaultValue = "1") Integer round,
			@ApiParam ("模拟器")
			@RequestParam (required = false, defaultValue = "夜神模拟器") String process
	                            ) throws InterruptedException, UnknownHostException {
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		List<ProjectParam> projectParams=new ArrayList<> ();
		ProjectsParam projectsParam=new ProjectsParam ("正式  大号永生之海（个人结界）",process,round,projectParams);
		//当前状态初始化
		projectParams.add (new ProjectParam (project_CSH,"1"));
		//个人突破
		projectParams.add (new ProjectParam (project_GRTP,"1"));
		//永生之海
		projectParams.add (new ProjectParam (project_YSZH,"1",30,true,null,
		                                     null));
		projectsParam.setProjectParams (projectParams);
		onmyojiService.onmyojiService (projectsParam);
		return Results.ok ("任务已启动");
	}
	
	@ApiOperation ("正式  大号御灵（个人结界）")
	@ApiOperationSupport (order = 8)
	@GetMapping ("/dealTaskYL")
	public Results<?> dealTaskYL (
			@ApiParam ("轮次")
			@RequestParam (required = false, defaultValue = "1") Integer round,
			@ApiParam ("模拟器")
			@RequestParam (required = false, defaultValue = "夜神模拟器") String process
	                            ) throws InterruptedException, UnknownHostException {
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		List<ProjectParam> projectParams=new ArrayList<> ();
		ProjectsParam projectsParam=new ProjectsParam ("正式  大号御灵",process,round,projectParams);
		//当前状态初始化
		projectParams.add (new ProjectParam (project_CSH,"1"));
		//个人突破
		projectParams.add (new ProjectParam (project_GRTP,"1"));
		//寄养检查
		projectParams.add (new ProjectParam (project_JYJC,"1"));
		//御灵
		log.info ("添加任务，御灵60次");
		projectParams.add (new ProjectParam (project_YL,"1",60,false,null,
		                                     null));
		projectsParam.setProjectParams (projectParams);
		onmyojiService.onmyojiService (projectsParam);
		return Results.ok ("任务已启动");
	}
	
	@ApiOperation ("正式  大号斗技10次")
	@ApiOperationSupport (order = 9)
	@GetMapping ("/dealTaskDJ")
	public Results<?> dealTaskDJ(
			@ApiParam ("轮次")
			@RequestParam (required = false, defaultValue = "1") Integer round,
			@ApiParam ("模拟器")
			@RequestParam (required = false, defaultValue = "夜神模拟器") String process
	                            ) throws InterruptedException, UnknownHostException {
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		List<ProjectParam> projectParams=new ArrayList<> ();
		ProjectsParam projectsParam=new ProjectsParam ("正式  大号斗技",process,round,projectParams);
		//当前状态初始化
		projectParams.add (new ProjectParam (project_CSH,"1"));
		//寄养检查
		projectParams.add (new ProjectParam (project_JYJC,"1"));
		//斗技
		projectParams.add (new ProjectParam (project_DJ,"1",10,false,null,
		                                     null));
		projectsParam.setProjectParams (projectParams);
		onmyojiService.onmyojiService (projectsParam);
		return Results.ok ("任务已启动");
	}
	

	
	@ApiOperation ("正式  大号 御魂整理 速度副属性极限强化")
	@ApiOperationSupport (order = 10)
	@GetMapping ("/dealTaskYHZLSD")
	public Results<?> dealTaskYHZLSD (
			@ApiParam ("轮次")
			@RequestParam (required = false, defaultValue = "1") Integer round,
			@ApiParam ("模拟器")
			@RequestParam (required = false, defaultValue = "夜神模拟器") String process
	                            ) throws InterruptedException, UnknownHostException {
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		List<ProjectParam> projectParams=new ArrayList<> ();
		ProjectsParam projectsParam=new ProjectsParam ("正式 大号 御魂整理 副属性极限强化",process,round,projectParams);
		//当前状态初始化
		projectParams.add (new ProjectParam (project_CSH,"1"));
		//寄养检查
		projectParams.add (new ProjectParam (project_JYJC,"1"));
		//御魂整理
		log.info ("添加任务，御魂整理-强化20次");
		projectParams.add (new ProjectParam (project_YJZL_JXFSXQH,"1",20,false,null,
		                                     null));
		projectsParam.setProjectParams (projectParams);
		onmyojiService.onmyojiService (projectsParam);
		return Results.ok ("任务已启动");
	}
	
	@ApiOperation ("正式  大号 探索")
	@ApiOperationSupport (order = 11)
	@GetMapping ("/dealTaskTS")
	public Results<?> dealTaskTS (
			@ApiParam ("轮次")
			@RequestParam (required = false, defaultValue = "1") Integer round,
			@ApiParam ("模拟器")
			@RequestParam (required = false, defaultValue = "夜神模拟器") String process
	                            ) throws InterruptedException, UnknownHostException {
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		List<ProjectParam> projectParams=new ArrayList<> ();
		ProjectsParam projectsParam=new ProjectsParam ("正式 大号 大号 探索",process,round,projectParams);
		//当前状态初始化
		projectParams.add (new ProjectParam (project_CSH,"1"));
		//寄养检查
		projectParams.add (new ProjectParam (project_JYJC,"1"));
		//个人突破
		projectParams.add (new ProjectParam (project_GRTP,"1"));
		//探索
		projectParams.add (new ProjectParam (project_GRTS,"1",20,false,null,
		                                     null));
		projectsParam.setProjectParams (projectParams);
		onmyojiService.onmyojiService (projectsParam);
		return Results.ok ("任务已启动");
	}
	
	@ApiOperation ("正式  大号 探索 只打2个")
	@ApiOperationSupport (order = 12)
	@GetMapping ("/dealTaskTSFast")
	public Results<?> dealTaskTSFast (
			@ApiParam ("轮次")
			@RequestParam (required = false, defaultValue = "1") Integer round,
			@ApiParam ("模拟器")
			@RequestParam (required = false, defaultValue = "夜神模拟器") String process
	                            ) throws InterruptedException, UnknownHostException {
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		List<ProjectParam> projectParams=new ArrayList<> ();
		ProjectsParam projectsParam=new ProjectsParam ("正式  大号 探索 只打2个",process,round,projectParams);
		//当前状态初始化
		projectParams.add (new ProjectParam (project_CSH,"1"));
		//寄养检查
		projectParams.add (new ProjectParam (project_JYJC,"1"));
		//个人突破
		projectParams.add (new ProjectParam (project_GRTP,"1"));
		//探索
		log.info ("添加任务，探索 只打2个 60次");
		projectParams.add (new ProjectParam (project_GRTS_FAST,"1",60,false,null,
		                                     null));
		projectsParam.setProjectParams (projectParams);
		onmyojiService.onmyojiService (projectsParam);
		return Results.ok ("任务已启动");
	}
}
