package com.example.demo.service;

import org.springframework.scheduling.annotation.Async;

/**
 * @Classname OnmyojiService
 * @Description 阴阳师自动战斗
 * @Date 2023/1/25 20:56
 * @Created by orcakill
 */
public interface OnmyojiService {
  /***
   * @description: 阴阳师自动战斗
   * @return: java.lang.String
   * @author: orcakill
   * @date: 2023/1/25 20:58
   */
  @Async
   void onmyojiService(Integer round,Integer number) throws InterruptedException;
}
