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
import java.net.InetAddress;
import java.net.UnknownHostException;
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

  public OnmyojiServiceImpl (GameThreadService gameThreadService) {
    this.gameThreadService = gameThreadService;
  }
  
  @Override
  public void onmyojiService(Integer type,Integer round) throws InterruptedException, UnknownHostException {
    //游戏进程id,游戏进程信息保存
    String threadId = IdUtil.objectId();
    log.info ("进程信息初始化开始");
    log.info ("游戏进程ID{}",threadId);
    GameThreadPO gameThreadPO=new GameThreadPO ();
    gameThreadPO.setThreadId (threadId);
    gameThreadPO.setThreadState (0);
    InetAddress inetAddress = InetAddress.getLocalHost ();
    gameThreadPO.setCreateUser (inetAddress.getHostName ());
    gameThreadService.save (gameThreadPO);
    log.info ("进程信息初始化结束");
    //监控线程
    FirstThread t= new FirstThread();
    t.setThreadId(threadId);
    //运行线程
    SecondThread t1=new SecondThread ();
    t1.setType (type);
    t1.setRound (round);
    t1.setThreadId (threadId);
    log.info ("启动监控线程");
    t.start();
    log.info ("启动运行线程");
    t1.start();
    gameThreadPO.setThreadState (1);
    gameThreadService.save (gameThreadPO);
    int threadState =gameThreadService.findById (threadId).getThreadState ();
    while (threadState!=2){
       Thread.sleep (60*1000);
      threadState=gameThreadService.findById (threadId).getThreadState ();
    }
    log.info ("主线程结束");
  }
  
  @Override
  public void autoActivity (Integer type, Integer round) throws IOException, InterruptedException, AWTException {

     for(int i=0;i<round;i++){

       //大号  阴阳寮突破+个人突破+魂十一40次+地域鬼王（每日一次）
       if(type==1){
         //  当前状态初始化，进入角色首页
         initializationState("1");
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
  public void initializationState (String userId) throws IOException, InterruptedException, AWTException {
    boolean initializeOrNot=false;
    String  thisPicture;//当前状态
    boolean announcementOrNot;//是否公告
    boolean  promptForAge=false;//是否适龄提示
    while (!initializeOrNot){
      thisPicture=thisState ();
      log.info ("当前状态{}",thisPicture);
      //阴阳师图标，需要点击应用图标->跳过登录动画->关闭公告->适龄提示
      if(thisPicture.equals (home_YYSTB.getValue ())){
        log.info ("点击阴阳师图标");
        ImageService.imagesBack0 (home_YYSTB.getValue ());
        while (!promptForAge){
          Thread.sleep (15000);
          log.info ("单击一下，防止有开场动画");
          MouseClickUtils.mouseClickBack (new PictureIdentifyWorkPO (500,500),"夜神模拟器");
          Thread.sleep (1000);
          log.info ("判断是否有公告需要返回");
          announcementOrNot=ImageService.imagesBackIsEmpty (return_FH.getValue (),2,1);
          if (announcementOrNot){
            log.info ("有公告");
            ImageService.imagesBack (return_FH.getValue (),2,1);
            Thread.sleep (1000);
          }
          promptForAge=ImageService.imagesBackIsEmpty (login_SLTS.getValue (),2,1);
          if(promptForAge){
            log.info ("当前页面有适龄提示");
          }
        }
        log.info ("切换用户和大区并登录到首页");
        login (userId);
        initializeOrNot=true;
        log.info ("当前用户首页");
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
    //小号 orcakill
    stringList.add (home_YH_NJZR.getValue ());
    //返回
    stringList.add (return_FH.getValue ());
    return ImageService.imagesBackListIsEmpty (stringList,2);
  }
  
  @Override
  public void returnHome () {
  
  }

  @Override
  public void login (String gameUserId) throws IOException, InterruptedException, AWTException {
  boolean userHomePageOrNot=ImageService.imagesBackSingleHideIsEmpty (login_YHZX.getValue (),2,3,true);
  if(userHomePageOrNot){
    log.info ("点击用户中心");
    ImageService.imagesBack (login_YHZX.getValue (),2);
    log.info ("切换账号");
    ImageService.imagesBack (login_QHZH.getValue (),2);
    log.info ("常用");
    ImageService.imagesBack (login_CY.getValue (),2);
    log.info ("选择账号");
    if(gameUserId!=null){
      if(gameUserId.equals ("1")){
        log.info ("手机号178");
        ImageService.imagesBack (login_XZZH_PHONE1.getValue (),2);
      }
      if(gameUserId.equals ("2")){
        log.info ("邮箱号1");
        ImageService.imagesBack (login_YHZX_EMAIIL1.getValue (),2);
      }
    }
    log.info ("切换服务器");
    if(gameUserId!=null){
      if(gameUserId.equals ("1")){
        log.info ("点击大号角色-缥缈之旅");
        ImageService.imagesBack (login_FWQ_PMZL.getValue (),2);
      }
    }
    log.info ("开始游戏");
    ImageService.imagesBack (login_KSYX.getValue (),2);
    log.info ("底部菜单栏");
    ImageService.imagesBack (home_DBCD.getValue (),2);
  }

  }
}
