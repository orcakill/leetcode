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

        String apiKey= getPassWord.getBaiduAppkey();
        String secretkey= getPassWord.getBaiduSecretkey();
        String  url1="http://openapi.baidu.com/oauth/2.0/authorize";
        String  param1="response_type=token"+
                "&client_id="+apiKey+
                "&client_secret="+secretkey;
        String result=HttpRequest.sendGet(url1,param1);
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String access_token = null;
        try {
            access_token = jsonObject.getString("access_token");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("获取到的AccessToken的值:"+access_token);
        return access_token;

    }
}
