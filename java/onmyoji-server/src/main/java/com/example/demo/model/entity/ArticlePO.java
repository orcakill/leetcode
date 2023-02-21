package com.example.demo.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Classname ArticlePO
 * @Description ArticlePO
 * @Date 2023/1/24 16:58
 * @Created by orcakill
 */
@Data
@TableName("articles") //手动指定表名，如果表名和实体类名相同的话可以不加
public class ArticlePO implements Serializable {
  private static final long serialVersionUID = -1849698844197610571L;
  @TableId //表示这个属性对应的是数据库中表的主键
  private String id;
  private String author;
  private String title;
  private String content;
  private String tags;
  private Integer type;
  private String category;
  private Long gmtCreate;
  private Long gmtUpdate;
  private String tabloid;
  private Integer views;
}

