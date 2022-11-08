package com.example.service;

import com.example.model.enums.ArrangeEnums;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.awt.*;
import java.io.IOException;

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
	public void FightTest1 () throws IOException, InterruptedException, AWTException {
		logger.info ("御魂整理开始");
		//御魂强化属性
		String soulSubduingEnhancementAttribute=null;
		//强化状态
		boolean reinforcementState=true;
		//强化次数
		int     strengtheningTimes=1;
		//消耗材料数量
		int     quantityOfConsumableMaterials=0;
		if (b1) {
		      //循环强化速度御魂
		      for(int i=1;i<=2;i++){
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
				  while (!reinforcementState){
					  if(strengtheningTimes==1){
						 
					  }
					  logger.info ("结束强化");
					  reinforcementState=false;
				  }
				  System.exit (0);
		      }
		}
	}
}
