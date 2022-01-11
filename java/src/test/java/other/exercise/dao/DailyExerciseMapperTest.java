package other.exercise.dao;

import org.junit.Test;
import other.exercise.entity.DailyExercisePO;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author orcakill
 * @version 1.0.0
 * @ClassName DailyExerciseMapperTest.java
 * @Description TODO
 * @createTime 2022年01月10日 14:33:00
 */
public class DailyExerciseMapperTest {
	
	@Test
	public void save1() throws SQLException, ParseException {
		String time="10:45:00";
		String time1="60";
		Date date=new Date();
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat ("yyyy-MM-dd");
		SimpleDateFormat simpleDateFormat1=new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
		String date1=simpleDateFormat.format (date);
		String date2=date1+" "+time;
		Date date3=simpleDateFormat1.parse (date2);
		Timestamp timestamp=new Timestamp (date3.getTime ()+8 * 60 * 60 * 1000);
		DailyExercisePO dailyExercisePO=new DailyExercisePO();
		dailyExercisePO.setExerciseDay (timestamp);
		dailyExercisePO.setExerciseType("站桩");
		dailyExercisePO.setExerciseTime(time1);
		DailyExerciseMapper.save(dailyExercisePO);
	}
	
	@Test
	public void deleteAll() throws SQLException {
		DailyExerciseMapper.deleteAll();
	}
}