package other.mail.test;

import java.util.Calendar;
import java.util.Date;

public class TimeTest {
	public static void main (String[] args){
		Date date=new Date ();
		Calendar calendar = Calendar.getInstance ();
		calendar.setTime (date);
		for(int i=0;i<10;i++) {
			calendar.add (Calendar.HOUR, 1);
			int hour = calendar.get (Calendar.HOUR_OF_DAY);
			int minute = calendar.get (Calendar.MINUTE);
			System.out.println (hour +":"+ "00");
		}
	}
}
