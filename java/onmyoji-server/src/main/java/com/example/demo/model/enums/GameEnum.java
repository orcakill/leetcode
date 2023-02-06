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
  home_SYGY ("首页/首页勾玉"),
  home_TS ("首页/探索"),
  home_YH_NJZR ("首页/用户/逆戟之刃"),
  home_YH_ORCAKILL ("首页/用户/orcakill"),
  home_YYSTB ("首页/阴阳师图标"),
  login_CY ("登录/常用"),
  login_SLTS ("登录/适龄提示"),
  login_YHZX ("登录/用户中心"),
  login_YYSTB ("登录/阴阳师图标"),
  return_FH ("返回");
  private final String value;
  
  GameEnum (String value) {
    this.value = value;
  }
  
  public String getValue () {
    return value;
  }
}
