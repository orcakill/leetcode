package com.example.model.entity;

import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class MessageEventPO {
    @Id
    private BigDecimal messageId; /*消息id*/
    private Date messageDate; /*消息日期*/
    private String messageTitle; /*消息标题*/
    private String messageContent; /*消息内容*/
    private Integer messageType; /*消息类型（0：习惯培养 1：考试提醒 2：游戏降价通知）*/
    private Date gmtCreate; /*创建时间*/
    private Date gmtUpdate; /*更新时间*/
    private Integer isDelete; /*是否已删除（0：否 1：是）*/
}
