package other.auto.method;

import other.auto.entity.ClassPO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static other.auto.util.CommonUtils.*;

public class CheckTest {
	public static StringBuilder checkTest (String name,
	                                       List<ClassPO> classPOS1) {
	StringBuilder stringBuilder=new StringBuilder ();
		Date date = new Date ();
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat ("yyyy-MM-dd");
		String  strDate=simpleDateFormat.format (date);
		stringBuilder.append ("@Test\r\n");
		stringBuilder.append ("public void check() throws SQLException {\n")
		             .append ("\tBoolean boolean1=")
		             .append (toUpper (name).replaceAll ("PO", ""))
		             .append ("Mapper.check(");
		getParamExample (classPOS1, stringBuilder, strDate);
		stringBuilder.append (");\n");
		stringBuilder.append ("\tSystem.out.println (boolean1);\r\n")
				.append ("}\n\n");
	return  stringBuilder;
	}
	

}
