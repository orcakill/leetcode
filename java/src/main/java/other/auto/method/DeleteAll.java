package other.auto.method;

import other.auto.entity.ClassPO;

import java.util.List;

public class DeleteAll {
	public static  StringBuilder deleteAll(String name, String name1, String table, List<ClassPO> classPOS,
	                                  List<ClassPO> classPOS1,List<ClassPO> classPOS2) {
		StringBuilder str = new StringBuilder ();
		str.append("\tpublic static void deleteAll () throws SQLException {\n" +
		           "\t\tString sql = \"delete  from task_list\";\n" +
		           "\t\tSql.deleteSQL (sql, (org.apache.logging.log4j.core.Logger) logger);\n" +
		           "\t}");
	     return  str;
	}
}
