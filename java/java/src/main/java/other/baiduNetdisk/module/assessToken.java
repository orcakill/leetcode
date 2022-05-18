package other.baiduNetdisk.module;

import util.getPassWord;

/**
 * @author orcakill
 * @date 2021/5/26  8:43
 **/
public class assessToken {
    public static   String getAssessToken(){
        //获取assessToken
        return getPassWord.getBaiduAssessToken();

    }
}
