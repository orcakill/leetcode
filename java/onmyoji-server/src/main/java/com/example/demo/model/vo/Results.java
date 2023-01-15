package com.example.demo.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author orcakill
 * @version 1.0.0
 * @ClassName Results.java
 * @Description 通用接口返回对象
 * @createTime 2023年01月06日 17:30:00
 */
@Data
@Accessors (chain = true)
@Builder
@ApiModel ("通用接口返回对象")
public class Results<T> {
	@ApiModelProperty (required = true, notes = "结果码", example = "0")
	private int code;
	@ApiModelProperty (required = true, notes = "返回信息", example = "操作成功")
	private String msg;
	@ApiModelProperty (required = true, notes = "返回数据", example = "{\"id\":2001}")
	private T data;
	@ApiModelProperty (required = true, notes = "时间戳", example = "2020-06-29 09:07:34")
	private String timestamp;
	
	public static <T> Results<T> ok (T data) {
		return Results.<T>builder ().msg ("操作成功").data (data).timestamp (BlogUtils.now ()).build ();
	}
	
	public static Results fromErrorInfo (IErrorInfo errorInfo) {
		return Results.builder ().code (errorInfo.getCode ()).msg (errorInfo.getMsg ()).timestamp (BlogUtils.now ()).build ();
	}
	
	public static <T> Results<T> ok (String msg, T data) {
		return Results.<T>builder ().msg (msg).data (data).timestamp (BlogUtils.now ()).build ();
	}
	
	public static <T> Results<T> error (T data) {
		return Results.<T>builder ().code (5000).msg ("操作失败").data (data).timestamp (BlogUtils.now ()).build ();
	}
	
	public static <T> Results<T> error (String msg, T data) {
		return Results.<T>builder ().code (5000).msg (msg).data (data).timestamp (BlogUtils.now ()).build ();
	}
}
