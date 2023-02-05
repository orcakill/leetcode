package com.example.demo.service.impl;

import cn.hutool.core.util.IdUtil;
import com.example.demo.model.entity.GameThreadPO;
import com.example.demo.model.entity.PictureIdentifyWorkPO;
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
import java.util.ArrayList;
import java.util.List;

import static com.example.demo.model.enums.GameEnum.*;

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

  public OnmyojiServiceImpl(GameThreadService gameThreadService) {
    this.gameThreadService = gameThreadService;
  }
  @Override
  public void onmyojiService(Integer type,Integer round) throws InterruptedException {
    //游戏进程id,游戏进程信息保存
    String threadId = IdUtil.objectId();
    log.info ("进程信息初始化开始");
    GameThreadPO gameThreadPO=new GameThreadPO ();
    gameThreadPO.setId (threadId);
    gameThreadPO.setThreadState (0);
    gameThreadPO.setThreadNumber (1);
    gameThreadService.save (gameThreadPO);
    log.info ("游戏进程ID{}",threadId);
    log.info ("进程信息初始化结束");
    //监控线程
    FirstThread t= new FirstThread(gameThreadService);
    t.setThreadId(threadId);
    //运行线程
    SecondThread t1=new SecondThread (gameThreadService);
    t1.setType (type);
    t1.setRound (round);
    t1.setThreadId (threadId);
    SecondThread t2=new SecondThread (gameThreadService);
    t2.setType (type);
    t2.setRound (round);
    t2.setThreadId (threadId);
    SecondThread t3=new SecondThread (gameThreadService);
    t3.setType (type);
    t3.setRound (round);
    t3.setThreadId (threadId);
    log.info ("启动监控线程");
    t.start();
    for (int i=0;i<3600;i++){
      gameThreadPO=gameThreadService.findById (threadId);
      int ThreadSecondNumber=gameThreadPO.getThreadNumber ();
      int ThreadSecondIsEnd=gameThreadPO.getThreadState ();
      if(ThreadSecondNumber==1 && !t1.isAlive ()&&ThreadSecondIsEnd==0){
        log.info ("主线程,启动线程1");
        t1.start();
        gameThreadPO.setThreadState (1);
        gameThreadPO.setThreadNumber (1);
      }
      if(ThreadSecondNumber==2&&t1.isAlive ()&&ThreadSecondIsEnd==1){
        log.info ("主线程,启动线程2");
        t1.interrupt ();
        t2.start();
        gameThreadPO.setThreadNumber (2);
      }
      if(ThreadSecondNumber==3&&t2.isAlive ()&&ThreadSecondIsEnd==1){
        log.info ("主线程,启动线程3");
        t2.interrupt ();
        t3.start();
        gameThreadPO.setThreadNumber (3);
      }
      if(ThreadSecondIsEnd==2){
        log.info ("主线程,结束监控线程");
        t.interrupt ();
        break;
      }
      log.info ("主线程游戏进程信息更新");
      gameThreadService.save (gameThreadPO);
      //等待一分钟
      Thread.sleep (60*1000);
    }
    log.info ("主线程结束");
  }
  
  @Override
  public void autoActivity (Integer type, Integer round) throws IOException, InterruptedException, AWTException {
     boolean initializeOrNot;//初始化完成标志
     String  thisPicture;//当前状态
     boolean announcementOrNot;//是否公告
     for(int i=0;i<round;i++){
       initializeOrNot=false;
       //大号  阴阳寮突破+个人突破+魂十一40次+地域鬼王（每日一次）
       if(type==1){
         //  当前状态初始化
         while (!initializeOrNot){
           thisPicture=thisState ();
           log.info ("当前状态{}",thisPicture);
           //阴阳师图标，需要点击应用图标->跳过登录动画->关闭公告
           if(thisPicture.equals (home_YYSTB.getValue ())){
              log.info ("点击阴阳师图标");
              ImageService.imagesBack0 (home_YYSTB.getValue ());
              Thread.sleep (10000);
              log.info ("单击一下，防止有开场动画");
              MouseClickUtils.mouseClickBack (new PictureIdentifyWorkPO (500,500),"夜神模拟器");
              Thread.sleep (1000);
              announcementOrNot=ImageService.imagesBackSingleHideIsEmpty (return_FH.getValue (),2,1,true);
              if (announcementOrNot){
                log.info ("有公告");
                ImageService.imagesBackSingleHide (return_FH.getValue (),2,1,true);
                Thread.sleep (1000);
              }
              
           }
         }
           //  当前状态检查，桌面、角色首页、有返回
           //  返回首页，先检查是否可以返回 允许返回则先返回到首页，不能返回则跳过
           //  账号登录（大号） 阴阳师登录/大号首页登录/小号首页登录
         //  阴阳寮突破
         //  个人突破
         //  魂十一（注意喂食宠物）
         //  地域鬼王+领取花合战每日奖励，无未攻打则跳过
         //  寄养检查，优先六星、五星、四星太鼓，其次六星、五星、四星斗鱼
         //  好友添加、好友删除、赠送小号红心、赠送其他人红心（待定）
       }
     }
  }
  
  @Override
  public String thisState () throws IOException, InterruptedException, AWTException {
    List<String> stringList=new ArrayList<> ();
    //桌面 阴阳师图标
    stringList.add (home_YYSTB.getValue ());
    //大号 逆戟之刃
    stringList.add (home_YH_NJZR.getValue ());
    //大号 逆戟之刃
    stringList.add (home_YH_NJZR.getValue ());
    //返回
    stringList.add (return_FH.getValue ());
    return ImageService.imagesBackListIsEmpty (stringList,2);
  }
  
  @Override
  public void returnHome () {
  
  }
  
  @Override
  public void login (String gameUserId) {
  }
}
