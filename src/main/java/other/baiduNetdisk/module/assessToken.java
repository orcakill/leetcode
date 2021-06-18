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
        String assessToken=getPassWord.getBaiduAssessToken();
        return assessToken;

    }
}
