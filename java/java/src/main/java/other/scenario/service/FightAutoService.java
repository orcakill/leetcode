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
	static void  soulBack(Integer num) throws Exception {
		FightAutoServiceImpl.soulBack(num);
	}
	
	
	static  void borderCheck() throws Exception {
		FightAutoServiceImpl.borderCheck();
	}
	
	static void soulEleven (int i,int j,boolean b) throws Exception {
		FightAutoServiceImpl.soulEleven(i,j,b);
	}
	
	static void spirit () throws Exception {
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
	
	static void Town (Integer num) throws InterruptedException, AWTException {
		String name;
		double x,y;
		if(num==1){
			name="町中";
			x=1.15;
			y=5.3;
			FightAutoServiceImpl.Town (num,name,x,y);
		}
		else if (num==2) {
			name="活动";
			x=1.5;
			y=4.3;
			FightAutoServiceImpl.Town (num,name,x,y);
		}
		
	}
	
	
	static void sendMail () throws InterruptedException, AWTException {
		FightAutoServiceImpl.sendMail();
	}
	
	static void activity (Integer num) {
		FightAutoServiceImpl.activity(num);
	}
}
