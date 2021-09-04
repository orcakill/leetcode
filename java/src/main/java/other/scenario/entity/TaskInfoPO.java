package other.scenario.entity;

import lombok.Data;

@Data
public class TaskInfoPO {
	private Integer taskNum;    //序号
	private String taskName;    //任务名称
	private Integer taskType = 0; //默认值
}
