package com.example.demo.model.vo;

import com.example.demo.model.enums.IErrorInfo;
import com.example.demo.utils.BlogUtils;
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
@Data  //lombok注解，简化setter、getter、以及构造函数的书写
@Accessors(chain = true) //lombok注解，开启链式调用
@Builder //lombok注解，可以使用 Builder方式创建对象
@ApiModel("通用接口返回对象")  //Swagger 注解，表示该类是一个接口模型
public class Results<T> {

  @ApiModelProperty(required = true, notes = "结果码", example = "0") // Swagger 注解，描述属性信息
  private int code;
  @ApiModelProperty(required = true, notes = "返回信息", example = "操作成功")
  private String msg;
  @ApiModelProperty(required = true, notes = "返回数据", example = "{\"id\":2001}")
  private T data;
  @ApiModelProperty(required = true, notes = "时间戳", example = "2023-01-01 00:00:00")
  private String timestamp;

  public static <T> Results<T> ok(T data) {
    return Results.<T>builder().msg("操作成功").data(data).timestamp(BlogUtils.now()).build();
  }

  public static Results<?> fromErrorInfo(IErrorInfo errorInfo) {
    return Results.builder()
        .code(errorInfo.getCode())
        .msg(errorInfo.getMsg())
        .timestamp(BlogUtils.now())
        .build();
  }

  public static <T> Results<T> ok(String msg, T data) {
    return Results.<T>builder().msg(msg).data(data).timestamp(BlogUtils.now()).build();
  }

  public static <T> Results<T> error(T data) {
    return Results.<T>builder().code(5000).msg("操作失败").data(data).timestamp(BlogUtils.now())
        .build();
  }

  public static <T> Results<T> error(String msg, T data) {
    return Results.<T>builder().code(5000).msg(msg).data(data).timestamp(BlogUtils.now()).build();
  }
}
