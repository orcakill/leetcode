package other.scenario;

import other.scenario.util.PlanTimerTask;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class ScenarioApp {
	public static void main (String[] args) throws Exception {
    /*阴阳师脚本
      1、自动登录和关机功能
     小号123：检查手机端是否已登录（）
             定时检查是否需要重新登录（）
             每天凌晨1点开始，无体力结束或任务完成或单个账号登录时间满2小时退出（）
             周三6-9点下线，任务处理截止到中午12点，截止后发送每日报告到邮箱()
             继续下个账号，3个账号退出后登录大号或结束（）
     大号：小号已结束登录后登录（）
            检查手机端是否已登录（）
            定时检查是否需要重新登录（）
     2、可执行任务列表
        邮件领取（）                                 -小号（）、大号（）
        好友删除，离线超30天的好友删除好友关系()          -小号（）、大号（）
        好友添加，好友不满上限则根据推荐好友添加 （）       -小号（）、大号（）
        体力领取（）                                 -小号（）、大号（）
        经验领取（）                                 -小号（）、大号（）
        替换经验已满的式神（）                         -小号（）、大号（）
        更换本地结界卡，变异卡、高星级优先（）            -小号（）、大号（）
        更换寄养结界卡，变异卡、高星优先（）              -小号（）、大号（）
        樱饼刷御魂，使用好友式神                        -小号（）
        樱饼刷经验，使用好友式神                        -小号（）
        领取当日花合战奖励                             -小号（）
        个人突破，击破三个一换或剩余全是击破失败一换，
                  无挑战劵退出                        -小号（）、大号（）
        个人突破，击破三个一换或剩余全是击破失败一换，
                  无挑战劵退出                        -小号（）、大号（）
        寮突破，穿插个人突破、御魂、经验，
                剩余全是击破失败或全部或结束             -大号（）
    */
		Calendar calendar= Calendar.getInstance ();
		TimerTask task = new PlanTimerTask ();
		Date firstTime = calendar.getTime ();
		//间隔：1分钟
		long period = 1000 * 60;
		
		Timer timer = new Timer();
		timer.schedule(task, firstTime, period);
		
		
	
	}
}
