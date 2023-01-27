package com.example.demo.model.enums;

/**
 * @Classname ErrorInfoEnum
 * @Description 错误信息枚举
 * @Date 2023/1/23 22:22
 * @Created by orcakill
 */
public enum ErrorInfoEnum implements IErrorInfo{
  SUCCESS(0, "操作成功"),
  MISSING_PARAMETERS(4004, "参数缺失"),
  UNKNOWN_ERROR(5000, "出现未知错误"),
  INVALID_ID(4008, "你的id不合法"),
  RESOURCES_NOT_FOUND(4003, "找不到相应资源");

  private final int code;
  private final String msg;

  @Override
  public String getMsg() {
    return msg;
  }

  ErrorInfoEnum(int code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  @Override
  public int getCode() {
    return code;
  }
}
