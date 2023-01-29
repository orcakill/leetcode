package com.example.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.model.entity.GameAccountPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Classname GameAccountMapper
 * @Description GameAccountMapper
 * @Date 2023/1/29 0:23
 * @Created by orcakill
 */
@Mapper
public interface GameAccountMapper extends BaseMapper<GameAccountPO> {
	//查询游戏账号集合
	List<GameAccountPO> findList (@Param ("map") Map<String,Object> map);
}
