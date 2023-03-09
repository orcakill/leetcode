package com.example.demo.model.param;

import lombok.Data;

/**
 * @Classname projectParam
 * @Description 项目参数 ，controller层传入
 * @Date 2023/3/8 1:39
 * @Created by orcakill
 */
@Data
public class ProjectParam {
	
	//项目名称
	private  String projectName;
	//项目执行的账号
	private  String projectUser;
	//项目次数
	private  Integer projectNum;
	//加成
	private boolean addition=false;
	
	//项目结束后等待时间 随机开始时间
	private  Integer projectWaitStartTime;
	//项目结束后等待时间 随机结束时间
	private  Integer projectWaitEndTime;
	public ProjectParam (String projectName, String projectUser, Integer projectNum, boolean addition,
	                     Integer projectWaitStartTime, Integer projectWaitEndTime) {
		this.projectName = projectName;
		this.projectUser = projectUser;
		this.projectNum = projectNum;
		this.addition = addition;
		this.projectWaitStartTime = projectWaitStartTime;
		this.projectWaitEndTime = projectWaitEndTime;
	}
	public ProjectParam (String projectName, String projectUser) {
		this.projectName = projectName;
		this.projectUser = projectUser;
	}
}
