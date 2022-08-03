package com.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class App{
    public  static  final Logger logger = LogManager.getLogger (App.class);
    
    public static void main(String[] args) {
        System.out.println(ClassLoader.getSystemResource("log4j.properties"));
        System.out.println(ClassLoader.getSystemResource("log4j.xml"));
        System.out.println (logger.getLevel ().toString ());
        logger.info ("1 阴阳寮结界+个人结界+魂土40次");
        logger.debug ("1 阴阳寮结界+个人结界+魂土40次");
        logger.error ("1 阴阳寮结界+个人结界+魂土40次");
        logger.warn ("1 阴阳寮结界+个人结界+魂土40次");
        logger.info ("2 个人结界+魂土60次");
        logger.info ("3 个人结界+业原火100张");
        logger.info ("4 个人结界+日轮之陨50次");
        logger.info ("5 个人结界+永生之海30次");
        logger.info ("6 御灵60次");
        logger.info ("7 斗技30次");
        logger.info ("8 为崽而战30次");
        Scanner scanner=new Scanner(System.in);
        int a=scanner.nextInt();//输入一个轮次
        int b=scanner.nextInt();//输入一个选项
        System.out.println (a);
        System.out.println (b);
    }

}
