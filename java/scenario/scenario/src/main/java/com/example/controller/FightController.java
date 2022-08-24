package com.example.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.util.RandomUtil.getRandom;

/**
 * @Classname FigthController
 * @Description TODO
 * @Date 2022/8/5 0:09
 * @Created by orcakill
 */
public class FightController {
	public static final Logger logger = LogManager.getLogger ("FightController");
	
	//循环战斗
	public static void fightGame (Integer a, Integer b) throws InterruptedException, AWTException {
		int num1;
		Map<String, Integer> map = new HashMap<> ();
		map.put ("阴阳寮累计战斗", 0);
		map.put ("阴阳寮累计战斗胜利",0);
		map.put ("阴阳寮累计战斗失败",0);
		map.put ("斗技累计战斗", 0);
		map.put ("斗技累计战斗胜利",0);
		map.put ("斗技累计战斗失败",0);
		//执行轮次
		for (int i = 1; i <= a; i++) {
			if (b == 1) {
				//阴阳寮结界
				FightAutoController.fightHome ();
				//个人结界
				FightAutoController.borderCheck ();
				//魂土
				FightAutoController.soulFight (11, 40, true);
			}
			else if (b == 2) {
				//个人结界
				FightAutoController.borderCheck ();
				//魂土
				FightAutoController.soulFight (11, 60, true);
			}
			else if (b == 3) {
				//阴阳寮结界
				FightAutoController.fightHome ();
				//个人结界
				FightAutoController.borderCheck ();
				//魂土
				FightAutoController.soulFight (21, 40, true);
			}
			else if (b == 4) {
				//个人结界
				FightAutoController.borderCheck ();
				//魂土
				FightAutoController.soulFight (31, 50, true);
			}
			else if (b == 5) {
				//永生之海
				FightAutoController.soulFight (41, 30, true);
			}
			else if (b == 6) {
				//御灵
				FightAutoController.spirit (60);
				
			}
			else if (b == 7 || b == 8) {
				//  斗技
				FightAutoController.pvp (10);
			}
			else if (b == 10 || b == 11) {
				//阴阳寮结界
				Map<String, Integer> map1 = FightAutoController.fightHome ();
				map.put ("阴阳寮本轮战斗", map1.get ("阴阳寮本轮战斗"));
				map.put ("阴阳寮本轮战斗胜利", map1.get ("阴阳寮本轮战斗胜利"));
				map.put ("阴阳寮本轮战斗失败", map1.get ("阴阳寮本轮战斗失败"));
				map.put ("阴阳寮累计战斗", map.get ("阴阳寮累计战斗") + map1.get ("阴阳寮本轮战斗"));
				map.put ("阴阳寮累计战斗胜利", map.get ("阴阳寮累计战斗胜利") + map1.get ("阴阳寮本轮战斗胜利"));
				map.put ("阴阳寮累计战斗失败", map.get ("阴阳寮累计战斗失败") + map1.get ("阴阳寮本轮战斗失败"));
				logger.info ("阴阳寮挑战第" + i + "轮,挑战" + map.get ("阴阳寮本轮战斗") + "次,成功" + map.get ("阴阳寮本轮战斗胜利") + "次，失败" + map.get ("阴阳寮本轮战斗失败") + "次");
				if (b == 11) {
					//斗技
					Map<String, Integer> map2 = FightAutoController.pvp (5);
					map.put ("斗技本轮战斗", map2.get ("斗技本轮战斗"));
					map.put ("斗技本轮战斗胜利", map2.get ("斗技本轮战斗胜利"));
					map.put ("斗技本轮战斗失败", map2.get ("斗技本轮战斗失败"));
					map.put ("斗技累计战斗", map.get ("斗技累计战斗") + map2.get ("斗技本轮战斗"));
					map.put ("斗技累计战斗胜利", map.get ("斗技累计战斗胜利") + map2.get ("斗技本轮战斗胜利"));
					map.put ("斗技累计战斗失败", map.get ("斗技累计战斗失败") + map2.get ("斗技本轮战斗失败"));
					logger.info ("斗技挑战第" + i + "轮,挑战" + map.get ("斗技本轮战斗") + "次,成功" + map.get ("斗技本轮战斗胜利") + "次，失败" + map.get ("斗技本轮战斗失败") + "次");
				}
				logger.info ("累计战斗统计******************");
				logger.info ("阴阳寮挑战第" + i + "轮,挑战" + map.get ("阴阳寮累计战斗") + "次,成功" + map.get ("阴阳寮累计战斗胜利") + "次，失败" + map.get ("阴阳寮累计战斗失败") + "次");
				if (b == 11) {
					logger.info ("斗技挑战第" + i + "轮,挑战" + map.get ("斗技累计战斗") + "次,成功" + map.get ("斗技累计战斗胜利") + "次，失败"
					             + map.get ("斗技累计战斗失败") + "次,胜率"+map.get ("斗技累计战斗胜利")*100 /map.get ("斗技累计战斗")+"%");
				}
				//检查寄养，无则寄养
				FightAutoController.foster ();
				if (i < a) {
					num1 = getRandom (30, 40);
					if(b==11&&i<=3){
						num1=getRandom (5, 10);
					}
					logger.info ("等待" + num1 + "分钟");
					Thread.sleep ((long) num1 * 1000 * 60);
				}
				else {
					logger.info ("最后一轮，不等待");
				}
			}
			
		}
		
	}
}
