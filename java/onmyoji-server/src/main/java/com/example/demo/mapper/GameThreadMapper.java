package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.model.entity.GameThreadPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Classname GameThreadMapper
 * @Description 游戏进程 mapper接口
 * @Date 2023/1/25 21:16
 * @Created by orcakill
 */
@Mapper
public interface GameThreadMapper extends BaseMapper<GameThreadPO> {

}
