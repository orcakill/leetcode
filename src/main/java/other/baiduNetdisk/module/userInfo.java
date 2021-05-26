package other.baiduNetdisk.module;

import other.baiduNetdisk.utils.HttpRequest;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static other.baiduNetdisk.module.assessToken.getAssessToken;
import static other.baiduNetdisk.utils.diskUtils.getAuth;

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

      public static void getUserInfo(){
          String accessToken   =getAssessToken();

          //发送 GET 请求
          String s= HttpRequest.sendGet("https://pan.baidu.com/rest/2.0/xpan/nas", "access_token="+accessToken+"&method=uinfo");
          System.out.println(s);
      }
}
