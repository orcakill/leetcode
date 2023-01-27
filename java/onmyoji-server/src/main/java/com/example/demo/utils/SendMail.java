package com.example.demo.utils;


import com.example.demo.model.entity.EmailBoxPO;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;

public class SendMail {

    //可用
    public  static void sendTextMail (EmailBoxPO emailBoxPO){
        SimpleEmail email = new SimpleEmail ();
        String  passWord=getPassWord.get163mail ();
        try {
            // 发送电子邮件的邮件服务器地址
            email.setHostName("smtp.163.com");
            email.setCharset("UTF-8");// 设置字符编码
            // 邮箱服务器身份验证
            email.setAuthentication("orcakill@163.com", passWord);
            // 设置发件人邮箱(与用户名保持一致) 并且 设置发件人昵称
            email.setFrom("orcakill@163.com","小助手");
            // 邮件主题
            email.setSubject(emailBoxPO.getTitle ());
            // 邮件内容
            email.setMsg(emailBoxPO.getContent ());
            // 收件人地址
            email.addTo(emailBoxPO.getReceiver ());
            //启用ssl加密
            email.setSSLOnConnect (true);
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
