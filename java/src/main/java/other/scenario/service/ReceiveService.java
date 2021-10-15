package other.scenario.service;

import other.scenario.service.impl.ReceiveMailImpl;

import java.awt.*;

public interface ReceiveService {
	//邮件领取
    static boolean receiveMail () throws InterruptedException, AWTException {
		return ReceiveMailImpl.receiveMail ();
	}
	
	//签到、领取每日勾玉、领取御魂加成、领取体力
	static boolean singIn () throws InterruptedException, AWTException {
		return ReceiveMailImpl.singIn ();
	}
	
	//领取体力食盒
	static boolean receiveBox () throws InterruptedException, AWTException {
		return ReceiveMailImpl.receiveBox ();
	}
	
}
