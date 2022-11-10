package com.example;

import com.example.controller.ThreadFirstController;
import com.example.controller.ThreadSecondController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.opencv.core.Core;

public class App{
    //App日志
    public  static  final Logger logger = LogManager.getLogger ("App");
    //异常记录
    public  static   boolean ThreadSecondIsAlive=true;
    //图片库目录
    
    static {
        System.loadLibrary (Core.NATIVE_LIBRARY_NAME); // 得保证先执行该语句，用于加载库，才能调用其他操作库的语句，
    }
    
    public static void main(String[] args) {
        ThreadFirstController t1 = new ThreadFirstController ();
        ThreadSecondController t2 = new ThreadSecondController ();
        t1.start();
        t2.start();
    }
    
}
