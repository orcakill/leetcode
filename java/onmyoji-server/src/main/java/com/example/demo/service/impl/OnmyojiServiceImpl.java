package com.example.demo.service.impl;

import cn.hutool.core.util.IdUtil;
import com.example.demo.model.entity.GameThreadPO;
import com.example.demo.service.GameThreadService;
import com.example.demo.service.OnmyojiService;
import com.example.demo.thread.FirstThread;
import com.example.demo.thread.SecondThread;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * @Classname OnmyojiServiceImpl
 * @Description 阴阳师自动化实现类
 * @Date 2023/1/25 20:59
 * @Created by orcakill
 */
@Service
public class OnmyojiServiceImpl implements OnmyojiService {
  public static final Logger logger = LogManager.getLogger ("OnmyojiServiceImpl");
  private final GameThreadService gameThreadService;

  public OnmyojiServiceImpl(GameThreadService gameThreadService) {
    this.gameThreadService = gameThreadService;
  }

  @Override
  public void onmyojiService(Integer round,Integer number) throws InterruptedException {
    //游戏进程id,游戏进程信息保存
    String threadId = IdUtil.objectId();
    logger.info ("进程信息初始化开始");
    GameThreadPO gameThreadPO=new GameThreadPO ();
    gameThreadPO.setId (threadId);
    gameThreadPO.setThreadState (0);
    gameThreadPO.setThreadNumber (1);
    gameThreadService.save (gameThreadPO);
    logger.info ("进程信息初始化结束");
    //监控线程
    FirstThread t= new FirstThread(gameThreadService);
    t.setThreadId(threadId);
    //运行线程
    SecondThread t1=new SecondThread (gameThreadService);
    t1.setA (round);
    t1.setB (number);
    SecondThread t2=new SecondThread (gameThreadService);
    t2.setA (round);
    t2.setB (number);
    SecondThread t3=new SecondThread (gameThreadService);
    t3.setA (round);
    t3.setB (number);
    logger.info ("启动监控线程");
    t.start();
    for (int i=0;i<3600;i++){
      gameThreadPO=gameThreadService.findById (threadId);
      int ThreadSecondNumber=gameThreadPO.getThreadNumber ();
      int ThreadSecondIsEnd=gameThreadPO.getThreadState ();
      if(ThreadSecondNumber==1 && !t1.isAlive ()&&ThreadSecondIsEnd==0){
        logger.info ("启动线程1");
        t1.start();
        gameThreadPO.setThreadState (1);
        gameThreadPO.setThreadNumber (1);
      }
      if(ThreadSecondNumber==2&&t1.isAlive ()&&ThreadSecondIsEnd==1){
        logger.info ("启动线程2");
        t1.interrupt ();
        t2.start();
        gameThreadPO.setThreadNumber (2);
      }
      if(ThreadSecondNumber==3&&t2.isAlive ()&&ThreadSecondIsEnd==1){
        logger.info ("启动线程3");
        t2.interrupt ();
        t3.start();
        gameThreadPO.setThreadNumber (3);
      }
      if(ThreadSecondIsEnd==2){
        logger.info ("结束监控线程");
        t.interrupt ();
        break;
      }
      gameThreadService.save (gameThreadPO);
      //等待一分钟
      Thread.sleep (60*1000);
    }
  }
}
