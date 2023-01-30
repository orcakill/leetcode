package com.example.demo.service.impl;

import static com.example.demo.model.enums.ErrorInfoEnum.INVALID_ID;

import cn.hutool.core.util.IdUtil;
import com.example.demo.mapper.GameThreadMapper;
import com.example.demo.exception.BlogException;
import com.example.demo.model.entity.GameThreadPO;
import com.example.demo.service.GameThreadService;
import java.util.Objects;
import org.springframework.stereotype.Service;

/**
 * @Classname GameThreadImpl
 * @Description GameThread服务实现类
 * @Date 2023/1/25 21:20
 * @Created by orcakill
 */
@Service
public class GameThreadImpl implements GameThreadService {
  private final GameThreadMapper gameThreadMapper;

  public GameThreadImpl(GameThreadMapper gameThreadMapper) {
    this.gameThreadMapper= gameThreadMapper;
  }
  public GameThreadPO findById(String id) {
    GameThreadPO gameThreadPO = gameThreadMapper.selectById(id);
    if (Objects.isNull(gameThreadPO)) {
      throw new BlogException(INVALID_ID);
    }
    return gameThreadPO;
  }

  @Override
  public void save(GameThreadPO gameThreadPO) {
    GameThreadPO gameThreadPO1 = gameThreadMapper.selectById(gameThreadPO.getId());
    //    插入
    if (Objects.isNull(gameThreadPO1)) {
      if(gameThreadPO.getId ()==null){
        String id = IdUtil.objectId();
        gameThreadPO.setId(id);
      }
      gameThreadPO.setCreateTime(System.currentTimeMillis());
      gameThreadMapper.insert(gameThreadPO);
    }
    //   更新
    else{
      gameThreadPO.setUpdateTime(System.currentTimeMillis());
      gameThreadMapper.updateById(gameThreadPO);
    }
  }

  @Override
  public void delete(String id) {
    int i = gameThreadMapper.deleteById(id);
    if (i <= 0) {
      throw new BlogException(INVALID_ID);
    }
  }
}
