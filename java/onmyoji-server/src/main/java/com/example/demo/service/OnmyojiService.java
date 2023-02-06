package com.example.demo.service;

import org.springframework.scheduling.annotation.Async;

import java.awt.*;
import java.io.IOException;

/**
 * @Classname OnmyojiService
 * @Description 阴阳师自动战斗
 * @Date 2023/1/25 20:56
 * @Created by orcakill
 */
public interface OnmyojiService {
	/***
	 * @description: 阴阳师自动战斗
	 * @param type  类型
	 * @param round   轮次
	 * @return: java.lang.String
	 * @author: orcakill
	 * @date: 2023/1/25 20:58
	 */
	@Async
	void onmyojiService (Integer type, Integer round) throws InterruptedException;
	
	/***
	 * @description: 开启自动游戏
	 * @param type  类型
	 * @param round   轮次
	 * @return: void
	 * @author: orcakill
	 * @date: 2023/1/28 23:42
	 */
	
	void autoActivity (Integer type, Integer round) throws IOException, InterruptedException, AWTException;

	/***
	 * @description: 状态初始化，登录到目标首页
	 * @return: void
	 * @author: orcakill
	 * @date: 2023/2/6 8:36
	 */
	
	void initializationState(String userId) throws IOException, InterruptedException, AWTException;
	
	/***
	 * @description: 当前状态判断  阴阳师图标、服务器界面、首页、其他有返回按钮的界面
	 * @return: void
	 * @author: orcakill
	 * @date: 2023/2/4 2:04
	 */
	String thisState() throws IOException, InterruptedException, AWTException;
	/***
	 * @description: 返回首页
	 * @return: void
	 * @author: orcakill
	 * @date: 2023/2/4 1:58
	 */
	void returnHome ();
	/***
	 * @description: 阴阳师游戏角色ID登录，默认登录大号角色
	 * @param gameUserId 阴阳师游戏角色ID
	 * @return: void
	 * @author: orcakill
	 * @date: 2023/1/28 23:21
	 */
	void login (String gameUserId) throws IOException, InterruptedException, AWTException;
}
