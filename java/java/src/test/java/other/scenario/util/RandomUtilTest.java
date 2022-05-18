package other.scenario.util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @Classname RandomUtilTest
 * @Description 测试类
 * @Date 2021/10/19 22:48
 * @Created by orcakill
 */
public class RandomUtilTest {
	
	@Test
	public void randomMinute () {
		System.out.println (RandomUtil.randomMinute (10));
	}
	
	@Test
	public void getRandom() {
		System.out.println (RandomUtil.getRandom (2,5));
	}
}