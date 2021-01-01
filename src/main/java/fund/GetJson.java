package fund;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetJson {
    public static JSONObject getHttpJson(String url, String referer) throws Exception {
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
                String jsonString = baos.toString();
                baos.close();
                is.close();
                //转换成json数据处理
                // getHttpJson函数的后面的参数1，表示返回的是json数据，2表示http接口的数据在一个（）中的数据
                //   JSONObject jsonArray = getJsonString(jsonString, comefrom);
                 jsonString=jsonString.replace("jQuery18306596328894644803_1571038362181","");
                 jsonString=jsonString.substring(1,jsonString.length()-1);
                 JSONObject js=new JSONObject(jsonString);

                return js;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}