package other.auto;

import io.swagger.annotations.ApiModelProperty;
import other.auto.entity.ClassPO;
import util.Case;

import javax.persistence.Id;
import java.lang.reflect.Field;
import java.util.*;

public class app {
    public static void main(String[] args) throws ClassNotFoundException {
      // 赋值类名
        Class<?> c= Class.forName("other.mail.model.entity.EmailBoxPO");
        final  String   name="EmailBoxPO";
        String   table="email_box";

        Field[] field = c.getDeclaredFields();
        List<ClassPO> classPOS=new ArrayList<>();
        for (Field f : field) {
            int size = field.length;// 属性个数
            f.setAccessible(true); // 设置些属性是可以访问的

            String type = f.getType().toString();// 得到此属性的类型
            String key = f.getName();// key:得到属性名
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
        }
        String   name1=name.substring(0,1).toLowerCase(Locale.ROOT)+name.substring(1);
        String   str1="\r\n private static final Logger logger = LogManager.getLogger("+ name+".class); \r\n"
                +"public static "+name+" findById(BigDecimal id) throws SQLException {  \r\n"
                +"  "+name+" "+name1+" =new "+name+"();\r\n";
        /*查询语句*/
        String  str2="  "+"String sql="+"\"select * from "+table+" where "+"\"  \r\n";

        String  str3="  Connection connection= Jdbc.getConnection();\r\n"
                +"  "+"Statement statement=connection.createStatement(); \r\n"
                +"  "+"ResultSet resultSet=statement.executeQuery(sql);\r\n"
                +"  "+"while(resultSet.next()){\r\n"
                ;
        StringBuilder str4 = new StringBuilder();
        for (ClassPO classPO : classPOS) {
            str4.append("    ").append(name1).append(".set").append(Case.toUpper(classPO.getName())).append("(resultSet.get").append("; \r\n");
        }
        String  str=str1+str2+str3+str4;
        System.out.println(str);
    }
}
