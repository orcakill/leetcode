package com.example.demo.service;

import com.example.demo.OnmyojiServerApplication;
import com.example.demo.model.entity.GameThreadPO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Classname GameThreadServiceTest
 * @Description GameThreadService测试类
 * @Date 2023/1/26 0:17
 * @Created by orcakill
 */
@Log4j2
@SpringBootTest(classes = {OnmyojiServerApplication.class})
class GameThreadServiceTest {

  @Autowired
  private  GameThreadService gameThreadService;

  @Test
  void findById() {
    GameThreadPO gameThreadPO=gameThreadService.findById("63ebbd67130f3f55d5608550");
    log.info("查询记录: {}",gameThreadPO.toString());
  }

  @Test
  void save() {
    GameThreadPO gameThreadPO=new GameThreadPO();
    gameThreadPO.setThreadState(0);
    gameThreadService.save(gameThreadPO);
  }

  @Test
  void delete() {
    gameThreadService.delete(1);
  }
}