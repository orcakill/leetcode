package other.auto.method;

import other.auto.entity.ClassPO;


import java.util.List;

import static other.dao.CommonUtils.*;

public class SaveTest {
	public static StringBuilder saveTest (String name, String name1, List<ClassPO> classPOS) {
		StringBuilder str = new StringBuilder ();
		str.append ("@Test\r\n");
		str.append ("public void save() throws SQLException {\n")
		   .append ("\t")
		   .append (name)
		   .append (" ")
		   .append (name1)
		   .append ("=new ")
		   .append (name)
		   .append ("();\r\n");
		for (ClassPO classPO : classPOS) {
			str.append ("\t")
			   .append (name1)
			   .append (".set")
			   .append (toUpper (classPO
					   .getName ()))
			   .append ("(");
			if (toType (classPO
					.getType ()).equals ("Date")) {
				str.append ("new Date()");
			}
			if (toType (classPO
					.getType ()).equals ("Integer")) {
				str.append (1);
			}
			if (toType (classPO
					.getType ()).equals ("String")) {
				str.append ("\"测试\"");
			}
			str.append (");\n");
			
		}
		str.append ("\t")
		   .append (name.replaceAll ("PO", ""))
		   .append ("Mapper.save(")
		   .append (name1)
		   .append (");\n")
		   .append ("}\n\n");
		return str;
	}
}
