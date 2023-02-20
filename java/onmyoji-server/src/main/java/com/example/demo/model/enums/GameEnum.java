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
  home_DBCD ("首页/底部菜单"),
  home_SYGY ("首页/首页勾玉"),
  home_TS ("首页/探索"),
  home_YH_NJZR ("首页/用户/逆戟之刃"),
  home_TXYHZX ("首页/头像用户中心"),
  home_YH_ORCAKILL ("首页/用户/orcakill"),
  home_YYSTB ("登录/阴阳师图标"),
  login_CY ("登录/常用"),
  login_DL ("登录/登录"),
  login_FWQ_PMZL ("登录/服务器/缥缈之旅"),
  login_KSYX ("登录/开始游戏"),
  login_QH ("登录/切换"),
  login_XSJ ("登录/小三角"),
  login_QHZH ("登录/切换账号"),
  login_SLTS ("登录/适龄提示"),
  login_XZZH_PHONE1 ("登录/选择账号/手机号1"),
  login_YHZX ("登录/用户中心"),
  login_YHZX_EMAIIL1 ("登录/选择账号/邮箱号1"),
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
