package other.scenario.controller;

import other.scenario.dao.TaskListMapper;
import other.scenario.entity.TaskListPO;
import other.scenario.service.LoginService;
import other.scenario.util.StartUpExeUtils;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TaskListController {
	public static   void  dealTaskList() throws Exception {
//		获取当前日期
		Date date=new Date ();
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat ("yyyy-MM-dd");
		String dateStr=simpleDateFormat.format (date);
		List<TaskListPO> taskListPOList= TaskListMapper.findByListDate (dateStr);
//		打开阴阳师
		LoginService.loginService ();
		for (TaskListPO taskListPO : taskListPOList) {
//          任务未完成时，开始处理
			if (taskListPO.getTaskState () == 0) {
//               获取到当前任务用户所在大区
			
			}
			break;
		}
	}
}
