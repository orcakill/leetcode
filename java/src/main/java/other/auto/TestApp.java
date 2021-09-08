package other.auto;

import other.auto.entity.ClassPO;
import other.auto.util.CommonUtils;
import util.Case;

import java.util.List;

import static other.auto.util.CommonUtils.toType;

public class TestApp {
	public static void main (String[] args) throws ClassNotFoundException {
		Class<?> c = Class.forName ("other.scenario.entity.TaskInfoPO");
		final String name = "TaskInfoPO";
		List<ClassPO> classPOS = CommonUtils.getClassPO (c);
		String name1 = CommonUtils.toLower (name);
		StringBuilder str = new StringBuilder ();
		str.append ("    @Test\r\n");
		str.append ("    public void findById() throws SQLException {\r\n");
		String substring = name.substring (0, name.length () - 2);
		str.append ("        " + name + " ")
		   .append (name1)
		   .append ("=")
		   .append (substring);
		str.append ("Mapper");
		str.append (".");
		str.append ("findByID(");
		str.append ("BigDecimal.valueOf(1));\r\n");
		for (ClassPO classPO : classPOS) {
			str.append ("        System.out.println(")
			   .append (name1)
			   .append (".get")
			   .append (Case.toUpper (classPO.getName ()))
			   .append ("());\r\n");
		}
		str.append ("}")
		   .append ("\r\n")
		   .append ("    @Test\r\n")
		   .append ("    public void insert() throws SQLException {\r\n")
		   .append ("        Date date=new Date();\r\n")
		   .append ("        " + name + " ")
		   .append (name1)
		   .append ("=new ")
		   .append (name)
		   .append ("();\r\n");
		for (int i = 1; i < classPOS.size (); i++) {
			str.append ("        ")
			   .append (name1)
			   .append (".set")
			   .append (Case.toUpper (classPOS.get (i)
			                                  .getName ()))
			   .append ("(");
			if (toType (classPOS.get (i)
			                    .getType ()).equals ("String")) {
				str.append ("\"测试\"");
			}
			if ("Integer".equals (toType (classPOS.get (i)
			                                      .getType ()))) {
				str.append ("0");
			}
			if (toType (classPOS.get (i)
			                    .getType ()).equals ("Date")) {
				str.append ("date");
			}
			str.append (");\r\n");
		}
		str.append ("\r\n        ");
		str.append (name, 0, name.length () - 2);
		str.append ("Mapper.");
		str.append ("insert(");
		str.append (name1);
		str.append (");\r\n}");
		str.append ("\r\n");
		str.append ("    @Test\r\n");
		str.append ("    public void deleteAll() throws SQLException {\r\n");
		str.append ("        ")
		   .append (substring)
		   .append ("Mapper.deleteAll();");
		str.append ("    \r\n");
		str.append ("}");
		System.out.println (str);
	}
	
}
