package other.scenario.entity;

import lombok.Data;

import java.util.Date;

@Data
public class TaskInfoPO {
	private  Integer taskNum;    //序号
	private  String  taskName;    //任务名称
	private  Integer taskType=0; //默认值
}
