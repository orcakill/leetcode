package com.example.demo.utils;

import java.util.Random;

public class RandomUtil {
	public static int randomMinute (int num) {
		Random random = new Random ();//默认构造方法
		return random.nextInt (num);
	}
	public static int getRandom(int min, int max){
			Random random = new Random();
		    return random.nextInt (max) % (max - min + 1) + min;

	}
}
