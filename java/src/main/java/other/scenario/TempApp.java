package other.scenario;

import other.scenario.map.ExeAddress;
import other.scenario.util.StartUpExeUtils;

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
/*直接重复挑战 1,2*/
class SoulApp {
	public static void main (String[] args) {
		ThreadFirst t1 = new ThreadFirst();
		ThreadSecond  t2 = new ThreadSecond ();
		t1.start();
		t2.start();
	}
}

//	自动登录,刷魂十一，个人结界 1 3
class SoulElevenApp {
	public static void main (String[] args) {
		ThreadFirst t1 = new ThreadFirst();
		ThreadThird  t2 = new ThreadThird ();
		t1.start();
		t2.start();
	}
}


//	自动登录，刷寮突破,处理个人结界，刷魂十一 1 4
class soulAllApp {
	public static void main (String[] args) {
		ThreadFirst t1 = new ThreadFirst();
		ThreadFourth  t2 = new ThreadFourth ();
		t1.start();
		t2.start();
	}
}

//	自动登录，刷寮突破,处理个人结界，刷业原火 1 5
class soulAll2App {
	public static void main (String[] args) {
		ThreadFirst t1 = new ThreadFirst();
		ThreadFifth  t2 = new ThreadFifth ();
		t1.start();
		t2.start();
	}
}



//	自动登录，刷魂十，个人结界 1 6
class SoulTenApp {
	public static void main (String[] args) {
		ThreadFirst t1 = new ThreadFirst();
		ThreadSixth  t2 = new ThreadSixth ();
		t1.start();
		t2.start();
	}
}

//	自动登录，刷御灵 1 7
class SpiritApp {
	public static void main (String[] args) {
		ThreadFirst t1 = new ThreadFirst();
		ThreadSeventh  t2 = new ThreadSeventh();
		t1.start();
		t2.start();
	}
}
 
//	自动登录，刷寮突破 1 8
class FightHomeApp {
	public static void main (String[] args) {
		ThreadFirst t1 = new ThreadFirst();
		ThreadEighth t2 = new ThreadEighth();
		t1.start();
		t2.start();
	}
}


//	自动登录，刷业原火，个人结界 1 9
class SoulFireApp {
	public static void  main (String[] args) {
		ThreadFirst t1 = new ThreadFirst();
		ThreadNinth t2 = new ThreadNinth();
		t1.start();
		t2.start();
	}
}

//	自动登录，刷日轮之陨，个人结界 1 10
class SoulSunApp {
	public static void main (String[] args) {
		ThreadFirst t1 = new ThreadFirst();
		ThreadTenth t2 = new ThreadTenth();
		t1.start();
		t2.start();
	}
}

//组队挑战 1 11
class SoulFriendApp {
	public static void main (String[] args) {
		ThreadFirst t1 = new ThreadFirst();
		ThreadEleventh t2 = new ThreadEleventh();
		t1.start();
		t2.start();
	}
}





