package other.scenario.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import other.scenario.dao.OnmyojiInfoMapper;
import other.scenario.dao.TaskListMapper;
import other.scenario.entity.TaskListPO;
import other.scenario.service.LoginService;
import other.scenario.service.ReceiveMail;
import other.scenario.util.StartUpExeUtils;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TaskListController {
	private static final Logger logger = LogManager.getLogger (TaskListController.class);
	
	public static   void  dealTaskList() throws Exception {
//		获取当前日期
		Date date=new Date ();
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat ("yyyy-MM-dd");
		String dateStr=simpleDateFormat.format (date);
		List<TaskListPO> taskListPOList= TaskListMapper.findByListDate (dateStr);
//		打开阴阳师
		LoginService.loginService ();
		String userName=null;
		for (int i=0;i<taskListPOList.size ();i++) {
			TaskListPO taskListPO=taskListPOList.get (i);
			String taskUser=taskListPO.getUserName ();
//          任务未完成时，开始处理
			if (taskListPO.getTaskState () == 0) {
				/*当前账号为空或不等于上一个任务执行的账号登录*/
				if(userName==null||!userName.equals (taskUser)){
					userName=taskUser;
					LoginService.loginBackService ();
				}
				logger.info ("登录"+taskListPO.getUserName ()+"成功");
//				任务1：签到、领取勾玉、领取邮件
				if(taskListPO.getTaskNum ()==1){
//					领取邮件
					boolean b1=ReceiveMail.receiveMail ();
//                  签到、领取每日勾玉、领取御魂加成、体力
                    boolean b2=ReceiveMail.singIn ();
//					任务1完成
					if(b1||b2){
						taskListPO.setTaskState (1);
					}
					TaskListMapper.save (taskListPO);
				}
			}
		}
	}
}
