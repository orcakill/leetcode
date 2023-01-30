package com.example.demo.service.impl;

import com.example.demo.mapper.GameAccountMapper;
import com.example.demo.exception.BlogException;
import com.example.demo.model.entity.GameAccountPO;
import com.example.demo.service.GameAccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.example.demo.model.enums.ErrorInfoEnum.INVALID_ID;

/**
 * @Classname GameAccountServiceImpl
 * @Description 游戏账号服务实现类
 * @Date 2023/1/29 2:14
 * @Created by orcakill
 */
@Service
public class GameAccountServiceImpl implements GameAccountService {
	private final GameAccountMapper gameAccountMapper;
	
	public GameAccountServiceImpl (GameAccountMapper gameAccountMapper) {
		this.gameAccountMapper = gameAccountMapper;
	}
	
	@Override
	public GameAccountPO findById (String id) {
		GameAccountPO gameAccountPO = gameAccountMapper.selectById (id);
		if (Objects.isNull (gameAccountPO)) {
			throw new BlogException (INVALID_ID);
		}
		return gameAccountPO;
	}
	
	@Override
	public List<GameAccountPO> findList (Map<String, Object> map) {
		return gameAccountMapper.findList (map);
	}
}
