package other.scenario.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import other.scenario.dao.TaskInfoMapper;
import other.scenario.dao.TaskListMapper;
import other.scenario.entity.TaskListPO;
import other.scenario.service.FightAutoService;
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
					logger.info ("当前账号"+userName+"  "+"任务账号"+taskUser);
					userName=taskUser;
//					返回切换界面
					LoginService.loginBackService ();
//					重新登录
					LoginService.loginAreaService (userName);
					logger.info ("登录"+taskListPO.getUserName ()+"成功");
				}
//				任务1：签到、领取勾玉、领取邮件
				if(taskListPO.getTaskId ()==1){
					logger.info (userName+"任务1：签到、领取勾玉、领取邮件开始");
//					领取邮件
					boolean b1= ReceiveService.receiveMail ();
//                  签到、领取每日勾玉、领取御魂加成、体力
                    boolean b2= ReceiveService.singIn ();
//					任务1完成
					if(b1&&b2){
						taskListPO.setTaskState (1);
					}
					TaskListMapper.save (taskListPO);
					logger.info (userName+"任务1：签到、领取勾玉、领取邮件结束");
				}
//				任务2：领取体力食盒体力
				if(taskListPO.getTaskId ()==2){
					logger.info (userName+"任务2：领取体力食盒开始");
//					领取体力食盒体力
					boolean b1= ReceiveService.receiveBox ();
//					任务1完成
					if(b1){
						taskListPO.setTaskState (1);
					}
					TaskListMapper.save (taskListPO);
					logger.info (userName+"任务2：领取体力食盒结束");
				}
//				任务3：樱饼刷御魂
				if(taskListPO.getTaskId()==3){
					logger.info (userName+"任务3：樱饼刷御魂开始");
					int taskNum= TaskInfoMapper.findById (taskListPO.getTaskId ()).getTaskNumber ();
					int listNum= taskListPO.getTaskNumber ();
					int  num=taskNum-listNum;
//					存在剩余需要挑战的次数
					if(num>0){
						num=FightAutoService.cherrySoul(num);
					}
					taskListPO.setTaskNumber (taskNum-num);
//					无需要挑战的次数
					if(num==0){
						taskListPO.setTaskState (1);
					}
					TaskListMapper.save (taskListPO);
					logger.info (userName+"任务3：樱饼刷御魂结束");
				}
//				任务4：樱饼刷经验
				if(taskListPO.getTaskId()==4){
					logger.info (userName+"任务4：樱饼刷经验开始");
					boolean b=FightAutoService.cherryExperience ();
					if(b){
						taskListPO.setTaskState (1);
					}
					TaskListMapper.save (taskListPO);
					logger.info (userName+"任务4：樱饼刷经验结束");
				}
//				任务5：好友助战打结界
				if(taskListPO.getTaskId()==5){
					logger.info (userName+"任务5：好友助战结界开始");
					int taskNum= TaskInfoMapper.findById (taskListPO.getTaskId ()).getTaskNumber ();
					int listNum= taskListPO.getTaskNumber ();
					int  num=taskNum-listNum;
//					存在剩余需要挑战的次数
					if(num>0){
						num=FightAutoService.friendBorder (num);
					}
					taskListPO.setTaskNumber (taskNum-num);
//					无需要挑战的次数
					if(num==0){
						taskListPO.setTaskState (1);
					}
					TaskListMapper.save (taskListPO);
					logger.info (userName+"任务5：好友助战结界结束");
				}
//				任务6：领取花合战奖励
				if(taskListPO.getTaskId()==6){
					logger.info (userName+"任务6：花合战奖励领取开始");
					boolean b=FightAutoService.receiveReward ();
					if(b){
						taskListPO.setTaskState (1);
					}
					TaskListMapper.save (taskListPO);
					logger.info (userName+"任务6：花合战奖励领取结束");
				}
//				任务7：协战截图
				if(taskListPO.getTaskId()==7){
					logger.info (userName+"任务7：协战截图");
					boolean b=FightAutoService.friendScreen (userName);
					if(b){
						taskListPO.setTaskState (1);
					}
					TaskListMapper.save (taskListPO);
					logger.info (userName+"任务7：协战截图");
				}
				
			}
		}
	}
}
