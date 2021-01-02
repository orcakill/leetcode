package fund.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetJson {
    public static JSONArray getHttpJson(String url, String referer) throws Exception {
        try {
            URL realUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("referer", referer);
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            //请求成功
            if (connection.getResponseCode() == 200) {
                InputStream is = connection.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                //10MB的缓存
                byte[] buffer = new byte[10485760];
                int len = 0;
                while ((len = is.read(buffer)) != -1) {
                    baos.write(buffer, 0, len);
                }
                String str = baos.toString();
                baos.close();
                is.close();
                //转换成json数据处理
                // getHttpJson函数的后面的参数1，表示返回的是json数据，2表示http接口的数据在一个（）中的数据
                //   JSONObject jsonArray = getJsonString(jsonString, comefrom);
                //获取str的长度
                str=str.replaceAll("jQuery18306596328894644803_1571038362181","");
                int length = str.length();
                //indexOf返回某个指定的字符串值在字符串中首次出现的位置
                int indexStart = str.indexOf("(");
                //截取字符串
                str = str.substring(indexStart + 9, length -91);
                //转换为Obj类型
                JSONObject jsonObject = JSON.parseObject(str);
                //获取数组
                com.alibaba.fastjson.JSONArray jsonArray = jsonObject.getJSONArray("LSJZList");
                //计算数组的长度
                int size = jsonArray.size();
                return jsonArray;

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}