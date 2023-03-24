package com.example.demo.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName picture_log_result
 */
@TableName(value ="picture_log_result")
@Data
public class PictureLogResultPO implements Serializable {
    /**
     * 结果ID
     */
    @TableId
    private String resultId;

    /**
     * 日志ID
     */
    private String logId;

    /**
     * 识别结果
     */
    private String recognizeResult;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改人
     */
    private String updateUser;

    /**
     * 修改时间
     */
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}