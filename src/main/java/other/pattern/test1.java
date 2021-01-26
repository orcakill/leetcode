package other.pattern;

/**
 * @author orcakill
 * @date 2021/1/25  17:10
 **/
public class test1 {
    //普通字符
    public static  void  main(String[] arg) throws Exception{
       String s1="This is a text,i:i love you-(you are my love) ";
       //查找text,替换text为空
       System.out.println(s1.replaceAll("[text]",""));

        //查找text,替换非text以外的字符为空
        System.out.println(s1.replaceAll("[^text]",""));
        //替换掉所有大写字母
        System.out.println(s1.replaceAll("[A-Z]",""));
        //替换掉所有空白符
        System.out.println(s1.replaceAll("[\\s]",""));
        //替换掉所有非空白符
        System.out.println(s1.replaceAll("[\\S]","1"));
        //替换掉所有字母、数字、下划线
        System.out.println(s1.replaceAll("[\\w]","1"));
    }
}
