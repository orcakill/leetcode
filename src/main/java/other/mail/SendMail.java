package other.mail;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class SendMail {
    //给指定邮箱，定时发送邮件
    public static void main(String[] args) throws Exception {
         String mail="orcakill@dingtalk.com";
         String title="java测试1";
         String content="这是一封java邮件";


          System.out.println(sendMail(mail,title,content));


    }

    public  static String sendMail(String mail,String title,String content) throws MessagingException {
        String s="";
        int num=1;
        Date nowDate=new Date();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String  date=format.format(nowDate);

        Properties properties = new Properties();
        properties.put("mail.transport.protocol", "smtp");// 连接协议
        properties.put("mail.smtp.host", "smtp.163.com");// 主机名
        properties.put("mail.smtp.port", 465);// 端口号
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", "true");//设置是否使用ssl安全连接  ---一般都使用
        properties.put("mail.debug", "true");//设置是否显示debug信息  true 会在控制台显示相关信息
        //得到回话对象
        Session session = Session.getInstance(properties);
        // 获取邮件对象
        Message message = new MimeMessage(session);
        //设置发件人邮箱地址
        message.setFrom(new InternetAddress("orcakill@163.com"));
        //设置收件人地址
         message.setRecipients( Message.RecipientType.TO,new InternetAddress[] { new InternetAddress(mail) });
        //设置邮件标题
        message.setSubject(title);
        //设置邮件内容
        message.setText(content);
        //得到邮差对象
        Transport transport = session.getTransport();
        //连接自己的邮箱账户
        transport.connect("orcakill@163.com", "LJPOYPSGLKTQWPBU");
        //密码为刚才得到的授权码
        //发送邮件
        transport.sendMessage(message, message.getAllRecipients());
        s="第二封  "+date;
        return  s;
    }
}
