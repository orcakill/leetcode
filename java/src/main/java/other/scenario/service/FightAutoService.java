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
	 * @param num
	 * @return: void
	 * @author: orcakill
	 * @date: 2021/11/14 17:27
	 */
	static void  soulBack(Integer num) throws InterruptedException, AWTException {
		FightAutoServiceImpl.soulBack(num);
	}
	
	/***
	 * @description: 拒接协战
	 * @param
	 * @return: void
	 * @author: orcakill
	 * @date: 2021/11/14 17:31
	 */
	static void  refuseBack() throws InterruptedException, AWTException {
		FightAutoServiceImpl.refuseBack();
	}
	
	static  void borderCheck() throws InterruptedException, AWTException {
		FightAutoServiceImpl.borderCheck();
	}
	
	static void soulEleven (int i,boolean b) throws InterruptedException, AWTException {
		FightAutoServiceImpl.soulEleven(i,b);
	}
	
	static void spirit () throws InterruptedException, AWTException {
		FightAutoServiceImpl.spirit ();
	}
	
	static void fightHome () throws InterruptedException, AWTException {
		FightAutoServiceImpl.fightHome ();
	}
}
