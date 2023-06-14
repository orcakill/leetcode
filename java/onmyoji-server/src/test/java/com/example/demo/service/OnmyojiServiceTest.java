package com.example.demo.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.opencv.core.Core;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.*;
import java.io.IOException;

import static com.example.demo.model.var.ProjectVar.project_HSY;
import static com.example.demo.model.var.ProjectVar.project_YYH;

/**
 * @author orcakill
 * @version 1.0.0
 * @ClassName OnmyojiServiceTest.java
 * @Description 测试类
 * @createTime 2023年02月15日 11:28:00
 */
@SpringBootTest
@Log4j2
class OnmyojiServiceTest {
	@Autowired
	private OnmyojiService onmyojiService;
	

	
	//当前状态初始化 首页
	@Test
	void initializationState () throws IOException, InterruptedException, AWTException {
		long startTime = System.currentTimeMillis ();
		log.info ("测试开始");
		System.setProperty ("java.awt.headless", "false");
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		// 阴阳师桌面图标.适龄提示,逆戟之刃首页,orcakill首页,洪荒修罗首页,炽热的惆怅物语首页,返回
		log.info ("阴阳师-逆戟之刃-首页");
		onmyojiService.initializationState ("夜神模拟器","1");
		log.info ("测试结束");
		log.info ("用时{}毫秒", System.currentTimeMillis () - startTime);
	}
	//返回
	@Test
	void returnHome () throws IOException, InterruptedException, AWTException {
		long startTime = System.currentTimeMillis ();
		log.info ("测试开始");
		System.setProperty ("java.awt.headless", "false");
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		// 阴阳师桌面图标.适龄提示,逆戟之刃首页,orcakill首页,洪荒修罗首页,炽热的惆怅物语首页,返回
		log.info ("阴阳师-逆戟之刃-首页");
		onmyojiService.returnHome ("夜神模拟器");
		log.info ("测试结束");
		log.info ("用时{}毫秒", System.currentTimeMillis () - startTime);
	}
	//寄养
	@Test
	void toFoster () throws IOException, InterruptedException, AWTException {
		long startTime = System.currentTimeMillis ();
		log.info ("测试开始");
		System.setProperty ("java.awt.headless", "false");
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		// 阴阳师桌面图标.适龄提示,逆戟之刃首页,orcakill首页,洪荒修罗首页,炽热的惆怅物语首页,返回
		log.info ("阴阳师-逆戟之刃-首页");
		onmyojiService.toFoster ("夜神模拟器");
		log.info ("测试结束");
		log.info ("用时{}毫秒", System.currentTimeMillis () - startTime);
	}
	//阴阳寮突破
	@Test
	void fightHouse () throws IOException, InterruptedException, AWTException {
		long startTime = System.currentTimeMillis ();
		log.info ("测试开始");
		System.setProperty ("java.awt.headless", "false");
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		// 阴阳师桌面图标.适龄提示,逆戟之刃首页,orcakill首页,洪荒修罗首页,炽热的惆怅物语首页,返回
		log.info ("阴阳师-逆戟之刃-首页");
		String process="夜神模拟器";
		onmyojiService.returnHome (process);
		onmyojiService.fightHouse (process);
		log.info ("测试结束");
		log.info ("用时{}毫秒", System.currentTimeMillis () - startTime);
	}
	
	//战斗
	@Test
	void fightEnd () throws IOException, InterruptedException, AWTException {
		long startTime = System.currentTimeMillis ();
		log.info ("测试开始");
		System.setProperty ("java.awt.headless", "false");
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		// 阴阳师桌面图标.适龄提示,逆戟之刃首页,orcakill首页,洪荒修罗首页,炽热的惆怅物语首页,返回
		log.info ("阴阳师-逆戟之刃-首页");
		String process="夜神模拟器";
		onmyojiService.fightEnd (process,10, 5, 10);
		log.info ("测试结束");
		log.info ("用时{}毫秒", System.currentTimeMillis () - startTime);
	}
	
	//个人结界
	@Test
	void borderCheck () throws IOException, InterruptedException, AWTException {
		long startTime = System.currentTimeMillis ();
		log.info ("测试开始");
		System.setProperty ("java.awt.headless", "false");
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		// 阴阳师桌面图标.适龄提示,逆戟之刃首页,orcakill首页,洪荒修罗首页,炽热的惆怅物语首页,返回
		log.info ("阴阳师-逆戟之刃-首页");
		onmyojiService.borderCheck ("夜神模拟器");
		log.info ("测试结束");
		log.info ("用时{}毫秒", System.currentTimeMillis () - startTime);
	}
	
	//魂十一
	@Test
	void soulBack11 () throws IOException, InterruptedException, AWTException {
		long startTime = System.currentTimeMillis ();
		log.info ("测试开始");
		System.setProperty ("java.awt.headless", "false");
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		// 阴阳师桌面图标.适龄提示,逆戟之刃首页,orcakill首页,洪荒修罗首页,炽热的惆怅物语首页,返回
		log.info ("阴阳师-逆戟之刃-首页");
		onmyojiService.returnHome ("夜神模拟器");
		onmyojiService.soulFight ("夜神模拟器",project_HSY,1,true);
		log.info ("测试结束");
		log.info ("用时{}毫秒", System.currentTimeMillis () - startTime);
	}
	//业原火
	@Test
	void soulBack21 () throws IOException, InterruptedException, AWTException {
		long startTime = System.currentTimeMillis ();
		log.info ("测试开始");
		System.setProperty ("java.awt.headless", "false");
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		// 阴阳师桌面图标.适龄提示,逆戟之刃首页,orcakill首页,洪荒修罗首页,炽热的惆怅物语首页,返回
		log.info ("阴阳师-逆戟之刃-首页");
		onmyojiService.soulFight ("夜神模拟器",project_YYH,1,false);
		log.info ("测试结束");
		log.info ("用时{}毫秒", System.currentTimeMillis () - startTime);
	}
	
	//御灵
	@Test
	void spirit () throws IOException, InterruptedException, AWTException {
		long startTime = System.currentTimeMillis ();
		log.info ("测试开始");
		System.setProperty ("java.awt.headless", "false");
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		// 阴阳师桌面图标.适龄提示,逆戟之刃首页,orcakill首页,洪荒修罗首页,炽热的惆怅物语首页,返回
		log.info ("阴阳师-逆戟之刃-首页");
		onmyojiService.spirit ("夜神模拟器",1);
		log.info ("测试结束");
		log.info ("用时{}毫秒", System.currentTimeMillis () - startTime);
	}
	//斗技
	@Test
	void pvp () throws IOException, InterruptedException, AWTException {
		long startTime = System.currentTimeMillis ();
		log.info ("测试开始");
		System.setProperty ("java.awt.headless", "false");
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		// 阴阳师桌面图标.适龄提示,逆戟之刃首页,orcakill首页,洪荒修罗首页,炽热的惆怅物语首页,返回
		log.info ("阴阳师-逆戟之刃-首页");
		onmyojiService.pvp ("夜神模拟器",1);
		log.info ("测试结束");
		log.info ("用时{}毫秒", System.currentTimeMillis () - startTime);
	}
	
	@Test
	void SoulEnhancements () throws IOException, InterruptedException, AWTException {
		long startTime = System.currentTimeMillis ();
		log.info ("测试开始");
		System.setProperty ("java.awt.headless", "false");
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		// 阴阳师桌面图标.适龄提示,逆戟之刃首页,orcakill首页,洪荒修罗首页,炽热的惆怅物语首页,返回
		log.info ("阴阳师-逆戟之刃-首页");
		onmyojiService.soulEnhancements ("夜神模拟器", 20);
		log.info ("测试结束");
		log.info ("用时{}毫秒", System.currentTimeMillis () - startTime);
	}
	
	@Test
	void soulLevelEnhancementRecognition () {
		long startTime = System.currentTimeMillis ();
		log.info ("测试开始");
		System.setProperty ("java.awt.headless", "false");
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		// 阴阳师桌面图标.适龄提示,逆戟之刃首页,orcakill首页,洪荒修罗首页,炽热的惆怅物语首页,返回
		log.info ("阴阳师-逆戟之刃-首页");
		onmyojiService.soulLevelEnhancementRecognition ("夜神模拟器");
		log.info ("测试结束");
		log.info ("用时{}毫秒", System.currentTimeMillis () - startTime);
	}
	@Test
	void explore() throws IOException, InterruptedException, AWTException {
		long startTime = System.currentTimeMillis ();
		log.info ("测试开始");
		System.setProperty ("java.awt.headless", "false");
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		// 阴阳师桌面图标.适龄提示,逆戟之刃首页,orcakill首页,洪荒修罗首页,炽热的惆怅物语首页,返回
		log.info ("阴阳师-逆戟之刃-首页");
		onmyojiService.explore ("夜神模拟器", 2);
		log.info ("测试结束");
		log.info ("用时{}毫秒", System.currentTimeMillis () - startTime);
	}
	
	@Test
	void exploreFast() throws IOException, InterruptedException, AWTException {
		long startTime = System.currentTimeMillis ();
		log.info ("测试开始");
		System.setProperty ("java.awt.headless", "false");
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		// 阴阳师桌面图标.适龄提示,逆戟之刃首页,orcakill首页,洪荒修罗首页,炽热的惆怅物语首页,返回
		log.info ("阴阳师-逆戟之刃-首页");
		onmyojiService.exploreFast("夜神模拟器", 1);
		log.info ("测试结束");
		log.info ("用时{}毫秒", System.currentTimeMillis () - startTime);
	}
	
	@Test
	void regionalGhostKing() throws IOException, InterruptedException, AWTException {
		long startTime = System.currentTimeMillis ();
		log.info ("测试开始");
		System.setProperty ("java.awt.headless", "false");
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		// 阴阳师桌面图标.适龄提示,逆戟之刃首页,orcakill首页,洪荒修罗首页,炽热的惆怅物语首页,返回
		log.info ("阴阳师-逆戟之刃-首页");
		onmyojiService.regionalGhostKing("夜神模拟器", "1");
		log.info ("测试结束");
		log.info ("用时{}毫秒", System.currentTimeMillis () - startTime);
	}
	

	
	
	
}