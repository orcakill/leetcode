package other.baiduNetdisk.module;

import other.baiduNetdisk.utils.HttpRequest;



import static other.baiduNetdisk.module.assessToken.getAssessToken;


/**
 * @author orcakill
 * @date 2021/5/24  18:46
 **/
public class userInfo {
      /**
       * @description: 获取用户信息
       * @param
       * @return: void
       * @author: orcakill
       * @date: 2021/5/25 11:25
       */

      public static String getUserInfo(){
          String accessToken   =getAssessToken();

          //发送 GET 请求
          return HttpRequest.sendGet("https://pan.baidu.com/rest/2.0/xpan/nas", "access_token="+accessToken+"&method=uinfo");
      }
}
