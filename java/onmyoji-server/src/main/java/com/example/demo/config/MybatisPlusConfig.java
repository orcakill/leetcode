package com.example.demo.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Collections;

/**
 * @Classname MybatisPlusConfig
 * @Description 配置分页
 * @Date 2023/1/24 18:16
 * @Created by orcakill
 */
@EnableTransactionManagement
@Configuration
@MapperScan("com.example.demo.mapper")
public class MybatisPlusConfig {

  /**
   * 分页插件 3.5.X
   */
  @Bean
  public PaginationInnerInterceptor paginationInnerInterceptor() {
    PaginationInnerInterceptor paginationInterceptor = new PaginationInnerInterceptor();
    // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
    paginationInterceptor.setOverflow(false);
    // 设置最大单页限制数量，默认 500 条，-1 不受限制
    paginationInterceptor.setMaxLimit(-1L);
    // 设置数据库类型
    paginationInterceptor.setDbType(DbType.MYSQL);
    // 开启 count 的 join 优化,只针对部分 left join
    paginationInterceptor.setOptimizeJoin(true);
    return paginationInterceptor;
  }
  @Bean
  public MybatisPlusInterceptor mybatisPlusInterceptor(){
    MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
    mybatisPlusInterceptor.setInterceptors(Collections.singletonList(paginationInnerInterceptor()));
    return mybatisPlusInterceptor;
  }
}
