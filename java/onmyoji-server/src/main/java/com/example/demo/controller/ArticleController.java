package com.example.demo.controller;

import cn.hutool.core.map.MapUtil;
import com.example.demo.model.dto.ArticleDTO;
import com.example.demo.model.vo.ArticleVO;
import com.example.demo.model.vo.PageVO;
import com.example.demo.model.vo.Results;
import com.example.demo.service.ArticleService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname ArticleController
 * @Description 文章api
 * @Date 2023/1/24 19:49
 * @Created by orcakill
 */
@Api(tags = "文章示例")
@ApiSort(value = 2)
@RestController
public class ArticleController {

  private final ArticleService articleService;

  public ArticleController(ArticleService articleService) {
    this.articleService = articleService;
  }

  @ApiOperation("批量获取文章")
  @ApiOperationSupport(order = 1)
  @GetMapping("/articles")
  public Results<PageVO<?>> getArticles(
      @ApiParam("页码")
      @RequestParam(required = false, defaultValue = "1") Integer page,
      @ApiParam("每页存放的记录数")
      @RequestParam(required = false, defaultValue = "5") Integer limit) {
    return Results.ok(articleService.getArticles(page, limit));
  }

  @PostMapping("/articles")
  @ApiOperationSupport(order = 2)
  @ApiOperation("文章添加")
  public Results<?> postArticles(
      @ApiParam(name = "文章信息", value = "传入json格式", required = true)
      @RequestBody @Valid ArticleDTO articleDTO) {
    String id = articleService.insArticle(articleDTO);
    return Results.ok(MapUtil.of("id", id));
  }

  @GetMapping("/article/{id}")
  @ApiOperationSupport(order = 3)
  @ApiOperation("文章查询")
  @ApiImplicitParam(name = "id", value = "文章id", required = true, dataType = "String",dataTypeClass = String.class,paramType = "path")
  public Results<ArticleVO> getArticle(@PathVariable String id) {
    ArticleVO articleVO = articleService.findById(id);
    return Results.ok(articleVO);
  }

  @GetMapping("/article/xml/{id}")
  @ApiOperationSupport(order = 4)
  @ApiOperation("文章查询xml")
  @ApiImplicitParam(name = "id", value = "文章id", required = true, dataType = "String",dataTypeClass = String.class, paramType = "path")
  public Results<ArticleVO> getArticleXML(@PathVariable String id) {
    ArticleVO articleVO = articleService.getById(id);
    return Results.ok(articleVO);
  }

  @DeleteMapping("/article/{id}")
  @ApiOperationSupport(order = 5)
  @ApiOperation("文章删除")
  @ApiImplicitParam(name = "id", value = "文章id", required = true, dataType = "String",dataTypeClass = String.class, paramType = "path")
  public Results<?> deleteArticle(@PathVariable String id) {
    articleService.deleteArticle(id);
    return Results.ok("删除成功", null);
  }

  @PutMapping("/article/{id}")
  @ApiOperationSupport(order = 6)
  @ApiOperation("文章修改")
  @ApiImplicitParam(name = "id", value = "文章id", required = true, dataType = "String",dataTypeClass = String.class, paramType = "path")
  public Results<Map<String, Object>> putArticles(
      @ApiParam(name = "要修改的文章信息", value = "传入json格式", required = true)
      @RequestBody ArticleDTO articleDTO,
      @PathVariable String id) {
    articleService.updateArticle(articleDTO, id);
    return Results.ok("更新成功", MapUtil.of("id", id));
  }
}