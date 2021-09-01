package other.scenario.entity;

import lombok.Data;

import java.util.Date;


@Data
public class TaskListPO {
	private  Date    taskListDate; //任务执行日期
	private  Integer taskNum;  //任务序号
	private  String  userName; //账号名
	private  Integer taskSate; //任务状态  0未完成 1已完成
}
