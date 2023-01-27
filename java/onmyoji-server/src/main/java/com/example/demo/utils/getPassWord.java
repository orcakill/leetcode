package com.example.demo.utils;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * @author orcakill
 * @date 2021/1/14  15:13
 **/
public class getPassWord {
    public  static   String  get163mail() {
        String file = "D:\\study\\password\\163mail授权码.txt";
        return getString (file);
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
