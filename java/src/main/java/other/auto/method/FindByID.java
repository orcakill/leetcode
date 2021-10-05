package other.auto.method;

import other.auto.entity.ClassPO;

import java.util.List;

import static other.auto.util.CommonUtils.*;

public class FindByID {
	public  static  StringBuilder findById (String name, String name1, String table, List<ClassPO> classPOS,
	                                        List<ClassPO> classPOS1){
		StringBuilder str=new StringBuilder ();
		str.append ("private static final Logger logger = LogManager.getLogger(")
		   .append (name.replaceAll ("PO", ""))
		   .append ("Mapper")
		   .append (".class); \r\n\r\n")
		   .append ("/*根据主键查询一条数据*/\r\npublic static ")
		   .append (name)
		   .append (" ")
		   .append ("findById(");
		getId (classPOS, str);
		str.append (") throws SQLException {  \r\n" + "  ")
		   .append (name)
		   .append (" ")
		   .append (name1)
		   .append (" =new ")
		   .append (name)
		   .append ("();\r\n")
		   .append ("  " + "String sql=" + "\"select * from ")
		   .append (table)
		   .append (" where ");
		getParam (classPOS1, str);
		str.append ("  ResultSet resultSet=preparedStatement.executeQuery();\r\n");
		str.append ("  while(resultSet.next())" +
		            "{\r\n");
		getResultSet (classPOS, name1, str);
		str.append ("    }\r\n")
		   .append ("    Jdbc.release(null, preparedStatement, connection);\r\n")
		   .append ("    return ")
		   .append (name1)
		   .append (";\r\n")
		   .append ("}\r\n\r\n");
		return  str;
	}
	
	public static void getParam (List<ClassPO> classPOS1, StringBuilder str) {
		for(int i=0;i<classPOS1.size ();i++){
			if(i>0){
				str.append (" and ");
			}
			str.append (toLowerLine (classPOS1.get (i).getName ()))
			   .append ("=?");
		}
		str.append ("\"; \r\n")
		   .append ("  Connection connection= Jdbc.getConnection();\r\n  " + "PreparedStatement " +
		            "preparedStatement=connection.prepareStatement(sql); \r\n");
		for(int i=0;i<classPOS1.size ();i++){
			str.append ("  preparedStatement.setObject (")
			   .append (i+1)
			   .append (",")
			   .append (classPOS1.get (i)
			                     .getName ())
			   .append (");\r\n");
		}
	}
}
