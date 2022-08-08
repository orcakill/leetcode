package com.example.service;

import com.example.service.impl.FightServiceImpl;

import java.awt.*;

/**
 * @Classname FigthService
 * @Description TODO
 * @Date 2022/8/5 23:03
 * @Created by orcakill
 */
public interface FightService {
	
	static boolean fightEnd (Integer begin_num,Integer start_num,Integer end_num) throws InterruptedException, AWTException {
		return FightServiceImpl.fightEnd (begin_num,start_num,end_num);
	}
	
	static void soulBack(Integer begin_num,Integer num) throws InterruptedException, AWTException {
		FightServiceImpl.soulBack(begin_num,num);
	}
	
	static void returnHome() throws InterruptedException, AWTException {
		FightServiceImpl.returnHome() ;
	}
	
	static boolean fightEndPVP (Integer begin_num,Integer start_num,Integer end_num) throws InterruptedException, AWTException {
		return FightServiceImpl.fightEndPVP (begin_num,start_num,end_num);
	}
	
}
