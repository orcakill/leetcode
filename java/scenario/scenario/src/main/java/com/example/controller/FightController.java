package com.example.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.util.RandomUtil.getRandom;

/**
 * @Classname FigthController
 * @Description TODO
 * @Date 2022/8/5 0:09
 * @Created by orcakill
 */
public class FightController {
	public  static  final Logger logger = LogManager.getLogger ("FightController");
	
	//循环战斗
	public  static void fightGame(Integer a,Integer b) throws InterruptedException, AWTException {
		int num=0;
        int num1=0;
		List<Integer> list=new ArrayList<> ();
		//执行轮次
		for(int i=0;i<a;i++){
			if(b==1){
				num=40;//魂土，有阴阳寮战斗
				//阴阳寮结界
				FightAutoController.fightHome ();
				//个人结界
				FightAutoController.borderCheck ();
				//魂土
				FightAutoController.soulFight(11,num,true);
			}
			else if (b==2) {
				num=60;//魂土，无阴阳寮战斗
				//个人结界
				FightAutoController.borderCheck ();
				//魂土
				FightAutoController.soulFight(11,num,true);
			}
			else if (b==3) {
				num=100;//业原火
				//个人结界
				FightAutoController.borderCheck ();
				//魂土
				FightAutoController.soulFight(21,num,true);
			}
			else if (b==4) {
				num=50;//日轮之陨
				//个人结界
				FightAutoController.borderCheck ();
				//魂土
				FightAutoController.soulFight(31,num,true);
			}
			else if (b==5) {
				num=30;//永生之海
				FightAutoController.soulFight(41,num,true);
			}
			else if (b==6) {
				num=60;//御灵
				FightAutoController.spirit (60);
				
			}
			else if (b==7||b==8) {
				num=10;//斗技  为崽而战
			
            //  斗技
				FightAutoController.pvp(num);
			}
			else if (b==10) {
				//阴阳寮结界
				list=FightAutoController.fightHome ();
				//检查寄养，无则寄养
				FightAutoController.foster();
				logger.info ("阴阳寮挑战第" + list.get (0)+ "次,成功" + list.get (1)+ "次，失败" + list.get (2) + "次");
				if(i>0){
					num1=getRandom (30,40);
					logger.info ("等待"+num1+"分钟");
					Thread.sleep ((long) num1 * 1000 * 60);
				}
			}
			else if (b==11) {
				num=5;
				//阴阳寮结界
				FightAutoController.fightHome ();
				//斗技
				FightAutoController.pvp(num);
				//检查寄养，无则寄养
				FightAutoController.foster();
			}
			
		}

	}
}
