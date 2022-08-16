package com.example.controller;

import com.example.model.entity.PictureIdentifyWorkPO;
import com.example.model.map.CoordinateAddress;
import com.example.service.FightService;
import com.example.service.ImageService;
import com.example.util.ImagesBackRec;
import com.example.util.MouseClick;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.util.*;
import java.util.List;

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
	public static List<Integer> fightHome () throws InterruptedException, AWTException {
		//变量赋值
		String file = "scenario/结界突破/结界突破";
		String file1 = "scenario/结界突破/阴阳寮";
		String file2 = "scenario/结界突破/挑战次数";
		String file3 = "scenario/结界突破/寮结界";
		String file4 = "scenario/结界突破/进攻";
		String file8 = "scenario/结界突破/退出进攻";
		String file41 = "scenario/返回";
		List<Integer> list =new ArrayList<> ();
		int num = 0;
		int num1 = 0;
		int num2 = 0;
		boolean b;
		boolean b1;
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
			b1 = FightService.fightEnd (30, 30,40 );
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
		list.add (num);
		list.add (num1);
		list.add(num2);
		return  list;
		
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
		logger.info ("准备进入探索");
		loginExplore ();
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
				FightService.fightEnd (30, 5, 10);
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
		logger.info ("准备进入探索");
		loginExplore ();
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
				MouseClick.mouseClickBack (pictureIdentifyWorkPOList, "夜神模拟器");
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
			soulBack (15, j);
			//挑战结束
			if (b) {
				//关闭加成
				ImageService.imagesClickBack (file2);
				logger.info ("点击加成成功，准备关闭御魂加成");
				MouseClick.mouseClickBack (pictureIdentifyWorkPOList, "夜神模拟器");
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
			soulBack (60, j);
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
			soulBack (15, j);
		}
		logger.info ("退出到探索");
		//退出到探索
		ImageService.imagesClickBack (file32);
		logger.info ("退出到首页");
		//退出到首页
		ImageService.imagesClickBack (file32);
	}
	
	public static void spirit (int num) throws InterruptedException, AWTException {
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
	
	public static void pvp (Integer num) throws InterruptedException, AWTException {
		String file = "scenario/斗技/开始挑战";
		String file1 = "scenario/斗技/自动选择";
		String file2 = "scenario/斗技/段位晋升";
		String file3 = "scenario/斗技/额外奖励";
		String file4 = "scenario/返回";
		String file5 = "scenario/斗技/斗技图标";
		String file6 = "scenario/斗技/自动战斗";
		String fileDZWG = "scenario/斗技/町中武馆";
		String fileDJFH = "scenario/斗技/斗技返回";
		boolean b;
		boolean b1;
		boolean b2;
		boolean booleanYCQK=false;
		int num1 = 0;
		int num2 = 0;
		logger.info ("开始斗技");
		//	进入町中
		LoginController.loginTown ();
		ImageService.imagesClickBack (fileDZWG);
		//  进入斗技
		ImageService.imagesClickBack (file5);
		for (int i = 1; i <= 10; i++) {
			logger.info ("准备挑战");
			booleanYCQK=ImageService.imagesClickBack (file,20);
			while (!booleanYCQK){
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
				booleanYCQK=ImageService.imagesClickBack (file,20);
			}
			logger.info ("进入挑战，准备自动选择");
			ImageService.imagesClickBack (file1,20);
			logger.info ("自动选择完成，准备自动战斗");
			Thread.sleep (2000);
			logger.info ("等待选将");
			Thread.sleep (30*1000);
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
		logger.info ("斗技结束");
		ImageService.imagesClickBack (file4);
		logger.info ("回町中武馆");
		ImageService.imagesClickBack (file4);
		logger.info ("回首页");
		ImageService.imagesClickBack (file4);
	}
	
	//寄养
	public static void foster () throws InterruptedException, AWTException {
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
		boolean booleanKJY = false;//寄养位是否为空，是则进行寄养，否则退出到首页
		boolean booleanWFZ = false;//好友是否放置结界卡，是则判断是否满足要求，不是则退出更换条件重进
		boolean booleanJJK = false;//结界卡是否满足当前要求，不满足则滚动，满足则进入结界
		boolean booleanJSJY = false;//判断寄养是否结束
		logger.info ("进入阴阳寮");
		ImageService.imagesClickBack (fileYYLTB);
		logger.info ("进入结界");
		ImageService.imagesClickBack (fileJJ);
		logger.info ("进入式神育成");
		ImageService.imagesClickBack (fileSSYC);
		logger.info ("判断是否可寄养");
		booleanKJY = ImageService.imagesClickBack (fileKJY, 5);
		if (booleanKJY) {
			logger.info ("进入可寄养");
			ImageService.imagesClickBackIsEmpty (fileHYBT);
			Thread.sleep (3000);
			logger.info ("已进入好友列表");
			pictureIdentifyWorkPO1 = ImagesBackRec.imagesRecognitionMouse (fileHYBT, "夜神模拟器");
			pictureIdentifyWorkPO2.setX (pictureIdentifyWorkPO1.getX ());
			pictureIdentifyWorkPO2.setY ((int) (pictureIdentifyWorkPO1.getY () * 2.3));
			pictureIdentifyWorkPO3.setX (pictureIdentifyWorkPO1.getX ());
			pictureIdentifyWorkPO3.setY ((int) (pictureIdentifyWorkPO1.getY () * 1.6));
			pictureIdentifyWorkPO4.setX (pictureIdentifyWorkPO1.getX ());
			pictureIdentifyWorkPO4.setY ((int) (pictureIdentifyWorkPO1.getY () * 0.5));
			//开循环，0 六星太鼓 1 五星太鼓 2 四星太鼓 3 六星斗鱼 4 五星斗鱼 5 四星斗鱼
			while (!booleanJSJY) {
				logger.info ("当前好友结界卡是否未放置");
				booleanWFZ = ImageService.imagesClickBack (fileWFZ,2);
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
							booleanJSJY=true;
						}
					}
				}
				
			}
		}
		//退出
		FightService.returnHome ();
		
	}
}
