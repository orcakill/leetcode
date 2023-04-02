package com.example.demo.service.impl;

import cn.hutool.core.util.IdUtil;
import com.example.demo.model.entity.GameThreadPO;
import com.example.demo.model.entity.PictureIdentifyWorkPO;
import com.example.demo.model.entity.ScreenshotPointPO;
import com.example.demo.model.map.CoordinateAddressMap;
import com.example.demo.model.map.ScreenshotPointMap;
import com.example.demo.model.param.MultipleImageParam;
import com.example.demo.model.param.MultipleImagesParam;
import com.example.demo.model.param.ProjectParam;
import com.example.demo.model.param.ProjectsParam;
import com.example.demo.model.thread.FirstThread;
import com.example.demo.model.thread.SecondThread;
import com.example.demo.service.GameThreadService;
import com.example.demo.service.ImageService;
import com.example.demo.service.OnmyojiService;
import com.example.demo.utils.ImageTesseractUtils;
import com.example.demo.utils.MouseClickUtils;
import com.example.demo.utils.RandomUtils;
import com.example.demo.utils.ScreenshotUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.*;

import static com.example.demo.model.param.ImageRecParam.*;
import static com.example.demo.model.var.CommVar.*;
import static com.example.demo.model.var.ProjectVar.*;
import static com.example.demo.service.ImageService.imagesBack;
import static com.example.demo.service.ImageService.imagesBackList;
import static java.lang.Thread.sleep;

/**
 * @Classname OnmyojiServiceImpl
 * @Description 阴阳师自动化实现类
 * @Date 2023/1/25 20:59
 * @Created by orcakill
 */
@Service
@Log4j2
public class OnmyojiServiceImpl implements OnmyojiService {
	private final GameThreadService gameThreadService;
	
	public OnmyojiServiceImpl (GameThreadService gameThreadService) {
		this.gameThreadService = gameThreadService;
	}
	
	@Override
	public void onmyojiService (ProjectsParam projectsParam) throws InterruptedException,
	                                                                UnknownHostException {
		//游戏进程id,游戏进程信息保存
		String threadId = IdUtil.objectId ();
		log.info ("进程信息初始化开始");
		log.info ("游戏进程ID{}", threadId);
		GameThreadPO gameThreadPO = new GameThreadPO ();
		gameThreadPO.setThreadId (threadId);
		gameThreadPO.setThreadState (0);
		InetAddress inetAddress = InetAddress.getLocalHost ();
		gameThreadPO.setCreateUser (inetAddress.getHostName ());
		gameThreadService.save (gameThreadPO);
		log.info ("进程信息初始化结束");
		//监控线程
		FirstThread t = new FirstThread ();
		t.setThreadId (threadId);
		t.setProcess (projectsParam.getProcess ());
		//运行线程
		SecondThread t1 = new SecondThread ();
		t1.setThreadId (threadId);
		t1.setProjectsParam (projectsParam);
		log.info ("启动监控线程");
		t.start ();
		log.info ("启动运行线程");
		t1.start ();
		gameThreadPO.setThreadState (1);
		gameThreadService.save (gameThreadPO);
		int threadState = gameThreadService.findById (threadId).getThreadState ();
		while (threadState != 2) {
			Thread.sleep (60 * 1000);
			threadState = gameThreadService.findById (threadId).getThreadState ();
		}
		log.info ("主线程结束");
	}
	
	@Override
	public void autoActivity (ProjectsParam projectsParam) throws IOException, InterruptedException,
	                                                              AWTException {
		Thread.sleep (10 * 1000);
		String process = projectsParam.getProcess ();
		for (int i = 0; i < projectsParam.getRound (); i++) {
			for (ProjectParam projectParam : projectsParam.getProjectParams ()) {
				//当前状态初始化
				if (projectParam.getProjectName ().equals (project_CSH)) {
					log.info (project_CSH);
					initializationState (process, "1");
				}
				//寄养检查
				if (projectParam.getProjectName ().equals (project_JYJC)) {
					log.info (project_JYJC);
					toFoster (process);
				}
				//阴阳寮突破
				if (projectParam.getProjectName ().equals (project_YYLTP)) {
					log.info (project_YYLTP);
					fightHouse (process);
					if (projectParam.getProjectWaitStartTime () != null) {
						int waitTime = RandomUtils.getRandom (projectParam.getProjectWaitStartTime (),
						                                      projectParam.getProjectWaitEndTime ());
						log.info ("等待{}分钟", waitTime);
						Thread.sleep (waitTime * 60 * 1000L);
					}
				}
				//个人突破
				if (projectParam.getProjectName ().equals (project_GRTP)) {
					log.info (project_GRTP);
					borderCheck (process);
				}
				//魂十一
				if (projectParam.getProjectName ().equals (project_HSY)) {
					log.info (project_HSY);
					soulFight (process, project_HSY, projectParam.getProjectNum (), projectParam.isAddition ());
				}
				//业原火
				if (projectParam.getProjectName ().equals (project_YYH)) {
					log.info (project_YYH);
					soulFight (process, project_YYH, projectParam.getProjectNum (), false);
				}
				//日轮之陨
				if (projectParam.getProjectName ().equals (project_RLZY)) {
					log.info (project_RLZY);
					soulFight (process, project_RLZY, projectParam.getProjectNum (), projectParam.isAddition ());
				}
				//永生之海
				if (projectParam.getProjectName ().equals (project_YSZH)) {
					log.info (project_YSZH);
					soulFight (process, project_YSZH, projectParam.getProjectNum (), projectParam.isAddition ());
				}
				//御灵
				if (projectParam.getProjectName ().equals (project_YL)) {
					log.info (project_YL);
					spirit (process, projectParam.getProjectNum ());
				}
				//斗技
				if (projectParam.getProjectName ().equals (project_DJ)) {
					log.info (project_DJ);
					pvp (process, projectParam.getProjectNum ());
				}
				//御魂整理 速度极限副属性强化
				if (projectParam.getProjectName ().equals (project_YJZL_JXFSXQH)) {
					log.info (project_YJZL_JXFSXQH);
					soulEnhancements (process, projectParam.getProjectNum ());
				}
				//探索  全打
				if (projectParam.getProjectName ().equals (project_GRTS)) {
					log.info (project_GRTS);
					explore (process, projectParam.getProjectNum ());
				}
				//探索  只打2个
				if (projectParam.getProjectName ().equals (project_GRTS_FAST)) {
					log.info (project_GRTS_FAST);
					exploreFast (process, projectParam.getProjectNum ());
				}
				
			}
		}
		
	}
	
	@Override
	public void initializationState (String process, String userId) throws IOException, InterruptedException,
	                                                                       AWTException {
		boolean initializeOrNot = false;
		String thisPicture;//当前状态
		boolean announcementOrNot;//是否公告
		boolean promptForAge = false;//是否适龄提示
		boolean targetHomePage = false;
		boolean switchAccount;
		log.info ("等待10秒");
		Thread.sleep (10 * 1000L);
		log.info ("当前状态初始化");
		while (!initializeOrNot) {
			thisPicture = thisState (process);
			log.info ("当前状态{}", thisPicture);
			if (userId.equals ("1")) {
				if (thisPicture.equals (home_YH_NJZR)) {
					targetHomePage = true;
				}
			}
			if (userId.equals ("2")) {
				if (thisPicture.equals (home_YH_ORCAKILL)) {
					targetHomePage = true;
				}
			}
			if (userId.equals ("3")) {
				if (thisPicture.equals (home_YH_HHXL)) {
					targetHomePage = true;
				}
			}
			if (userId.equals ("4")) {
				if (thisPicture.equals (home_YH_CRDECCWY)) {
					targetHomePage = true;
				}
			}
			if (thisPicture.equals (return_FH)) {
				log.info ("先返回到首页");
				returnHome (process);
				log.info ("重新判断当前状态");
				continue;
			}
			if (!targetHomePage) {
				//  当前页面阴阳师图标，需要点击应用图标->跳过登录动画->关闭公告->适龄提示
				if (thisPicture.equals (login_YYSTB)) {
					log.info ("点击阴阳师图标");
					ImageService.imagesBack (login_YYSTB, paramSIFT (process));
					while (!promptForAge) {
						Thread.sleep (15000);
						log.info ("单击一下，防止有开场动画");
						MouseClickUtils.mouseClickBack (new PictureIdentifyWorkPO (500, 500), "夜神模拟器");
						Thread.sleep (1000);
						log.info ("判断是否有公告需要返回");
						announcementOrNot = ImageService.imagesBack (return_FH, paramRGB (process));
						if (announcementOrNot) {
							log.info ("有公告");
							ImageService.imagesBack (return_FH, paramSIFT (process));
							Thread.sleep (1000);
						}
						promptForAge = ImageService.imagesBack (login_SLTS, paramSIFTNotClick (process));
						if (promptForAge) {
							log.info ("当前页面有适龄提示");
						}
					}
				}
				// 当前页面非目标首页
				if (thisPicture.equals (home_YH_NJZR)) {
					log.info ("点击用户头像");
					ImageService.imagesBack (home_YH_NJZR, paramSIFT (process, 20));
					log.info ("点击用户中心");
					ImageService.imagesBack (home_TXYHZX, paramSIFT (process, 20));
				}
				if (thisPicture.equals (home_YH_ORCAKILL)) {
					log.info ("点击用户头像");
					ImageService.imagesBack (home_YH_ORCAKILL, paramSIFT (process, 20));
					log.info ("点击用户中心");
					ImageService.imagesBack (home_TXYHZX, paramSIFT (process, 20));
				}
				// 当前页面适龄提示->点击用户中心
				if (thisPicture.equals (login_SLTS) || promptForAge) {
					log.info ("点击头像用户中心");
					ImageService.imagesBack (login_YHZX, paramSIFT (process));
				}
				log.info ("判断是否切换账号");
				switchAccount = ImageService.imagesBack (login_QHZH, paramSIFTNotClick (process));
				if (switchAccount) {
					log.info ("切换用户和大区并登录到首页");
					login (process, userId);
					log.info ("当前用户首页");
				}
			}
			else {
				initializeOrNot = true;
			}
			
		}
		log.info ("当前状态初始化完成");
	}
	
	@Override
	public String thisState (String process) throws IOException, InterruptedException, AWTException {
		// 图像集
		MultipleImagesParam multipleImagesParams = new MultipleImagesParam ();
		List<MultipleImageParam> multipleImageParamList = new ArrayList<> ();
		//桌面       阴阳师图标
		multipleImageParamList.add (new MultipleImageParam (login_YYSTB, paramSIFT (process)));
		//登录界面    适龄提示
		multipleImageParamList.add (new MultipleImageParam (login_SLTS, paramRGB (process)));
		//大号首页    缥缈之旅 逆戟之刃
		multipleImageParamList.add (new MultipleImageParam (home_YH_NJZR, paramSIFT (process, 20)));
		//小号1首页   缥缈之旅 orcakill
		multipleImageParamList.add (new MultipleImageParam (home_YH_ORCAKILL, paramSIFT (process, 20)));
		//小号2首页   两情相悦  洪荒的修罗
		multipleImageParamList.add (new MultipleImageParam (home_YH_HHXL, paramSIFT (process, 20)));
		//小号3首页   桃映春馨  炽热的惆怅物语
		multipleImageParamList.add (new MultipleImageParam (home_YH_CRDECCWY, paramSIFT (process, 20)));
		//返回       返回
		multipleImageParamList.add (new MultipleImageParam (return_FH, paramRGB (process)));
		multipleImageParamList.add (new MultipleImageParam (return_FH, paramSIFT (process, 4)));
		multipleImagesParams.setClick (false);
		multipleImagesParams.setMultipleImageParamList (multipleImageParamList);
		return imagesBackList (multipleImagesParams);
	}
	
	@Override
	public void returnHome (String process) throws IOException, InterruptedException, AWTException {
		int num = 1;
		log.info ("判断是否首页");
		boolean homePageOrNot = ImageService.imagesBack (home_TS, paramSIFTNotClick (process, 1, 20));
		while (!homePageOrNot) {
			log.info ("点击返回按钮");
			imagesBack (return_FH, paramRGB (process, 1));
			if (num % 2 == 0) {
				ImageService.imagesBack (return_FH, paramSIFT (process, 1, 4));
			}
			log.info ("点击可能存在的退出挑战");
			imagesBack (soul_TCTZ, paramRGB (process, 1));
			log.info ("点击可能存在的确定");
			imagesBack (explore_QR, paramSIFT (process, 1, 4));
			num++;
			homePageOrNot = imagesBack (home_TS, paramSIFTNotClick (process, 1, 20));
		}
		Thread.sleep (2000);
		log.info ("判断底部菜单是否打开");
		boolean bottomMenu = imagesBack (home_DBCDDK, paramSIFTNotClick (process, 1, 20));
		if (!bottomMenu) {
			log.info ("打开底部菜单");
			ImageService.imagesBack (home_DBCD, paramSIFT (process, 1, 20));
		}
		log.info ("返回首页完成");
	}
	
	@Override
	public void login (String process, String gameUserId) throws IOException, InterruptedException, AWTException {
		log.info ("切换账号");
		imagesBack (login_QHZH, paramSIFT (process));
		log.info ("常用");
		imagesBack (login_CY, paramSIFT (process, 0.8, 4));
		log.info ("选择账号");
		if (gameUserId != null) {
			if (gameUserId.equals ("1")) {
				log.info ("手机号178");
				imagesBack (login_XZZH_PHONE1, paramSIFT (process, 0.8, 4));
			}
			if (gameUserId.equals ("2")) {
				log.info ("邮箱号1");
				imagesBack (login_YHZX_EMAIIL1, paramSIFT (process, 0.8, 4));
			}
		}
		log.info ("登录");
		imagesBack (login_DL, paramSIFT (process));
		log.info ("切换服务器");
		imagesBack (login_QH, paramSIFT (process));
		log.info ("点击小三角");
		imagesBack (login_XSJ, paramSIFT (process, 0.8, 4));
		if (gameUserId != null) {
			if (gameUserId.equals ("1")) {
				log.info ("点击大号角色-缥缈之旅");
				imagesBack (login_FWQ_PMZL, paramSIFT (process));
			}
			if (gameUserId.equals ("2")) {
				log.info ("点击小号角色1-缥缈之旅");
				imagesBack (login_FWQ_PMZL, paramSIFT (process));
			}
			if (gameUserId.equals ("3")) {
				log.info ("点击小号角色2-两情相悦");
				imagesBack (login_FWQ_LQXY, paramSIFT (process));
			}
			if (gameUserId.equals ("4")) {
				log.info ("点击小号角色2-桃映春馨");
				imagesBack (login_FWQ_TYCX, paramSIFT (process));
			}
		}
		log.info ("开始游戏");
		imagesBack (login_KSYX, paramSIFT (process, 0.7, 4));
		sleep (5 * 1000L);
		log.info ("底部菜单栏");
		imagesBack (home_DBCD, paramSIFT (process));
		boolean openBottom = ImageService.imagesBack (home_DBCDDK, paramSIFTNotClick (process, 1, 4));
		while (!openBottom) {
			log.info ("未打开底部菜单栏，点击打开");
			imagesBack (home_DBCD, paramSIFT (process, 1, 4));
			log.info ("点击可能存在的返回按钮");
			imagesBack (return_FH, paramSIFT (process, 1, 4));
			log.info ("点击可能存在的下载按钮");
			imagesBack (home_XZ, paramSIFT (process, 1, 4));
			log.info ("重新判断是否打开底部菜单");
			openBottom = ImageService.imagesBack (home_DBCDDK, paramSIFTNotClick (process, 1, 4));
			sleep (1000);
		}
		sleep (2 * 1000L);
	}
	
	@Override
	
	public void toFoster (String process) throws InterruptedException, IOException, AWTException {
		String fileJJK = house_JJK_TG_LXTG;
		int num = 0;//退出后重新进入好友列表的次数
		PictureIdentifyWorkPO pictureIdentifyWorkPO1;//记录好友标题位置
		PictureIdentifyWorkPO pictureIdentifyWorkPO2 = new PictureIdentifyWorkPO ();//记录滚动起始位置
		PictureIdentifyWorkPO pictureIdentifyWorkPO3 = new PictureIdentifyWorkPO ();//记录滚动终止位置
		PictureIdentifyWorkPO pictureIdentifyWorkPO4 = new PictureIdentifyWorkPO ();//记录退出好友列表位置
		boolean booleanKJY;//寄养位是否为空，是则进行寄养，否则退出到首页
		boolean booleanWFZ;//好友是否放置结界卡，是则判断是否满足要求，不是则退出更换条件重进
		boolean booleanJJK;//结界卡是否满足当前要求，不满足则滚动，满足则进入结界
		boolean booleanJSJY = false;//判断寄养是否结束
		sleep (2000);
		log.info ("进入阴阳寮");
		imagesBack (house_YYLTB, paramSIFT (process));
		sleep (2000);
		log.info ("进入结界");
		imagesBack (house_JJ, paramSIFT (process));
		sleep (2000);
		log.info ("进入式神育成");
		imagesBack (house_SSYC, paramSIFT (process));
		sleep (2000);
		log.info ("判断是否可寄养");
		booleanKJY = imagesBack (house_KJY, paramRGBNotClick (process, 5));
		if (booleanKJY) {
			log.info ("进入可寄养");
			imagesBack (house_KJY, paramRGB (process));
			sleep (3000);
			log.info ("已进入好友列表");
			pictureIdentifyWorkPO1 = ImageService.imagesBackGetCoordinate (house_HYBT, paramRGB (process));
			pictureIdentifyWorkPO2.setX ((int) (pictureIdentifyWorkPO1.getX () * 1.3));
			pictureIdentifyWorkPO2.setY ((int) (pictureIdentifyWorkPO1.getY () * 2.3));
			pictureIdentifyWorkPO3.setX ((int) (pictureIdentifyWorkPO1.getX () * 1.3));
			pictureIdentifyWorkPO3.setY ((int) (pictureIdentifyWorkPO1.getY () * 1.6));
			pictureIdentifyWorkPO4.setX (pictureIdentifyWorkPO1.getX ());
			pictureIdentifyWorkPO4.setY ((int) (pictureIdentifyWorkPO1.getY () * 0.5));
			//开循环，0 六星太鼓 1 五星太鼓 2 四星太鼓 3 六星斗鱼 4 五星斗鱼 5 四星斗鱼
			while (!booleanJSJY) {
				log.info ("当前好友结界卡是否未放置");
				booleanWFZ = imagesBack (house_WFZ, paramRGB (process, 5));
				if (booleanWFZ) {
					log.info ("好友未放置结界卡，退出后重新进入");
					if (num == 0) {
						fileJJK = house_JJK_TG_LXTG;
					}
					if (num == 1) {
						fileJJK = house_JJK_TG_WXTG;
					}
					if (num == 2) {
						fileJJK = house_JJK_TG_SXTG;
					}
					if (num == 3) {
						fileJJK = house_JJK_DY_LXDY;
					}
					if (num == 4) {
						fileJJK = house_JJK_DY_WXDY;
					}
					if (num == 5) {
						fileJJK = house_JJK_DY_SXDY;
					}
					if (num > 5) {
						log.info ("无高星结界卡，开始退出");
						break;
					}
					num++;
					log.info ("退出到可寄养界面");
					MouseClickUtils.mouseClickBack (pictureIdentifyWorkPO4, "夜神模拟器");
					log.info ("重新进入好友列表");
					imagesBack (house_KJY, paramRGB (process));
				}
				else {
					log.info ("存在结界卡，判断是否是高星结界卡");
					booleanJJK = imagesBack (fileJJK, paramRGBNotClick (process, 3));
					if (!booleanJJK) {
						log.info ("当前结界不是{},不可放置,滚动到下一个", fileJJK);
						MouseClickUtils.mouseClickBackDrag (pictureIdentifyWorkPO2, pictureIdentifyWorkPO3,
						                                    "夜神模拟器");
						log.info ("滚动完成,点击终止位置坐标");
						MouseClickUtils.mouseClickBack (pictureIdentifyWorkPO3, "夜神模拟器");
					}
					else {
						log.info ("存在高星结界卡" + fileJJK);
						imagesBack (house_JRJJ, paramRGB (process));
						log.info ("进入结界");
						imagesBack (house_DJDM, paramRGB (process));
						log.info ("大吉达摩寄养");
						imagesBack (comm_QR, paramRGB (process));
						log.info ("确认");
						log.info ("检查是否寄养成功");
						imagesBack (return_FH, paramRGB (process));
						imagesBack (return_FH, paramRGB (process));
						log.info ("返回到式神育成");
						imagesBack (house_SSYC, paramRGB (process));
						log.info ("进入育成界面");
						booleanJSJY = imagesBack (house_KJY, paramRGB (process, 3));
						if (booleanJSJY) {
							log.info ("寄养失败，重新进入好友列表");
							imagesBack (house_KJY, paramRGB (process));
						}
						else {
							log.info ("寄养成功");
							booleanJSJY = true;
						}
					}
				}
				
			}
		}
		//退出
		returnHome (process);
	}
	
	@Override
	public void fightHouse (String process) throws IOException, InterruptedException, AWTException {
		int num = 0;
		int num1 = 0;
		int num2 = 0;
		boolean b;
		boolean b1;
		boolean booleanYYL;
		boolean boolean_TCJG;
		//流程开始
		log.info ("准备进入探索");
		ImageService.imagesBack (home_TS, paramSIFT (process));
		log.info ("准备进入结界突破");
		ImageService.imagesBack (region_JJTP, paramSIFT (process));
		log.info ("进入结界突破，准备点击阴阳寮");
		booleanYYL = ImageService.imagesBack (region_YYL, paramRGB (process, 5));
		while (!booleanYYL) {
			sleep (2000);
			if (ImageService.imagesBack (region_GTRQ, paramRGBNotClick (process, 5))) {
				log.info ("有呱太入侵");
				ImageService.imagesBack (region_GTRQ, paramRGB (process));
				log.info ("呱太入侵点击完成");
				
			}
			booleanYYL = ImageService.imagesBack (region_YYL, paramRGB (process, 5));
		}
		sleep (2000);
		log.info ("进入寮突破，判断当前有无挑战次数");
		//退出到探索
		while (!ImageService.imagesBack (region_TZCS, paramRGB (process, 5)) && ImageService.imagesBack (region_LJJ,
		                                                                                                 paramRGBNotClick (
				                                                                                                 process,
				                                                                                                 5))) {
			log.info ("存在可攻打结界，且存在挑战次数");
			log.info ("准备选择结界");
			ImageService.imagesBack (region_LJJ, paramRGB (process));
			Thread.sleep (1000);
			log.info ("选择结界成功，准备进攻");
			ImageService.imagesBack (region_JG, paramRGB (process));
			log.info ("开始进攻");
			b = ImageService.imagesBack (region_JG, paramRGBNotClick (process, 3));
			if (b) {
				boolean_TCJG = ImageService.imagesBack (region_TCJG, paramRGB (process, 10));
				while (boolean_TCJG) {
					log.info ("结界已被攻破，退出进攻");
					ImageService.imagesBack (region_TCJG, paramRGB (process));
					log.info ("判断是否退出进攻");
					boolean_TCJG = ImageService.imagesBack (region_TCJG, paramRGB (process, 10));
				}
				ImageService.imagesBack (return_FH, paramRGB (process));
				log.info ("退出到探索");
				log.info ("准备重新进入结界突破");
				ImageService.imagesBack (region_JJTP, paramSIFT (process));
				log.info ("进入结界突破，准备点击阴阳寮");
				ImageService.imagesBack (region_YYL, paramRGB (process));
				log.info ("重新判断是否有结界可以攻打");
				continue;
			}
			else {
				log.info ("结界未被攻破");
			}
			b1 = fightEnd (process, 30, 30, 40);
			if (b1) {
				num1++;
			}
			else {
				num2++;
			}
			num++;
			log.info ("阴阳寮挑战第" + num + "次,成功" + num1 + "次，失败" + num2 + "次");
			if (num == 8) {
				log.info ("阴阳寮挑战到达8次");
				break;
			}
		}
		log.info ("无挑战次数或无可攻打结界");
		returnHome (process);
	}
	
	/**
	 * @param begin_num 开始识别时间
	 * @param start_num 识别间隔开始时间
	 * @param end_num   识别间隔结束时间
	 * @description: 战斗结果 挑战成功或失败 （统计战斗数据）
	 * @return: void
	 * @author: orcakill
	 * @date: 2023/2/21 22:21
	 */
	@Override
	public boolean fightEnd (String process, Integer begin_num, Integer start_num, Integer end_num)
			throws InterruptedException, IOException, AWTException {
		// 图像集
		MultipleImagesParam multipleImagesParams = new MultipleImagesParam ();
		List<MultipleImageParam> multipleImageParamList = new ArrayList<> ();
		//御魂 角色头像
		multipleImageParamList.add (new MultipleImageParam (soul_JSTX, paramRGB (process)));
		//御魂 退出挑战
		multipleImageParamList.add (new MultipleImageParam (soul_TCTZ, paramRGB (process)));
		//结界突破 失败
		multipleImageParamList.add (new MultipleImageParam (region_SB, paramRGB (process)));
		multipleImagesParams.setClick (true);
		multipleImagesParams.setStart_time (start_num);
		multipleImagesParams.setEnd_time (end_num);
		multipleImagesParams.setMultipleImageParamList (multipleImageParamList);
		log.info ("战斗开始，等待" + begin_num + "秒");
		sleep (begin_num * 1000);
		log.info ("准备点击角色头像、点击退出挑战、失败、宠物奖励");
		String thisState = imagesBackList (multipleImagesParams);
		log.info ("当前{}", thisState);
		if (thisState == null) {
			log.info ("未找到退出挑战");
			return false;
		}
		if (thisState.equals (soul_JSTX)) {
			log.info ("点击角色头像");
			ImageService.imagesBack (soul_TCTZ, paramRGB (process));
			
		}
		if (thisState.equals (region_SB)) {
			log.info ("战斗失败");
			return false;
		}
		log.info ("战斗胜利");
		return true;
	}
	
	/***
	 * @description: 个人结界
	 * @return: void
	 * @author: orcakill
	 * @date: 2023/2/22 1:19
	 */
	@Override
	public void borderCheck (String process) throws IOException, InterruptedException, AWTException {
		
		boolean booleanKXJJ;//可选结界
		boolean booleanZBTJ;//准备挑战
		boolean whetherAdditionalReward;
		log.info ("准备进入探索");
		ImageService.imagesBack (home_TS, paramSIFT (process));
		//进入结界挑战
		log.info ("准备进入结界突破");
		ImageService.imagesBack (region_JJTP, paramSIFT (process));
		sleep (3000);
		MouseClickUtils.mouseClickBack (new PictureIdentifyWorkPO (0, 0), "夜神模拟器");
		sleep (1000);
		MouseClickUtils.mouseClickBack (new PictureIdentifyWorkPO (0, 0), "夜神模拟器");
		log.info ("进入结界突破，检查结界挑战劵");
		//判断结界挑战劵是否为0
		while (!ImageService.imagesBack (region_JJTZJS, paramRGB (process, 3))) {
			//不为0则进行结界挑战
			log.info ("结界劵数不为零");
			//判断能否选择个人结界
			booleanKXJJ = ImageService.imagesBack (region_GRJJ, paramRGBNotClick (process, 3));
			if (booleanKXJJ) {
				log.info ("能选择个人结界");
				ImageService.imagesBack (region_GRJJ, paramRGB (process));
				log.info ("点击个人结界成功，准备进攻");
				ImageService.imagesBack (region_JG, paramRGB (process));
				log.info ("开始进攻");
				sleep (5 * 1000);
				log.info ("判断是否准备挑战");
				booleanZBTJ = ImageService.imagesBack (region_ZBTZ, paramSIFTNotClick (process, 3, 4));
				if (booleanZBTJ) {
					log.info ("准备挑战");
					ImageService.imagesBack (region_ZBTZ, paramSIFT (process));
				}
				else {
					log.info ("无需准备");
				}
				log.info ("进入战斗");
				fightEnd (process, 10, 10, 15);
				log.info ("判断是否有额外奖励");
				whetherAdditionalReward = imagesBack (soul_TCTZ, paramRGB (process, 5));
				if (whetherAdditionalReward) {
					ImageService.imagesBack (soul_TCTZ, paramRGB (process));
					log.info ("领取额外奖励成功");
				}
				else {
					log.info ("没有额外奖励");
				}
			}
			log.info ("战斗后检查是否还有结界挑战劵");
		}
		//为0则不进行结界挑战
		log.info ("结界劵数为0,返回到首页");
		//退出到探索
		returnHome (process);
	}
	
	/***
	 * @description: 御魂战斗  魂十一、魂一（协战用）、魂十、业原火、日轮之陨、永生之海
	 * @param soulType 御魂类型
	 * @param soulNum  战斗场次
	 * @param addition  是否开启加成
	 * @return: void
	 * @author: orcakill
	 * @date: 2023/2/22 1:37
	 */
	@Override
	public void soulFight (String process, String soulType, int soulNum, boolean addition)
			throws IOException, InterruptedException, AWTException {
		boolean b1;
		boolean b2;
		List<PictureIdentifyWorkPO> pictureIdentifyWorkPOList;
		log.info ("准备进入探索");
		imagesBack (home_TS, paramSIFT (process));
		log.info ("准备进入御魂");
		imagesBack (soul_Icon, paramSIFT (process));
		if (Objects.equals (soulType, project_HSY) || Objects.equals (soulType, project_HS)) {
			log.info ("进入御魂成功，准备选择八岐大蛇");
			imagesBack (soul_YHLX_BQDS, paramSIFT (process));
			log.info ("进入八岐大蛇挑战页面");
			//获取加成地址，手动测试出御魂加成的坐标，然后存储到代码，然后根据当前分辨率获取唯一坐标
			pictureIdentifyWorkPOList = CoordinateAddressMap.getCoordinate ("御魂加成");
			if (addition) {
				log.info ("准备开启加成");
				imagesBack (soul_JC_BQDS, paramRGB (process));
				log.info ("点击加成成功，准备点击御魂加成");
				MouseClickUtils.mouseClickBack (pictureIdentifyWorkPOList, "夜神模拟器", true);
				log.info ("点击御魂加成成功，准备退出");
				imagesBack (soul_JC_BQDS, paramRGB (process));
				//退出加成页面
				log.info ("退出加成页面");
			}
			if (soulType.equals (project_HSY)) {
				log.info ("选择魂十一");
				b1 = imagesBack (soul_CS_HSY, paramSIFTNotClick (process, 30));
				if (!b1) {
					log.info ("没有选择到魂十一");
				}
				imagesBack (soul_CS_HSY, paramSIFT (process));
			}
			else {
				log.info ("选择魂十");
				b2 = imagesBack (soul_CS_HS, paramSIFTNotClick (process, 30));
				if (!b2) {
					log.info ("没有选择到魂十");
				}
				imagesBack (soul_CS_HS, paramSIFT (process));
			}
			
			//选择魂十或魂十一
			//开始挑战
			soulBack (process, soul_TZLX_BQDSTZ, 22, soulNum);
			//挑战结束
			if (addition) {
				//关闭加成
				imagesBack (soul_JC_BQDS, paramRGB (process));
				log.info ("点击加成成功，准备关闭御魂加成");
				MouseClickUtils.mouseClickBack (pictureIdentifyWorkPOList, "夜神模拟器", true);
				log.info ("关闭御魂加成成功，准备退出");
				imagesBack (soul_JC_BQDS, paramRGB (process));
				//退出加成页面
				log.info ("退出加成页面");
			}
		}
		//业原火
		if (Objects.equals (soulType, project_YYH)) {
			log.info ("进入御魂成功，准备选择业原火");
			imagesBack (soul_YHLX_YYH, paramSIFT (process));
			log.info ("进入业原火");
			log.info ("选择业原火第三层");
			b1 = imagesBack (soul_CS_YYHSC, paramSIFT (process));
			if (!b1) {
				log.info ("没有选择到业原火第三层");
			}
			log.info ("开始挑战");
			//开始挑战
			soulBack (process, soul_TZLX_YYHTZ, 40, soulNum);
		}
		if (Objects.equals (soulType, project_RLZY)) {
			log.info ("进入御魂成功，准备选择日轮之陨");
			imagesBack (soul_YHLX_RLZY, paramSIFT (process));
			log.info ("进入日轮之陨");
			log.info ("选择日轮之陨第三层");
			b1 = imagesBack (soul_CS_RLZYSC, paramSIFT (process));
			if (!b1) {
				log.info ("没有选择到日轮之陨三层");
			}
			log.info ("开始挑战");
			//开始挑战
			soulBack (process, soul_TZLX_RLZYTZ, 15, soulNum);
		}
		if (Objects.equals (soulType, project_YSZH)) {
			log.info ("进入御魂成功，准备选择永生之海");
			imagesBack (soul_YHLX_YSZH, paramSIFT (process));
			log.info ("选择永生之海第四层");
			b1 = imagesBack (soul_CS_YSZHSC, paramSIFT (process));
			if (!b1) {
				log.info ("没有选择到永生之海四层");
			}
			log.info ("开启永生之海加成");
			imagesBack (soul_JC_YSZHJC, paramSIFT (process));
			log.info ("开始挑战");
			//开始挑战
			soulBack (process, soul_TZLX_YSZHTZ, 40, soulNum);
		}
		returnHome (process);
	}
	
	/***
	 * @description: 御魂战斗详情
	 * @param begin_time  识别开始时间
	 * @param soulNumber      战斗场次
	 * @return: void
	 * @author: orcakill
	 * @date: 2023/2/22 2:11
	 */
	@Override
	public void soulBack (String process, String fightType, int begin_time, int soulNumber) throws InterruptedException,
	                                                                                               IOException,
	                                                                                               AWTException {
		sleep (3000);
		log.info ("战斗开始");
		boolean b1;
		//开始挑战,处理剩余次数的御魂
		for (int i = 1; soulNumber > 0; i++) {
			long a = System.currentTimeMillis ();//获取当前系统时间(毫秒)
			log.info ("准备开始挑战");
			sleep (1000);
			b1 = imagesBack (fightType, paramSIFT (process, 5));
			while (!b1) {
				log.info ("判断是否有宠物奖励");
				imagesBack (soul_CWJL, paramRGB (process, 5));
				log.info ("判断是否没退出挑战成功");
				imagesBack (soul_TCTZ, paramRGB (process, 5));
				log.info ("重新点击调整");
				b1 = imagesBack (fightType, paramSIFT (process, 5));
			}
			if (fightType.equals (soul_TZLX_BQDSTZ) && i == 1) {
				Thread.sleep (2000);
				log.info ("喂食小三角");
				imagesBack (soul_WSXSJ, paramSIFT (process, 1, 4));
				log.info ("喂食");
				imagesBack (soul_WS, paramSIFT (process, 1, 4));
			}
			log.info ("第{}次挑战中,等待挑战完成", i);
			fightEnd (process, begin_time, 1, 2);
			soulNumber--;
			log.info ("第{}次挑战完成，剩余{}次", i, soulNumber);
			log.info ("该次挑战使用时间为{}秒", (System.currentTimeMillis () - a) / 1000);
		}
		log.info ("判断当前是否可挑战");
		b1 = imagesBack (fightType, paramSIFTNotClick (process, 1));
		while (!b1) {
			log.info ("判断是否有宠物奖励");
			imagesBack (soul_CWJL, paramRGB (process, 5));
			log.info ("判断是否没退出挑战成功");
			imagesBack (soul_TCTZ, paramRGB (process, 5));
			b1 = imagesBack (fightType, paramSIFT (process, 5));
		}
		log.info ("结束挑战");
	}
	
	/***
	 * @description: 御灵战斗
	 * @param process  夜神模拟器
	 * @param num  战斗次数
	 * @return: void
	 * @author: orcakill
	 * @date: 2023/3/1 11:14
	 */
	@Override
	public void spirit (String process, int num) throws IOException, InterruptedException, AWTException {
		log.info ("准备进入探索");
		imagesBack (home_TS, paramSIFT (process));
		log.info ("准备进入御灵");
		imagesBack (spirit_YLTB, paramSIFT (process));
		log.info ("进入御灵");
		//判断当前是星期几，周一无法打御灵，周二神龙，周三白藏主，周四黑豹，周五孔雀，周六周日白藏主
		Date today = new Date ();
		Calendar c = Calendar.getInstance ();
		c.setTime (today);
		int weekday = c.get (Calendar.DAY_OF_WEEK);
		//周一
		if (weekday == 2) {
			log.info ("无法打御灵");
		}
		else {
			//周二
			if (weekday == 3) {
				
				log.info ("准备进入御灵神龙");
				imagesBack (spirit_SL, paramSIFT (process));
				log.info ("进入御灵神龙");
			}
			//周三、周六和周日
			else if (weekday == 4 || weekday == 7 || weekday == 1) {
				log.info ("准备进入御灵白藏主");
				imagesBack (spirit_BZZ, paramSIFT (process));
				log.info ("进入御灵白藏主");
			}
			//周四
			else if (weekday == 5) {
				log.info ("准备进入御灵黑豹");
				imagesBack (spirit_HB, paramSIFT (process));
				log.info ("进入御灵黑豹");
			}
			//周五
			else if (weekday == 6) {
				log.info ("准备进入御灵孔雀");
				imagesBack (spirit_KQ, paramSIFT (process));
				log.info ("进入御灵孔雀");
			}
			log.info ("选择第三层");
			imagesBack (spirit_DSC, paramSIFT (process));
			log.info ("开始挑战");
			soulBack (process, spirit_TZ, 15, num);
		}
		log.info ("结束御灵");
		returnHome (process);
	}
	
	/***
	 * @description: 斗技
	 * @param process  夜神模拟器
	 * @param num  次数
	 * @return: void
	 * @author: orcakill
	 * @date: 2023/3/1 22:30
	 */
	@Override
	public void pvp (String process, int num) throws IOException, InterruptedException, AWTException {
		int num1 = 0;
		int num2 = 0;
		log.info ("开始斗技");
		//	进入町中
		imagesBack (contend_DZ, paramSIFT (process));
		log.info ("町中武馆");
		imagesBack (contend_DZWG, paramSIFT (process));
		//  进入斗技
		imagesBack (contend_DJTB, paramSIFT (process));
		for (int i = 1; i <= num; i++) {
			log.info ("准备挑战");
			boolean booleanYCQK = imagesBack (contend_KSTZ, paramSIFT (process, 1, 4));
			while (!booleanYCQK) {
				log.info ("判断是否段位晋升");
				boolean b2 = imagesBack (contend_DWJS, paramSIFT (process, 1, 4));
				if (!b2) {
					log.info ("没有段位晋升");
				}
				else {
					log.info ("存在段位晋升");
				}
				log.info ("判断是否有额外奖励");
				boolean b1 = imagesBack (contend_EWJL, paramRGB (process));
				if (!b1) {
					log.info ("没有额外奖励");
				}
				else {
					log.info ("存在额外奖励");
				}
				booleanYCQK = imagesBack (contend_KSTZ, paramSIFT (process, 1, 4));
			}
			log.info ("进入挑战，准备自动选择");
			imagesBack (contend_ZDXZ, paramSIFT (process, 1, 4));
			log.info ("自动选择完成，准备自动战斗");
			sleep (2000);
			log.info ("等待选将");
			sleep (30 * 1000);
			log.info ("准备拔得头筹、战斗胜利或战斗失败");
			boolean b = fightPVP (process, 30, 30, 60);
			if (b) {
				num1++;
			}
			else {
				num2++;
			}
			log.info ("第" + i + "次挑战完成，胜利" + num1 + "次，失败" + num2 + "次，胜率" + num1 * 100 / i + "%");
		}
		boolean booleanFH = imagesBack (return_FH, paramRGBNotClick (process, 2));
		while (!booleanFH) {
			log.info ("判断斗技结束后，是否段位晋升");
			boolean b1 = imagesBack (contend_DWJS, paramRGB (process, 1));
			if (!b1) {
				log.info ("没有段位晋升");
			}
			else {
				log.info ("存在段位晋升");
			}
			log.info ("判断斗技结束后，是否有额外奖励");
			boolean b2 = imagesBack (contend_EWJL, paramRGB (process, 1));
			if (!b2) {
				log.info ("没有额外奖励");
			}
			else {
				log.info ("存在额外奖励");
			}
			booleanFH = imagesBack (return_FH, paramRGBNotClick (process, 1));
		}
		//返回首页
		log.info ("斗技结束,返回首页");
		returnHome (process);
		
	}
	
	/***
	 * @description: 斗技战斗
	 * @param process   夜神模拟器
	 * @param begin_num  开始时间
	 * @param start_num  开始间隔时间
	 * @param end_num    结束间隔时间
	 * @return: boolean
	 * @author: orcakill
	 * @date: 2023/3/2 0:17
	 */
	@Override
	public boolean fightPVP (String process, Integer begin_num, Integer start_num, Integer end_num)
			throws InterruptedException, IOException, AWTException {
		// 图像集
		MultipleImagesParam multipleImagesParams = new MultipleImagesParam ();
		List<MultipleImageParam> multipleImageParamList = new ArrayList<> ();
		multipleImagesParams.setClick (true);
		//斗技 头筹
		multipleImageParamList.add (new MultipleImageParam (contend_TC, paramRGB (process)));
		//斗技 战斗胜利
		multipleImageParamList.add (new MultipleImageParam (contend_ZDSL, paramRGB (process)));
		//斗技 失败
		multipleImageParamList.add (new MultipleImageParam (contend_ZDSB, paramRGB (process)));
		multipleImagesParams.setStart_time (start_num);
		multipleImagesParams.setEnd_time (end_num);
		multipleImagesParams.setMultipleImageParamList (multipleImageParamList);
		log.info ("战斗开始，等待" + begin_num + "秒");
		sleep (begin_num * 1000);
		log.info ("准备点击战斗胜利，点击头筹、战斗胜利，失败");
		String thisState = imagesBackList (multipleImagesParams);
		if (thisState.equals (contend_TC)) {
			log.info ("点击战斗胜利");
			ImageService.imagesBack (contend_ZDSL, paramRGB (process));
			log.info ("退出挑战");
		}
		log.info ("退出挑战完成");
		return true;
	}
	
	/***
	 * @description: 御魂强化
	 * @param num 次数
	 * @return: void
	 * @author: orcakill
	 * @date: 2023/3/3 22:59
	 */
	@Override
	public void soulEnhancements (String process, Integer num) throws InterruptedException, IOException, AWTException {
		log.info ("进入式神录");
		imagesBack (arrange_SSL, paramSIFT (process));
		sleep (2000);
		log.info ("进入式神录详细");
		imagesBack (arrange_XX, paramSIFT (process));
		sleep (2000);
		log.info ("进入式神录右侧御魂");
		imagesBack (arrange_YCYH_WDJ, paramSIFT (process));
		sleep (1000);
		log.info ("御魂整理开始");
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
		//循环强化速度御魂
		for (int i = 1; i <= num; i++) {
			log.info ("*************第{}个御魂", i);
			//设置强化状态
			reinforcementState = true;
			log.info ("进入更换御魂");
			imagesBack (arrange_GHYH, paramSIFT (process));
			sleep (1000);
			log.info ("进入整理");
			imagesBack (arrange_ZL_WDJ, paramSIFT (process));
			sleep (1000);
			log.info ("进入方案");
			imagesBack (arrange_FA_WDJ, paramSIFT (process));
			sleep (1000);
			log.info ("进入方案下强化");
			imagesBack (arrange_FAXQH_WDJ, paramSIFT (process));
			sleep (1000);
			log.info ("进入方案速度提升");
			imagesBack (arrange_FASDTS_WDJ, paramSIFT (process));
			sleep (1000);
			log.info ("排序");
			imagesBack (arrange_PX, paramSIFT (process));
			sleep (1000);
			log.info ("点击左上角御魂");
			imagesBack (arrange_ZSJYH, paramSIFT (process));
			sleep (1000);
			log.info ("进入强化");
			imagesBack (arrange_JRQH, paramSIFT (process));
			sleep (1000);
			log.info ("*****当前强化状态为true,开始强化");
			while (reinforcementState) {
				if (strengtheningTimes > 1) {
					log.info ("当前强化状态为true,继续强化");
				}
				if (strengtheningTimes == 1) {
					log.info ("第一次强化，强化+3,2个四星青吉鬼");
					quantityOfConsumableMaterials = 2;
				}
				if (strengtheningTimes == 2) {
					log.info ("第二次强化，强化+6,4个四星青吉鬼");
					quantityOfConsumableMaterials = 4;
				}
				if (strengtheningTimes == 3) {
					log.info ("第三次强化，强化+9,6个四星青吉鬼");
					quantityOfConsumableMaterials = 5;
				}
				if (strengtheningTimes == 4) {
					log.info ("第四次强化，强化+12,7个四星青吉鬼");
					quantityOfConsumableMaterials = 8;
				}
				if (strengtheningTimes == 5) {
					log.info ("第五次强化，强化+15,9个四星青吉鬼");
					quantityOfConsumableMaterials = 9;
				}
				log.info ("第{}次,点击{}个四星青吉鬼", strengtheningTimes, quantityOfConsumableMaterials);
				for (int j = 1; j <= quantityOfConsumableMaterials; j++) {
					log.info ("第{}个青吉鬼", j);
					boolean b = imagesBack (arrange_SXQJG, paramSIFT (process));
					if (!b) {
						log.info ("本次点击不成功，可能有悬赏封印，等待10秒后重新点击");
						Thread.sleep (10 * 1000);
						imagesBack (arrange_SXQJG, paramSIFT (process));
					}
					sleep (1000);
				}
				log.info ("御魂强化");
				imagesBack (arrange_QH, paramSIFT (process));
				log.info ("等级提升");
				levelPromotion = imagesBack (arrange_DJTS, paramSIFTNotClick (process, 5, 5));
				if (!levelPromotion) {
					log.info ("点击第一次可能存在的确定按钮");
					imagesBack (arrange_QD, paramSIFT (process, 1, 5));
					sleep (1000);
					imagesBack (arrange_DJTS, paramSIFTNotClick (process, 1, 5));
				}
				sleep (1000);
				log.info ("****开始判断御魂强化结果");
				soulSubduingEnhancementAttribute = soulLevelEnhancementRecognition (process);
				if (soulSubduingEnhancementAttribute == null) {
					log.info ("未找到御魂强化属性,重新判断");
					System.exit (0);
				}
				log.info ("御魂强化属性为{}", soulSubduingEnhancementAttribute);
				//如果强化属性为攻击、生命和防御，则直接弃置
				if ((
						    soulSubduingEnhancementAttribute.equals ("攻 击") ||
						    soulSubduingEnhancementAttribute.equals ("生 命") ||
						    soulSubduingEnhancementAttribute.equals ("防 御")) && strengtheningTimes < 4) {
					log.info ("御魂强化属性为{},直接弃置", soulSubduingEnhancementAttribute);
					log.info ("直接弃置");
					imagesBack (arrange_QZ, paramSIFT (process));
					sleep (1000);
					log.info ("确定强化结果，完全垃圾御魂，本轮强化结束");
					imagesBack (arrange_QD, paramSIFT (process));
					sleep (1000);
					log.info ("结束强化，强化状态置为false,强化次数置为1");
					reinforcementState = false;
					strengtheningTimes = 1;
				}
				else {
					if (strengtheningTimes == 1) {
						log.info ("第一次强化，记录强化属性，继续强化");
						soulSubduingEnhancementAttributeLast = soulSubduingEnhancementAttribute;
						log.info ("确定");
						imagesBack (arrange_QD, paramSIFT (process));
						log.info ("御魂强化次数+1");
						strengtheningTimes++;
						sleep (1000);
					}
					else {
						if (soulSubduingEnhancementAttribute.equals (soulSubduingEnhancementAttributeLast) ||
						    strengtheningTimes >= 4) {
							log.info ("御魂强化属性{}，御魂上次强化属性{}", soulSubduingEnhancementAttribute,
							          soulSubduingEnhancementAttributeLast);
							log.info ("御魂强化次数{}", strengtheningTimes);
							log.info ("御魂强化属性和上一次相同，或者御魂强化次数大于等4次");
							log.info ("确定强化结果，本轮强化结束,继续强化");
							imagesBack (arrange_QD, paramSIFT (process));
							sleep (1000);
							if (strengtheningTimes == 5) {
								log.info ("强化满级,本次强化结束");
								log.info ("结束强化，强化状态置为false,强化次数置为1");
								reinforcementState = false;
								strengtheningTimes = 1;
							}
							else {
								log.info ("强化未满级，进入下一轮");
								log.info ("御魂强化次数+1");
								strengtheningTimes++;
							}
						}
						else {
							log.info ("御魂强化属性{}，御魂上次强化属性{}", soulSubduingEnhancementAttribute,
							          soulSubduingEnhancementAttributeLast);
							log.info ("御魂强化次数{}", strengtheningTimes);
							log.info ("御魂强化属性和上一次不同,且强化次数小于4次");
							log.info ("弃置");
							imagesBack (arrange_QZ, paramSIFT (process));
							sleep (1000);
							log.info ("确定强化结果，本轮强化结束");
							imagesBack (arrange_QD, paramSIFT (process));
							sleep (1000);
							log.info ("结束强化，强化状态置为false,强化次数置为1");
							reinforcementState = false;
							strengtheningTimes = 1;
						}
					}
				}
			}
			log.info ("返回更换御魂");
			imagesBack (return_FH, paramSIFT (process));
			sleep (1000);
			log.info ("返回更换御魂");
			imagesBack (return_FH, paramSIFT (process));
			sleep (1000);
			log.info ("结束本次御魂强化");
		}
		log.info ("御魂强化结束");
		returnHome (process);
	}
	
	/***
	 * @description: 分割图片
	 * @param process  夜神模拟器
	 * @return: java.lang.String
	 * @author: orcakill
	 * @date: 2023/3/3 23:30
	 */
	@Override
	public String soulLevelEnhancementRecognition (String process) {
		//模拟器截屏处理
		BufferedImage Window = ScreenshotUtils.screenshotBack (process);
		//截图 1 2 3 的裁剪信息
		ScreenshotPointPO screenshotPointPO1 = ScreenshotPointMap.getScreenshotPoint ("御魂等级强化第一部分");
		ScreenshotPointPO screenshotPointPO2 = ScreenshotPointMap.getScreenshotPoint ("御魂等级强化第二部分");
		ScreenshotPointPO screenshotPointPO3 = ScreenshotPointMap.getScreenshotPoint ("御魂等级强化第三部分");
		//截图 1 2 3 裁剪
		BufferedImage bufferedImage1 =
				Window.getSubimage (screenshotPointPO1.getX (), screenshotPointPO1.getY (), screenshotPointPO1.getW (),
				                    screenshotPointPO1.getH ());
		BufferedImage bufferedImage2 =
				Window.getSubimage (screenshotPointPO2.getX (), screenshotPointPO2.getY (), screenshotPointPO2.getW (),
				                    screenshotPointPO2.getH ());
		BufferedImage bufferedImage3 =
				Window.getSubimage (screenshotPointPO3.getX (), screenshotPointPO3.getY (), screenshotPointPO3.getW (),
				                    screenshotPointPO3.getH ());
		//截图 1 2 3 图像文字识别
		String s1 = ImageTesseractUtils.findOCR (bufferedImage1, true).replaceAll ("(?m)^\\s*$(\\n|\\r\\n)", "");
		String s2 = ImageTesseractUtils.findOCR (bufferedImage2, false).replaceAll ("(?m)^\\s*$(\\n|\\r\\n)", "");
		String s3 = ImageTesseractUtils.findOCR (bufferedImage3, false).replaceAll ("(?m)^\\s*$(\\n|\\r\\n)", "");
		//识别结果 1  2 3 string 转 list
		List<String> list1 = Arrays.asList (s1.split ("\n"));
		List<String> list2 = Arrays.asList (s2.split ("\n"));
		List<String> list3 = Arrays.asList (s3.split ("\n"));
		list1.removeAll (Collections.singleton (null));
		list2.removeAll (Collections.singleton (null));
		list3.removeAll (Collections.singleton (null));
		//识别结果 1  2 3 识别出现等级变化的御魂属性
		if (list1.size () == 5 && list1.size () == list2.size () || list2.size () == list3.size ()) {
			for (int i = 1; i < 5; i++) {
				if (!list2.get (i).equals (list3.get (i))) {
					return list1.get (i);
				}
			}
		}
		return null;
	}
	
	/***
	 * @description: 探索
	 * @param num 次数
	 * @return: void
	 * @author: orcakill
	 * @date: 2023/3/3 23:49
	 */
	@Override
	public void explore (String process, Integer num) throws IOException, InterruptedException, AWTException {
		//右移界面数据准备
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment ().getDefaultScreenDevice ();
		int windows_width = gd.getDisplayMode ().getWidth ();
		int windows_height = gd.getDisplayMode ().getHeight ();
		PictureIdentifyWorkPO pictureIdentifyWorkPO = new PictureIdentifyWorkPO ();
		//boss状态
		boolean bossState;
		//小怪状态
		boolean littleMonsterState;
		//式神录状态
		boolean theStateOfStyleGodRecord;
		//宝箱
		boolean treasureChestState;
		//移动次数
		int numberOfMoves;
		//战斗次数
		int numberOfBattles = 0;
		//战斗结果
		boolean battleResults;
		log.info ("进入探索");
		imagesBack (home_TS, paramSIFT (process));
		log.info ("开始探索战斗");
		long start_time = System.currentTimeMillis ();
		for (int i = 1; i <= num; i++) {
			log.info ("*************第{}轮挑战开始", i);
			//初始化移动次数
			numberOfMoves = 0;
			log.info ("当前探索界面");
			log.info ("检查左侧是否有宝箱");
			treasureChestState = imagesBack (explore_ZCBX, paramRGB (process, 1));
			while (treasureChestState) {
				log.info ("有宝箱");
				imagesBack (explore_ZCBX, paramRGB (process, 1));
				sleep (1000);
				log.info ("点击退出挑战");
				imagesBack (explore_TCTZ, paramSIFT (process, 1));
				sleep (1000);
				treasureChestState = imagesBack (explore_ZCBX, paramRGBNotClick (process, 1));
			}
			sleep (1000);
			log.info ("探索");
			boolean lastChapter = imagesBack (explore_ZHYZ, paramRGB (process));
			while (!lastChapter) {
				log.info ("没找到探索,重新退回探索");
				returnExplore (process);
				lastChapter = imagesBack (explore_ZHYZ, paramRGB (process));
			}
			log.info ("最后一章");
			imagesBack (explore_TS, paramRGB (process));
			sleep (1000);
			if (i == 1) {
				log.info ("固定阵容");
				imagesBack (explore_GDZR, paramSIFT (process, 1, 4));
				log.info ("点击自动轮换");
				imagesBack (explore_ZDLH, paramSIFT (process, 1, 4));
			}
			log.info ("当前探索中,检查是否有BOSS");
			bossState = imagesBack (explore_BOSSZD, paramSIFT (process, 1, 4));
			while (!bossState) {
				log.info ("没有BOSS,寻找小怪并点击");
				littleMonsterState = imagesBack (explore_DTXGZD, paramSIFT (process, 1, 4));
				if (littleMonsterState) {
					sleep (1000);
					log.info ("检查是否未点击成功");
					theStateOfStyleGodRecord = imagesBack (explore_TSSSL, paramSIFTNotClick (process, 1, 4));
					if (theStateOfStyleGodRecord) {
						log.info ("小怪点击不成功");
						littleMonsterState = false;
					}
					else {
						sleep (8 * 1000);
						log.info ("退出挑战");
						battleResults = imagesBack (explore_TCTZ, paramSIFT (process, 5));
						if (battleResults) {
							numberOfBattles++;
						}
						imagesBack (explore_TCTZ, paramSIFT (process, 1, 5));
					}
					
				}
				if (!littleMonsterState) {
					log.info ("没有小怪，开始移动");
					numberOfMoves++;
					if (numberOfMoves >= 100) {
						log.info ("移动超100次，出现异常，退出探索");
						break;
					}
					log.info ("当前移动次数{}", numberOfMoves);
					boolean isItARecordOfStyleGods = imagesBack (explore_TSSSL, paramSIFTNotClick (process, 3, 4));
					if (isItARecordOfStyleGods) {
						log.info ("没找到小怪，且当前有式神录，移动位置,每20次改变移动方向，当前移动次数{},余数{}",
						          numberOfMoves,
						          Math.ceil (numberOfMoves / 20.0) % 2);
						if (Math.ceil (numberOfMoves / 10.0) % 2 == 1) {
							pictureIdentifyWorkPO.setX (windows_width / 8 * 7);
							pictureIdentifyWorkPO.setY (windows_height / 10 * 7);
						}
						else {
							pictureIdentifyWorkPO.setX (windows_width / 8);
							pictureIdentifyWorkPO.setY (windows_height / 10 * 7);
						}
						log.info ("点击坐标({},{})", pictureIdentifyWorkPO.getX (), pictureIdentifyWorkPO.getY ());
						MouseClickUtils.mouseClickBack (pictureIdentifyWorkPO, "夜神模拟器");
					}
					
				}
				sleep (1000);
				log.info ("小怪战斗结束，检查是否出现BOSS");
				bossState = imagesBack (explore_BOSSZD, paramSIFT (process, 1, 4));
			}
			log.info ("小怪战斗结束，boss战");
			sleep (6000);
			log.info ("退出挑战");
			battleResults = imagesBack (explore_TCTZ, paramSIFT (process));
			if (battleResults) {
				numberOfBattles++;
			}
			log.info ("再退出一次挑战");
			imagesBack (explore_TCTZ, paramSIFT (process, 1, 4));
			Thread.sleep (2000);
			log.info ("返回探索界面");
			returnExplore (process);
		}
		long hostTime = System.currentTimeMillis () - start_time;
		log.info ("************当前轮次,平均每次战斗用时{}秒", hostTime / numberOfBattles / 1000);
		returnHome (process);
	}
	
	/***
	 * @description: 探索-只打2个
	 * @param num 次数
	 * @return: void
	 * @author: orcakill
	 * @date: 2023/3/3 23:49
	 */
	@Override
	public void exploreFast (String process, Integer num) throws IOException, InterruptedException, AWTException {
		//小怪状态
		boolean littleMonsterState;
		//退出挑战
		boolean exitTheChallenge;
		//战斗次数
		int numberOfBattles = 0;
		log.info ("进入探索");
		imagesBack (home_TS, paramSIFT (process));
		log.info ("开始探索战斗");
		long start_time = System.currentTimeMillis ();
		for (int i = 1; i <= num; i++) {
			log.info ("*************第{}轮挑战开始", i);
			log.info ("当前探索界面");
			log.info ("探索");
			imagesBack (explore_ZHYZ, paramRGB (process));
			log.info ("最后一章");
			imagesBack (explore_TS, paramRGB (process));
			sleep (1000);
			log.info ("当前探索中,检查是否有小怪，打2个静止不动的即可");
			log.info ("寻找小怪,点击战斗");
			littleMonsterState = imagesBack (explore_JTXGZD, paramRGB (process, 1));
			while (littleMonsterState) {
				log.info ("点击成功，等待结束");
				sleep (11 * 1000);
				log.info ("退出挑战");
				exitTheChallenge = imagesBack (explore_TCTZ, paramRGB (process, 10));
				if (!exitTheChallenge) {
					log.info ("未成功退出挑战，重新退出");
					imagesBack (explore_TCTZ, paramRGB (process, 10));
					sleep (1000);
				}
				numberOfBattles++;
				log.info ("小怪战斗结束，检查是否还有小怪");
				littleMonsterState = imagesBack (explore_JTXGZD, paramRGB (process, 1));
			}
			log.info ("再退出一次挑战，防止退出不成功");
			imagesBack (explore_TCTZ, paramRGB (process, 1));
			log.info ("小怪战斗结束，退出");
			returnExplore (process);
		}
		long hostTime = System.currentTimeMillis () - start_time;
		if (numberOfBattles > 0) {
			log.info ("************当前轮次,平均每次战斗用时{}秒", hostTime / numberOfBattles / 1000);
		}
		returnHome (process);
	}
	
	/***
	 * @description: 地域鬼王
	 * @param process  进程
	 * @param gameUserId   账号ID
	 * @return: void
	 * @author: orcakill
	 * @date: 2023/3/30 9:24
	 */
	@Override
	public void regionalGhostKing (String process, String gameUserId)
			throws IOException, InterruptedException, AWTException {
		log.info ("进入探索");
		imagesBack (explore_TS, paramSIFT (process, 1, 4));
		log.info ("进入地域鬼王");
		log.info ("点击左侧，判断是否还有未挑战");
		log.info ("判断是否有已挑战-鸟巢");
		log.info ("判断是否有已挑战-黄鹤楼");
		log.info ("判断是否有已挑战-少林寺藏经阁");
		log.info ("判断是否有已挑战-丹霞山");
		log.info ("总结当前状态，是否有未挑战，已挑战那些");
		log.info ("点击筛选");
		log.info ("判断是否有筛选-鸟巢");
		log.info ("判断是否有筛选-黄鹤楼");
		log.info ("判断是否有筛选-少林寺藏经阁");
		log.info ("判断是否有筛选-丹霞山");
		log.info ("点击未挑战");
		log.info ("根据账号类型，大号挑战极地域鬼王");
		log.info ("根据账号类型，判断是否挑战普通鬼王，需要将等级置为1级");
		log.info ("挑战");
		log.info ("退出挑战");
		log.info ("退出鬼王页面，重新判断当前状态");
		log.info ("已完成地域鬼王挑战，返回首页");
		
	}
	
	private void returnExplore (String process) throws IOException, InterruptedException, AWTException {
		boolean exploreState = imagesBack (soul_Icon, paramSIFTNotClick (process, 1, 4));
		while (!exploreState) {
			log.info ("未退出探索，点击返回");
			imagesBack (return_FH, paramRGB (process, 1));
			log.info ("点击可能出现的确认");
			imagesBack (explore_QR, paramSIFT (process, 1, 4));
			log.info ("点击可能出现的退出挑战");
			imagesBack (soul_TCTZ, paramSIFT (process, 1, 4));
			log.info ("判断是否是探索界面");
			exploreState = imagesBack (soul_Icon, paramSIFTNotClick (process, 1, 4));
		}
		log.info ("当前已是探索界面");
	}
	
}
