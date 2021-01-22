package other.articles.map;

import java.util.HashMap;
import java.util.Map;

/**
 * @author orcakill
 * @date 2021/1/19  10:50
 **/
public class getWebAddress {
    public  static  String getWebAddress(String name){
        Map<String,String> map=new HashMap<>();
        map.put("百度实时热点","http://top.baidu.com/buzz?b=1&fr=20811");
        map.put("IT之家AI","https://next.ithome.com/ai");
        return map.get(name);
    }
}
