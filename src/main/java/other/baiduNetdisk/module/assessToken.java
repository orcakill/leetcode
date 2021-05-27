package other.baiduNetdisk.module;

import org.json.JSONException;
import org.json.JSONObject;
import other.baiduNetdisk.utils.HttpRequest;
import util.getPassWord;

/**
 * @author orcakill
 * @date 2021/5/26  8:43
 **/
public class assessToken {
    public static   String getAssessToken(){
        //获取assessToken
        String apiKey= getPassWord.getBaiduAppkey();
        String secretkey= getPassWord.getBaiduSecretkey();
        String  url1="http://openapi.baidu.com/oauth/2.0/authorize";
        String  param1="response_type=token"+
                "&client_id="+apiKey+
                "&redirect_uri=oob"+
                "&scope=basic,netdisk"+
                "&display=popup"+
                "&state=xxx";
        String url=url1+"?"+param1;
        String assessToken=getPassWord.getBaiduAssessToken();
        return assessToken;

    }
}
