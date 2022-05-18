package other.auto.method;

import other.auto.entity.ClassPO;

import java.util.List;

import static other.dao.CommonUtils.getResultSet;

public class FindAll {
	public  static  StringBuilder findAll (String name, String name1, String table, List<ClassPO> classPOS){
		StringBuilder str=new StringBuilder ();
		str.append ("/*查询全部数据*/\r\npublic static ")
		   .append ("List<")
		   .append (name)
		   .append (">")
		   .append (" findAll() ");
		str.append ("throws SQLException {  \r\n" + "  " + "List<")
		   .append (name)
		   .append ("> ")
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
		   .append (name)
		   .append (" ")
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
		   .append ("List")
		   .append (";\r\n")
		   .append ("}\r\n\r\n");
		return  str;
	}
}
