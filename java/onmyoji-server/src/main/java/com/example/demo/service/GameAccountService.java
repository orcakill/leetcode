package com.example.demo.service;

import com.example.demo.model.entity.GameAccountPO;

import java.util.List;
import java.util.Map;

/**
 * @Classname GameAccountService
 * @Description 游戏账号
 * @Date 2023/1/29 2:13
 * @Created by orcakill
 */
public interface GameAccountService {
	GameAccountPO findById(String id);
	
	List<GameAccountPO> findList (Map<String,Object> map);
}
