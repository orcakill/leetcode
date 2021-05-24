package other.baiduNetdisk.module;

import static other.baiduNetdisk.utils.diskUtils.getAuth;

/**
 * @author orcakill
 * @date 2021/5/24  18:46
 **/
public class upload {
    /*post请求地址*/
    String API = "https://pcs.baidu.com/rest/2.0/pcs/file"; /*本地文件路径, 例如:"/Users/macuser/Documents/workspace/test.jpg"*/
    String mLocalPath = "F:\\test\\video_compress\\a.zip"; /*上传文件路径(含上传的文件名称), 例如:"/apps/yuantest/test.jpg"*/
    String mDestPath = "/apps/a.zip"; /*开发者准入标识 access_token, 通过OAuth获得*/
    String mAccessToken = getAuth();
    String user="https://pan.baidu.com/rest/2.0/xpan/nas?method=uinfo";
}
