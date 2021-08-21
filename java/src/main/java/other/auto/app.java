package other.auto;

import io.swagger.annotations.ApiModelProperty;
import other.auto.entity.ClassPO;
import other.dao.Jdbc;
import util.Case;

import javax.persistence.Id;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.*;

public class app {
public static void main(String[] args) throws ClassNotFoundException {        /* 赋值类名*/
	Class<?> c = Class.forName("other.mail.model.entity.EmailBoxPO");
	final String name = "EmailBoxPO";
	String table = "email_box";
	Field[] field = c.getDeclaredFields();
	List<ClassPO> classPOS = new ArrayList<>();
	for (Field f : field) {
		int size = field.length;/* 属性个数*/
		f.setAccessible(true); /* 设置些属性是可以访问的*/
		String type = f.getType().toString();/* 得到此属性的类型*/
		String key = f.getName();/* key:得到属性名*/
		int text = 0;
		Id id = f.getAnnotation(Id.class);
		if (id != null) {
			text = 1;
		}
		ClassPO classPO = new ClassPO();
		classPO.setName(key);
		classPO.setType(type);
		classPO.setPk(text);
		classPOS.add(classPO);
	}        /*根据id查询一条数据的mapper方法*/
	String name1 = name.substring(0, 1).toLowerCase(Locale.ROOT) + name.substring(1);
	StringBuilder str = new StringBuilder();
	str.append("private static final Logger logger = LogManager.getLogger(" + name + ".class); \r\n" + "public static " + name + " findById(BigDecimal id) throws SQLException {  \r\n" + "  " + name + " ").append(name1).append(" =new ").append(name).append("();\r\n");
	str.append("  " + "String sql=" + "\"select * from ").append(table).append(" where ").append("\"  \r\n");
	str.append("  Connection connection= Jdbc.getConnection();\r\n  " + "Statement statement=connection.createStatement(); \r\n  " + "ResultSet resultSet=statement.executeQuery(sql);\r\n  " + "while(resultSet.next()){\r\n");
	for (int i = 0; i < classPOS.size(); i++) {
		str.append("    ").append(name1).append(".set").append(Case.toUpper(classPOS.get(i).getName())).append("(resultSet.get").append(toType(classPOS.get(i).getType())).append("(").append(i + 1).append("))").append("; \r\n");
	}
	str.append("public static void insert(" + name + " ").append(name1).append(") throws SQLException {\r\n").append("  ").append("String sql=").append("\"").append("insert into ").append(table).append("(");
    for (int i=0;i< classPOS.size();i++) {
        str.append(classPOS.get(i).getName());
        if(i!=classPOS.size()-1){
            str.append(",");
        }
    }

	str.append(")\r\n" + "              values(");
	System.out.println(str);
}

public static String toType(String str) {
	int x = str.lastIndexOf(".", str.length());
	return str.substring(x + 1, str.length());
}
}
