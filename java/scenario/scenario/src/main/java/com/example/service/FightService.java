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
	
	static boolean fightEnd () throws InterruptedException, AWTException {
		return FightServiceImpl.fightEnd ();
	}
	
	static void soulBack(Integer num) throws InterruptedException, AWTException {
		FightServiceImpl.soulBack(num);
	}
}
