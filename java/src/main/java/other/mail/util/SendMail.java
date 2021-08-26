package other.mail.util;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;

public class SendMail {
    //不可用
    public  static void sendOneMail(String mail,String title,String content,String passWord) throws MessagingException {
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
        transport.connect("orcakill@163.com", passWord);
        //密码为刚才得到的授权码
        //发送邮件
        transport.sendMessage(message, message.getAllRecipients());
    }
    //可用
    public  static void sendTextMail(String mail,String title,String content,String passWord){
        SimpleEmail email = new SimpleEmail();
        try {
            // 发送电子邮件的邮件服务器地址
            email.setHostName("smtp.163.com");
            email.setCharset("UTF-8");// 设置字符编码
            // 邮箱服务器身份验证
            email.setAuthentication("orcakill@163.com", passWord);
            // 设置发件人邮箱(与用户名保持一致) 并且 设置发件人昵称
            email.setFrom("orcakill@163.com","逆戟之刃");
            // 邮件主题
            email.setSubject(title);
            // 邮件内容
            email.setMsg(content);
            // 收件人地址
            email.addTo(mail);
            // 邮件发送
            email.send();
            System.out.println("邮件发送成功！");
        }catch (EmailException e){
            e.printStackTrace();
            System.err.println("邮件发送失败！");
        }
    }
    
    //可用
    public  static void sendHtmlMail(String mail,String title,String content,String passWord){
    
        HtmlEmail email = new HtmlEmail();
        try {
            // 发送电子邮件的邮件服务器地址
            email.setHostName("smtp.163.com");
            email.setCharset("UTF-8");// 设置字符编码
            // 邮箱服务器身份验证
            email.setAuthentication("orcakill@163.com", passWord);
            // 设置发件人邮箱(与用户名保持一致) 并且 设置发件人昵称
            email.setFrom("orcakill@163.com","逆戟之刃");
            // 邮件主题
            email.setSubject(title);
            // 邮件内容：由于使用了HtmlEmail，可以在邮件内容中使用HTML标签
            email.setMsg("<h1 style='color:red'>邮件测试，</h1>" + " 请忽略！");
            // 收件人地址
            email.addTo(mail);
            // 邮件发送
            email.send();
            System.out.println("邮件发送成功！");
        }catch (EmailException e){
            e.printStackTrace();
            System.err.println("邮件发送失败！");
        }
    }


    
    
}
