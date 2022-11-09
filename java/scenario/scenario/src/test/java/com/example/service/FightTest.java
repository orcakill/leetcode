package com.example.service;

import com.example.model.enums.ArrangeEnums;
import com.example.model.enums.BackEnums;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @Classname FightTest
 * @Description TODO
 * @Date 2022/11/7 22:57
 * @Created by orcakill
 */
public class FightTest {
	public static final Logger logger = LogManager.getLogger ("FightTest");
	
	public static boolean b1 = true;//是否开启测试
	
	//御魂强化-提升速度
	@Test
	public void FightTest1 () throws  InterruptedException, AWTException {
		logger.info ("御魂整理开始");
		//御魂强化属性
		String soulSubduingEnhancementAttribute;
		//御魂强化属性
		String soulSubduingEnhancementAttributeLast=null;
		//强化状态
		boolean reinforcementState;
		//等级提升状态
		boolean levelPromotion;
		//更换御魂状态
		boolean changeTheSoulState;
		//强化次数
		int     strengtheningTimes=1;
		//消耗材料数量
		int     quantityOfConsumableMaterials=0;
		//御魂强化属性结果
		Map<String,String> strengthenResultSet=new HashMap<> ();
		//初始化御魂强化结果
		strengthenResultSet.put ("速度",ArrangeEnums.arrange_YHQHSX_SD.getValue ());
		strengthenResultSet.put ("效果命中",ArrangeEnums.arrange_YHQHSX_XGMZ.getValue ());
		strengthenResultSet.put ("效果抵抗",ArrangeEnums.arrange_YHQHSX_XGDK.getValue ());
		strengthenResultSet.put ("暴击",ArrangeEnums.arrange_YHQHSX_BJ.getValue ());
		strengthenResultSet.put ("暴击伤害",ArrangeEnums.arrange_YHQHSX_BJSH.getValue ());
		strengthenResultSet.put ("攻击",ArrangeEnums.arrange_YHQHSX_GJ.getValue ());
		strengthenResultSet.put ("攻击加成",ArrangeEnums.arrange_YHQHSX_GJJC.getValue ());
		strengthenResultSet.put ("生命",ArrangeEnums.arrange_YHQHSX_SM.getValue ());
		strengthenResultSet.put ("生命加成",ArrangeEnums.arrange_YHQHSX_SMJC.getValue ());
		strengthenResultSet.put ("防御",ArrangeEnums.arrange_YHQHSX_FY.getValue ());
		strengthenResultSet.put ("防御加成",ArrangeEnums.arrange_YHQHSX_FYJC.getValue ());
		if (b1) {
		      //循环强化速度御魂
		      for(int i=1;i<=1;i++){
				  //设置强化状态
			      reinforcementState=true;
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
				  while (reinforcementState){
					  if(strengtheningTimes>1){
						  logger.info ("当前强化状态为true,继续强化");
					  }
					  if(strengtheningTimes==1){
						  logger.info ("第一次强化，强化+3,2个四星青吉鬼");
						  quantityOfConsumableMaterials=2;
					  }
					  if(strengtheningTimes==2){
						  logger.info ("第二次强化，强化+6,4个四星青吉鬼");
						  quantityOfConsumableMaterials=4;
					  }
					  if(strengtheningTimes==3){
						  logger.info ("第三次强化，强化+9,5个四星青吉鬼");
						  quantityOfConsumableMaterials=5;
					  }
					  if(strengtheningTimes==4){
						  logger.info ("第四次强化，强化+12,9个四星青吉鬼");
						  quantityOfConsumableMaterials=9;
					  }
					  if(strengtheningTimes==5){
						  logger.info ("第五次强化，强化+15,12个四星青吉鬼");
						  quantityOfConsumableMaterials=12;
					  }
					  logger.info ("第{}次,点击{}个四星青吉鬼",strengtheningTimes,quantityOfConsumableMaterials);
					  for(int j=1;j<=quantityOfConsumableMaterials;j++){
						  ImageService.imagesClickBack (ArrangeEnums.arrange_SXQJG.getValue ());
						  Thread.sleep (1000);
					  }
					  logger.info ("御魂强化");
					  ImageService.imagesClickBack (ArrangeEnums.arrange_QH.getValue ());
					  logger.info ("等级提升");
					  levelPromotion=ImageService.imagesClickBackIsEmpty (ArrangeEnums.arrange_DJTS.getValue (),5);
					  while (!levelPromotion){
						  Thread.sleep (1000);
						  levelPromotion=ImageService.imagesClickBackIsEmpty (ArrangeEnums.arrange_DJTS.getValue (),5);
					  }
					  Thread.sleep (1000);
					  logger.info ("****开始判断御魂强化结果");
					  soulSubduingEnhancementAttribute=ImageService.imagesClickBack(strengthenResultSet,2,4);
					  if(soulSubduingEnhancementAttribute==null){
						  logger.info ("未找到御魂强化属性，程序退出");
						  System.exit (0);
					  }
					  //如果强化属性为攻击、生命和防御，则直接弃置
					  if((soulSubduingEnhancementAttribute.equals ("攻击")||soulSubduingEnhancementAttribute.equals ("生命")||
					     soulSubduingEnhancementAttribute.equals ("防御"))&&strengtheningTimes <4){
						  logger.info ("御魂强化属性为{},直接弃置",soulSubduingEnhancementAttribute);
						  logger.info ("弃置");
						  ImageService.imagesClickBack(ArrangeEnums.arrange_QZ.getValue ());
						  Thread.sleep (1000);
						  logger.info ("确定强化结果，本轮强化结束");
						  ImageService.imagesClickBack(ArrangeEnums.arrange_QD.getValue ());
						  Thread.sleep (1000);
						  logger.info ("结束强化，强化状态置为false,强化次数置为1");
						  reinforcementState=false;
						  strengtheningTimes=1;
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
								  logger.info ("御魂强化属性和上一次相同，或者御魂强化次数大于等4次");
								  logger.info ("确定强化结果，本轮强化结束");
								  ImageService.imagesClickBack (ArrangeEnums.arrange_QD.getValue ());
								  Thread.sleep (1000);
								  if (strengtheningTimes == 5) {
									  logger.info ("强化满级,本次强化结束");
									  logger.info ("结束强化，强化状态置为false,强化次数置为1");
									  reinforcementState = false;
									  strengtheningTimes = 1;
								  }
								  else{
									  logger.info ("强化未满级，进入下一轮");
								  }
								  logger.info ("御魂强化次数+1");
								  strengtheningTimes++;
								  
							  }
							  else {
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
			      changeTheSoulState=ImageService.imagesClickBackIsEmpty (BackEnums.back.getValue ());
			      Thread.sleep (1000);
			      while (changeTheSoulState){
				      changeTheSoulState=ImageService.imagesClickBack (BackEnums.back.getValue (),5);
			      }
			      logger.info ("结束本次御魂强化");
		      }
		}
	}
}
