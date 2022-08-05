package com.example.controller;

import com.example.model.entity.PictureIdentifyWorkPO;
import com.example.model.map.CoordinateAddress;
import com.example.service.FightService;
import com.example.service.ImageService;
import com.example.util.MouseClick;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.controller.LoginController.loginExplore;
import static com.example.service.FightService.soulBack;
import static java.lang.Thread.sleep;

/**
 * @author orcakill
 * @version 1.0.0
 * @ClassName FightAutoController.java
 * @Description TODO
 * @createTime 2022年08月05日 09:49:00
 */
public class FightAutoController {
	public static final Logger logger = LogManager.getLogger ("FightAutoController");
	
	//阴阳寮战斗
	public static void fightHome () throws InterruptedException, AWTException {
		//变量赋值
		String file = "scenario/结界突破/结界突破";
		String file1 = "scenario/结界突破/阴阳寮";
		String file2 = "scenario/结界突破/挑战次数";
		String file3 = "scenario/结界突破/寮结界";
		String file4 = "scenario/结界突破/进攻";
		String file5 = "scenario/御魂/角色头像";
		String file6 = "scenario/御魂/退出挑战";
		String file7 = "scenario/结界突破/失败";
		String file8 = "scenario/结界突破/退出进攻";
		String file41 = "scenario/返回";
		String file9 = "scenario/首页";
		int num = 0;
		int num1 = 0;
		int num2 = 0;
		boolean b;
		boolean b1 = true;
		//流程开始
		logger.info ("准备进入探索");
		loginExplore ();
		logger.info ("准备进入结界突破");
		ImageService.imagesClickBack (file);
		sleep (2000);
		logger.info ("进入结界突破，准备点击阴阳寮");
		ImageService.imagesClickBack (file1);
		sleep (2000);
		logger.info ("进入寮突破，判断当前有无挑战次数");
		//退出到探索
		while (!ImageService.imagesClickBackIsEmpty (file2, 5) && ImageService.imagesClickBackIsEmpty (file3, 5)) {
			logger.info ("存在可攻打结界，且存在挑战次数");
			logger.info ("准备选择结界");
			ImageService.imagesClickBack (file3);
			logger.info ("选择结界成功，准备进攻");
			ImageService.imagesClickBack (file4);
			logger.info ("开始进攻");
			b = ImageService.imagesClickBackIsEmpty (file4, 3);
			if (b) {
				ImageService.imagesClickBack (file8);
				logger.info ("结界已被攻破，退出进攻");
				ImageService.imagesClickBack (file41);
				logger.info ("退出到探索");
				logger.info ("准备重新进入结界突破");
				ImageService.imagesClickBack (file);
				logger.info ("进入结界突破，准备点击阴阳寮");
				ImageService.imagesClickBack (file1);
				logger.info ("重新判断是否有结界可以攻打");
				continue;
			}
			else {
				logger.info ("结界未被攻破");
			}
			b1 = FightService.fightEnd ();
			if (b1) {
				num1++;
			}
			else {
				num2++;
			}
			num++;
			if (num == 8) {
				logger.info ("阴阳寮挑战到达8次");
				break;
			}
			logger.info ("阴阳寮挑战第" + num + "次,成功" + num1 + "次，失败" + num2 + "次");
		}
		logger.info ("无挑战次数或无可攻打结界");
		ImageService.imagesClickBack (file41);
		logger.info ("退出到探索");
		ImageService.imagesClickBack (file41);
		logger.info ("退出到首页");
		
	}
	
	public static void borderCheck () throws InterruptedException, AWTException {
		String file = "scenario/结界突破/结界突破";
		String file1 = "scenario/结界突破/结界挑战劵数";
		String file2 = "scenario/结界突破/个人结界";
		String file3 = "scenario/结界突破/进攻";
		String file4 = "scenario/御魂/角色头像";
		String file5 = "scenario/御魂/退出挑战";
		String file51 = "scenario/结界突破/失败";
		String file6 = "scenario/结界突破/呱太结界";
		String file7 = "scenario/结界突破/准备挑战";
		boolean b1;
		boolean b2 = false;
		int num = 0; //战斗次数 默认为0次
		//进入结界挑战
		logger.info ("准备进入结界突破");
		ImageService.imagesClickBack (file);
		logger.info ("进入结界突破，检查结界挑战劵");
		//判断结界挑战劵是否为0
		while (!ImageService.imagesClickBackIsEmpty (file1, 3)) {
			//不为0则进行结界挑战
			logger.info ("结界劵数不为零");
			//判断能否选择未挑战的个人结界
			b1 = ImageService.imagesClickBackIsEmpty (file2, 3);
			if (!b1) {
				b2 = ImageService.imagesClickBackIsEmpty (file2, 3);
			}
			if (b1 || b2) {
				logger.info ("能选择个人结界");
				if (b1) {
					ImageService.imagesClickBack (file2);
				}
				if (b2) {
					ImageService.imagesClickBack (file6);
				}
				logger.info ("点击个人结界成功，准备进攻");
				ImageService.imagesClickBack (file3);
				logger.info ("开始进攻");
				if (b2) {
					ImageService.imagesClickBack (file7);
				}
				FightService.fightEnd ();
				if (ImageService.imagesClickBackIsEmpty (file5, 4)) {
					logger.info ("每打完三个有额外奖励");
					ImageService.imagesClickBack (file5);
					logger.info ("领取额外奖励成功");
				}
				else {
					logger.info ("没有额外奖励");
				}
			}
			logger.info ("战斗后检查是否还有结界挑战劵");
		}
		//为0则不进行结界挑战
		logger.info ("结界劵数为0");
		//退出到探索
		String file21 = "scenario/返回";
		ImageService.imagesClickBack (file21);
		logger.info ("退出到探索");
		ImageService.imagesClickBack (file21);
		logger.info ("退出到首页");
	}
	
	public static void soulFight (int i, int j, boolean b) throws InterruptedException, AWTException {
		String file = "scenario/御魂/御魂图标";
		String file1 = "scenario/御魂/御魂类型/八岐大蛇";
		String file2 = "scenario/御魂/加成";
		String file3 = "scenario/御魂/层数/魂十一";
		String file31 = "scenario/御魂/层数/魂十";
		String file11 = "scenario/御魂/御魂类型/业原火";
		String file21 = "scenario/御魂/层数/业原火三层";
		String file13 = "scenario/御魂/御魂类型/日轮之陨";
		String file23 = "scenario/御魂/层数/日轮之陨三层";
		String file32 = "scenario/返回";
		boolean b1;
		boolean b2;
		List<PictureIdentifyWorkPO> pictureIdentifyWorkPOList = new ArrayList<> ();
		logger.info ("准备进入御魂");
		ImageService.imagesClickBack (file);
		if (i == 10 || i == 11) {
			logger.info ("进入御魂成功，准备选择八岐大蛇");
			ImageService.imagesClickBack (file1);
			logger.info ("进入八岐大蛇挑战页面");
			//获取加成地址，手动测试出御魂加成的坐标，然后存储到代码，然后根据当前分辨率获取唯一坐标
			pictureIdentifyWorkPOList = CoordinateAddress.getCoordinate ("御魂加成");
			if (b) {
				logger.info ("准备开启加成");
				ImageService.imagesClickBack (file2);
				logger.info ("点击加成成功，准备点击御魂加成");
				MouseClick.mouseClickBack (pictureIdentifyWorkPOList);
				logger.info ("点击御魂加成成功，准备退出");
				ImageService.imagesClickBack (file2);
				//退出加成页面
				logger.info ("退出加成页面");
			}
			if (i == 11) {
				
				logger.info ("选择魂十一");
				b1 = ImageService.imagesClickBackIsEmpty (file3, 30);
				if (!b1) {
					logger.info ("没有选择到魂十一");
					System.exit (0);
				}
				ImageService.imagesClickBack (file3);
			}
			else {
				
				logger.info ("选择魂十");
				b2 = ImageService.imagesClickBackIsEmpty (file31, 30);
				if (!b2) {
					logger.info ("没有选择到魂十");
					System.exit (0);
				}
				ImageService.imagesClickBack (file3);
			}
			
			//选择魂十或魂十一
			//开始挑战
			soulBack (j);
			//挑战结束
			if (b) {
				//关闭加成
				ImageService.imagesClickBack (file2);
				logger.info ("点击加成成功，准备关闭御魂加成");
				MouseClick.mouseClickBack (pictureIdentifyWorkPOList);
				logger.info ("关闭御魂加成成功，准备退出");
				ImageService.imagesClickBack (file2);
				//退出加成页面
				logger.info ("退出加成页面");
			}
		}
		//业原火
		if (i == 21) {
			logger.info ("进入御魂成功，准备选择业原火");
			ImageService.imagesClickBack (file11);
			logger.info ("进入业原火");
			logger.info ("选择业原火第三层");

			b1 = ImageService.imagesClickBackIsEmpty (file21, 30);
			if (!b1) {
				logger.info ("没有选择到业原火第三层");
				System.exit (0);
			}
			ImageService.imagesClickBack (file21);
			logger.info ("开始挑战");
			//开始挑战
			soulBack (j);
		}
		if (i == 31) {
			logger.info ("进入御魂成功，准备选择日轮之陨");

			ImageService.imagesClickBack (file13);
			logger.info ("进入日轮之陨");
			logger.info ("选择日轮之陨第三层");

			b1 = ImageService.imagesClickBackIsEmpty (file23, 30);
			if (!b1) {
				logger.info ("没有选择到日轮之陨三层");
				System.exit (0);
			}
			ImageService.imagesClickBack (file23);
			logger.info ("开始挑战");
			//开始挑战
			soulBack (j);
		}
		logger.info ("退出到探索");
		//退出到探索
		ImageService.imagesClickBack (file32);
		logger.info ("退出到首页");
		//退出到首页
		ImageService.imagesClickBack (file32);
	}
}
