package other.articles.util;

public class blankFill {
    public static  String getblankFill(String str,Integer i){
        return String.format("%1$-"+i+"s",str);
    }
}
