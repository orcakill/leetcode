package other.auto.method;

import other.auto.entity.ClassPO;

import java.util.List;

public class DeleteAll {
	public static  StringBuilder deleteAll(String name, String name1, String table, List<ClassPO> classPOS,
	                                  List<ClassPO> classPOS1,List<ClassPO> classPOS2) {
		StringBuilder str = new StringBuilder ();
		str.append("/*删除全部数据*/\npublic static void deleteAll () throws SQLException {\n" +
		           "\tString sql = \"delete  from task_list\";\n" +
		           "\tSql.deleteSQL (sql, (org.apache.logging.log4j.core.Logger) logger);\n" +
		           "}\r\n\r\n");
	     return  str;
	}
}
