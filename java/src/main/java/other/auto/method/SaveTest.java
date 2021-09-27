package other.auto.method;

import other.auto.entity.ClassPO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static other.auto.util.CommonUtils.*;

public class SaveTest {
	public static StringBuilder saveTest (String name, String name1, List<ClassPO> classPOS,
	                                      List<ClassPO> classPOS1) {
		StringBuilder str = new StringBuilder ();
		Date date = new Date ();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat ("yyyy-MM-dd");
		String strDate = simpleDateFormat.format (date);
		str.append ("@Test\r\n");
		str.append ("public void save() throws SQLException {\n")
		   .append ("\t" + name + " " + name1 + "=new " + name + "();\r\n");
		for (int i = 0; i < classPOS.size (); i++) {
			str.append ("\t" + name1 + ".set" + toUpper (classPOS.get (i)
			                                                     .getName ()))
			   .append ("(");
			if (toType (classPOS.get (i)
			                    .getType ()).equals ("Date")) {
				str.append ("CommonUtils.getDate()");
			}
			if (toType (classPOS.get (i)
			                    .getType ()).equals ("Integer")) {
				str.append (1);
			}
			if (toType (classPOS.get (i)
			                    .getType ()).equals ("String")) {
				str.append ("\"测试\"");
			}
			str.append (");\n");
			
		}
		str.append ("\t"+name.replaceAll ("PO", ""))
		   .append ("Mapper.save(")
		   .append (name1)
		   .append (");\n")
		   .append ("}\n\n");
		return str;
	}
}
