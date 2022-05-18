package other.document.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import other.mail.util.GetJson;

import java.io.File;

import static other.document.utils.VideoFind.downloadMovie;

/**
 * @author orcakill
 * @version 1.0.0
 * @ClassName FindServiceImpl.java
 * @Description TODO
 * @createTime 2021年11月02日 08:25:00
 */
public class FindServiceImpl {
	
	public static void findVideo () {
		System.out.println("开始");
		long start = System.currentTimeMillis();
		/**
		 *  从json中获取到的 url
		 *  请获取后手动填写
		 */
		String url="https://api.bilibili.com/x/player/playurl?cid=111804822&otype=json&avid=58906853&fnver=0&fnval" +
		           "=2&player=1&qn=112\n";
		String  str= GetJson.getHttpJson (url);
		JSONObject jsonObject=JSONObject.parseObject (str);
		JSONArray jsonArray=jsonObject.getJSONObject ("data").getJSONArray ("durl");
		String lastUrl =jsonArray.getJSONObject (0).get ("url").toString ();
		//自定义文件名称
		
		String fileName = "D:\\test\\video_find\\a.flv";
		File file=new File (fileName);
		if(!file.exists ()){
			downloadMovie(lastUrl, fileName);
		}
		long end = System.currentTimeMillis();
		System.out.println("完成 ");
		System.err.println("总共耗时：" + (end - start) / 1000 + "s");
	}
}
