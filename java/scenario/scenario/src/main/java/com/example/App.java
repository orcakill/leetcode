package com.example;

import com.example.controller.ThreadFirstController;
import com.example.controller.ThreadSecondController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class App{
    public  static  final Logger logger = LogManager.getLogger ("App");
    
    public static void main(String[] args) {
        ThreadFirstController t1 = new ThreadFirstController ();
        ThreadSecondController t2 = new ThreadSecondController ();
        t1.start();
        t2.start();
        //第二个进程结束，结束整个程序
        if(!t2.isAlive ()){
            logger.info ("程序结束");
            System.exit (0);
        }
    }

}
