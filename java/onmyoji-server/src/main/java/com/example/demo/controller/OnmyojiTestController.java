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
@Api(tags = "阴阳师自动化测试")
@ApiSort(value = 4)
@RestController("/test")
public class OnmyojiTestController {
    private final OnmyojiService onmyojiService;

    public OnmyojiTestController(OnmyojiService onmyojiService) {
        this.onmyojiService = onmyojiService;
    }

    @ApiOperation("当前状态初始化")
    @ApiOperationSupport(order = 1)
    @GetMapping("/dealTaskTest0")
    public Results<?> dealTaskTestDQZTCSH(
            @ApiParam("轮次")
            @RequestParam(required = false, defaultValue = "1") Integer round,
            @ApiParam("模拟器")
            @RequestParam(required = false, defaultValue = "夜神模拟器") String process
    ) throws InterruptedException, UnknownHostException {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        List<ProjectParam> projectParams = new ArrayList<>();
        ProjectsParam projectsParam = new ProjectsParam("当前状态初始化", process, round, projectParams);
        //当前状态初始化
        projectParams.add(new ProjectParam(project_CSH, "1"));
        projectsParam.setProjectParams(projectParams);
        onmyojiService.onmyojiService(projectsParam);
        return Results.ok("任务已启动");
    }

    @ApiOperation("大号寄养功能")
    @ApiOperationSupport(order = 1)
    @GetMapping("/dealTaskTest1")
    public Results<?> dealTaskTest1(
            @ApiParam("轮次")
            @RequestParam(required = false, defaultValue = "1") Integer round,
            @ApiParam("模拟器")
            @RequestParam(required = false, defaultValue = "夜神模拟器") String process
    ) throws InterruptedException, UnknownHostException {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        List<ProjectParam> projectParams = new ArrayList<>();
        ProjectsParam projectsParam = new ProjectsParam("测试 大号寄养功能", process, round, projectParams);
        //当前状态初始化
        projectParams.add(new ProjectParam(project_CSH, "1"));
        //寄养检查
        projectParams.add(new ProjectParam(project_JYJC, "1", null, false, null,
                null));
        projectsParam.setProjectParams(projectParams);
        onmyojiService.onmyojiService(projectsParam);
        return Results.ok("任务已启动");
    }

    @ApiOperation("大号阴阳寮突破")
    @ApiOperationSupport(order = 2)
    @GetMapping("/dealTaskTest2")
    public Results<?> dealTaskTest2(
            @ApiParam("轮次")
            @RequestParam(required = false, defaultValue = "1") Integer round,
            @ApiParam("模拟器")
            @RequestParam(required = false, defaultValue = "夜神模拟器") String process
    ) throws InterruptedException, UnknownHostException {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        List<ProjectParam> projectParams = new ArrayList<>();
        ProjectsParam projectsParam = new ProjectsParam("测试 大号阴阳寮突破", process, round, projectParams);
        //当前状态初始化
        projectParams.add(new ProjectParam(project_CSH, "1"));
        //阴阳寮突破
        projectParams.add(new ProjectParam(project_YYLTP, "1", null, false, null,
                null));
        projectsParam.setProjectParams(projectParams);
        onmyojiService.onmyojiService(projectsParam);
        return Results.ok("任务已启动");
    }

    @ApiOperation("个人突破")
    @ApiOperationSupport(order = 3)
    @GetMapping("/dealTaskTest3")
    public Results<?> dealTaskTest3(
            @ApiParam("轮次")
            @RequestParam(required = false, defaultValue = "1") Integer round,
            @ApiParam("模拟器")
            @RequestParam(required = false, defaultValue = "夜神模拟器") String process
    ) throws InterruptedException, UnknownHostException {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        List<ProjectParam> projectParams = new ArrayList<>();
        ProjectsParam projectsParam = new ProjectsParam("测试 个人突破", process, round, projectParams);
        //当前状态初始化
        projectParams.add(new ProjectParam(project_CSH, "1"));
        //个人突破
        projectParams.add(new ProjectParam(project_GRTP, "1", null, false, null,
                null));
        projectsParam.setProjectParams(projectParams);
        onmyojiService.onmyojiService(projectsParam);
        return Results.ok("任务已启动");
    }

    @ApiOperation("魂十一")
    @ApiOperationSupport(order = 4)
    @GetMapping("/dealTaskTest4")
    public Results<?> dealTaskTest4(
            @ApiParam("轮次")
            @RequestParam(required = false, defaultValue = "1") Integer round,
            @ApiParam("模拟器")
            @RequestParam(required = false, defaultValue = "夜神模拟器") String process
    ) throws InterruptedException, UnknownHostException {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        List<ProjectParam> projectParams = new ArrayList<>();
        ProjectsParam projectsParam = new ProjectsParam("测试 魂十一", process, round, projectParams);
        //当前状态初始化
        projectParams.add(new ProjectParam(project_CSH, "1"));
        //魂十一
        projectParams.add(new ProjectParam(project_HSY, "1", 1, true, null,
                null));
        projectsParam.setProjectParams(projectParams);
        onmyojiService.onmyojiService(projectsParam);
        return Results.ok("任务已启动");
    }

    @ApiOperation("业原火")
    @ApiOperationSupport(order = 5)
    @GetMapping("/dealTaskTest5")
    public Results<?> dealTaskTest5(
            @ApiParam("轮次")
            @RequestParam(required = false, defaultValue = "1") Integer round,
            @ApiParam("模拟器")
            @RequestParam(required = false, defaultValue = "夜神模拟器") String process
    ) throws InterruptedException, UnknownHostException {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        List<ProjectParam> projectParams = new ArrayList<>();
        ProjectsParam projectsParam = new ProjectsParam("测试 业原火", process, round, projectParams);
        //当前状态初始化
        projectParams.add(new ProjectParam(project_CSH, "1"));
        //业原火
        projectParams.add(new ProjectParam(project_YYH, "1", 1, false, null,
                null));
        projectsParam.setProjectParams(projectParams);
        onmyojiService.onmyojiService(projectsParam);
        return Results.ok("任务已启动");
    }

    @ApiOperation("日轮之陨")
    @ApiOperationSupport(order = 6)
    @GetMapping("/dealTaskTest6")
    public Results<?> dealTaskTest6(
            @ApiParam("轮次")
            @RequestParam(required = false, defaultValue = "1") Integer round,
            @ApiParam("模拟器")
            @RequestParam(required = false, defaultValue = "夜神模拟器") String process
    ) throws InterruptedException, UnknownHostException {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        List<ProjectParam> projectParams = new ArrayList<>();
        ProjectsParam projectsParam = new ProjectsParam("测试 日轮之陨", process, round, projectParams);
        //当前状态初始化
        projectParams.add(new ProjectParam(project_CSH, "1"));
        //日轮之陨
        projectParams.add(new ProjectParam(project_RLZY, "1", 1, false, null,
                null));
        projectsParam.setProjectParams(projectParams);
        onmyojiService.onmyojiService(projectsParam);
        return Results.ok("任务已启动");
    }

    @ApiOperation("永生之海")
    @ApiOperationSupport(order = 7)
    @GetMapping("/dealTaskTest7")
    public Results<?> dealTaskTest7(
            @ApiParam("轮次")
            @RequestParam(required = false, defaultValue = "1") Integer round,
            @ApiParam("模拟器")
            @RequestParam(required = false, defaultValue = "夜神模拟器") String process
    ) throws InterruptedException, UnknownHostException {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        List<ProjectParam> projectParams = new ArrayList<>();
        ProjectsParam projectsParam = new ProjectsParam("测试 永生之海", process, round, projectParams);
        //当前状态初始化
        projectParams.add(new ProjectParam(project_CSH, "1"));
        //日轮之陨
        projectParams.add(new ProjectParam(project_YSZH, "1", 1, false, null,
                null));
        projectsParam.setProjectParams(projectParams);
        onmyojiService.onmyojiService(projectsParam);
        return Results.ok("任务已启动");
    }

    @ApiOperation("斗技")
    @ApiOperationSupport(order = 8)
    @GetMapping("/dealTaskTest8")
    public Results<?> dealTaskTest8(
            @ApiParam("轮次")
            @RequestParam(required = false, defaultValue = "1") Integer round,
            @ApiParam("模拟器")
            @RequestParam(required = false, defaultValue = "夜神模拟器") String process
    ) throws InterruptedException, UnknownHostException {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        List<ProjectParam> projectParams = new ArrayList<>();
        ProjectsParam projectsParam = new ProjectsParam("测试 斗技", process, round, projectParams);
        //当前状态初始化
        projectParams.add(new ProjectParam(project_CSH, "1"));
        //日轮之陨
        projectParams.add(new ProjectParam(project_DJ, "1", 1, false, null,
                null));
        projectsParam.setProjectParams(projectParams);
        onmyojiService.onmyojiService(projectsParam);
        return Results.ok("任务已启动");
    }

    @ApiOperation("御魂整理 极限副属性强化")
    @ApiOperationSupport(order = 9)
    @GetMapping("/dealTaskTest9")
    public Results<?> dealTaskTest9(
            @ApiParam("轮次")
            @RequestParam(required = false, defaultValue = "1") Integer round,
            @ApiParam("模拟器")
            @RequestParam(required = false, defaultValue = "夜神模拟器") String process
    ) throws InterruptedException, UnknownHostException {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        List<ProjectParam> projectParams = new ArrayList<>();
        ProjectsParam projectsParam = new ProjectsParam("测试 御魂整理 极限副属性强化", process, round, projectParams);
        //当前状态初始化
        projectParams.add(new ProjectParam(project_CSH, "1"));
        //日轮之陨
        projectParams.add(new ProjectParam(project_YJZL_JXFSXQH, "1", 1, false, null,
                null));
        projectsParam.setProjectParams(projectParams);
        onmyojiService.onmyojiService(projectsParam);
        return Results.ok("任务已启动");
    }

    @ApiOperation("个人探索")
    @ApiOperationSupport(order = 10)
    @GetMapping("/dealTaskTest10")
    public Results<?> dealTaskTest10(
            @ApiParam("轮次")
            @RequestParam(required = false, defaultValue = "1") Integer round,
            @ApiParam("模拟器")
            @RequestParam(required = false, defaultValue = "夜神模拟器") String process
    ) throws InterruptedException, UnknownHostException {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        List<ProjectParam> projectParams = new ArrayList<>();
        ProjectsParam projectsParam = new ProjectsParam("测试 个人探索", process, round, projectParams);
        //当前状态初始化
        projectParams.add(new ProjectParam(project_CSH, "1"));
        //日轮之陨
        projectParams.add(new ProjectParam(project_GRTS, "1", 1, false, null,
                null));
        projectsParam.setProjectParams(projectParams);
        onmyojiService.onmyojiService(projectsParam);
        return Results.ok("任务已启动");
    }

    @ApiOperation("个人探索 只打2个小怪")
    @ApiOperationSupport(order = 11)
    @GetMapping("/dealTaskTest11")
    public Results<?> dealTaskTest11(
            @ApiParam("轮次")
            @RequestParam(required = false, defaultValue = "1") Integer round,
            @ApiParam("模拟器")
            @RequestParam(required = false, defaultValue = "夜神模拟器") String process
    ) throws InterruptedException, UnknownHostException {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        List<ProjectParam> projectParams = new ArrayList<>();
        ProjectsParam projectsParam = new ProjectsParam("测试 个人探索 只打2个小怪", process, round, projectParams);
        //当前状态初始化
        projectParams.add(new ProjectParam(project_CSH, "1"));
        //日轮之陨
        projectParams.add(new ProjectParam(project_GRTS_FAST, "1", 1, false, null,
                null));
        projectsParam.setProjectParams(projectParams);
        onmyojiService.onmyojiService(projectsParam);
        return Results.ok("任务已启动");
    }

    @ApiOperation("地域鬼王")
    @ApiOperationSupport(order = 12)
    @GetMapping("/dealTaskTest12")
    public Results<?> dealTaskTest12(
            @ApiParam("轮次")
            @RequestParam(required = false, defaultValue = "1") Integer round,
            @ApiParam("模拟器")
            @RequestParam(required = false, defaultValue = "夜神模拟器") String process
    ) throws InterruptedException, UnknownHostException {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        List<ProjectParam> projectParams = new ArrayList<>();
        ProjectsParam projectsParam = new ProjectsParam("地域鬼王", process, round, projectParams);
        //当前状态初始化
        projectParams.add(new ProjectParam(project_CSH, "1"));
        //地域鬼王
        projectParams.add(new ProjectParam(project_DYGW, "1"));
        projectsParam.setProjectParams(projectParams);
        onmyojiService.onmyojiService(projectsParam);
        return Results.ok("任务已启动");
    }

}
