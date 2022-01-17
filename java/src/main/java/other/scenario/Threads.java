package other.scenario;

import lombok.SneakyThrows;
import other.scenario.controller.AutoLoginController;
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
			ImageService.imagesClickBackNumber (file1, 3, false);
			Thread.sleep (30*1000);
		}
	}
}


class   ThreadSecond extends  Thread {
	@SneakyThrows
	public void run () {
		System.out.println ("重复挑战");
		//重复 挑战150次
		Scanner myInput = new Scanner (System.in);
		System.out.println ("请输入一个整数:");
		int x = myInput.nextInt ();
		soulBack (x);
	}
}
class   ThreadThird extends  Thread {
		@SneakyThrows
		public void run () {
			System.out.println ("自动登录,刷魂十一，个人结界");
			//自动登录到探索
			AutoLoginController.login ();
			//刷魂十一或者刷个人结界
			//攻打轮次
			int num=1;
			//每轮次攻打次数
			int num1=60;
			//开启加成
			boolean b=true;
			AutoLoginController.soulEleven (num,num1,b);
		}
}
class   ThreadFourth extends  Thread {
	@SneakyThrows
	public void run () {
		System.out.println ("自动登录，刷寮突破,处理个人结界，刷魂十一");
		//自动登录到探索
		AutoLoginController.login ();
		//攻打轮次
		int num=1;
		//御魂类型
		int num1=11;
		AutoLoginController.soulAll(num,num1);
	}
}


class   ThreadFifth extends  Thread{
	@SneakyThrows
	public void run (){
		System.out.println("自动登录，刷寮突破,处理个人结界，刷业原火");
		//自动登录到探索
		AutoLoginController.login ();
		//攻打轮次
		int num=2;
		//御魂类型
		int num1=21;
		AutoLoginController.soulAll(num,num1);
	}
}


class   ThreadSixth extends  Thread{
	@SneakyThrows
	public void run (){
		System.out.println("自动登录，刷魂十，个人结界");
		//自动登录到探索
		AutoLoginController.login ();
		//刷魂十或者刷个人结界
		//攻打轮次
		int num=1;
		//每轮次攻打次数
		int num1=120;
		//开启加成
		boolean b=false;
		AutoLoginController.soulTen (num,num1,b);
	}
}


class   ThreadSeventh extends  Thread{
	@SneakyThrows
	public void run (){
		System.out.println("自动登录，刷御灵");
		//自动登录到探索
		AutoLoginController.login ();
		//刷御灵
		//攻打轮次
		int num=1;
		AutoLoginController.spirit(num);
	}
}

class   ThreadEighth extends  Thread{
	@SneakyThrows
	public void run (){
		System.out.println("自动登录，刷寮突破");
		//自动登录到探索
		AutoLoginController.login ();
		//刷魂十或者刷个人结界
		//攻打轮次
		int num=12;
		AutoLoginController.fightHome(num,true);
	}
}


class   ThreadNinth extends  Thread{
	@SneakyThrows
	public void run (){
		System.out.println("自动登录，刷业原火，个人结界");
		//自动登录到探索
		AutoLoginController.login ();
		//刷魂业原火或者刷个人结界
		//攻打轮次
		int num=1;
		//每轮次攻打次数
		int num1=120;
		//开启加成
		boolean b=false;
		AutoLoginController.soulFire (num,num1,b);
	}
}


class   ThreadTenth extends  Thread{
	@SneakyThrows
	public void run (){
		System.out.println("自动登录，刷日轮之陨，个人结界");
		//自动登录到探索
		AutoLoginController.login ();
		//刷魂业原火或者刷个人结界
		//攻打轮次
		int num=1;
		//每轮次攻打次数
		int num1=50;
		//开启加成
		boolean b=false;
		AutoLoginController.soulSun (num,num1,b);
	}
}

class   ThreadEleventh extends  Thread{
	@SneakyThrows
	public void run (){
		System.out.println("组队挑战");
		AutoLoginController.soulFriend();
	}
}

class   ThreadTwelfth extends  Thread{
	@SneakyThrows
	public void run (){
		System.out.println("斗技");
		AutoLoginController.PVP();
	}
}