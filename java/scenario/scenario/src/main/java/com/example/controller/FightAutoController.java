package com.example.controller;

import com.example.model.entity.FightResultPO;
import com.example.model.entity.PictureIdentifyWorkPO;
import com.example.model.enums.*;
import com.example.model.map.CoordinateAddress;
import com.example.service.FightService;
import com.example.service.ImageOpenCVService;
import com.example.service.ImageService;
import com.example.util.ImagesBackRec;
import com.example.util.MouseClick;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

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
	public static Map<String, Integer> fightHome () throws InterruptedException, AWTException, IOException {
		//变量赋值
		String file = "scenario/结界突破/结界突破";
		String file1 = "scenario/结界突破/阴阳寮";
		String file2 = "scenario/结界突破/挑战次数";
		String file3 = "scenario/结界突破/寮结界";
		String file4 = "scenario/结界突破/进攻";
		String file8 = "scenario/结界突破/退出进攻";
		String file41 = "scenario/返回";
		String fileGTRQ = "scenario/结界突破/呱太入侵";
		Map<String, Integer> map = new HashMap<> ();
		int num = 0;
		int num1 = 0;
		int num2 = 0;
		boolean b;
		boolean b1;
		boolean booleanYYL;
		boolean boolean_TCJG;
		//流程开始
		logger.info ("准备进入探索");
		loginExplore ();
		logger.info ("准备进入结界突破");
		ImageService.imagesClickBack (file);
		logger.info ("进入结界突破，准备点击阴阳寮");
		booleanYYL = ImageService.imagesClickBack (file1, 5);
		while (!booleanYYL) {
			Thread.sleep (2000);
			if (ImageService.imagesClickBackIsEmpty (fileGTRQ, 5)) {
				logger.info ("有呱太入侵");
				ImageService.imagesClickBack (fileGTRQ);
				logger.info ("呱太入侵点击完成");
				
			}
			booleanYYL = ImageService.imagesClickBack (file1, 5);
		}
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
				boolean_TCJG = ImageService.imagesClickBackIsEmpty (file8, 10);
				while (boolean_TCJG) {
					logger.info ("结界已被攻破，退出进攻");
					ImageService.imagesClickBack (file8);
					logger.info ("判断是否退出进攻");
					boolean_TCJG = ImageService.imagesClickBackIsEmpty (file8, 10);
				}
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
			b1 = FightService.fightEnd (30, 30, 40);
			if (b1) {
				num1++;
			}
			else {
				num2++;
			}
			num++;
			logger.info ("阴阳寮挑战第" + num + "次,成功" + num1 + "次，失败" + num2 + "次");
			if (num == 8) {
				logger.info ("阴阳寮挑战到达8次");
				break;
			}
		}
		logger.info ("无挑战次数或无可攻打结界");
		ImageService.imagesClickBack (file41);
		logger.info ("退出到探索");
		ImageService.imagesClickBack (file41);
		logger.info ("退出到首页");
		map.put ("阴阳寮本轮战斗", num);
		map.put ("阴阳寮本轮战斗胜利", num1);
		map.put ("阴阳寮本轮战斗失败", num2);
		return map;
		
	}
	
	public static void borderCheck () throws InterruptedException, AWTException, IOException {
		String file = "scenario/结界突破/结界突破";
		String file1 = "scenario/结界突破/结界挑战劵数";
		String file2 = "scenario/结界突破/个人结界";
		String file3 = "scenario/结界突破/进攻";
		String file5 = "scenario/御魂/退出挑战";
		String file7 = "scenario/结界突破/准备挑战";
		boolean booleanKXJJ;//可选结界
		boolean booleanZBTJ;//准备挑战
		boolean whetherAdditionalReward;
		logger.info ("准备进入探索");
		loginExplore ();
		//进入结界挑战
		logger.info ("准备进入结界突破");
		ImageService.imagesClickBack (file);
		Thread.sleep (3000);
		MouseClick.mouseClickBack (0, 0, "夜神模拟器");
		Thread.sleep (1000);
		MouseClick.mouseClickBack (0, 0, "夜神模拟器");
		logger.info ("进入结界突破，检查结界挑战劵");
		//判断结界挑战劵是否为0
		while (!ImageService.imagesClickBackIsEmpty (file1, 3)) {
			//不为0则进行结界挑战
			logger.info ("结界劵数不为零");
			//判断能否选择个人结界
			booleanKXJJ = ImageService.imagesClickBackIsEmpty (file2, 3);
			if (booleanKXJJ) {
				logger.info ("能选择个人结界");
				ImageService.imagesClickBack (file2);
				logger.info ("点击个人结界成功，准备进攻");
				ImageService.imagesClickBack (file3);
				logger.info ("开始进攻");
				Thread.sleep (10 * 1000);
				logger.info ("判断是否准备挑战");
				booleanZBTJ = ImageOpenCVService.imagesOpenCVIsEmpty (file7, 5);
				if (booleanZBTJ) {
					logger.info ("准备挑战");
					ImageOpenCVService.imagesOpenCV (file7);
				}
				else {
					logger.info ("无需准备");
				}
				logger.info ("进入战斗");
				FightService.fightEnd (30, 5, 10);
				logger.info ("判断是否有额外奖励");
				whetherAdditionalReward = ImageService.imagesClickBackIsEmpty (file5, 5);
				if (whetherAdditionalReward) {
					ImageService.imagesClickBack (file5, 5);
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
	
	public static void soulFight (int i, int j, boolean b) throws InterruptedException, AWTException, IOException {
		boolean b1;
		boolean b2;
		List<PictureIdentifyWorkPO> pictureIdentifyWorkPOList;
		logger.info ("准备进入探索");
		loginExplore ();
		logger.info ("准备进入御魂");
		ImageService.imagesClickBack (SoulEnums.icon.getValue ());
		if (i == 10 || i == 11) {
			logger.info ("进入御魂成功，准备选择八岐大蛇");
			ImageService.imagesClickBack (SoulEnums.type_BQDS.getValue ());
			logger.info ("进入八岐大蛇挑战页面");
			//获取加成地址，手动测试出御魂加成的坐标，然后存储到代码，然后根据当前分辨率获取唯一坐标
			pictureIdentifyWorkPOList = CoordinateAddress.getCoordinate ("御魂加成");
			if (b) {
				logger.info ("准备开启加成");
				ImageService.imagesClickBack (SoulEnums.addition.getValue ());
				logger.info ("点击加成成功，准备点击御魂加成");
				MouseClick.mouseClickBack (pictureIdentifyWorkPOList, "夜神模拟器", true);
				logger.info ("点击御魂加成成功，准备退出");
				ImageService.imagesClickBack (SoulEnums.addition.getValue ());
				//退出加成页面
				logger.info ("退出加成页面");
			}
			if (i == 11) {
				
				logger.info ("选择魂十一");
				b1 = ImageService.imagesClickBackIsEmpty (SoulEnums.type_HSY.getValue (), 30);
				if (!b1) {
					logger.info ("没有选择到魂十一");
					System.exit (0);
				}
				ImageService.imagesClickBack (SoulEnums.type_HSY.getValue ());
			}
			else {
				logger.info ("选择魂十");
				b2 = ImageService.imagesClickBackIsEmpty (SoulEnums.type_HS.getValue (), 30);
				if (!b2) {
					logger.info ("没有选择到魂十");
					System.exit (0);
				}
				ImageService.imagesClickBack (SoulEnums.type_HSY.getValue ());
			}
			
			//选择魂十或魂十一
			//开始挑战
			soulBack (22, j);
			//挑战结束
			if (b) {
				//关闭加成
				ImageService.imagesClickBack (SoulEnums.addition.getValue ());
				logger.info ("点击加成成功，准备关闭御魂加成");
				MouseClick.mouseClickBack (pictureIdentifyWorkPOList, "夜神模拟器", true);
				logger.info ("关闭御魂加成成功，准备退出");
				ImageService.imagesClickBack (SoulEnums.addition.getValue ());
				//退出加成页面
				logger.info ("退出加成页面");
			}
		}
		//业原火
		if (i == 21) {
			logger.info ("进入御魂成功，准备选择业原火");
			ImageService.imagesClickBack (SoulEnums.type_YYH.getValue ());
			logger.info ("进入业原火");
			logger.info ("选择业原火第三层");
			
			b1 = ImageService.imagesClickBackIsEmpty (SoulEnums.type_YYHSC.getValue (), 30);
			if (!b1) {
				logger.info ("没有选择到业原火第三层");
				System.exit (0);
			}
			ImageService.imagesClickBack (SoulEnums.type_YYHSC.getValue ());
			logger.info ("开始挑战");
			//开始挑战
			soulBack (40, j);
		}
		if (i == 31) {
			logger.info ("进入御魂成功，准备选择日轮之陨");
			
			ImageService.imagesClickBack (SoulEnums.type_RLZY.getValue ());
			logger.info ("进入日轮之陨");
			logger.info ("选择日轮之陨第三层");
			b1 = ImageService.imagesClickBackIsEmpty (SoulEnums.type_RLZYSC.getValue (), 30);
			if (!b1) {
				logger.info ("没有选择到日轮之陨三层");
				System.exit (0);
			}
			ImageService.imagesClickBack (SoulEnums.type_RLZYSC.getValue ());
			logger.info ("开始挑战");
			//开始挑战
			soulBack (15, j);
		}
		if (i == 41) {
			logger.info ("进入御魂成功，准备选择永生之海");
			ImageService.imagesClickBack (SoulEnums.type_YSZH.getValue ());
			logger.info ("选择永生之海第四层");
			b1 = ImageService.imagesClickBackIsEmpty (SoulEnums.type_YSZHSC.getValue (), 30);
			if (!b1) {
				logger.info ("没有选择到永生之海四层");
				System.exit (0);
			}
			logger.info ("开启永生之海加成");
			ImageService.imagesClickBack (SoulEnums.type_YSZHJC.getValue ());
			logger.info ("开始挑战");
			//开始挑战
			soulBack (15, j);
		}
		logger.info ("退出到探索");
		//退出到探索
		ImageService.imagesClickBack (BackEnums.back.getValue ());
		logger.info ("退出到首页");
		//退出到首页
		ImageService.imagesClickBack (BackEnums.back.getValue ());
	}
	
	public static void spirit (int num) throws InterruptedException, AWTException, IOException {
		String file = "scenario/御灵/御灵图标";
		String file1 = "scenario/御灵/神龙";
		String file2 = "scenario/御灵/白藏主";
		String file3 = "scenario/御灵/黑豹";
		String file4 = "scenario/御灵/孔雀";
		String file5 = "scenario/御灵/第三层";
		String file6 = "scenario/返回";
		logger.info ("准备进入探索");
		loginExplore ();
		logger.info ("准备进入御灵");
		ImageService.imagesClickBack (file);
		logger.info ("进入御灵");
		//判断当前是星期几，周一无法打御灵，周二神龙，周三白藏主，周四黑豹，周五孔雀，周六周日白藏主
		Date today = new Date ();
		Calendar c = Calendar.getInstance ();
		c.setTime (today);
		int weekday = c.get (Calendar.DAY_OF_WEEK);
		//周一
		if (weekday == 2) {
			logger.info ("无法打御灵");
			System.exit (0);
		}
		//周二
		else if (weekday == 3) {
			
			logger.info ("准备进入御灵神龙");
			ImageService.imagesClickBack (file1);
			logger.info ("进入御灵神龙");
		}
		//周三、周六和周日
		else if (weekday == 4 || weekday == 7 || weekday == 1) {
			
			logger.info ("准备进入御灵白藏主");
			ImageService.imagesClickBack (file2);
			logger.info ("进入御灵白藏主");
		}
		//周四
		else if (weekday == 5) {
			
			logger.info ("准备进入御灵黑豹");
			ImageService.imagesClickBack (file3);
			logger.info ("进入御灵黑豹");
		}
		//周五
		else if (weekday == 6) {
			
			logger.info ("准备进入御灵孔雀");
			ImageService.imagesClickBack (file4);
			logger.info ("进入御灵孔雀");
		}
		logger.info ("选择第三层");
		ImageService.imagesClickBack (file5);
		logger.info ("开始挑战");
		soulBack (15, num);
		//退出到探索
		logger.info ("退出到探索");
		ImageService.imagesClickBack (file6);
		//退出到探索
		logger.info ("退出到首页");
		ImageService.imagesClickBack (file6);
	}
	
	public static Map<String, Integer> pvp (Integer num) throws InterruptedException, AWTException, IOException {
		String file = "scenario/斗技/开始挑战";
		String file1 = "scenario/斗技/自动选择";
		String file2 = "scenario/斗技/段位晋升";
		String file3 = "scenario/斗技/额外奖励";
		String file4 = "scenario/返回";
		String file5 = "scenario/斗技/斗技图标";
		String fileDZWG = "scenario/斗技/町中武馆";
		Map<String, Integer> map = new HashMap<> ();
		boolean b;
		boolean b1;
		boolean b2;
		boolean booleanYCQK;
		boolean booleanFH;
		int num1 = 0;
		int num2 = 0;
		logger.info ("开始斗技");
		//	进入町中
		LoginController.loginTown ();
		ImageOpenCVService.imagesOpenCV (fileDZWG);
		//  进入斗技
		ImageService.imagesClickBack (file5);
		for (int i = 1; i <= num; i++) {
			logger.info ("准备挑战");
			booleanYCQK = ImageService.imagesClickBack (file, 5);
			while (!booleanYCQK) {
				logger.info ("判断是否段位晋升");
				b2 = ImageService.imagesClickBack (file2, 5);
				if (!b2) {
					logger.info ("没有段位晋升");
				}
				else {
					logger.info ("存在段位晋升");
				}
				logger.info ("判断是否有额外奖励");
				b1 = ImageService.imagesClickBack (file3, 5);
				if (!b1) {
					logger.info ("没有额外奖励");
				}
				else {
					logger.info ("存在额外奖励");
				}
				booleanYCQK = ImageService.imagesClickBack (file, 20);
			}
			logger.info ("进入挑战，准备自动选择");
			ImageService.imagesClickBack (file1, 20);
			logger.info ("自动选择完成，准备自动战斗");
			Thread.sleep (2000);
			logger.info ("等待选将");
			Thread.sleep (30 * 1000);
			logger.info ("准备拔得头筹、战斗胜利或战斗失败");
			b = FightService.fightEndPVP (30, 30, 60);
			if (b) {
				num1++;
			}
			else {
				num2++;
			}
			logger.info ("第" + i + "次挑战完成，胜利" + num1 + "次，失败" + num2 + "次，胜率" + num1 * 100 / i + "%");
		}
		//返回首页
		logger.info ("斗技结束,返回首页");
		booleanFH = ImageService.imagesClickBack (file4, 5);
		while (!booleanFH) {
			logger.info ("判断斗技结束后，是否段位晋升");
			b2 = ImageService.imagesClickBack (file2, 5);
			if (!b2) {
				logger.info ("没有段位晋升");
			}
			else {
				logger.info ("存在段位晋升");
			}
			logger.info ("判断斗技结束后，是否有额外奖励");
			b1 = ImageService.imagesClickBack (file3, 5);
			if (!b1) {
				logger.info ("没有额外奖励");
			}
			else {
				logger.info ("存在额外奖励");
			}
			booleanFH = ImageService.imagesClickBack (file4, 20);
		}
		FightService.returnHome ();
		map.put ("斗技本轮战斗", num);
		map.put ("斗技本轮战斗胜利", num1);
		map.put ("斗技本轮战斗失败", num2);
		return map;
	}
	
	//寄养
	public static void foster () throws InterruptedException, AWTException, IOException {
		String fileYYLTB = "scenario/阴阳寮/阴阳寮图标";
		String fileJJ = "scenario/阴阳寮/结界";
		String fileSSYC = "scenario/阴阳寮/式神育成";
		String fileKJY = "scenario/阴阳寮/可寄养";
		String fileHYBT = "scenario/阴阳寮/好友标题";
		String fileLXTG = "scenario/阴阳寮/六星太鼓";
		String fileWXTG = "scenario/阴阳寮/五星太鼓";
		String fileSXTG = "scenario/阴阳寮/四星太鼓";
		String fileLXDY = "scenario/阴阳寮/六星斗鱼";
		String fileWXDY = "scenario/阴阳寮/五星斗鱼";
		String fileSXDY = "scenario/阴阳寮/四星斗鱼";
		String fileJJK = fileLXTG;
		String fileWFZ = "scenario/阴阳寮/未放置";
		String fileJRJJ = "scenario/阴阳寮/进入结界";
		String fileDJDM = "scenario/阴阳寮/大吉达摩";
		String fileQR = "scenario/通用/确认";
		String fileFH = "scenario/返回";
		int num = 0;//退出后重新进入好友列表的次数
		PictureIdentifyWorkPO pictureIdentifyWorkPO1 = new PictureIdentifyWorkPO ();//记录好友标题位置
		PictureIdentifyWorkPO pictureIdentifyWorkPO2 = new PictureIdentifyWorkPO ();//记录滚动起始位置
		PictureIdentifyWorkPO pictureIdentifyWorkPO3 = new PictureIdentifyWorkPO ();//记录滚动终止位置
		PictureIdentifyWorkPO pictureIdentifyWorkPO4 = new PictureIdentifyWorkPO ();//记录退出好友列表位置
		boolean booleanKJY;//寄养位是否为空，是则进行寄养，否则退出到首页
		boolean booleanWFZ;//好友是否放置结界卡，是则判断是否满足要求，不是则退出更换条件重进
		boolean booleanJJK;//结界卡是否满足当前要求，不满足则滚动，满足则进入结界
		boolean booleanJSJY = false;//判断寄养是否结束
		Thread.sleep (2000);
		logger.info ("进入阴阳寮");
		ImageService.imagesClickBack (fileYYLTB);
		Thread.sleep (2000);
		logger.info ("进入结界");
		ImageOpenCVService.imagesOpenCV (fileJJ);
		Thread.sleep (2000);
		logger.info ("进入式神育成");
		ImageService.imagesClickBack (fileSSYC);
		Thread.sleep (2000);
		logger.info ("判断是否可寄养");
		booleanKJY = ImageService.imagesClickBack (fileKJY, 5);
		if (booleanKJY) {
			logger.info ("进入可寄养");
			ImageService.imagesClickBackIsEmpty (fileHYBT);
			Thread.sleep (3000);
			logger.info ("已进入好友列表");
			pictureIdentifyWorkPO1 = ImagesBackRec.imagesRecognitionMouse (fileHYBT, "夜神模拟器");
			pictureIdentifyWorkPO2.setX ((int) (pictureIdentifyWorkPO1.getX () * 1.3));
			pictureIdentifyWorkPO2.setY ((int) (pictureIdentifyWorkPO1.getY () * 2.3));
			pictureIdentifyWorkPO3.setX ((int) (pictureIdentifyWorkPO1.getX () * 1.3));
			pictureIdentifyWorkPO3.setY ((int) (pictureIdentifyWorkPO1.getY () * 1.6));
			pictureIdentifyWorkPO4.setX (pictureIdentifyWorkPO1.getX ());
			pictureIdentifyWorkPO4.setY ((int) (pictureIdentifyWorkPO1.getY () * 0.5));
			//开循环，0 六星太鼓 1 五星太鼓 2 四星太鼓 3 六星斗鱼 4 五星斗鱼 5 四星斗鱼
			while (!booleanJSJY) {
				logger.info ("当前好友结界卡是否未放置");
				booleanWFZ = ImageService.imagesClickBack (fileWFZ, 2);
				if (booleanWFZ) {
					logger.info ("好友未放置结界卡，退出后重新进入");
					if (num == 0) {
						fileJJK = fileWXTG;
					}
					if (num == 1) {
						fileJJK = fileSXTG;
					}
					if (num == 2) {
						fileJJK = fileLXDY;
					}
					if (num == 3) {
						fileJJK = fileWXDY;
					}
					if (num == 4) {
						fileJJK = fileSXDY;
					}
					if (num > 4) {
						logger.info ("无高星结界卡，开始退出");
						break;
					}
					num++;
					logger.info ("退出到可寄养界面");
					MouseClick.mouseClickBack (pictureIdentifyWorkPO4, "夜神模拟器");
					logger.info ("重新进入好友列表");
					ImageService.imagesClickBack (fileKJY);
				}
				else {
					logger.info ("存在结界卡，判断是否是高星结界卡");
					booleanJJK = ImageService.imagesClickBack (fileJJK, 2);
					if (!booleanJJK) {
						logger.info ("当前结界不是" + fileJJK + ",不可放置,滚动到下一个");
						ImageService.imagesClickBackDrag (pictureIdentifyWorkPO2, pictureIdentifyWorkPO3, "夜神模拟器");
						logger.info ("滚动完成,点击终止位置坐标");
						MouseClick.mouseClickBack (pictureIdentifyWorkPO3, "夜神模拟器");
					}
					else {
						logger.info ("存在高星结界卡" + fileJJK);
						ImageService.imagesClickBack (fileJRJJ);
						logger.info ("进入结界");
						ImageService.imagesClickBack (fileDJDM);
						logger.info ("大吉达摩寄养");
						ImageService.imagesClickBack (fileQR);
						logger.info ("确认");
						logger.info ("检查是否寄养成功");
						ImageService.imagesClickBack (fileFH);
						ImageService.imagesClickBack (fileFH);
						logger.info ("返回到式神育成");
						ImageService.imagesClickBack (fileSSYC);
						logger.info ("进入育成界面");
						booleanJSJY = ImageService.imagesClickBack (fileKJY, 3);
						if (booleanJSJY) {
							logger.info ("寄养失败，重新进入好友列表");
							ImageService.imagesClickBack (fileKJY);
						}
						else {
							logger.info ("寄养成功");
							booleanJSJY = true;
						}
					}
				}
				
			}
		}
		//退出
		FightService.returnHome ();
		
	}
	
	public static void SoulEnhancements (Integer num) throws InterruptedException, AWTException, IOException {
		logger.info ("进入式神录");
		ImageService.imagesClickBack (ArrangeEnums.arrange_SSL.getValue ());
		Thread.sleep (2000);
		logger.info ("进入式神录详细");
		ImageOpenCVService.imagesOpenCV (ArrangeEnums.arrange_XX.getValue ());
		Thread.sleep (2000);
		logger.info ("进入式神录右侧御魂");
		ImageService.imagesClickBack (ArrangeEnums.arrange_YCYH_WDJ.getValue ());
		Thread.sleep (1000);
		logger.info ("御魂整理开始");
		//御魂强化属性
		String soulSubduingEnhancementAttribute;
		//御魂强化属性
		String soulSubduingEnhancementAttributeLast = null;
		//强化状态
		boolean reinforcementState;
		//等级提升状态
		boolean levelPromotion;
		//更换御魂状态
		//强化次数
		int strengtheningTimes = 1;
		//消耗材料数量
		int quantityOfConsumableMaterials = 0;
		//御魂强化属性结果
		//List<StrengthenResultPO> strengthenResultSet = new ArrayList<> ();
		////初始化御魂强化结果
		//strengthenResultSet.add (new StrengthenResultPO ("速度", ArrangeEnums.arrange_YHQHSX_SD.getValue (), 100));
		//strengthenResultSet.add (new StrengthenResultPO ("暴击", ArrangeEnums.arrange_YHQHSX_BJ.getValue (), 100));
		//strengthenResultSet.add (new StrengthenResultPO ("攻击", ArrangeEnums.arrange_YHQHSX_GJ.getValue (), 100));
		//strengthenResultSet.add (new StrengthenResultPO ("生命", ArrangeEnums.arrange_YHQHSX_SM.getValue (), 100));
		//strengthenResultSet.add (new StrengthenResultPO ("防御", ArrangeEnums.arrange_YHQHSX_FY.getValue (), 100));
		//strengthenResultSet.add (new StrengthenResultPO ("生命加成", ArrangeEnums.arrange_YHQHSX_SMJC.getValue (), 100));
		//strengthenResultSet.add (new StrengthenResultPO ("攻击加成", ArrangeEnums.arrange_YHQHSX_GJJC.getValue (), 100));
		//strengthenResultSet.add (new StrengthenResultPO ("暴击伤害", ArrangeEnums.arrange_YHQHSX_BJSH.getValue (), 100));
		//strengthenResultSet.add (new StrengthenResultPO ("效果命中", ArrangeEnums.arrange_YHQHSX_XGMZ.getValue (), 100));
		//strengthenResultSet.add (new StrengthenResultPO ("效果抵抗", ArrangeEnums.arrange_YHQHSX_XGDK.getValue (), 100));
		//strengthenResultSet.add (new StrengthenResultPO ("防御加成", ArrangeEnums.arrange_YHQHSX_FYJC.getValue (), 100));
		//
		//循环强化速度御魂
		for (int i = 1; i <= num; i++) {
			logger.info ("第{}个御魂", i);
			//设置强化状态
			reinforcementState = true;
			logger.info ("进入更换御魂");
			ImageService.imagesClickBack (ArrangeEnums.arrange_GHYH.getValue ());
			Thread.sleep (1000);
			logger.info ("进入整理");
			ImageService.imagesClickBack (ArrangeEnums.arrange_ZL_WDJ.getValue ());
			Thread.sleep (1000);
			logger.info ("进入方案");
			ImageService.imagesClickBack (ArrangeEnums.arrange_FA_WDJ.getValue ());
			Thread.sleep (1000);
			logger.info ("进入方案下强化");
			ImageService.imagesClickBack (ArrangeEnums.arrange_FAXQH_WDJ.getValue ());
			Thread.sleep (1000);
			logger.info ("进入方案速度提升");
			ImageService.imagesClickBack (ArrangeEnums.arrange_FASDTS_WDJ.getValue ());
			Thread.sleep (1000);
			logger.info ("排序");
			ImageService.imagesClickBack (ArrangeEnums.arrange_PX.getValue ());
			Thread.sleep (1000);
			logger.info ("点击左上角御魂");
			ImageService.imagesClickBack (ArrangeEnums.arrange_ZSJYH.getValue ());
			Thread.sleep (1000);
			logger.info ("进入强化");
			ImageService.imagesClickBack (ArrangeEnums.arrange_JRQH.getValue ());
			Thread.sleep (1000);
			logger.info ("*****当前强化状态为true,开始强化");
			while (reinforcementState) {
				if (strengtheningTimes > 1) {
					logger.info ("当前强化状态为true,继续强化");
				}
				if (strengtheningTimes == 1) {
					logger.info ("第一次强化，强化+3,2个四星青吉鬼");
					quantityOfConsumableMaterials = 2;
				}
				if (strengtheningTimes == 2) {
					logger.info ("第二次强化，强化+6,4个四星青吉鬼");
					quantityOfConsumableMaterials = 4;
				}
				if (strengtheningTimes == 3) {
					logger.info ("第三次强化，强化+9,5个四星青吉鬼");
					quantityOfConsumableMaterials = 5;
				}
				if (strengtheningTimes == 4) {
					logger.info ("第四次强化，强化+12,7个四星青吉鬼");
					quantityOfConsumableMaterials = 8;
				}
				if (strengtheningTimes == 5) {
					logger.info ("第五次强化，强化+15,9个四星青吉鬼");
					quantityOfConsumableMaterials = 9;
				}
				logger.info ("第{}次,点击{}个四星青吉鬼", strengtheningTimes, quantityOfConsumableMaterials);
				for (int j = 1; j <= quantityOfConsumableMaterials; j++) {
					ImageOpenCVService.imagesOpenCV (ArrangeEnums.arrange_SXQJG.getValue ());
					Thread.sleep (1000);
				}
				logger.info ("御魂强化");
				ImageService.imagesClickBack (ArrangeEnums.arrange_QH.getValue ());
				logger.info ("等级提升");
				levelPromotion = ImageService.imagesClickBackIsEmpty (ArrangeEnums.arrange_DJTS.getValue (), 5);
				while (!levelPromotion) {
					ImageService.imagesClickBack (ArrangeEnums.arrange_QD.getValue ());
					Thread.sleep (1000);
					levelPromotion = ImageService.imagesClickBackIsEmpty (ArrangeEnums.arrange_DJTS.getValue (), 5);
				}
				Thread.sleep (1000);
				logger.info ("****开始判断御魂强化结果");
				soulSubduingEnhancementAttribute = FightService.soulLevelEnhancementRecognition ();
				if (soulSubduingEnhancementAttribute == null) {
					logger.info ("未找到御魂强化属性，程序退出");
					System.exit (0);
				}
				logger.info ("御魂强化属性为{}", soulSubduingEnhancementAttribute);
				//如果强化属性为攻击、生命和防御，则直接弃置
				if ((
						    soulSubduingEnhancementAttribute.equals ("攻击") || soulSubduingEnhancementAttribute.equals ("生命") ||
						    soulSubduingEnhancementAttribute.equals ("防御")) && strengtheningTimes < 4) {
					logger.info ("御魂强化属性为{},直接弃置", soulSubduingEnhancementAttribute);
					logger.info ("直接弃置");
					ImageService.imagesClickBack (ArrangeEnums.arrange_QZ.getValue ());
					Thread.sleep (1000);
					logger.info ("确定强化结果，完全垃圾御魂，本轮强化结束");
					ImageService.imagesClickBack (ArrangeEnums.arrange_QD.getValue ());
					Thread.sleep (1000);
					logger.info ("结束强化，强化状态置为false,强化次数置为1");
					reinforcementState = false;
					strengtheningTimes = 1;
				}
				else {
					if (strengtheningTimes == 1) {
						logger.info ("第一次强化，记录强化属性，继续强化");
						soulSubduingEnhancementAttributeLast = soulSubduingEnhancementAttribute;
						logger.info ("确定");
						ImageService.imagesClickBack (ArrangeEnums.arrange_QD.getValue ());
						logger.info ("御魂强化次数+1");
						strengtheningTimes++;
						Thread.sleep (1000);
					}
					else {
						if (soulSubduingEnhancementAttribute.equals (soulSubduingEnhancementAttributeLast) || strengtheningTimes >= 4) {
							logger.info ("御魂强化属性{}，御魂上次强化属性{}", soulSubduingEnhancementAttribute,
							             soulSubduingEnhancementAttributeLast);
							logger.info ("御魂强化次数{}", strengtheningTimes);
							logger.info ("御魂强化属性和上一次相同，或者御魂强化次数大于等4次");
							logger.info ("确定强化结果，本轮强化结束,继续强化");
							ImageService.imagesClickBack (ArrangeEnums.arrange_QD.getValue ());
							Thread.sleep (1000);
							if (strengtheningTimes == 5) {
								logger.info ("强化满级,本次强化结束");
								logger.info ("结束强化，强化状态置为false,强化次数置为1");
								reinforcementState = false;
								strengtheningTimes = 1;
							}
							else {
								logger.info ("强化未满级，进入下一轮");
							}
							logger.info ("御魂强化次数+1");
							strengtheningTimes++;
							
						}
						else {
							logger.info ("御魂强化属性{}，御魂上次强化属性{}", soulSubduingEnhancementAttribute,
							             soulSubduingEnhancementAttributeLast);
							logger.info ("御魂强化次数{}", strengtheningTimes);
							logger.info ("御魂强化属性和上一次不同,且强化次数小于4次");
							logger.info ("弃置");
							ImageService.imagesClickBack (ArrangeEnums.arrange_QZ.getValue ());
							Thread.sleep (1000);
							logger.info ("确定强化结果，本轮强化结束");
							ImageService.imagesClickBack (ArrangeEnums.arrange_QD.getValue ());
							Thread.sleep (1000);
							logger.info ("结束强化，强化状态置为false,强化次数置为1");
							reinforcementState = false;
							strengtheningTimes = 1;
						}
					}
				}
			}
			logger.info ("返回更换御魂");
			ImageService.imagesClickBack (BackEnums.back.getValue ());
			Thread.sleep (1000);
			logger.info ("返回更换御魂");
			ImageService.imagesClickBack (BackEnums.back.getValue ());
			Thread.sleep (1000);
			logger.info ("结束本次御魂强化");
		}
		FightService.returnHome ();
		
	}
	
	/***
	 * @description: 探索
	 * @return: void
	 * @author: orcakill
	 * @date: 2022/11/11 8:29
	 */
	public static void explore (Integer num) throws InterruptedException, AWTException, IOException {
		//右移界面数据准备
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment ().getDefaultScreenDevice ();
		int windows_width = gd.getDisplayMode ().getWidth ();
		int windows_height = gd.getDisplayMode ().getHeight ();
		PictureIdentifyWorkPO pictureIdentifyWorkPO = new PictureIdentifyWorkPO ();
		//boss状态
		boolean bossState;
		//小怪状态
		boolean littleMonsterState;
		//自动轮换
		boolean rotationState;
		//宝箱
		boolean treasureChestState;
		//额外奖励
		boolean rewardState;
		//探索界面判断
		boolean exploreState;
		//移动次数
		int numberOfMoves;
		long a;
		List<Double> b = new ArrayList<> ();
		logger.info ("进入探索");
		loginExplore ();
		logger.info ("开始探索战斗");
		for (int i = 1; i <= num; i++) {
			logger.info ("*************第{}轮挑战开始", i);
			a = System.currentTimeMillis ();//获取当前系统时间(毫秒)
			//初始化移动次数
			numberOfMoves = 0;
			logger.info ("当前探索界面");
			logger.info ("检查左侧是否有宝箱");
			treasureChestState = ImageService.imagesClickBackIsEmpty (ExploreEnums.explore_ZCBX.getValue (), 2);
			while (treasureChestState) {
				logger.info ("有宝箱");
				ImageService.imagesClickBack (ExploreEnums.explore_ZCBX.getValue (), 1);
				Thread.sleep (1000);
				logger.info ("点击退出挑战");
				ImageService.imagesClickBack (ExploreEnums.explore_TCTZ.getValue (), 1);
				Thread.sleep (1000);
				treasureChestState = ImageService.imagesClickBackIsEmpty (ExploreEnums.explore_ZCBX.getValue (), 2);
			}
			Thread.sleep (1000);
			logger.info ("探索");
			ImageService.imagesClickBack (ExploreEnums.explore_ZHYZ.getValue ());
			logger.info ("最后一章");
			ImageService.imagesClickBack (ExploreEnums.explore_TS.getValue ());
			Thread.sleep (1000);
			logger.info ("当前探索中,检查是否未点击自动轮换");
			rotationState = ImageService.imagesClickBackIsEmpty (ExploreEnums.explore_ZDLH.getValue (), 3);
			if (rotationState) {
				logger.info ("自动轮换未点击");
				ImageService.imagesClickBack (ExploreEnums.explore_ZDLH.getValue (), 3);
				Thread.sleep (1000);
			}
			logger.info ("当前探索中,检查是否有BOSS");
			bossState = ImageService.imagesClickBackIsEmpty (ExploreEnums.explore_BOSSZD.getValue (), 3);
			while (!bossState) {
				logger.info ("寻找小怪");
				littleMonsterState = ImageOpenCVService.imagesOpenCVIsEmpty (ExploreEnums.explore_XGZD.getValue (), 3);
				if (littleMonsterState) {
					logger.info ("找到小怪，点击战斗");
					ImageOpenCVService.imagesOpenCV (ExploreEnums.explore_XGZD.getValue (), 3);
					Thread.sleep (3000);
					logger.info ("检查是否未点击成功");
					littleMonsterState = ImageOpenCVService.imagesOpenCVIsEmpty (ExploreEnums.explore_XGZD.getValue (), 1);
					while (littleMonsterState) {
						logger.info ("小怪点击不成功，重新点击");
						ImageOpenCVService.imagesOpenCV (ExploreEnums.explore_XGZD.getValue (), 10);
						Thread.sleep (1000);
						littleMonsterState = ImageOpenCVService.imagesOpenCVIsEmpty (ExploreEnums.explore_XGZD.getValue (), 1);
					}
					Thread.sleep (4 * 1000);
					logger.info ("退出挑战");
					ImageService.imagesClickBack (ExploreEnums.explore_TCTZ.getValue (), 5);
					Thread.sleep (1000);
				}
				else {
					logger.info ("没找到小怪，每10次改变移动方向，当前移动次数{},余数{}", numberOfMoves, numberOfMoves % 10 % 2);
					if (Math.ceil (numberOfMoves / 10) % 2 == 0) {
						pictureIdentifyWorkPO.setX (windows_width / 8 * 7);
						pictureIdentifyWorkPO.setY (windows_height / 8 * 6);
					}
					else {
						pictureIdentifyWorkPO.setX (windows_width / 8);
						pictureIdentifyWorkPO.setY (windows_height / 8 * 6);
					}
					logger.info ("坐标({},{})", pictureIdentifyWorkPO.getX (), pictureIdentifyWorkPO.getY ());
					MouseClick.mouseClickBack (pictureIdentifyWorkPO.getX (), pictureIdentifyWorkPO.getY (), "夜神模拟器");
					numberOfMoves++;
				}
				Thread.sleep (1000);
				logger.info ("小怪战斗结束，检查是否出现BOSS");
				bossState = ImageService.imagesClickBackIsEmpty (ExploreEnums.explore_BOSSZD.getValue (), 2);
			}
			logger.info ("小怪战斗结束，boss战");
			ImageService.imagesClickBack (ExploreEnums.explore_BOSSZD.getValue ());
			Thread.sleep (5000);
			logger.info ("退出挑战");
			ImageService.imagesClickBack (ExploreEnums.explore_TCTZ.getValue ());
			Thread.sleep (3000);
			logger.info ("判断是否已在探索界面");
			exploreState = ImageService.imagesClickBackIsEmpty (ExploreEnums.explore_ZHYZ.getValue (), 3);
			while (!exploreState) {
				logger.info ("再退出挑战一次");
				ImageService.imagesClickBack (ExploreEnums.explore_TCTZ.getValue (), 1);
				logger.info ("判断是否有额外奖励");
				rewardState = ImageService.imagesClickBackIsEmpty (ExploreEnums.explore_TSSSL.getValue (), 2);
				if (rewardState) {
					logger.info ("未退出探索，点击返回");
					ImageService.imagesClickBack (BackEnums.back.getValue (), 1);
					Thread.sleep (1000);
					logger.info ("确认");
					ImageService.imagesClickBack (ExploreEnums.explore_QR.getValue (), 1);
					Thread.sleep (1000);
				}
				logger.info ("再次判断是否回探索，因最后一章导致，返回一次");
				rewardState = ImageService.imagesClickBackIsEmpty (ExploreEnums.explore_TS.getValue (), 2);
				if (rewardState) {
					logger.info ("在最后一章探索界面，返回探索界面");
					ImageService.imagesClickBack (BackEnums.back.getValue (), 1);
					Thread.sleep (1000);
				}
				logger.info ("判断是否回到探索界面");
				exploreState = ImageService.imagesClickBackIsEmpty (ExploreEnums.explore_ZHYZ.getValue (), 3);
			}
			logger.info ("当前已是探索界面");
			logger.info ("****************************************************第{}轮挑战完成***************************************", i);
			logger.info ("该次挑战使用时间为{}秒,{}分", (System.currentTimeMillis () - a) / 1000,
			             (System.currentTimeMillis () - a) / 1000 / 60);
			b.add ((double) (System.currentTimeMillis () - a));
		}
		
		double average = b.stream ().mapToDouble (Double::valueOf).average ().getAsDouble ();
		logger.info ("该次挑战使用时间为{}秒,{}分", average / 1000, average / 1000 / 60);
		FightService.returnHome ();
	}
	
	/***
	 * @description: 探索  快速战斗 只打 2个
	 * @param fightResultPOS  战斗结果
	 * @param num   本轮战斗次数
	 * @return: java.util.List<com.example.model.entity.FightResultPO>
	 * @author: orcakill
	 * @date: 2023/1/8 22:50
	 */
	public static List<FightResultPO> exploreFast (List<FightResultPO> fightResultPOS, int num)
			throws InterruptedException, AWTException, IOException {
		//小怪状态
		boolean littleMonsterState;
		//退出挑战
		boolean exitTheChallenge;
		//探索界面
		boolean whetherToExplore;
		//当前轮次 默认为 1
		int currentRound = 1;
		// 本次战斗开始时间
		long a;
		// 本次战斗结束时间
		long b;
		// 平均战斗时间
		double avgTime = 0;
		//  探索的历史数据
		List<FightResultPO> fightResultPOList =
				fightResultPOS.stream ().filter (fightResultPO -> fightResultPO.getFightName ().equals (FightResultEnums.result_TS))
				              .collect (Collectors.toList ());
		//探索轮次加一
		if (fightResultPOList.size () > 0) {
			currentRound = fightResultPOList.get (fightResultPOList.size () - 1).getThisRound () + 1;
		}
		logger.info ("进入探索");
		loginExplore ();
		logger.info ("开始探索战斗");
		for (int i = 1; i <= num; i++) {
			//战斗结果
			FightResultPO fightResultPO = new FightResultPO ();
			logger.info ("*************第{}轮挑战开始", i);
			a = System.currentTimeMillis ();//获取当前系统时间(毫秒)
			logger.info ("当前探索界面");
			logger.info ("探索");
			ImageService.imagesClickBack (ExploreEnums.explore_ZHYZ.getValue ());
			logger.info ("最后一章");
			ImageService.imagesClickBack (ExploreEnums.explore_TS.getValue ());
			Thread.sleep (1000);
			logger.info ("当前探索中,检查是否有小怪，打2个静止不动的即可");
			logger.info ("寻找小怪");
			littleMonsterState = ImageService.imagesClickBackIsEmpty (ExploreEnums.explore_JTXGZD.getValue (), 3);
			while (littleMonsterState) {
				logger.info ("找到小怪，点击战斗");
				littleMonsterState = ImageService.imagesClickBack (ExploreEnums.explore_JTXGZD.getValue (), 3);
				if (littleMonsterState) {
					logger.info ("点击成功，等待结束");
					Thread.sleep (12 * 1000);
					logger.info ("退出挑战");
					exitTheChallenge = ImageService.imagesClickBack (ExploreEnums.explore_TCTZ.getValue (), 10);
					Thread.sleep (1000);
					while (!exitTheChallenge) {
						logger.info ("未成功退出挑战，重新退出");
						exitTheChallenge = ImageService.imagesClickBack (ExploreEnums.explore_TCTZ.getValue (), 10);
						Thread.sleep (1000);
					}
				}
				logger.info ("小怪战斗结束，检查是否还有小怪");
				littleMonsterState = ImageService.imagesClickBackIsEmpty (ExploreEnums.explore_JTXGZD.getValue (), 3);
			}
			logger.info ("再退出一次挑战，防止退出不成功");
			ImageService.imagesClickBack (ExploreEnums.explore_TCTZ.getValue (), 3);
			logger.info ("小怪战斗结束，退出");
			logger.info ("未退出探索，点击返回");
			ImageService.imagesClickBack (BackEnums.back.getValue (), 3);
			logger.info ("确认");
			ImageService.imagesClickBack (ExploreEnums.explore_QR.getValue (), 3);
			Thread.sleep (1000);
			logger.info ("在最后一章探索界面，返回探索界面");
			ImageService.imagesClickBack (BackEnums.back.getValue (), 3);
			whetherToExplore = ImageService.imagesClickBackIsEmpty (ExploreEnums.explore_ZHYZ.getValue (),2);
			if (!whetherToExplore) {
				logger.info ("返回不成功，重新返回");
				ImageService.imagesClickBack (BackEnums.back.getValue (), 3);
			}
			logger.info ("当前已是探索界面");
			b = System.currentTimeMillis ();//获取当前系统时间(毫秒)
			//战斗类型
			fightResultPO.setFightName (FightResultEnums.result_TS.getValue ());
			//战斗胜利
			fightResultPO.setFightWin (true);
			//本次战斗用时 秒
			fightResultPO.setFightTime (b - a);
			//战斗轮次
			fightResultPO.setThisRound (currentRound);
			//本轮战斗次数
			fightResultPO.setThisRoundNumber (i * 2);
			//本次战斗信息入库
			fightResultPOList.add (fightResultPO);
			fightResultPOS.add (fightResultPO);
			//计算平均战斗时间
			logger.info ("探索的平均战斗时间{}秒", fightResultPO.getFightTime ()/fightResultPO.getThisRoundNumber ()/1000);
		}
		FightService.returnHome ();
		return fightResultPOS;
	}
}
