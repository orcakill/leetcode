package other.auto.method;

import other.auto.entity.ClassPO;

import java.util.List;

import static other.dao.CommonUtils.*;


public class DeleteOne {
	public static StringBuilder deleteById (String table, List<ClassPO> classPOS,
	                                        List<ClassPO> classPOS1) {
		StringBuilder str = new StringBuilder ();
		str.append ("/*根据主键删除数据*/\npublic static void deleteById(");
		getId (classPOS, str);
		str.append (") throws SQLException {\r\n")
		   .append ("\tString sql = \"delete  from ")
		   .append (table)
		   .append (" where ");
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
		            "\tPreparedStatement preparedStatement = connection.prepareStatement (sql);\n");
		for(int i=0;i<classPOS1.size ();i++){
			str.append ("\tpreparedStatement.set")
			   .append ("Object")
			   .append ("(")
			   .append (i + 1)
			   .append (",")
			   .append (classPOS1.get (i)
			                     .getName ())
			   .append (");")
			   .append ("\r\n");
		}
		str.append ("\tint num = preparedStatement.executeUpdate ();\n" +
		            "\tif (num > 0) {\n" +
		            "\t\tlogger.info (\"删除成功\");\n" +
		            "\t}\n" +
		            "\telse {\n" +
		            "\t\tlogger.error (\"删除失败\");\n" +
		            "\t}\n" +
		            "\tJdbc.release (null, preparedStatement, connection);\n" +
		            "}");
		return str;
	}
}
