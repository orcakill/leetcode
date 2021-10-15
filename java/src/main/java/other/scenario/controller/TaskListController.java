package other.scenario.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import other.scenario.dao.TaskListMapper;
import other.scenario.entity.TaskListPO;
import other.scenario.service.LoginService;
import other.scenario.service.ReceiveService;

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
				/*当前账号为空或不等于上一个任务执行的账号是登录*/
				if(userName==null){
//					第一次登录
					userName=taskUser;
					LoginService.loginAreaService (userName);
					logger.info ("登录"+taskListPO.getUserName ()+"成功");
				}
				if(!userName.equals (taskUser)){
					userName=taskUser;
//					返回切换界面
					LoginService.loginBackService ();
//					重新登录
					LoginService.loginAreaService (userName);
					logger.info ("登录"+taskListPO.getUserName ()+"成功");
				}
//				任务1：签到、领取勾玉、领取邮件
				if(taskListPO.getTaskNum ()==1){
//					领取邮件
					boolean b1= ReceiveService.receiveMail ();
//                  签到、领取每日勾玉、领取御魂加成、体力
                    boolean b2= ReceiveService.singIn ();
//					任务1完成
					if(b1&&b2){
						taskListPO.setTaskState (1);
					}
//					TaskListMapper.save (taskListPO);
				}
//				任务4：领取体力食盒体力
				if(taskListPO.getTaskNum ()==4){
//					领取体力食盒体力
					boolean b1= ReceiveService.receiveMail ();
//					任务1完成
					if(b1){
						taskListPO.setTaskState (1);
					}
//					TaskListMapper.save (taskListPO);
				}
				
			}
		}
	}
}
