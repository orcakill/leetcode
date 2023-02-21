package com.example.demo.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.exception.BlogException;
import com.example.demo.mapper.ArticleMapper;
import com.example.demo.model.dto.ArticleDTO;
import com.example.demo.model.entity.ArticlePO;
import com.example.demo.model.vo.ArticleVO;
import com.example.demo.model.vo.PageVO;
import com.example.demo.service.ArticleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.example.demo.model.enums.ErrorInfoEnum.INVALID_ID;

/**
 * @Classname ArticleService
 * @Description 文章Service
 * @Date 2023/1/24 19:45
 * @Created by orcakill
 */
@Service
public class ArticleServiceImpl implements ArticleService {

  private final ArticleMapper articleMapper;

  public ArticleServiceImpl(ArticleMapper articleMapper) {
    this.articleMapper = articleMapper;
  }

  public PageVO<ArticleVO> getArticles(int page, int limit) {
    QueryWrapper<ArticlePO> qw = new QueryWrapper<>();
    qw.select(ArticlePO.class, i -> !"content".equals(i.getColumn()));
    Page<ArticlePO> res = articleMapper.selectPage(new Page<>(page, limit), qw);
    List<ArticleVO> articleVOS = res.getRecords().stream()
        .map(ArticleVO::fromArticlePO)
        .collect(Collectors.toList());
    return PageVO.<ArticleVO>builder()
        .records(articleVOS.isEmpty() ? new ArrayList<>() : articleVOS)
        .total(res.getTotal())
        .current(res.getCurrent())
        .size(res.getSize())
        .build();
  }

  public String insertArticle(ArticleDTO articleDTO) {
    ArticlePO po = articleDTO.toArticlePO(false);
    String id = IdUtil.objectId();
    po.setId(id);
    articleMapper.insert(po);
    return id;
  }

  public ArticleVO findById(String id) {
    ArticlePO articlePO = articleMapper.selectById(id);
    if (Objects.isNull(articlePO)) {
      throw new BlogException(INVALID_ID);
    }
    return ArticleVO.fromArticlePO(articlePO);
  }

  public ArticleVO getById(String id) {
    ArticlePO articlePO = articleMapper.getById(id);
    if (Objects.isNull(articlePO)) {
      throw new BlogException(INVALID_ID);
    }
    return ArticleVO.fromArticlePO(articlePO);
  }

  public void deleteArticle(String id) {
    int i = articleMapper.deleteById(id);
    if (i <= 0) {
      throw new BlogException(INVALID_ID);
    }
  }

  public void updateArticle(ArticleDTO articleDTO, String id) {
    ArticlePO dbArticle = articleMapper.selectById(id);
    if (Objects.isNull(dbArticle)) {
      throw new BlogException(INVALID_ID);
    }
    ArticlePO articlePO = articleDTO.toArticlePO(true);
    articlePO.setId(id);
    articleMapper.updateById(articlePO);
  }
}
