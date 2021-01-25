package other.pattern;

/**
 * @author orcakill
 * @date 2021/1/25  17:10
 **/
public class test1 {
    //普通字符
    public static  void  main(String[] arg) throws Exception{
       String s1="This is a text,aaa";
       //查找text,替换text为空
       System.out.println(s1.replaceAll("[text]",""));

        //查找text,替换非text以外的字符为空
        System.out.println(s1.replaceAll("[^text]",""));
    }
}
