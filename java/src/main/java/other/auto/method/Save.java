package other.auto.method;

import other.auto.entity.ClassPO;
import other.auto.util.CommonUtils;
import util.Case;

import java.util.List;

import static other.auto.util.CommonUtils.toLowerLine;
import static other.auto.util.CommonUtils.toType;

public class Save {
	public static  StringBuilder save(String name, String name1, String table, List<ClassPO> classPOS,
	                                  List<ClassPO> classPOS1,List<ClassPO> classPOS2){
		StringBuilder str=new StringBuilder ();
		str.append ("/*保存方法*/\r\npublic static void save(" + name + " ")
		   .append (name1)
		   .append (") throws SQLException {\r\n")
		   .append ("\tBoolean check = check (dateToString (");
		for(int i=0;i<classPOS1.size ();i++){
			str.append (name1)
			   .append (".get")
			   .append (CommonUtils.toUpper (classPOS1.get (i).getName ()))
			   .append ("()");
			if(i<classPOS1.size ()-1){
				str.append (",");
			}
		}
		str.append (");\r\n")
		   .append ("\tif (check) {\r\n")
		   .append ("\tString sql=\"update ")
		   .append (table)
		   .append (" set ");
		for(int i=0;i<classPOS2.size ();i++){
			str.append (toLowerLine(classPOS2.get (i).getName ()))
					.append ("=?");
			if(i<classPOS2.size ()-1){
				str.append (",");
			}
		}
		str.append (" where ");
		for(int i=0;i<classPOS1.size ();i++){
			str.append (toLowerLine(classPOS1.get (i).getName ()))
			   .append ("=? ");
			if(i<classPOS1.size ()-1){
				str.append (" and ");
			}
		}
		str.append ("\";\r\n")
				.append ("\tConnection connection = Jdbc.getConnection ();\n" +
				         "\tPreparedStatement preparedStatement = connection.prepareStatement (sql);\r\n");
		int num=1;
		for(int i=0;i<classPOS2.size ();i++){
			str.append ("\tpreparedStatement.set");
			if(toType(classPOS2.get (i).getType ()).equals ("Integer")){
				str.append ("Int");
			}
			else {
				str.append (toType (classPOS2.get (i).getType ()));
			}
			str.append ("("+num+","+name+"."+")");
			num++;
			str.append ("\r\n");
		}
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
		   .append ("}")
		   .append ("\r\n")
		   .append ("\r\n");
		
		return  str;
	}
}
