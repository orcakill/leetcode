package other.mail.dao;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import other.dao.Jdbc;
import other.mail.model.entity.EmailBoxPO;

import java.math.BigDecimal;
import java.sql.*;

public class EmailBoxMapper {
    private static final Logger logger = LogManager.getLogger(EmailBoxPO.class);

    public static EmailBoxPO findById(BigDecimal id) throws SQLException {
        EmailBoxPO emailBoxPO=new EmailBoxPO();
        String sql="select * from email_box where email_id='"+id+"'";
        Connection connection= Jdbc.getConnection();
        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery(sql);
        while(resultSet.next()){
            emailBoxPO.setEmailId(resultSet.getBigDecimal(1));
            emailBoxPO.setSender(resultSet.getString(2));
            emailBoxPO.setReceiver(resultSet.getString(3));
            emailBoxPO.setCc(resultSet.getString(4));
            emailBoxPO.setTitle(resultSet.getString(5));
            emailBoxPO.setContent(resultSet.getString(6));
            emailBoxPO.setSendType(resultSet.getInt(7));
            emailBoxPO.setSendDate(resultSet.getDate(8));
            emailBoxPO.setBoxType(resultSet.getInt(9));
            emailBoxPO.setEmailStatus(resultSet.getInt(10));
            emailBoxPO.setIsDelete(resultSet.getInt(11));
            emailBoxPO.setGmtCreate(resultSet.getDate(12));
            emailBoxPO.setGmtUpdate(resultSet.getDate(13));
        }
        Jdbc.release(null, statement, connection);
        return emailBoxPO;
    }


    public static void insert(EmailBoxPO emailBoxPO) throws SQLException {
        String sql = "insert into email_box" +
                "(email_id,sender,receiver,cc,title,content,send_type,send_date,box_type,email_status,is_delete,gmt_create,gmt_update) " +
                "values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Connection connection = Jdbc.getConnection();
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setBigDecimal(1, emailBoxPO.getEmailId());
        pstmt.setString(2, emailBoxPO.getSender());
        pstmt.setString(3, emailBoxPO.getReceiver());
        pstmt.setString(4, emailBoxPO.getCc());
        pstmt.setString(5, emailBoxPO.getTitle());
        pstmt.setString(6, emailBoxPO.getContent());
        pstmt.setInt(7, emailBoxPO.getSendType());
        pstmt.setDate(8, new java.sql.Date(emailBoxPO.getSendDate().getTime()));
        pstmt.setInt(9, emailBoxPO.getBoxType());
        pstmt.setInt(10,emailBoxPO.getEmailStatus());
        pstmt.setInt(11,emailBoxPO.getIsDelete());
        pstmt.setDate(12,new java.sql.Date(emailBoxPO.getGmtCreate().getTime()));
        pstmt.setDate(13,new java.sql.Date(emailBoxPO.getGmtUpdate().getTime()));

        int num =pstmt.executeUpdate();
        System.out.println(pstmt);
        if(num>0){
            logger.info("插入成功");
        }
        else{
            logger.error("插入失败");
        }

        Jdbc.release(null, pstmt, connection);
    }


    public static void deleteAll() throws SQLException {
        String sql = "delete   from email_box  ";
        Connection connection = Jdbc.getConnection();
        PreparedStatement pstmt = connection.prepareStatement(sql);


        int num =pstmt.executeUpdate();

        if(num>0){
            logger.info("删除成功");
        }
        else{
            logger.error("删除失败");
        }

        Jdbc.release(null, pstmt, connection);
    }

}
