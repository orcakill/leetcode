package com.example.demo.service;

import com.example.demo.model.entity.GameThreadPO;

/**
 * @Classname GameThreadService
 * @Description GameThread服务接口
 * @Date 2023/1/25 21:18
 * @Created by orcakill
 */
public interface GameThreadService {
    //游戏进程查询
    GameThreadPO  findById(String id);
    //游戏进程保存
    void  save(GameThreadPO gameThreadPO);
    //游戏进程删除
    void  delete(String id);
}
