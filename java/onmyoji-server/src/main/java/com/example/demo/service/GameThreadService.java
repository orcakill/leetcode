package com.example.demo.service;

import com.example.demo.model.entity.GameThreadPO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author orca
* @description 针对表【game_thread】的数据库操作Service
* @createDate 2023-02-15 00:20:27
*/
public interface GameThreadService extends IService<GameThreadPO> {
	
	//游戏进程查询
	GameThreadPO  findById(String threadId);
	//游戏进程保存
	boolean save(GameThreadPO gameThreadPO);
	//游戏进程删除
	void  delete(Integer id);
}
