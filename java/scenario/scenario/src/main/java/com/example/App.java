package com.example;

import com.example.controller.ThreadFirstController;
import com.example.controller.ThreadSecondController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.opencv.core.Core;

import java.util.Scanner;

public class App{
    //App日志
    public  static  final Logger logger = LogManager.getLogger ("App");
    //执行进程
    public  static   int ThreadSecondNumber=1;
    //进程结束标志
    public  static  boolean ThreadSecondIsEnd=false;

    
    public static void main(String[] args) throws InterruptedException {
        long startTime=System.currentTimeMillis ();
	    System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
        logger.info ("1 阴阳寮结界+个人结界+魂土40次");
        logger.info ("2 个人结界+魂土60次");
        logger.info ("3 阴阳寮结界+个人结界+业原火40张");
        logger.info ("4 个人结界+日轮之陨50次");
        logger.info ("5 个人结界+永生之海30次");
        logger.info ("6 御灵60次");
        logger.info ("7 斗技10次");
        logger.info ("8 当前活动战斗");
        logger.info ("9 小号刷大号协战");
        logger.info ("10 阴阳寮结界");
        logger.info ("11 阴阳寮结界+斗技5次");
        logger.info ("12 御魂整理-速度强化-极限副属性");
        logger.info ("13 个人结界+探索20次(全打)");
        logger.info ("14 个人结界+一轮探索60次(极限速度)");
        Scanner scanner = new Scanner (System.in);
        logger.info ("输入一个轮次");
        int a = scanner.nextInt ();//输入一个轮次
        logger.info ("输入一个选项");
        int b = scanner.nextInt ();//输入一个选项
        ThreadFirstController t = new ThreadFirstController ();
        ThreadSecondController t1 = new ThreadSecondController (a,b);
        ThreadSecondController t2 = new ThreadSecondController (a,b);
        ThreadSecondController t3 = new ThreadSecondController (a,b);
        logger.info ("启动监控线程");
        t.start();
        while (ThreadSecondNumber<=4){
            if(ThreadSecondNumber==1 && !t1.isAlive ()&&!ThreadSecondIsEnd){
                logger.info ("启动线程1");
                t1.start();
            }
            if(ThreadSecondNumber==2&&t1.isAlive ()&&!ThreadSecondIsEnd){
                logger.info ("启动线程2");
                t1.interrupt ();
                t2.start();
            }
            if(ThreadSecondNumber==3&&t2.isAlive ()&&!ThreadSecondIsEnd){
                logger.info ("启动线程3");
                t2.interrupt ();
                t3.start();
            }
            if(ThreadSecondIsEnd){
                logger.info ("结束监控线程");
                t.interrupt ();
                break;
            }
            Thread.sleep (60*1000);
        }
        long endTime=System.currentTimeMillis ();
        long difference=endTime-startTime;
        logger.info ("运行时间为{}分/{}秒",difference/1000/60,difference/1000);
        
    }
    
}
