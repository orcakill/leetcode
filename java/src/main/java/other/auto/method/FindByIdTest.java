package other.auto.method;

import other.auto.entity.ClassPO;
import util.Case;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static other.dao.CommonUtils.getParamExample;


public class FindByIdTest {
	public static StringBuilder findByIdTest (String name, String name1, List<ClassPO> classPOS,
	                                        List<ClassPO> classPOS1) {
		StringBuilder str = new StringBuilder ();
		Date date = new Date ();
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat ("yyyy-MM-dd");
		String  strDate=simpleDateFormat.format (date);
		str.append ("@Test\r\n");
		str.append ("public void findById(");
		str.append (") throws SQLException {\r\n");
		String substring = name.substring (0, name.length () - 2);
		str.append ("\t")
		   .append (name)
		   .append (" ")
		   .append (name1)
		   .append ("=")
		   .append (substring);
		str.append ("Mapper");
		str.append (".");
		str.append ("findById(");
		getParamExample (classPOS1, str, strDate);
		str.append (");\r\n");
		for (ClassPO classPO : classPOS) {
			str.append ("\tSystem.out.println(")
			   .append (name1)
			   .append (".get")
			   .append (Case.toUpper (classPO.getName ()))
			   .append ("());\r\n");
		}
		str.append ("}")
		   .append ("\r\n\r\n");
		return str;
	}
}
