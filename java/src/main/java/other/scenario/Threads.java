package other.scenario;

import lombok.SneakyThrows;
import other.scenario.service.ImageService;

import java.util.Scanner;

import static other.scenario.service.FightAutoService.soulBack;

/**
 * @author orcakill
 * @version 1.0.0
 * @ClassName Treads.java
 * @Description TODO
 * @createTime 2022年01月04日 09:24:00
 */
public class Threads {
}

class   ThreadFirst extends  Thread{
	@SneakyThrows
	public void run (){
		System.out.println("拒接协战");
		String file1 = "scenario/御魂/拒接协战";
		for (int i = 0; i <= 300; i++) {
			Thread.sleep (30*1000);
			ImageService.imagesClickBackNumber (file1, 3, false);
		}
	}
}


class   ThreadSecond extends  Thread{
	@SneakyThrows
	public void run (){
		System.out.println("重复挑战");
		//重复 挑战150次
		Scanner myInput=new Scanner (System.in);
		System.out.println("请输入一个整数:");
		int x=myInput.nextInt();
		soulBack (x);
	}
}