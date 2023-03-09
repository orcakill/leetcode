package com.example.demo.model.param;

import lombok.Data;

import java.util.List;

/**
 * @author orcakill
 * @version 1.0.0
 * @ClassName ProjectsParam.java
 * @Description 项目组参数
 * @createTime 2023年03月09日 09:00:00
 */
@Data
public class ProjectsParam {
	//项目组名称
	private String projectsName;
	//进程名
	private String process;
	//执行轮次
	private Integer  round;
	// 项目信息
	private List<ProjectParam> projectParams;
}
