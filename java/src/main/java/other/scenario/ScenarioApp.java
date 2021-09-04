package other.scenario;

import other.scenario.util.PlanTimerTask;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class ScenarioApp {
	public static void main (String[] args) throws Exception {
    /*阴阳师脚本
     可执行任务列表
        1、自动登录（凌晨0-1点）                          -小号（）、大号（）
        2、提示账号在其他手机登录，则过一小时后再登录         -小号（）、大号（）
        3、定时检查是否需要重新登录                     -小号（）、大号（）
        4、邮件领取                                 -小号（）、大号（）
        5、好友删除，离线超30天的好友删除好友关系          -小号（）、大号（）
        6、好友添加，好友不满上限则根据推荐好友添加        -小号（）、大号（）
        7、体力领取                                -小号（）、大号（）
        8、经验领取                                 -小号（）、大号（）
        9、替换经验已满的式神                         -小号（）、大号（）
        10、更换本地结界卡，变异卡、高星级优先            -小号（）、大号（）
        11、更换寄养结界卡，变异卡、高星优先              -小号（）、大号（）
        12、樱饼刷御魂，使用好友式神                        -小号（）
        13、樱饼刷经验，使用好友式神                        -小号（）
        14、领取当日花合战奖励                             -小号（）
        15、个人突破，击破三个一换或剩余全是击破失败一换，
                  无挑战劵退出                        -小号（）、大号（）
        16、个人突破，击破三个一换或剩余全是击破失败一换，
                  无挑战劵退出                        -小号（）、大号（）
        17、寮突破，穿插个人突破、御魂、经验，
                剩余全是击破失败或全部或结束             -大号（）
        18、无体力结束或任务完成或单个账号登录时间满2小时退出 -小号（）、大号（）
        19、周三6-9点下线，任务处理截止到中午12点，截止后发送每日报告到邮箱  -小号（）、大号（）
        20、继续下个账号，3个账号退出后登录大号或结束              -小号（）、大号（）
        21、自动斗技                                         -小号（）
    */
		Calendar calendar = Calendar.getInstance ();
		TimerTask task = new PlanTimerTask ();
		Date firstTime = calendar.getTime ();
		//间隔：1分钟
		long period = 1000 * 60;
		
		Timer timer = new Timer ();
		timer.schedule (task, firstTime, period);
		
	}
}
