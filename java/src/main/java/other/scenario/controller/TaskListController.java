package other.scenario.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import other.scenario.dao.OnmyojiInfoMapper;
import other.scenario.dao.TaskListMapper;
import other.scenario.entity.TaskListPO;
import other.scenario.service.LoginService;
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
		for (int i=0;i<1;i++) {
			TaskListPO taskListPO=taskListPOList.get (i);
//          任务未完成时，开始处理
			if (taskListPO.getTaskState () == 0) {
				/*登录*/
				LoginService.loginAreaService(taskListPO.getUserName ());
				logger.info ("登录"+taskListPO.getUserName ()+"成功");
			}
			break;
		}
	}
}
