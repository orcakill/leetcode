package other.scenario.util;

import java.util.Random;

public class RandomUtil {
	public static int randomMinute (int num) {
		Random random = new Random ();//默认构造方法
		return random.nextInt (num);
	}
	public static int getRandom(int min, int max){
			Random random = new Random();
			int s = random.nextInt(max) % (max - min + 1) + min;
			return s;

	}
	
	
}
