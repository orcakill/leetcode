package other.scenario.entity;

import lombok.Data;

import javax.persistence.Id;
import java.util.Date;

@Data
public class TaskListPO {
	@Id
	private Date    ListDate; //任务执行日期
	@Id
	private Integer ListNum;  //任务执行序号
	private Integer taskId;  //任务序号
	private String  userName; //账号名
	private Integer taskState; //任务状态  0未完成 1已完成
	private Integer taskNumber;  //任务当前执行次数
}
