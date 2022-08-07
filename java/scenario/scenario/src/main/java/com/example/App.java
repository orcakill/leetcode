package com.example;

import com.example.controller.ThreadFirstController;
import com.example.controller.ThreadSecondController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App{
    public  static  final Logger logger = LogManager.getLogger ("App");
    public  static   boolean ThreadSecondIsAlive=true;
    
    public static void main(String[] args) {
        ThreadFirstController t1 = new ThreadFirstController ();
        ThreadSecondController t2 = new ThreadSecondController ();
        t1.start();
        t2.start();
    }

}
