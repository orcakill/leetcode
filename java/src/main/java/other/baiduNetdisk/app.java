package other.baiduNetdisk;




import java.io.UnsupportedEncodingException;


import static other.baiduNetdisk.module.CapacityInfo.getCapacityInfo;
import static other.baiduNetdisk.module.userInfo.getUserInfo;

/**
 * @author orcakill @date 2021/5/12  11:11
 */
public class app {

    public static void main(String[] args) throws UnsupportedEncodingException {
       //基础能力-获取用户信息
       String userInfo=getUserInfo();
       System.out.println(userInfo);
       //基础能力-获取网盘容量信息
        String capacityInfo=getCapacityInfo();
        System.out.println(capacityInfo);
    }
}
