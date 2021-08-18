package other.mail.model.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

@Data
public class EmailBoxPO {
    private BigDecimal emailId; /*邮件id*/
    private String sender; /*发送人*/
    private String receiver; /*收信人*/
    private String cc; /*抄送人*/
    private String title; /*主题*/
    private String content; /*内容*/
    private Integer sendType; /*发送类型（0：普通 1：急件）*/
    private Date sendDate; /*发送时间*/
    private Integer boxType; /*邮箱类型（0：草稿箱 1：收件箱 2：发件箱 3：垃圾箱）*/
    private Integer emailStatus; /*邮件类型（0：未读 1：已读 2：回复 3：转发 4：全部转发）*/
    private Integer isDelete; /*是否删除（0：否 1：是）*/
    private Date gmtCreate; /*创建时间*/
    private Date gmtUpdate; /*更新时间*/
}
