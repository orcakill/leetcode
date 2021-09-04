package other.scenario.entity;

import lombok.Data;

import javax.persistence.Id;
import java.util.Date;

@Data
public class TaskListPO {
	@Id
	private Date    taskListDate; //任务执行日期
	@Id
	private Integer taskListNum;  //任务执行序号
	private Integer taskNum;  //任务序号
	private String  userName; //账号名
	private Integer taskState; //任务状态  0未完成 1已完成
}
