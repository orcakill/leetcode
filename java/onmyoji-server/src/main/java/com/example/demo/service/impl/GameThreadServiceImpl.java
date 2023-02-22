package com.example.demo.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.exception.BlogException;
import com.example.demo.mapper.GameThreadMapper;
import com.example.demo.model.entity.GameThreadPO;
import com.example.demo.service.GameThreadService;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.example.demo.model.enums.ErrorInfoEnum.INVALID_ID;

/**
* @author orca
* @description 针对表【game_thread】的数据库操作Service实现
* @createDate 2023-02-15 00:20:27
*/
@Service
public class GameThreadServiceImpl extends ServiceImpl<GameThreadMapper, GameThreadPO>
    implements GameThreadService{
	
	private final GameThreadMapper gameThreadMapper;
	
	public GameThreadServiceImpl (GameThreadMapper gameThreadMapper) {
		this.gameThreadMapper = gameThreadMapper;
	}
	
	@Override
	public GameThreadPO findById (String threadId) {
		return gameThreadMapper.selectById (threadId);
	}
	
	@Override
	public boolean save (GameThreadPO gameThreadPO) {
		GameThreadPO gameThreadPO1 = gameThreadMapper.selectById(gameThreadPO.getThreadId ());
		//    插入
		if (Objects.isNull(gameThreadPO1)) {
			if(gameThreadPO.getThreadId ()==null){
				String id = IdUtil.objectId ();
				gameThreadPO.setThreadId (id);
			}
			gameThreadPO.setCreateTime(System.currentTimeMillis());
			gameThreadMapper.insert(gameThreadPO);
		}
		//   更新
		else{
			gameThreadPO.setUpdateTime(System.currentTimeMillis());
			gameThreadMapper.updateById(gameThreadPO);
		}
		return  true;
	}
	
	@Override
	public void delete (Integer id) {
		int i = gameThreadMapper.deleteById(id);
		if (i <= 0) {
			throw new BlogException(INVALID_ID);
		}
	}
}




