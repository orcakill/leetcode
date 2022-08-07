package com.example.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;

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
		Integer num=0;
		//执行轮次
		for(int i=0;i<a;i++){
			if(b==1){
				num=1;//魂土，有阴阳寮战斗
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
			}
			else if (b==6) {
				num=60;//御灵
			}
			else if (b==7||b==8) {
				num=60;//斗技  为崽而战
			}
		}
	}
}
