package other.auto;

import java.util.Locale;

public class app {
    public static void main(String[] args) throws ClassNotFoundException {
      // 赋值类名
        Class<?> c= Class.forName("other.mail.model.entity.MessageEventPO");
        final  String   name="MessageEventPO";
        String   table="message_event";





        String   name1=name.substring(0,1).toLowerCase(Locale.ROOT)+name.substring(1);
        String   str="private static final Logger logger = LogManager.getLogger("+ name+".class); \r\n"
                +"public static "+name+" findById(BigDecimal id) throws SQLException {  \r\n"
                +name+" "+name1+" =new "+name+"();\r\n"
                +"String sql=\"select * from "+table+" where email_id='\"+id+\"'\";"

                +"}";
        System.out.println(str);
    }
}
