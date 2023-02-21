package com.example.demo.service;

import com.example.demo.model.dto.ArticleDTO;
import com.example.demo.model.vo.ArticleVO;
import com.example.demo.model.vo.PageVO;


/**
 * @Classname ArticleService
 * @Description 文章Service
 * @Date 2023/1/24 19:45
 * @Created by orcakill
 */

public interface ArticleService {


  PageVO<ArticleVO> getArticles(int page, int limit);
  
  String insertArticle(ArticleDTO articleDTO);

  ArticleVO findById(String id);

  ArticleVO getById(String id);

  void deleteArticle(String id);

  void updateArticle(ArticleDTO articleDTO, String id);
}
