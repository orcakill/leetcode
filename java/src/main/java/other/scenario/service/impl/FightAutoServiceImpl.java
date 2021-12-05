package other.scenario.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import other.scenario.entity.PictureIdentifyWorkPO;
import other.scenario.map.CoordinateAddress;
import other.scenario.service.FightAutoService;
import other.scenario.service.ImageService;
import other.scenario.service.IndexService;
import other.scenario.util.ImageRecognition;
import other.scenario.util.MouseClick;
import other.scenario.util.Screenshot;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static other.scenario.util.RandomUtil.getRandom;

/**
 * @Classname FightAutoServiceImpl
 * @Description TODO
 * @Date 2021/10/16 15:43
 * @Created by orcakill
 */
public class FightAutoServiceImpl {
	//	记录日志
	public static final Logger logger = LogManager.getLogger (FightAutoServiceImpl.class);
	
	//	樱饼刷御魂
	public static int cherrySoul (Integer num) throws InterruptedException, AWTException {
//		进入探索
		boolean b = FightAutoService.comeExplore ();
		if (b) {
//		        进入御魂
			File file3 = new File ("java/src/main/resources/image/scenario/御魂.png");
			logger.info ("准备进入御魂");
			ImageService.imageClick (file3);
			logger.info ("进入御魂");
//		        进入八岐大蛇
			File file4 = new File ("java/src/main/resources/image/scenario/八岐大蛇.png");
			logger.info ("准备进入八岐大蛇");
			ImageService.imageClick (file4);
			logger.info ("进入八岐大蛇");
//				已选择了第一层
			File file5 = new File ("java/src/main/resources/image/scenario/御魂第五层-选择中.png");
			boolean b1 = ImageService.imageClickIsEmpty (file5);
			if (b1) {
				logger.info ("已选择御魂第五层");
			}
			else {
				logger.info ("准备选择御魂第五层");
				File file51=new File ("java/src/main/resources/image/scenario/御魂第五层.png");
				ImageService.imageClick (file51);
				logger.info ("选择成功");
			}
//			开始挑战,处理剩余次数的御魂
			for (int i = 0; num > 0; i++) {
				String file6 = "scenario/挑战";
				logger.info ("准备挑战");
				ImageService.imagesClick (file6);
				logger.info ("等待挑战完成");
				String file7 = "scenario/退出挑战";
				Thread.sleep (20 * 1000);
				logger.info ("准备退出挑战");
				ImageService.imagesClick (file7);
				logger.info ("退出挑战完成");
				num--;
				Thread.sleep (2 * 1000);
				logger.info ("第" + (i + 1) + "次挑战完成，剩余" + (num) + "次");
			}
//			开始退出御魂挑战
			IndexService.indexBack ();
			IndexService.indexBack ();
//			退出首页底部功能菜单
			File file8 = new File ("java/src/main/resources/image/scenario/退出底部菜单栏.png");
			logger.info ("准备关闭底部菜单栏");
			ImageService.imageClick (file8);
			logger.info ("关闭底部菜单栏成功");
			return num;
			
		}
		return num;
	}
	
	//	樱饼刷经验
	public static boolean cherryExperience () throws InterruptedException, AWTException {
//		进入探索
		boolean b = FightAutoService.comeExplore ();
		if (b) {
//				选择经验关卡第一张，通过妖字和御魂的位置判断
			File file3 = new File ("java/src/main/resources/image/scenario/探索关卡妖.png");
			PictureIdentifyWorkPO pictureIdentifyWorkPO4 = ImageRecognition.imageRecognitionMouse (file3);
			File file4 = new File ("java/src/main/resources/image/scenario/御魂.png");
			PictureIdentifyWorkPO pictureIdentifyWorkPO5 = ImageRecognition.imageRecognitionMouse (file4);
			if (pictureIdentifyWorkPO4.getX () != null && pictureIdentifyWorkPO5.getX () != null) {
//				  确定妖和御魂的位置
				int x1 = pictureIdentifyWorkPO4.getX ();
				int y1 =
						pictureIdentifyWorkPO4.getY () + (int) ((pictureIdentifyWorkPO5.getY () - pictureIdentifyWorkPO4.getY ()) * 0.2);
				List<PictureIdentifyWorkPO> pictureIdentifyWorkPOList1 = new ArrayList<> ();
				PictureIdentifyWorkPO pictureIdentifyWorkPO6 = new PictureIdentifyWorkPO ();
				pictureIdentifyWorkPO6.setX (x1);
				pictureIdentifyWorkPO6.setY (y1);
				pictureIdentifyWorkPOList1.add (pictureIdentifyWorkPO6);
//				    通过滚轮滑动
				logger.info ("移动探索位置，滑动到第一章");
				MouseClick.mouseRoll (pictureIdentifyWorkPOList1, 20, -5);
				logger.info ("进入第一章关卡选择");
//				    进入经验探索
				File file5 = new File ("java/src/main/resources/image/scenario/探索.png");
				logger.info ("准备进入经验探索");
				ImageService.imageClick (file5);
				logger.info ("进入经验探索");
//				    樱饼小纸人
				String file6 = "scenario/樱饼小纸人";
				logger.info ("准备进入樱饼小纸人");
				ImageService.imagesClick (file6);
				logger.info ("进入樱饼小纸人");
//				    喂食
				String file7 = "scenario/喂食";
				logger.info ("准备喂食");
				ImageService.imagesClick (file7);
				logger.info ("喂食完成");
				String file71 = "scenario/饱食度";
				logger.info ("准备退出喂食");
				ImageService.imagesClick (file71);
				logger.info ("退出喂食");
//				 自动战斗
				String file8 = "scenario/自动战斗";
				logger.info ("准备自动战斗");
				ImageService.imagesClick (file8);
				logger.info ("自动战斗开始");
//				等待自动战斗完成
				Thread.sleep (20 * 60 * 1000);
//				停止自动战斗
				logger.info ("自动战斗停止");
				Thread.sleep (5* 1000);
//				退出到首页
				IndexService.indexBack ();
				String file81 = "scenario/确认";
				logger.info ("确认退出关卡");
				ImageService.imagesClick (file81);
				logger.info ("退出关卡");
				IndexService.indexBack ();
				IndexService.indexBack ();
//				退出底部菜单栏
				File file9 = new File ("java/src/main/resources/image/scenario/退出底部菜单栏.png");
				logger.info ("准备关闭底部菜单栏");
				ImageService.imageClick (file9);
				logger.info ("关闭底部菜单栏成功");
				return true;
			}
			else {
				logger.info ("无法选择出探索关卡位置");
			}
		}
		return false;
	}
	
	public static int friendBorder (Integer num) throws AWTException, InterruptedException {
//		进入探索
		boolean b = FightAutoService.comeExplore ();
		if (b) {
//			进入结界突破
			logger.info ("准备进入结界突破");
			File file = new File ("java/src/main/resources/image/scenario/结界突破.png");
			ImageService.imageClick (file);
			logger.info ("进入结界突破");
//			开始循环开始准备选择
			for (int i = 0; num > 0; i++) {
				logger.info ("开始选择第" + (i + 1) + "个结界");
				String folder = "scenario/个人结界";
//				判断是否存在可攻打结界
				boolean b1 = ImageService.imagesClickIsEmpty (folder);
				if (!b1) {
					logger.info ("不存在可攻打结界");
					File file1 = new File ("java/src/main/resources/image/scenario/刷新.png");
					ImageService.imageClick (file1);
				}
				ImageService.imagesClick (folder);
				logger.info ("准备选择进攻");
				String folder1 = "scenario/进攻";
				ImageService.imagesClick (folder1);
				logger.info ("等待进攻完成");
				Thread.sleep (60 * 100);
				String folder2 = "scenario/退出挑战";
				boolean b2 = ImageService.imagesClickIsEmpty (folder2);
				if (b2) {
					logger.info ("挑战成功，退出挑战");
					ImageService.imagesClick (folder2);
					num--;
					logger.info ("第" + (i + 1) + "结界挑战完成");
					Thread.sleep (3 * 1000);
					boolean b3 = ImageService.imagesClickIsEmpty (folder2);
					if (b3) {
						ImageService.imagesClick (folder2);
					}
				}
				else {
					FightAutoService.fightFalse ();
				}
			}
//			返回首页
			IndexService.indexBack ();
			IndexService.indexBack ();
//			退出底部菜单栏
			File file2 = new File ("java/src/main/resources/image/scenario/退出底部菜单栏.png");
			logger.info ("准备关闭底部菜单栏");
			ImageService.imageClick (file2);
			logger.info ("关闭底部菜单栏成功");
		}
		return num;
	}
	
	//	进入探索
	public static boolean comeExplore () throws InterruptedException, AWTException {
//		判断是否为首页
		if (IndexService.indexEmpty ()) {
			//进入首页底部功能菜单
			File file = new File ("java/src/main/resources/image/scenario/底部菜单.png");
			logger.info ("准备点击底部菜单栏");
			ImageService.imageClick (file);
			logger.info ("打开底部菜单栏成功");
			//进入探索
			File file1 = new File ("java/src/main/resources/image/scenario/首页勾玉.png");
			PictureIdentifyWorkPO pictureIdentifyWorkPO1 = ImageRecognition.imageRecognitionMouse (file1);
			File file2 = new File ("java/src/main/resources/image/scenario/首页体力.png");
			PictureIdentifyWorkPO pictureIdentifyWorkPO2 = ImageRecognition.imageRecognitionMouse (file2);
			if (pictureIdentifyWorkPO1 != null && pictureIdentifyWorkPO2 != null) {
//		        进入探索，通过首页勾玉和首页体力图标判断探索的位置点击按比例计算的探索
				int x = pictureIdentifyWorkPO1.getX ();
				int y =
						pictureIdentifyWorkPO1.getY () + (pictureIdentifyWorkPO2.getX () - pictureIdentifyWorkPO1.getX ());
				List<PictureIdentifyWorkPO> pictureIdentifyWorkPOList = new ArrayList<> ();
				PictureIdentifyWorkPO pictureIdentifyWorkPO3 = new PictureIdentifyWorkPO ();
				pictureIdentifyWorkPO3.setX (x);
				pictureIdentifyWorkPO3.setY (y);
				pictureIdentifyWorkPOList.add (pictureIdentifyWorkPO3);
				logger.info ("准备点击探索");
				MouseClick.mouseClicks (pictureIdentifyWorkPOList);
				logger.info ("进入探索");
				Thread.sleep (3 * 1000);
				return true;
			}
			
		}
		return false;
	}
	
	public static void fightFalse () throws InterruptedException, AWTException {
		String folder = "scenario/挑战失败";
		boolean b = ImageService.imagesClickIsEmpty (folder);
		if (b) {
			logger.info ("挑战已失败，退出挑战");
			ImageService.imagesClick (folder);
			logger.info ("退出挑战成功");
		}
		else {
			logger.info ("无法判断当前是否挑战失败");
		}
	}
	
	public static boolean receiveReward () throws InterruptedException, AWTException {
//		判断是否为首页
		if (IndexService.indexEmpty ()) {
			//进入首页底部功能菜单
			File file = new File ("java/src/main/resources/image/scenario/底部菜单.png");
			logger.info ("准备点击底部菜单栏");
			ImageService.imageClick (file);
			logger.info ("打开底部菜单栏成功");
//			进入花合战
			File file1 = new File ("java/src/main/resources/image/scenario/花合战.png");
			logger.info ("准备进入花合战");
			ImageService.imageClick (file1);
			logger.info ("进入花合战");
			Thread.sleep (2000);
//			是否有全部领取
			MouseClick.mouseClickNow (0,0);
			File file11=new File ("java/src/main/resources/image/scenario/任务.png");
			logger.info ("点击任务");
			ImageService.imageClick (file11);
			logger.info ("进入任务");
			File file2 = new File ("java/src/main/resources/image/scenario/花合战全部领取.png");
			boolean b = ImageService.imageClickIsEmpty (file2);
			if (b) {
				ImageService.imageClick (file2);
			}
			MouseClick.mouseClickNow (0,0);
//			返回首页
			IndexService.indexBack ();
//			退出底部菜单栏
			File file3 = new File ("java/src/main/resources/image/scenario/退出底部菜单栏.png");
			logger.info ("准备关闭底部菜单栏");
			ImageService.imageClick (file3);
			logger.info ("关闭底部菜单栏成功");
			return true;
		}
		return false;
	}
	
	public static boolean friendScreen (String userName) throws InterruptedException, AWTException, IOException {
//		判断是否为首页
		if (IndexService.indexEmpty ()) {
			//进入首页底部功能菜单
			File file = new File ("java/src/main/resources/image/scenario/底部菜单.png");
			logger.info ("准备点击底部菜单栏");
			ImageService.imageClick (file);
			logger.info ("打开底部菜单栏成功");
//			进入好友
			File file1 = new File ("java/src/main/resources/image/scenario/好友.png");
			logger.info ("准备点击好友");
			ImageService.imageClick (file1);
			logger.info ("打开好友成功");
//			点击添加
			File file2 = new File ("java/src/main/resources/image/scenario/添加.png");
			logger.info ("准备点击添加");
			ImageService.imageClick (file2);
			logger.info ("打开添加");
//			进入协战
			File file3 = new File ("java/src/main/resources/image/scenario/协战.png");
			logger.info ("准备点击协战");
			ImageService.imageClick (file3);
			logger.info ("打开协战");
//			开始截图
//		    屏幕截图
			BufferedImage window = Screenshot.screenshot ();
			Date date = new Date ();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat ("yyyy-MM-dd");
			String str = simpleDateFormat.format (date);
			ImageIO.write (window, "jpg",
					new File ("java/src/main/resources/image/scenario/每日截图/" + str + "_" + userName + ".jpg"));
//			返回首页
			IndexService.indexBack ();
//			退出底部菜单栏
			File file4 = new File ("java/src/main/resources/image/scenario/退出底部菜单栏.png");
			logger.info ("准备关闭底部菜单栏");
			ImageService.imageClick (file4);
			logger.info ("关闭底部菜单栏成功");
			return true;
		}
		return false;
	}
	
	public static void soulBack (Integer num) throws InterruptedException, AWTException {
		Thread.sleep (3000);
		logger.info ("开始");
//		开始挑战,处理剩余次数的御魂
		for (int i = 0; num > 0; i++) {
			String file1 = "scenario/御魂/挑战";
			logger.info ("准备开始挑战");
			ImageService.imagesClickBack (file1);
			logger.info ("第" + (i + 1) +"次挑战中，等待挑战完成");
			Thread.sleep (getRandom(5, 6) * 1000L);
			
			String file2 = "scenario/御魂/角色头像";
			String file3 = "scenario/御魂/退出挑战";
			logger.info ("准备点击角色头像、退出挑战或直接点击退出挑战");
			ImageService.imagesClickBackNumberOrder (file2,file3,30);
			
			logger.info ("退出挑战完成");
			num--;
			Thread.sleep ((long) getRandom (1, 2) * getRandom (500,1000));
			logger.info ("第" + (i + 1) + "次挑战完成，剩余" + (num) + "次");
			
		}
	}
	
	public static void refuseBack () throws InterruptedException, AWTException {
		for(int i=0;i<=150;i++){
			String file1 = "scenario/temp/御魂/拒接协战";
			ImageService.imagesClickBackNumber (file1,30,false);
			
		}
	}
	
	public static void borderCheck () throws InterruptedException, AWTException {
		//进入结界挑战
		String file="scenario/结界突破/结界突破";
		logger.info ("准备进入结界突破");
		ImageService.imagesClickBack (file);
		logger.info ("进入结界突破，检查结界挑战劵");
		//判断结界挑战劵是否为0
		String file1="scenario/结界突破/结界挑战劵数";
		while(!ImageService.imagesClickBackIsEmpty (file1,3)){
			//不为0则进行结界挑战
			logger.info ("结界劵数不为零");
			String file2="scenario/结界突破/个人结界";
			logger.info ("选择个人结界");
			//判断能否选择个人结界
			if(ImageService.imagesClickBackIsEmpty (file2,3)){
				ImageService.imagesClickBack (file2);
				logger.info ("点击个人结界成功，准备进攻");
				String file3="scenario/结界突破/进攻";
				ImageService.imagesClickBack (file3);
				logger.info ("开始进攻");
				String file4 = "scenario/御魂/角色头像";
				String file5 = "scenario/御魂/退出挑战";
				String file51 = "scenario/结界突破/失败";
				logger.info ("准备点击角色头像、退出挑战或直接点击退出挑战或失败");
				ImageService.imagesClickBackNumberOrderThree (file4,file5,file51,90);
				logger.info ("退出挑战完成");
				if(ImageService.imagesClickBackIsEmpty (file5,4)){
					logger.info ("每打完三个有额外奖励");
					ImageService.imagesClickBack (file5);
					logger.info ("领取额外奖励成功");
				}
				else{
					logger.info ("没有额外奖励");
				}
			}
			else{
				System.exit(0);
				String file6="scenario/结界突破/刷新";
				logger.info ("有结界挑战劵，没有可攻打的结界，准备刷新");
				//判断不处于刷新冷却期
				if(ImageService.imagesClickBackIsEmpty (file6,4)){
					ImageService.imagesClickBack (file6);
				}
				else{
					ImageService.imagesClickBackNumber(file6,60,true);
				}
				String file7="scenario/结界突破/确认";
				ImageService.imagesClickBack (file7);
				logger.info ("刷新成功");
			}
			logger.info ("检查是否还有结界挑战劵");
		}
	    //为0则不进行结界挑战
		logger.info ("结界劵数为0");
	    //退出到探索
		String file2="scenario/返回";
		ImageService.imagesClickBack (file2);
		logger.info ("退出到探索");
	}
	
	public static void soulEleven (int i,boolean b) throws InterruptedException, AWTException {
		String  file="scenario/御魂/御魂图标";
		logger.info ("准备进入御魂");
		ImageService.imagesClickBack (file);
		logger.info ("进入御魂成功，准备选择八岐大蛇");
		String  file1="scenario/御魂/八岐大蛇";
		ImageService.imagesClickBack (file1);
		logger.info ("进入八岐大蛇挑战页面");
		//获取加成地址，手动测试出御魂加成的坐标，然后存储到代码，然后根据当前分辨率获取唯一坐标
		List<PictureIdentifyWorkPO> pictureIdentifyWorkPOList= CoordinateAddress.getCoordinate ("御魂加成");
		String  file2="scenario/御魂/加成";
		if(b){
			logger.info ("准备开启加成");
			ImageService.imagesClickBack (file2);
			logger.info ("点击加成成功，准备点击御魂加成");
			MouseClick.mouseClickBack (pictureIdentifyWorkPOList);
			logger.info ("点击御魂加成成功，准备退出");
			ImageService.imagesClickBack (file2);
			//退出加成页面
			logger.info ("退出加成页面");
		}
	    //选择魂十或魂十一
		if(i==11){
		   String file3="scenario/御魂/层数/魂十一";
			logger.info ("选择魂十一");
			ImageService.imagesClickBack (file3);
		}
		else if(i==10){
			String file3="scenario/御魂/层数/魂十";
			logger.info ("选择魂十");
			ImageService.imagesClickBack (file3);
		}
	    //开始挑战
		soulBack (3);
		//挑战结束
		if(b){
			//关闭加成
			ImageService.imagesClickBack (file2);
			logger.info ("点击加成成功，准备关闭御魂加成");
			MouseClick.mouseClickBack (pictureIdentifyWorkPOList);
			logger.info ("关闭御魂加成成功，准备退出");
			ImageService.imagesClickBack (file2);
			//退出加成页面
			logger.info ("退出加成页面");
		}
		//退出到探索
		String file3="scenario/返回";
		ImageService.imagesClickBack (file3);
	}
	
	public static void spirit () throws InterruptedException, AWTException {
		String  file="scenario/御灵/御灵图标";
		logger.info ("准备进入御灵");
		ImageService.imagesClickBack (file);
		logger.info ("进入御灵");
		//判断当前是星期几，周一无法打御灵，周二神龙，周三白藏主，周四黑豹，周五孔雀，周六周日白藏主
		Date today = new Date();
		Calendar c = Calendar.getInstance ();
		c.setTime(today);
		int weekday = c.get(Calendar.DAY_OF_WEEK);
		//周一
		if(weekday==2){
			logger.info ("无法打御灵");
			System.exit(0);
		}
		//周二
		else if (weekday==3){
			String  file1="scenario/御灵/神龙";
			logger.info ("准备进入御灵神龙");
			ImageService.imagesClickBack (file1);
			logger.info ("进入御灵神龙");
		}
		//周三
		else if (weekday==4){
			String  file1="scenario/御灵/白藏主";
			logger.info ("准备进入御灵白藏主");
			ImageService.imagesClickBack (file1);
			logger.info ("进入御灵白藏主");
		}
		//周四
		else if (weekday==5){
			String  file1="scenario/御灵/黑豹";
			logger.info ("准备进入御灵黑豹");
			ImageService.imagesClickBack (file1);
			logger.info ("进入御灵黑豹");
		}
		//周五
		else if (weekday==6){
			String  file1="scenario/御灵/孔雀";
			logger.info ("准备进入御灵孔雀");
			ImageService.imagesClickBack (file1);
			logger.info ("进入御灵孔雀");
		}
	}
}


