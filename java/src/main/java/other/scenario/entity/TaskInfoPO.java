package other.scenario.entity;

import lombok.Data;

import javax.persistence.Id;

@Data
public class TaskInfoPO {
	@Id
	private Integer taskId;    //序号
	private String taskName;    //任务名称
	private Integer taskType = 0; //默认值
	private Integer taskNumber ; //任务需要执行次数
}
