package other.auto.method;

import other.auto.entity.ClassPO;
import other.dao.CommonUtils;
import util.Case;

import java.util.List;

import static other.dao.CommonUtils.*;

public class Save {
	public static StringBuilder save (String name, String name1, String table, List<ClassPO> classPOS,
	                                  List<ClassPO> classPOS1, List<ClassPO> classPOS2) {
		StringBuilder str = new StringBuilder ();
		str.append ("/*保存方法*/\r\npublic static void save(")
		   .append (name)
		   .append (" ")
		   .append (name1)
		   .append (") throws SQLException {\r\n")
		   .append ("\tBoolean check = check (");
		for (int i = 0; i < classPOS1.size (); i++) {
			if (toType (classPOS1.get (i)
			                     .getType ()).equals ("Date")) {
				str.append ("dateToString (")
				   .append (name1)
				   .append (".get")
				   .append (CommonUtils.toUpper (classPOS1.get (i)
				                                          .getName ()))
				   .append ("()")
				   .append (")");
			}
			else {
				str.append (name1)
				   .append (".get")
				   .append (CommonUtils.toUpper (classPOS1.get (i)
				                                          .getName ()))
				   .append ("()");
				
			}
			if (i < classPOS1.size () - 1) {
				str.append (",");
			}
		}
		str.append (");\r\n")
		   .append ("\tif (check) {\r\n")
		   .append ("\tString sql=\"update ")
		   .append (table)
		   .append (" set ");
		for (int i = 0; i < classPOS2.size (); i++) {
			str.append (toLowerLine (classPOS2.get (i)
			                                  .getName ()))
			   .append ("=?");
			if (i < classPOS2.size () - 1) {
				str.append (",");
			}
		}
		str.append (" where ");
		for (int i = 0; i < classPOS1.size (); i++) {
			str.append (toLowerLine (classPOS1.get (i)
			                                  .getName ()))
			   .append ("=? ");
			if (i < classPOS1.size () - 1) {
				str.append (" and ");
			}
		}
		str.append ("\";\r\n")
		   .append ("\tConnection connection = Jdbc.getConnection ();\n" +
		            "\tPreparedStatement preparedStatement = connection.prepareStatement (sql);\r\n");
		int num = 1;
		for (ClassPO classPO : classPOS2) {
			str.append ("\tpreparedStatement.set");
			if (toType (classPO
					.getType ()).equals ("Integer")) {
				str.append ("Int");
			}
			else {
				str.append (toType (classPO
						.getType ()));
			}
			str.append ("(")
			   .append (num)
			   .append (",");
			if (toType (classPO
					.getType ()).equals ("Date")) {
				str.append ("new java.sql.Date (")
				   .append (toLower (name))
				   .append (".get")
				   .append (toUpper (classPO
						   .getName ()))
				   .append ("().getTime")
				   .append ("()))")
				   .append (";");
			}
			else {
				str.append (toLower (name))
				   .append (".get")
				   .append (toUpper (classPO
						   .getName ()))
				   .append ("());");
			}
			num++;
			str.append ("\r\n");
		}
		for (ClassPO classPO : classPOS1) {
			str.append ("\tpreparedStatement.set");
			if (toType (classPO
					.getType ()).equals ("Integer")) {
				str.append ("Int");
			}
			else {
				str.append (toType (classPO
						.getType ()));
			}
			str.append ("(")
			   .append (num)
			   .append (",");
			if (toType (classPO
					.getType ()).equals ("Date")) {
				str.append ("new java.sql.Date (")
				   .append (toLower (name))
				   .append (".get")
				   .append (toUpper (classPO
						   .getName ()))
				   .append ("()")
				   .append (".getTime()))")
				   .append (";");
			}
			else {
				str.append (toLower (name))
				   .append (".get")
				   .append (toUpper (classPO
						   .getName ()))
				   .append ("());");
			}
			
			num++;
			str.append ("\r\n");
		}
		str.append ("\tint num = preparedStatement.executeUpdate ();\n" +
		            "\tif (num > 0) {\n" +
		            "\t\tlogger.info (\"更新成功\");\n" +
		            "\t}\n" +
		            "\telse {\n" +
		            "\t\tlogger.error (\"更新失败\");\n" +
		            "\t}\n" +
		            "\tJdbc.release (null, preparedStatement, connection);\n" +
		            "\t}\n" +
		            "\telse {");
		str.append ("\r\n\tString sql=")
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
		   .append ("\tConnection connection = Jdbc.getConnection();\r\n")
		   .append ("\tPreparedStatement preparedStatement = connection.prepareStatement(sql);\r\n");
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
		   .append ("    }\r\n")
		   .append ("}")
		   .append ("\r\n")
		   .append ("\r\n");
		
		return str;
	}
}
