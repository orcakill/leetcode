package other.scenario.service;

import other.scenario.service.impl.FightAutoServiceImpl;

import java.awt.*;
import java.io.IOException;

/**
 * @Classname FightAutoService
 * @Description 自动战斗
 * @Date 2021/10/16 15:39
 * @Created by orcakill
 */
public interface FightAutoService {
	static int cherrySoul(Integer num) throws InterruptedException, AWTException {
		return FightAutoServiceImpl.cherrySoul(num);
	}
	
//	樱饼刷经验
	static boolean cherryExperience() throws InterruptedException, AWTException {
		return FightAutoServiceImpl.cherryExperience();
	}
	
//	好友助战刷结界
	static int friendBorder(Integer num) throws InterruptedException, AWTException {
		return FightAutoServiceImpl.friendBorder(num);
	}
	
    //	进入探索
	static  boolean comeExplore () throws InterruptedException, AWTException {
		return  FightAutoServiceImpl.comeExplore ();
	}
//	 挑战失败
    static  void fightFalse () throws InterruptedException, AWTException {
	   FightAutoServiceImpl.fightFalse ();
    }
	
	//	领取每日任务奖励
	static boolean receiveReward() throws InterruptedException, AWTException {
		return FightAutoServiceImpl.receiveReward() ;
	}
	
	//	协战截图
	static boolean friendScreen(String userName) throws InterruptedException, AWTException, IOException {
		return FightAutoServiceImpl.friendScreen(userName) ;
	}
	/***
	 * @description: 固定次数刷御魂
	 * @return: void
	 * @author: orcakill
	 * @date: 2021/11/14 17:27
	 */
	static void  soulBack(Integer num) throws InterruptedException, AWTException {
		FightAutoServiceImpl.soulBack(num);
	}
	
	
	static  void borderCheck() throws Exception {
		FightAutoServiceImpl.borderCheck();
	}
	
	static void soulEleven (int i,int j,boolean b) throws Exception {
		FightAutoServiceImpl.soulEleven(i,j,b);
	}
	
	static void spirit () throws InterruptedException, AWTException {
		FightAutoServiceImpl.spirit ();
	}
	
	static void fightHome () throws Exception {
		FightAutoServiceImpl.fightHome ();
	}
	
	static void soulFriend () throws InterruptedException, AWTException {
		FightAutoServiceImpl.soulFriend ();
	}
	
	static void PVP () throws InterruptedException, AWTException {
		FightAutoServiceImpl.PVP();
	}
}
