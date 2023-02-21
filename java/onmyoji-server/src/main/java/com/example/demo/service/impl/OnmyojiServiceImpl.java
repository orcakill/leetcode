package com.example.demo.service.impl;

import cn.hutool.core.util.IdUtil;
import com.example.demo.model.entity.GameThreadPO;
import com.example.demo.model.entity.PictureIdentifyWorkPO;
import com.example.demo.model.param.MultipleImageParam;
import com.example.demo.model.param.MultipleImagesParam;
import com.example.demo.model.thread.FirstThread;
import com.example.demo.model.thread.SecondThread;
import com.example.demo.service.GameThreadService;
import com.example.demo.service.ImageService;
import com.example.demo.service.OnmyojiService;
import com.example.demo.utils.MouseClickUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import static com.example.demo.model.enums.GameEnum.*;
import static com.example.demo.model.param.ImageRecParam.*;

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
	public void onmyojiService (Integer type, Integer round) throws InterruptedException, UnknownHostException {
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
		//运行线程
		SecondThread t1 = new SecondThread ();
		t1.setType (type);
		t1.setRound (round);
		t1.setThreadId (threadId);
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
	public void autoActivity (Integer type, Integer round) throws IOException, InterruptedException, AWTException {
		
		for (int i = 0; i < round; i++) {
			
			//大号  阴阳寮突破+个人突破+魂十一40次+地域鬼王（每日一次）
			if (type == 1) {
				//  当前状态初始化，进入角色首页
				initializationState ("1");
				//  寄养检查（+体力领取+经验领取+更换式神），优先六星、五星、四星太鼓，其次六星、五星、四星斗鱼
				
				//  阴阳寮突破
				//  个人突破
				//  魂十一（注意喂食宠物）
				//  地域鬼王+领取花合战每日奖励，无未攻打则跳过
				//  好友添加、好友删除、赠送小号红心、赠送其他人红心（待定）
			}
		}
		
	}
	
	@Override
	public void initializationState (String userId) throws IOException, InterruptedException, AWTException {
		boolean initializeOrNot = false;
		String thisPicture;//当前状态
		boolean announcementOrNot;//是否公告
		boolean promptForAge = false;//是否适龄提示
		boolean targetHomePage = false;
		boolean switchAccount=false;
		while (!initializeOrNot) {
			thisPicture = thisState ();
			log.info ("当前状态{}", thisPicture);
			if (userId.equals ("1")) {
				if (thisPicture.equals (home_YH_NJZR.getValue ())) {
					targetHomePage = true;
				}
			}
			if (userId.equals ("2")) {
				if (thisPicture.equals (home_YH_ORCAKILL.getValue ())) {
					targetHomePage = true;
				}
			}
			if(thisPicture.equals (return_FH.getValue ())){
				log.info ("先返回到首页");
				returnHome ();
				log.info ("重新判断当前状态");
				continue;
			}
			if (!targetHomePage) {
				//  当前页面阴阳师图标，需要点击应用图标->跳过登录动画->关闭公告->适龄提示
				if (thisPicture.equals (home_YYSTB.getValue ())) {
					log.info ("点击阴阳师图标");
					ImageService.imagesBack (home_YYSTB.getValue (), paramRGB);
					while (!promptForAge) {
						Thread.sleep (15000);
						log.info ("单击一下，防止有开场动画");
						MouseClickUtils.mouseClickBack (new PictureIdentifyWorkPO (500, 500), "夜神模拟器");
						Thread.sleep (1000);
						log.info ("判断是否有公告需要返回");
						announcementOrNot = ImageService.imagesBack (return_FH.getValue (), paramRGB);
						if (announcementOrNot) {
							log.info ("有公告");
							ImageService.imagesBack (return_FH.getValue (), paramSIFT);
							Thread.sleep (1000);
						}
						promptForAge = ImageService.imagesBack (login_SLTS.getValue (), paramSIFTNotClick);
						if (promptForAge) {
							log.info ("当前页面有适龄提示");
						}
					}
				}
				// 当前页面非目标首页
				if (thisPicture.equals (home_YH_NJZR.getValue ())) {
					log.info ("点击用户头像");
					ImageService.imagesBack (home_YH_NJZR.getValue (), paramSIFT (20));
					log.info ("点击用户中心");
					ImageService.imagesBack (home_TXYHZX.getValue (), paramSIFT (20));
				}
				if (thisPicture.equals (home_YH_ORCAKILL.getValue ())) {
					log.info ("点击用户头像");
					ImageService.imagesBack (home_YH_ORCAKILL.getValue (), paramSIFT (20));
					log.info ("点击用户中心");
					ImageService.imagesBack (home_TXYHZX.getValue (), paramSIFT (20));
				}
				// 当前页面适龄提示->点击用户中心
				if (thisPicture.equals (login_SLTS.getValue ()) || promptForAge) {
					log.info ("点击头像用户中心");
					ImageService.imagesBack (login_YHZX.getValue (), paramSIFT);
				}
				log.info ("判断是否切换账号");
				switchAccount=ImageService.imagesBack (login_QHZH.getValue (),paramSIFTNotClick);
				if(switchAccount){
					log.info ("切换用户和大区并登录到首页");
					login (userId);
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
	public String thisState () throws IOException, InterruptedException, AWTException {
		// 图像集
		MultipleImagesParam multipleImagesParams = new MultipleImagesParam ();
		List<MultipleImageParam> multipleImageParamList = new ArrayList<> ();
		//桌面       阴阳师图标
		multipleImageParamList.add (new MultipleImageParam (login_YYSTB.getValue (), paramRGB));
		//登录界面    适龄提示
		multipleImageParamList.add (new MultipleImageParam (login_SLTS.getValue (), paramRGB));
		//大号首页    缥缈之旅 逆戟之刃
		multipleImageParamList.add (new MultipleImageParam (home_YH_NJZR.getValue (), paramSIFT (20)));
		//小号1首页   缥缈之旅 orcakill
		multipleImageParamList.add (new MultipleImageParam (home_YH_ORCAKILL.getValue (), paramSIFT (20)));
		//小号2首页   两情相悦  洪荒的修罗
		//小号3首页   桃映春馨  炽热的惆怅物语
		//返回       返回
		multipleImageParamList.add (new MultipleImageParam (return_FH.getValue (), paramRGB));
		multipleImageParamList.add (new MultipleImageParam (return_FH.getValue (), paramSIFT (4)));
		multipleImagesParams.setClick (false);
		multipleImagesParams.setMultipleImageParamList (multipleImageParamList);
		return ImageService.imagesBackList (multipleImagesParams);
	}
	
	@Override
	public void returnHome () throws IOException, InterruptedException, AWTException {
		int num=1;
		log.info ("判断是否首页");
		boolean homePageOrNot=ImageService.imagesBack (home_TS.getValue (),paramSIFTNotClick(1,20));
		while (!homePageOrNot){
			log.info ("开始返回到首页");
			ImageService.imagesBack (return_FH.getValue (),paramRGB(1));
			if(num>20){
				ImageService.imagesBack (return_FH.getValue (),paramSIFT(1,20));
				num=1;
			}
			num++;
			homePageOrNot=ImageService.imagesBack (home_TS.getValue (),paramSIFTNotClick (1,20));
		}
		Thread.sleep (2000);
		log.info ("判断底部菜单是否打开");
		boolean bottomMenu=ImageService.imagesBack (home_DBCD.getValue (),paramSIFTNotClick (1,20));
		if(bottomMenu){
			log.info ("打开底部菜单");
			ImageService.imagesBack (home_DBCD.getValue (),paramSIFT(3,20));
		}
		log.info ("返回首页完成");
	}
	
	@Override
	public void login (String gameUserId) throws IOException, InterruptedException, AWTException {
		log.info ("切换账号");
		ImageService.imagesBack (login_QHZH.getValue (), paramSIFT);
		log.info ("常用");
		ImageService.imagesBack (login_CY.getValue (), paramTM_SQDIFF_NORMED (3E-10));
		log.info ("选择账号");
		if (gameUserId != null) {
			if (gameUserId.equals ("1")) {
				log.info ("手机号178");
				ImageService.imagesBack (login_XZZH_PHONE1.getValue (), paramTM_SQDIFF_NORMED (2E-11));
			}
			if (gameUserId.equals ("2")) {
				log.info ("邮箱号1");
				ImageService.imagesBack (login_YHZX_EMAIIL1.getValue (), paramTM_SQDIFF_NORMED (2E-11));
			}
		}
		log.info ("登录");
		ImageService.imagesBack (login_DL.getValue (), paramSIFT);
		log.info ("切换服务器");
		ImageService.imagesBack (login_QH.getValue (), paramSIFT);
		log.info ("点击小三角");
		ImageService.imagesBack (login_XSJ.getValue (), paramRGB);
		if (gameUserId != null) {
			if (gameUserId.equals ("1")) {
				log.info ("点击大号角色-缥缈之旅");
				ImageService.imagesBack (login_FWQ_PMZL.getValue (), paramRGB);
			}
		}
		log.info ("开始游戏");
		ImageService.imagesBack (login_KSYX.getValue (), paramTM_SQDIFF_NORMED (3E-10));
		Thread.sleep (5 * 1000L);
		log.info ("底部菜单栏");
		ImageService.imagesBack (home_DBCD.getValue (), paramRGB);
		Thread.sleep (2 * 1000L);
	}
	
	@Override
	
	public void toFoster () throws InterruptedException, IOException, AWTException {
		
		String fileJJK = house_JJK_TG_LXTG.getValue();
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
		log.info ("进入阴阳寮");
		ImageService.imagesBack (house_YYLTB.getValue(),paramSIFT);
		Thread.sleep (2000);
		log.info ("进入结界");
		ImageService.imagesBack  (house_JJ.getValue(),paramSIFT);
		Thread.sleep (2000);
		log.info ("进入式神育成");
		ImageService.imagesBack (house_SSYC.getValue(),paramSIFT);
		Thread.sleep (2000);
		log.info ("判断是否可寄养");
		booleanKJY = ImageService.imagesBack (house_KJY.getValue(), paramRGBNotClick(5));
		if (booleanKJY) {
			log.info ("进入可寄养");
			ImageService.imagesBack (house_HYBT.getValue(),paramRGB);
			Thread.sleep (3000);
			log.info ("已进入好友列表");
			pictureIdentifyWorkPO1 = ImageService.imagesBackGetCoordinate (house_HYBT.getValue (), paramRGB);
			pictureIdentifyWorkPO2.setX ((int) (pictureIdentifyWorkPO1.getX () * 1.3));
			pictureIdentifyWorkPO2.setY ((int) (pictureIdentifyWorkPO1.getY () * 2.3));
			pictureIdentifyWorkPO3.setX ((int) (pictureIdentifyWorkPO1.getX () * 1.3));
			pictureIdentifyWorkPO3.setY ((int) (pictureIdentifyWorkPO1.getY () * 1.6));
			pictureIdentifyWorkPO4.setX (pictureIdentifyWorkPO1.getX ());
			pictureIdentifyWorkPO4.setY ((int) (pictureIdentifyWorkPO1.getY () * 0.5));
			//开循环，0 六星太鼓 1 五星太鼓 2 四星太鼓 3 六星斗鱼 4 五星斗鱼 5 四星斗鱼
			while (!booleanJSJY) {
				log.info ("当前好友结界卡是否未放置");
				booleanWFZ = ImageService.imagesBack (house_WFZ.getValue(), paramRGB);
				if (booleanWFZ) {
					log.info ("好友未放置结界卡，退出后重新进入");
					if (num == 0) {
						fileJJK = house_JJK_TG_WXTG.getValue();
					}
					if (num == 1) {
						fileJJK = house_JJK_TG_SXTG.getValue();
					}
					if (num == 2) {
						fileJJK = house_JJK_DY_LXDY.getValue();
					}
					if (num == 3) {
						fileJJK = house_JJK_DY_WXDY.getValue() ;
					}
					if (num == 4) {
						fileJJK = house_JJK_DY_SXDY.getValue();
					}
					if (num > 4) {
						log.info ("无高星结界卡，开始退出");
						break;
					}
					num++;
					log.info ("退出到可寄养界面");
					MouseClickUtils.mouseClickBack (pictureIdentifyWorkPO4, "夜神模拟器");
					log.info ("重新进入好友列表");
					ImageService.imagesBack (house_KJY.getValue(),paramRGB);
				}
				//else {
				//	log.info ("存在结界卡，判断是否是高星结界卡");
				//	booleanJJK = ImageService.imagesBack (fileJJK, paramRGB(2));
				//	if (!booleanJJK) {
				//		log.info ("当前结界不是" + fileJJK + ",不可放置,滚动到下一个");
				//		ImageService.imagesClickBackDrag (pictureIdentifyWorkPO2, pictureIdentifyWorkPO3, "夜神模拟器");
				//		log.info ("滚动完成,点击终止位置坐标");
				//		MouseClick.mouseClickBack (pictureIdentifyWorkPO3, "夜神模拟器");
				//	}
				//	else {
				//		log.info ("存在高星结界卡" + fileJJK);
				//		ImageService.imagesClickBack (house_JRJJ.getValue());
				//		log.info ("进入结界");
				//		ImageService.imagesClickBack (house_DJDM.getValue());
				//		log.info ("大吉达摩寄养");
				//		ImageService.imagesClickBack (comm_QR.getValue());
				//		log.info ("确认");
				//		log.info ("检查是否寄养成功");
				//		ImageService.imagesClickBack (return_FH.getValue());
				//		ImageService.imagesClickBack (return_FH.getValue());
				//		log.info ("返回到式神育成");
				//		ImageService.imagesClickBack (house_SSYC.getValue());
				//		log.info ("进入育成界面");
				//		booleanJSJY = ImageService.imagesClickBack (house_KJY.getValue(), 3);
				//		if (booleanJSJY) {
				//			log.info ("寄养失败，重新进入好友列表");
				//			ImageService.imagesClickBack (house_KJY.getValue());
				//		}
				//		else {
				//			log.info ("寄养成功");
				//			booleanJSJY = true;
				//		}
				//	}
				//}
				
			}
		}
		//退出
		returnHome ();
	}
	
}
