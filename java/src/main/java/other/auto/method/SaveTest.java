package other.auto.method;

import other.auto.entity.ClassPO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SaveTest {
	public static StringBuilder saveTest (String name, String name1, List<ClassPO> classPOS,
	                                       List<ClassPO> classPOS1) {
		StringBuilder str=new StringBuilder ();
		Date date = new Date ();
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat ("yyyy-MM-dd");
		String  strDate=simpleDateFormat.format (date);
		str.append ("@Test\r\n");
		str.append ("public void save() throws SQLException {\n");
		return  str;
	}
}
