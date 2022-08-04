package com.example.controller;

import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class ThreadSecondController extends Thread {
	public  static  final Logger logger = LogManager.getLogger ("ThreadSecondController");
	
	@SneakyThrows
	public void run () {
		logger.info ("1 阴阳寮结界+个人结界+魂土40次");
		logger.info ("2 个人结界+魂土60次");
		logger.info ("3 个人结界+业原火100张");
		logger.info ("4 个人结界+日轮之陨50次");
		logger.info ("5 个人结界+永生之海30次");
		logger.info ("6 御灵60次");
		logger.info ("7 斗技30次");
		logger.info ("8 为崽而战30次");
		logger.info ("9 小号刷大号协战");
		Scanner scanner=new Scanner(System.in);
		logger.info ("输入一个轮次");
		int a=scanner.nextInt();//输入一个轮次
		logger.info ("输入一个选项");
		int b=scanner.nextInt();//输入一个选项
		//  进入登录界面
		
     	//	默认大号登录，选项为9则启用小号登录，小号登录
	}
}
