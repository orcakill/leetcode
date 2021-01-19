package other.articles.util;

public class replace {
    public  static  String replaceA(String str){
        str=str.replaceAll("<a href[^>]*>", "");
        str=str.replaceAll("</a>", "");
        return  str;
    }
}
