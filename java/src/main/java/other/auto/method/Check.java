package other.auto.method;

import other.auto.entity.ClassPO;

import java.util.List;

import static other.dao.CommonUtils.getId;

public class Check {
	public  static  StringBuilder check (String table, List<ClassPO> classPOS,
	                                     List<ClassPO> classPOS1){
		StringBuilder str=new StringBuilder ();
		str.append ("/*根据主键判断数据已存在*/\r\npublic static " + "Boolean" + " check(");
		getId (classPOS, str);
		str.append (") throws SQLException {  \r\n")
		   .append ("  " + "String sql=" + "\"select count(*) num from ")
		   .append (table)
		   .append (" where ");
		FindByID.getParam (classPOS1, str);
		str.append ("  ResultSet resultSet=preparedStatement.executeQuery();\r\n");
		str.append ("  int num = 0;\r\n")
		   .append ("\twhile (resultSet.next ()) {\r\n")
		   .append ("\t\tnum = resultSet.getInt (\"num\");\r\n\t}\r\n")
		   .append ("\tJdbc.release (null, preparedStatement, connection);\r\n")
		   .append ("\treturn num != 0;\r\n");
		str.append ("}\r\n\r\n");
		return  str;
	}
}
