package other.auto.method;

import other.auto.entity.ClassPO;

import java.util.List;

public class DeleteAll {
	public static  StringBuilder deleteAll(String name, String name1, String table, List<ClassPO> classPOS,
	                                  List<ClassPO> classPOS1,List<ClassPO> classPOS2) {
		StringBuilder str = new StringBuilder ();
		str.append ("/*删除方法*/\r\n public static void deleteAll () throws SQLException {\r\n")
		   .append ("\t\tString sql = \"delete   from ")
		   .append (table)
		   .append ("\";\r\n")
		   .append ("\t\tConnection connection = Jdbc.getConnection ();\r\n")
		   .append ("\t\tPreparedStatement preparedStatement = connection.prepareStatement (sql);\r\n")
		   .append ("\t\tint num = preparedStatement.executeUpdate ();\r\n")
		   .append ("\t\tif (num > 0) {\n\t\t\tlogger.info (\"删除成功\");\n\t\t}\n\t\telse {\n\t\t\tlogger.error " +
		            "(\"删除失败\");\n\t\t}\n\t\tJdbc.release (null, preparedStatement, connection);\n\t}");
	     return  str;
	}
}
