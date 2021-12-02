package util;

import java.math.BigDecimal;

/**
 * @author orcakill
 * @version 1.0.0
 * @ClassName ScientificNotation.java
 * @Description TODO
 * @createTime 2021年11月29日 09:20:00
 */
public class ScientificNotation {
	public static void main (String[] args) throws Exception {
		BigDecimal bd = new BigDecimal("-1.34115318480052E-6");
		String str = bd.toPlainString();
		System.out.println(str);
	}
	
	
}
