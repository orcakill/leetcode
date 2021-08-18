package other.mail.dao;

import org.junit.Test;
import other.mail.model.entity.EmailBoxPO;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;

public class EmailBoxMapperTest {

    @Test
    public void findById() throws SQLException {
        EmailBoxPO  emailBoxPO=EmailBoxMapper.findById(BigDecimal.valueOf(1));
        System.out.println(emailBoxPO.getTitle());

    }

    @Test
    public void insert() throws SQLException {
        Date date=new Date();
        EmailBoxPO emailBoxPO=new EmailBoxPO();
        emailBoxPO.setEmailId(BigDecimal.valueOf(1));
        emailBoxPO.setSender("张三");
        emailBoxPO.setReceiver("李四");
        emailBoxPO.setCc("王五");
        emailBoxPO.setTitle("测试主题");
        emailBoxPO.setContent("测试内容");
        emailBoxPO.setSendType(0);
        emailBoxPO.setSendDate(date);
        emailBoxPO.setBoxType(0);
        emailBoxPO.setEmailStatus(0);
        emailBoxPO.setIsDelete(0);
        emailBoxPO.setGmtCreate(date);
        emailBoxPO.setGmtUpdate(date);

        EmailBoxMapper.insert(emailBoxPO);
    }

    @Test
    public void deleteAll() throws SQLException {
        EmailBoxMapper.deleteAll();
    }
}