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
    
    public static void main(String[] args) {
	    System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
        ThreadFirstController t1 = new ThreadFirstController ();
        ThreadSecondController t2 = new ThreadSecondController ();
        t1.start();
        t2.start();
    }
    
}
