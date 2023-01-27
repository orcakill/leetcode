package com.example.demo.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @Classname GameThreadPO
 * @Description 游戏进程状态
 * @Date 2023/1/25 21:13
 * @Created by orcakill
 */
@Data
@TableName("game_thread")
public class GameThreadPO implements Serializable {
  private static final long serialVersionUID = -1849698844197610571L;
  @TableId //表示这个属性对应的是数据库中表的主键
  private String id;


  private Integer threadState;

  private Integer threadNumber;
  private String createUser;
  private long   createTime;
  private String updateUser;
  private long   updateTime;

}
