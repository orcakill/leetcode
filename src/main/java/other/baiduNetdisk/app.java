package other.baiduNetdisk;

import other.baiduNetdisk.module.userInfo;

import java.io.UnsupportedEncodingException;

import static other.baiduNetdisk.utils.diskUtils.getAuth;

/**
 * @author orcakill @date 2021/5/12  11:11
 */
public class app {

    public static void main(String[] args) throws UnsupportedEncodingException {
       //基础能力-获取用户信息
        userInfo.getUserInfo();

    }
}
