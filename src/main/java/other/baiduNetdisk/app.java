package other.baiduNetdisk;




import java.io.UnsupportedEncodingException;

import static other.baiduNetdisk.module.assessToken.getAssessToken;
import static other.baiduNetdisk.module.userInfo.getUserInfo;

/**
 * @author orcakill @date 2021/5/12  11:11
 */
public class app {

    public static void main(String[] args) throws UnsupportedEncodingException {
        //接入,获取
       String assessToken= getAssessToken();
       //基础能力-获取用户信息
        getUserInfo();

    }
}
