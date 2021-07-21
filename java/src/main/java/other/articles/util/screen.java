package other.articles.util;

import other.articles.model.entity.ITHomeNews;
import other.dao.ITNews;

import java.sql.SQLException;
import java.util.List;

/**
 * @author orcakill
 * @date 2021/1/23  14:34
 **/
public class screen {

    public  static List<ITHomeNews> screenITNews(List<ITHomeNews> itHomeNews) throws SQLException {

        for(int i=0;i<itHomeNews.size();i++){
             ITHomeNews itHomeNews1=itHomeNews.get(i);
            boolean b= ITNews.isEmpty(itHomeNews1.getNewsName());
            if(b){
                itHomeNews.remove(i);
                i--;
            }
        }


        return  itHomeNews;
    }
}
