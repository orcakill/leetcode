package other.scenario;


import other.scenario.controller.AutoLoginController;
import other.scenario.map.ExeAddress;
import other.scenario.service.ImageService;
import other.scenario.util.StartUpExeUtils;

import java.awt.*;

import static other.scenario.service.FightAutoService.soulBack;

/**
 * @Classname TempApp
 * @Description 刷御魂（随机时间）
 * @Date 2021/10/19 22:25
 * @Created by orcakill
 */
/*刷御魂*/
//启动模拟器，需要调整成全屏后隐藏到后台
public class TempApp {
	public static void main (String[] args) throws Exception {
		//启动程序
		StartUpExeUtils.startUpExeOnly ("CMD /C " + ExeAddress.exeAddress ());
	}
}

/*拒接协战*/
class RefuseApp {
	public static void main (String[] args) throws Exception {
		refuse ();
	}
	public static void refuse () throws InterruptedException, AWTException {
		for (int i = 0; i <= 150; i++) {
			String file1 = "scenario/御魂/拒接协战";
			Thread.sleep (3000);
			ImageService.imagesClickBackNumber (file1, 30, false);
		}
	}
}

/*直接重复挑战*/
class SoulApp {
	public static void main (String[] args) throws Exception {
		//重复 挑战150次
		soulBack (50);
	}
}



//	自动登录,刷魂十一，个人结界
class SoulElevenApp {
	public static void main (String[] args) throws Exception {
		//自动登录到探索
		AutoLoginController.login ();
	    //刷魂十一或者刷个人结界
		//攻打轮次
		int num=3;
		//每轮次攻打次数
		int num1=120;
		//开启加成
		boolean b=true;
		AutoLoginController.soulEleven (num,num1,b);
		
	}
}


//	自动登录，刷寮突破,处理个人结界，刷魂十一
class soulAllApp {
	public static void main (String[] args) throws Exception {
		//自动登录到探索
		AutoLoginController.login ();
		//攻打轮次
		int num=12;
		//御魂类型
		int num1=11;
		AutoLoginController.soulAll(num,num1);
	}
}

//	自动登录，刷寮突破,处理个人结界，刷业原火
class soulAll2App {
	public static void main (String[] args) throws Exception {
		//自动登录到探索
		AutoLoginController.login ();
		//攻打轮次
		int num=12;
		//御魂类型
		int num1=21;
		AutoLoginController.soulAll(num,num1);
	}
}



//	自动登录，刷魂十，个人结界
class SoulTenApp {
	public static void main (String[] args) throws Exception {
		//自动登录到探索
		AutoLoginController.login ();
		//刷魂十或者刷个人结界
		//攻打轮次
		int num=3;
		//每轮次攻打次数
		int num1=120;
		//开启加成
		boolean b=false;
		AutoLoginController.soulTen (num,num1,b);
	}
}

//	自动登录，刷御灵
class SpiritApp {
	public static void main (String[] args) throws Exception {
		//自动登录到探索
		AutoLoginController.login ();
		//刷魂十或者刷个人结界
		//攻打轮次
		int num=3;
		AutoLoginController.spirit(num);
	}
}
 
//	自动登录，刷寮突破
class FightHomeApp {
	public static void main (String[] args) throws Exception {
		//自动登录到探索
		AutoLoginController.login ();
		//刷魂十或者刷个人结界
		//攻打轮次
		int num=12;
		AutoLoginController.fightHome(num,true);
	}
}


//	自动登录，刷业原火，个人结界
class SoulFireApp {
	public static void main (String[] args) throws Exception {
		//自动登录到探索
		AutoLoginController.login ();
		//刷魂业原火或者刷个人结界
		//攻打轮次
		int num=3;
		//每轮次攻打次数
		int num1=120;
		//开启加成
		boolean b=false;
		AutoLoginController.soulFire (num,num1,b);
	}
}

//	自动登录，刷日轮之陨，个人结界
class SoulSunApp {
	public static void main (String[] args) throws Exception {
		//自动登录到探索
		AutoLoginController.login ();
		//刷魂业原火或者刷个人结界
		//攻打轮次
		int num=3;
		//每轮次攻打次数
		int num1=50;
		//开启加成
		boolean b=false;
		AutoLoginController.soulSun (num,num1,b);
	}
}




