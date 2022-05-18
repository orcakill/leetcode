package other.exercise.entity;

import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * @author orcakill
 * @version 1.0.0
 * @ClassName Exercise.java
 * @Description TODO
 * @createTime 2022年01月10日 11:48:00
 */
@Data
public class DailyExercisePO {
	@Id
	private BigDecimal exerciseId; /*锻炼ID*/
	private Timestamp exerciseDay; /*锻炼日期*/
	private String exerciseType; /*锻炼类型*/
	private String exerciseTime; /*锻炼时间*/
	
}
