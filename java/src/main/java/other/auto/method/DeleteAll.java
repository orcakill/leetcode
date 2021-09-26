package other.auto.method;



public class DeleteAll {
	public static  StringBuilder deleteAll (String table) {
		StringBuilder str = new StringBuilder ();
		str.append ("/*删除全部数据*/\npublic static void deleteAll () throws SQLException {\n" + "\tString sql = \"delete  " +
		            "from ")
		   .append (table)
		   .append ("\";\n")
		   .append ("\tSql.deleteSQL (sql, (org.apache.logging.log4j.core.Logger) logger);\n")
		   .append ("}\r\n\r\n");
	     return  str;
	}
}
