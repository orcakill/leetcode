package other.divination;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author orcakill
 * @version 1.0.0
 * @ClassName App.java
 * @Description 周易算卦
 * @createTime 2021年10月09日 15:22:00
 */
public class BookOfChange {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		List<String> gua = new ArrayList<String> ();
		Random shu = new Random();
		//创建6个爻
		for (int i = 0; i < 6; i++) {
			String he = String.valueOf(shu.nextInt(2));
			gua.add(he);
		}
		//总
		//for (String g : gua) {
		//    System.out.println(g);
		//}
		//判断
		System.out.println("如果该循环的当前值为1，则为阳爻，若为0.则为阴爻");
		//整体转换为爻
		for (int i = 5; i >= 0; i--) {
			if (gua.get(i).equals("1")) {
				gua.set(i, "阳爻");
			}else
			if (gua.get(i).equals("0")) {
				gua.set(i, "阴爻");
			}
		}
		for (int i = 5; i >=0; i--) {
			System.out.println(gua.get(i));
		}
		//上卦
		for (int i = 5; i > 2; i--) {
			if (gua.get(i).equals("阳爻")) {
				gua.set(i, "—");
			}else
			if (gua.get(i).equals("阴爻")) {
				gua.set(i, "--");
			}
		}
		//下卦
		for (int i = 2; i >= 0; i--) {
			if (gua.get(i).equals("阳爻")) {
				gua.set(i, "—");
			}else
			if (gua.get(i).equals("阴爻")) {
				gua.set(i, "--");
			}
		}
		//输出卦象
		for (int i = 5; i >=0; i--) {
			System.out.println(gua.get(i));
			if(i==3)
			{
				System.out.println("");
			}
		}
		String shang = null;
		String xia = null;
		if(gua.get(5).equals("—")&&gua.get(4).equals("—")&&gua.get(3).equals("—")) {shang="乾"; }
		if(gua.get(5).equals("--")&&gua.get(4).equals("—")&&gua.get(3).equals("—")) {shang="兑"; }
		if(gua.get(5).equals("—")&&gua.get(4).equals("--")&&gua.get(3).equals("—")) {shang="离"; }
		if(gua.get(5).equals("--")&&gua.get(4).equals("--")&&gua.get(3).equals("—")) {shang="震"; }
		if(gua.get(5).equals("—")&&gua.get(4).equals("—")&&gua.get(3).equals("--")) {shang="巽"; }
		if(gua.get(5).equals("--")&&gua.get(4).equals("—")&&gua.get(3).equals("--")) {shang="坎"; }
		if(gua.get(5).equals("—")&&gua.get(4).equals("--")&&gua.get(3).equals("--")) {shang="艮"; }
		if(gua.get(5).equals("--")&&gua.get(4).equals("--")&&gua.get(3).equals("--")) {shang="坤"; }
		if(gua.get(2).equals("—")&&gua.get(1).equals("—")&&gua.get(0).equals("—")) {xia="乾"; }
		if(gua.get(2).equals("--")&&gua.get(1).equals("—")&&gua.get(0).equals("—")) {xia="兑"; }
		if(gua.get(2).equals("—")&&gua.get(1).equals("--")&&gua.get(0).equals("—")) {xia="离"; }
		if(gua.get(2).equals("--")&&gua.get(1).equals("--")&&gua.get(0).equals("—")) {xia="震"; }
		if(gua.get(2).equals("—")&&gua.get(1).equals("—")&&gua.get(0).equals("--")) {xia="巽"; }
		if(gua.get(2).equals("--")&&gua.get(1).equals("—")&&gua.get(0).equals("--")) {xia="坎"; }
		if(gua.get(2).equals("—")&&gua.get(1).equals("--")&&gua.get(0).equals("--")) {xia="艮"; }
		if(gua.get(2).equals("--")&&gua.get(1).equals("--")&&gua.get(0).equals("--")) {xia="坤"; }
		System.out.println(shang);
		System.out.println(xia);
	}


}
