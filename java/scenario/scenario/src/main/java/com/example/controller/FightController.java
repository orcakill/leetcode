package com.example.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @Classname FigthController
 * @Description TODO
 * @Date 2022/8/5 0:09
 * @Created by orcakill
 */
public class FightController {
	public  static  final Logger logger = LogManager.getLogger ("FightController");
	
	//循环战斗
	public  static void fightGame(Integer a,Integer b){
		Integer num=0;
		if(b==1){
		    num=40;//魂土，有阴阳寮战斗
		}
		else if (b==2) {
			num=60;//魂土，无阴阳寮战斗
		}
		else if (b==3) {
			num=100;//业原火
		}
		else if (b==4) {
			num=50;//日轮之陨
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
		//执行轮次
		for(int i=0;i<a;i++){
		
		}
	}
}
