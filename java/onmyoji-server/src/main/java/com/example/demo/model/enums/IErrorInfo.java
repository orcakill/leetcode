package com.example.demo.model.enums;

/**
 * @Classname IErrorInfo
 * @Description 错误信息
 * @Date 2023/1/16 0:54
 * @Created by orcakill
 */
public interface IErrorInfo {
	/**
	 * 获取错误信息
	 * @return 错误信息
	 */
	String getMsg();
	
	/**
	 * 获取错误码
	 * @return 错误码
	 */
	int getCode();
}
