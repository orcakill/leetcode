package com.example.demo.controller;

import com.example.demo.exception.BlogException;
import com.example.demo.model.enums.ErrorInfoEnum;
import com.example.demo.model.vo.Results;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname HelloController
 * @Description 测试端口是否正常访问
 * @Date 2022/12/27 16:51
 * @Created by orcakill
 */
@Api(tags = "通用接口")
@ApiSort(value = 1)
@RestController
public class CommController {
  public  static  final Logger logger = LogManager.getLogger ("OnmyojiServerApplication");
  @ApiOperation(value = "服务端检查")
  @ApiOperationSupport(order = 1)
  @GetMapping("/ping")
  public Results<?> ping() {
    logger.info ("sf4j日志");
    return Results.ok("欢迎访问阴阳后端API", null);
  }

  @ApiOperation(value = "自定义异常检查")
  @ApiOperationSupport(order = 2)
  @GetMapping("/exception")
  public Results<?> exception() {
    throw new BlogException(ErrorInfoEnum.MISSING_PARAMETERS);
  }
}
