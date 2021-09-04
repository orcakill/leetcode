package other.auto;

import other.auto.entity.ClassPO;
import util.Case;

import javax.persistence.Id;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TestApp {
	public static void main (String[] args) throws ClassNotFoundException {
		Class<?> c = Class.forName ("other.scenario.entity.TaskInfoPO");
		final String name = "TaskInfoPO";
		Field[] field = c.getDeclaredFields ();
		List<ClassPO> classPOS = new ArrayList<> ();
		for (Field f : field) {
			f.setAccessible (true); /* 设置些属性是可以访问的*/
			String type = f.getType ()
			               .toString ();/* 得到此属性的类型*/
			String key = f.getName ();/* key:得到属性名*/
			int text = 0;
			Id id = f.getAnnotation (Id.class);
			if (id != null) {
				text = 1;
			}
			ClassPO classPO = new ClassPO ();
			classPO.setName (key);
			classPO.setType (type);
			classPO.setPk (text);
			classPOS.add (classPO);
		}
		String name1 = name.substring (0, 1)
		                   .toLowerCase (Locale.ROOT) + name.substring (1);
		StringBuilder str = new StringBuilder ().append ("    @Test\r\n")
		                                        .append ("    public void findById() throws SQLException {\r\n")
		                                        .append ("        " + name + " " + name1 + "=" + name.substring (0,
				                                        name.length () - 2))
		                                        .append ("Mapper")
		                                        .append (".")
		                                        .append ("findByID(")
		                                        .append ("BigDecimal.valueOf(1));\r\n");
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
			if ("Integer" .equals (toType (classPOS.get (i)
			                                       .getType ()))) {
				str.append ("0");
			}
			if (toType (classPOS.get (i)
			                    .getType ()).equals ("Date")) {
				str.append ("date");
			}
			str.append (");\r\n");
		}
		str.append ("\r\n        " + name.substring (0, name.length () - 2))
		   .append ("Mapper.")
		   .append ("insert(")
		   .append (name1)
		   .append (");\r\n}")
		   .append ("\r\n")
		   .append ("    @Test\r\n")
		   .append ("    public void deleteAll() throws SQLException {\r\n")
		   .append ("        " + name.substring (0, name.length () - 2) + "Mapper.deleteAll();")
		   .append ("    \r\n")
		   .append ("}");
		System.out.println (str);
	} /*将java.lang.Integer转换为Integer*/
	
	public static String toType (String str) {
		return str.substring (str.lastIndexOf (".") + 1);
	} /*将emailId转换为email_id*/
	
	public static String toLowerLine (String str) {
		if (str.length () == 0) {
			return str;
		}
		StringBuilder stringBuilder = new StringBuilder ();
		for (int i = -0; i < str.length (); i++) {
			char c = str.charAt (i);
			if (Character.isLowerCase (c)) {
				stringBuilder.append (c);
			}
			else {
				stringBuilder.append ("_")
				             .append (str.substring (i, i + 1)
				                         .toLowerCase ());
			}
		}
		return String.valueOf (stringBuilder);
	}
}
