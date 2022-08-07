package com.example.util;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * @author orcakill
 * @date 2021/1/14  15:13
 **/
public class getPassWord {
    public  static   String  get163mail(){
        String  file="D:\\study\\password\\163mail授权码.txt";
        return getString(file);
    }
    public  static   String  getMysqlPassword(){
        String  file="D:\\study\\password\\mysql.txt";
        return getString(file);
    }

    public  static   String  getTestMysqlPassword(){
        String  file="D:\\study\\password\\testmysql.txt";
        return getString(file);
    }

    public  static   String  getBaiduAppID(){
        String  file="D:\\study\\password\\百度网盘AppID.txt";
        return getString(file);
    }

    public  static   String  getBaiduAppkey(){
        String  file="D:\\study\\password\\百度网盘Appkey.txt";
        return getString(file);
    }

    public  static   String  getBaiduSecretkey(){
        String  file="D:\\study\\password\\百度网盘Secretkey.txt";
        return getString(file);
    }

    public  static   String  getBaiduSignkey(){
        String  file="D:\\study\\password\\百度网盘Signkey.txt";
        return getString(file);
    }
    public  static   String  getBaiducode(){
        String  file="D:\\study\\password\\百度网盘code.txt";
        return getString(file);
    }

    public  static   String  getBaiduRefreshToken(){
        String  file="D:\\study\\password\\百度网盘RefreshToken.txt";
        return getString(file);
    }

    public  static   String  getBaiduAssessToken(){
        String  file="D:\\study\\password\\百度网盘AssessToken.txt";
        return getString(file);
    }




    private static String getString(String file) {
        StringBuilder result = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                result.append(System.lineSeparator()).append(s);
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        String  ss=result.toString();
        ss=ss.replaceAll("\r\n","");
        return ss;
    }




}
