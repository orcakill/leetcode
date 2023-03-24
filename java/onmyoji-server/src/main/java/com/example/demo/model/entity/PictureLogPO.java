package com.example.demo.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 图片日志
 * @TableName picture_log
 */
@TableName (value = "picture_log")
@Data
public class PictureLogPO implements Serializable {
	/**
	 * 日志ID
	 */
	@TableId
	private String logId;
	
	/**
	 * 图片类型
	 */
	private String logType;
	
	/**
	 * 图片名称
	 */
	private String logFolder;
	
	/**
	 * 图片路径
	 */
	private String logPath;
	
	/**
	 * 主机名
	 */
	private String logHostname;
	
	/**
	 * IP
	 */
	private String logIp;
	
	/**
	 * 创建人
	 */
	private String createUser;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 修改人
	 */
	private String updateUser;
	
	/**
	 * 修改时间
	 */
	private Date updateTime;
	
	@TableField (exist = false)
	private static final long serialVersionUID = 1L;
}