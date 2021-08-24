package other.auto;

import other.auto.entity.ClassPO;
import util.Case;

import javax.persistence.Id;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MapperApp {
	public static void main (String[] args) throws ClassNotFoundException {        /* 赋值类名*/
		Class<?> c = Class.forName ("other.mail.model.entity.MessageEventPO");
		final String name = "MessageEventPO";
		String table = "message_event";
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
		} /*根据id查询一条数据的mapper方法*/
		String name1 = name.substring (0, 1)
		                   .toLowerCase (Locale.ROOT) + name.substring (1);
		StringBuilder str =
				new StringBuilder ().append ("private static final Logger logger = LogManager.getLogger(" + name +
				                             ".class); \r\n" + "public static " + name + " findById(BigDecimal id) " +
				                             "throws SQLException {  \r\n" + "  " + name + " ")
				                    .append (name1)
				                    .append (" =new ")
				                    .append (name)
				                    .append ("();\r\n")
				                    .append ("  " + "String sql=" + "\"select * from ")
				                    .append (table)
				                    .append (" where ")
				                    .append (toLowerLine (classPOS.get (0)
				                                                  .getName ()))
				                    .append ("=")
				                    .append ("\"+id;  \r\n")
				                    .append ("  Connection connection= Jdbc.getConnection();\r\n  " + "Statement " +
				                             "statement=connection.createStatement(); \r\n  " + "ResultSet " +
				                             "resultSet=statement.executeQuery(sql);\r\n  " + "while(resultSet.next())" +
				                             "{\r\n");
		for (int i = 0; i < classPOS.size (); i++) {
			str.append ("    ")
			   .append (name1)
			   .append (".set")
			   .append (Case.toUpper (classPOS.get (i)
			                                  .getName ()))
			   .append ("(resultSet.get");
			if (toType (classPOS.get (i)
			                    .getType ()).equals ("BigInteger")) {
				str.append ("BigDecimal");
			}
			else if (toType (classPOS.get (i)
			                         .getType ()).equals ("Integer")) {
				str.append ("Int");
			}
			else {
				str.append (toType (classPOS.get (i)
				                            .getType ()));
			}
			str.append ("(")
			   .append (i + 1)
			   .append ("))")
			   .append ("; \r\n");
		}
		str.append ("    }\r\n")
		   .append ("    Jdbc.release(null, statement, connection);\r\n")
		   .append ("    return ")
		   .append (name1)
		   .append (";\r\n")
		   .append ("}\r\n")
		   .append ("public static void insert(" + name + " ")
		   .append (name1)
		   .append (") throws SQLException {\r\n")
		   .append ("  ")
		   .append ("String sql=")
		   .append ("\"")
		   .append ("insert into ")
		   .append (table)
		   .append ("(");
		for (int i = 0; i < classPOS.size (); i++) {
			str.append (toLowerLine (classPOS.get (i)
			                                 .getName ()));
			if (i != classPOS.size () - 1) {
				str.append (",");
			}
		}
		str.append (")" + "values(");
		for (int i = 0; i < classPOS.size (); i++) {
			str.append ("?");
			if (i != classPOS.size () - 1) {
				str.append (",");
			}
		}
		str.append (")" + "\"" + ";\r\n")
		   .append ("  Connection connection = Jdbc.getConnection();\r\n")
		   .append ("  PreparedStatement preparedStatement = connection.prepareStatement(sql);\r\n");
		for (int i = 0; i < classPOS.size (); i++) {
			str.append ("    ")
			   .append ("preparedStatement.set");
			if (toType (classPOS.get (i)
			                    .getType ()).equals ("Integer")) {
				str.append ("Int");
			}
			else {
				str.append (toType (classPOS.get (i)
				                            .getType ()));
			}
			str.append ("(")
			   .append (i + 1)
			   .append (",");
			if (toType (classPOS.get (i)
			                    .getType ()).equals ("Date")) {
				str.append ("new java.sql.Date(")
				   .append (name1)
				   .append (".get")
				   .append (Case.toUpper (classPOS.get (i)
				                                  .getName ()))
				   .append ("()")
				   .append (".getTime()")
				   .append (")");
			}
			else {
				str.append (name1)
				   .append (".get")
				   .append (Case.toUpper (classPOS.get (i)
				                                  .getName ()))
				   .append ("()");
			}
			str.append (")")
			   .append ("; \r\n");
		}
		str.append ("    ")
		   .append ("int num =preparedStatement.executeUpdate();\r\n")
		   .append ("    if(num>0){\r\n")
		   .append ("    logger.info(\"插入成功\");\n")
		   .append ("    }\r\n")
		   .append ("    else{\r\n")
		   .append ("    logger.error(\"插入失败\");\r\n")
		   .append ("    }\r\n")
		   .append ("    Jdbc.release(null, preparedStatement, connection);\r\n")
		   .append ("}")
		   .append ("\r\n")
		   .append ("\r\n")
		   .append ("public static void deleteAll () throws SQLException {\r\n")
		   .append ("\t\tString sql = \"delete   from ")
		   .append (table)
		   .append ("\";\r\n")
		   .append ("\t\tConnection connection = Jdbc.getConnection ();\r\n")
		   .append ("\t\tPreparedStatement preparedStatement = connection.prepareStatement (sql);\r\n")
		   .append ("\t\tint num = preparedStatement.executeUpdate ();\r\n")
		   .append ("\t\tif (num > 0) {\n\t\t\tlogger.info (\"删除成功\");\n\t\t}\n\t\telse {\n\t\t\tlogger.error (\"删除失败\");\n\t\t}\n\t\tJdbc.release (null, preparedStatement, connection);\n\t}");
		System.out.println (str);
	}
	
	public static String toType (String str) { /*将java.lang.Integer转换为Integer*/
		return str.substring (str.lastIndexOf (".") + 1);
	}
	
	public static String toLowerLine (String str) { /*将emailId转换为email_id*/
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
