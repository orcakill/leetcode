package com.example.demo.service;

import org.springframework.scheduling.annotation.Async;

import java.awt.*;
import java.io.IOException;
import java.net.UnknownHostException;

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
	void onmyojiService (String process,Integer type, Integer round) throws InterruptedException, UnknownHostException;
	
	/***
	 * @description: 开启自动游戏
	 * @param type  类型
	 * @param round   轮次
	 * @return: void
	 * @author: orcakill
	 * @date: 2023/1/28 23:42
	 */
	
	void autoActivity (String process,Integer type, Integer round) throws IOException, InterruptedException, AWTException;
	
	/***
	 * @description: 状态初始化，登录到目标首页
	 * @return: void
	 * @author: orcakill
	 * @date: 2023/2/6 8:36
	 */
	
	void initializationState (String process,String userId) throws IOException, InterruptedException, AWTException;
	
	/***
	 * @description: 当前状态判断  阴阳师图标、服务器界面、首页、其他有返回按钮的界面
	 * @return: void
	 * @author: orcakill
	 * @date: 2023/2/4 2:04
	 */
	String thisState (String process) throws IOException, InterruptedException, AWTException;
	
	/***
	 * @description: 返回首页
	 * @return: void
	 * @author: orcakill
	 * @date: 2023/2/4 1:58
	 */
	void returnHome (String process) throws IOException, InterruptedException, AWTException;
	
	/***
	 * @description: 阴阳师游戏角色ID登录，默认登录大号角色
	 * @param gameUserId 阴阳师游戏角色ID
	 * @return: void
	 * @author: orcakill
	 * @date: 2023/1/28 23:21
	 */
	void login (String process,String gameUserId) throws IOException, InterruptedException, AWTException;
	
	/***
	 * @description: 式神寄养 优先太鼓其次斗鱼，无则寄养第一个
	 * @return: void
	 * @author: orcakill
	 * @date: 2023/2/21 10:04
	 */
	void toFoster (String process) throws InterruptedException, IOException, AWTException;
	
	/***
	 * @description: 阴阳寮突破  突破8次或无挑战次数或已攻破
	 * @return: void
	 * @author: orcakill
	 * @date: 2023/2/21 21:10
	 */
	void fightHouse (String process) throws IOException, InterruptedException, AWTException;
	
	/**
	 * @param begin_num 开始识别时间
	 * @param start_num 识别间隔开始时间
	 * @param end_num   识别间隔结束时间
	 * @description: 战斗结果 挑战成功或失败 （统计战斗数据）
	 * @return: void
	 * @author: orcakill
	 * @date: 2023/2/21 22:21
	 */
	boolean fightEnd (String process,Integer begin_num, Integer start_num, Integer end_num)
			throws InterruptedException, IOException, AWTException;
	
	/***
	 * @description: 个人结界
	 * @return: void
	 * @author: orcakill
	 * @date: 2023/2/22 1:19
	 */
	void borderCheck (String process) throws IOException, InterruptedException, AWTException;
	
	/***
	 * @description: 御魂战斗  魂十一、魂一（协战用）、魂十、业原火、日轮之陨、永生之海
	 * @param soulType 御魂类型
	 * @param soulNum  战斗场次
	 * @param addition  是否开启加成
	 * @return: void
	 * @author: orcakill
	 * @date: 2023/2/22 1:37
	 */
	void soulFight (String process,int soulType, int soulNum, boolean addition) throws IOException, InterruptedException,
	                                                                    AWTException;
	
	/***
	 * @description: 御魂战斗详情
	 * @param begin_time  识别开始时间
	 * @param soulNumber      战斗场次
	 * @return: void
	 * @author: orcakill
	 * @date: 2023/2/22 2:11
	 */
	
	void soulBack (String process,int begin_time, int soulNumber) throws InterruptedException, IOException, AWTException;
	/***
	 * @description: 御灵战斗
	 * @param process  夜神模拟器
     * @param num  战斗次数
	 * @return: void
	 * @author: orcakill
	 * @date: 2023/3/1 11:14
	 */
	void spirit(String process,int num) throws IOException, InterruptedException, AWTException;
}

