package com.example.demo.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName game_thread
 */
@TableName(value ="game_thread")
@Data
public class GameThreadPO implements Serializable {
    /**
     * 游戏进程标识
     */
    @TableId(value = "thread_id")
    private String threadId;

    /**
     * 游戏进程状态
     */
    @TableField(value = "thread_state")
    private Integer threadState;

    /**
     * 创建人
     */
    @TableField(value = "create_user")
    private String createUser;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Long createTime;

    /**
     * 修改人
     */
    @TableField(value = "update_user")
    private String updateUser;

    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    private Long updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}