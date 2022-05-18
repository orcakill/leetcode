package util;

import java.util.Locale;

public class Case {

    public static   String toUpper(String str){
        return  str.substring(0,1).toUpperCase(Locale.ROOT)+str.substring(1);

    }

    public static   String toLower(String str){
        return  str.substring(0,1).toLowerCase(Locale.ROOT)+str.substring(1);

    }
}
