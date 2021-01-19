package other.articles.util;

public class blankFill {
    public static  String blankFill(String str,Integer i){
        String s=String.format("%1$-"+i+"s",str);
        return  s;
    }
}
