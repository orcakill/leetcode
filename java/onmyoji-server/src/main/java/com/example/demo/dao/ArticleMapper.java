package com.example.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.model.entity.ArticlePO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Classname ArticleMapper
 * @Description ArticlePO dao层接口
 * @Date 2023/1/24 18:14
 * @Created by orcakill
 */
@Mapper
public interface ArticleMapper extends BaseMapper<ArticlePO> {
  ArticlePO getById(String id);
}
