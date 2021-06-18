package other.baiduNetdisk.module;

import other.baiduNetdisk.utils.HttpRequest;

import static other.baiduNetdisk.module.assessToken.getAssessToken;

/**
 * @author orcakill
 * @date 2021/6/18  8:19
 **/
public class CapacityInfo {
    public static String getCapacityInfo(){
        String accessToken   =getAssessToken();

        //发送 GET 请求
        String s= HttpRequest.sendGet("https://pan.baidu.com/api/quota", "access_token="+accessToken+"&checkfree=1&checkexpire=1");
        return  s;

    }
}
