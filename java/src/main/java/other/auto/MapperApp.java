package other.auto;

import other.auto.entity.ClassPO;
import other.auto.util.CommonUtils;
import util.Case;

import java.util.List;

import static other.auto.util.CommonUtils.*;

public class MapperApp {
	public static void main (String[] args) throws ClassNotFoundException {        /* 赋值类名*/
		Class<?> c = Class.forName ("other.scenario.entity.TaskListPO");
		final String name = "TaskListPO";
		String table = "task_list";
		List<ClassPO> classPOS = CommonUtils.getClassPO (c);
		List<ClassPO> classPOS1=CommonUtils.getPk (classPOS);
		String name1 = CommonUtils.toLower (name);
		/*根据主键查询一条数据*/
		StringBuilder str =
				new StringBuilder ().append ("private static final Logger logger = LogManager.getLogger(" + name +
				                             ".class); \r\n\r\n" + "/*根据主键查询一条数据*/\r\npublic static " + name + " " +
				                             "findById(");
		getId (classPOS, str);
		str.append (") throws SQLException {  \r\n" + "  " + name + " ")
		   .append (name1)
		   .append (" =new ")
		   .append (name)
		   .append ("();\r\n")
		   .append ("  " + "String sql=" + "\"select * from ")
		   .append (table)
		   .append (" where ");
		for(int i=0;i<classPOS1.size ();i++){
			if(i>0){
				str.append (" and ");
			}
			str.append (toLowerLine (classPOS1.get (i).getName ()))
			   .append ("=?");
		}
		str.append ("\"; \r\n")
		   .append ("  Connection connection= Jdbc.getConnection();\r\n  " + "PreparedStatement " +
		            "preparedStatement=connection.prepareStatement(); \r\n");
		for(int i=0;i<classPOS1.size ();i++){
			str.append ("  preparedStatement.setObject (")
			   .append (i)
			   .append (",")
			   .append (classPOS1.get (i)
			                     .getName ())
			   .append (");\r\n");
		}
		str.append ("  ResultSet resultSet=preparedStatement.executeQuery(sql);\r\n");
		str.append ("  while(resultSet.next())" +
		            "{\r\n");
		getResultSet (classPOS, name1, str);
		str.append ("    }\r\n")
		   .append ("    Jdbc.release(null, statement, connection);\r\n")
		   .append ("    return ")
		   .append (name1)
		   .append (";\r\n")
		   .append ("}\r\n\r\n");
		/*查询所有数据的mapper方法*/
		str.append ("/*查询全部数据*/\r\npublic static " + name + " findAll() ");
		str.append ("throws SQLException {  \r\n" + "  " + "List<" + name + "> ")
		   .append (name1)
		   .append ("List")
		   .append ("=new ArrayList<>")
		   .append ("();\r\n")
		   .append ("  " + "String sql=" + "\"select * from ")
		   .append (table)
		   .append ("\";  \r\n")
		   .append ("  Connection connection= Jdbc.getConnection();\r\n  " + "Statement " +
		            "statement=connection.createStatement(); \r\n  " + "ResultSet " +
		            "resultSet=statement.executeQuery(sql);\r\n  " + "while(resultSet.next())" +
		            "{\r\n")
		   .append ("    ")
		   .append (name + " ")
		   .append (name1)
		   .append (" =new ")
		   .append (name)
		   .append ("();\r\n");
		
		getResultSet (classPOS, name1, str);
		str.append ("    ")
		   .append (name1)
		   .append ("List.add(")
		   .append (name1)
		   .append (");\r\n")
		   .append ("    }\r\n")
		   .append ("    Jdbc.release(null, statement, connection);\r\n")
		   .append ("    return ")
		   .append (name1)
		   .append (";\r\n")
		   .append ("}\r\n\r\n");
		
		/*根据主键判断数据已存在的mapper方法*/
		str.append ("/*根据主键判断数据已存在*/\r\npublic static " + "Boolean" + " check(");
		getId (classPOS, str);
		str.append (") throws SQLException {  \r\n")
		   .append ("  " + "String sql=" + "\"select count(*) num from ")
		   .append (table)
		   .append (" where ");
		for(int i=0;i<classPOS1.size ();i++){
			if(i>0){
				str.append (" and ");
			}
			str.append (toLowerLine (classPOS1.get (i).getName ()))
			   .append ("=?");
		}
		str.append ("\"; \r\n")
		   .append ("  Connection connection= Jdbc.getConnection();\r\n  " + "PreparedStatement " +
		            "preparedStatement=connection.prepareStatement(); \r\n");
		for(int i=0;i<classPOS1.size ();i++){
			str.append ("  preparedStatement.setObject (")
			   .append (i)
			   .append (",")
			   .append (classPOS1.get (i)
			                     .getName ())
			   .append (");\r\n");
		}
		str.append ("  ResultSet resultSet=preparedStatement.executeQuery();\r\n");
		str.append ("  int num = 0;\r\n")
				.append ("\twhile (resultSet.next ()) {\r\n")
				.append ("\t\tnum = resultSet.getInt (\"num\");\r\n\t}\r\n")
				.append ("\tJdbc.release (null, preparedStatement, connection);\r\n")
				.append ("\treturn num != 0;\r\n");
		str.append ("}\r\n\r\n");
		
		/*保存方法*/
		str.append ("/*保存方法*/\r\n public static void save(" + name + " ")
		   .append (name1)
		   .append (") throws SQLException {\r\n")
		   .append ("Boolean check = check (dateToString (");
		   for(int i=0;i<classPOS1.size ();i++){
			   str.append (name1)
			      .append ("get")
			      .append (name);
		   }
		   str.append ("taskListPO.getTaskListDate ()), taskListPO" +
				         ".getTaskListNum ());c")
		   .append (");\r\n")
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
		   .append ("/*删除方法*/\r\n public static void deleteAll () throws SQLException {\r\n")
		   .append ("\t\tString sql = \"delete   from ")
		   .append (table)
		   .append ("\";\r\n")
		   .append ("\t\tConnection connection = Jdbc.getConnection ();\r\n")
		   .append ("\t\tPreparedStatement preparedStatement = connection.prepareStatement (sql);\r\n")
		   .append ("\t\tint num = preparedStatement.executeUpdate ();\r\n")
		   .append ("\t\tif (num > 0) {\n\t\t\tlogger.info (\"删除成功\");\n\t\t}\n\t\telse {\n\t\t\tlogger.error " +
		            "(\"删除失败\");\n\t\t}\n\t\tJdbc.release (null, preparedStatement, connection);\n\t}");
		System.out.println (str);
	}
	

	
}
