package com.example.demo.model.enums;

/**
 * @Classname CommEnum
 * @Description 通用枚举
 * @Date 2023/1/25 20:45
 * @Created by orcakill
 */
public enum GameEnum {
  comm_JJXZ ("通用/拒接协战"),
  comm_QR ("通用/确认"),
  login_YYSTB("登录/阴阳师图标"),
  
  home_TS("首页/探索")
  ;
  private final String value;


  GameEnum(String value) {
    this.value = value;
  }

  public String getValue () {
    return value;
  }
}
