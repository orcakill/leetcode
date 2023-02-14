package com.example.demo.mapper;

import com.example.demo.model.entity.GameThreadPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author orca
* @description 针对表【game_thread】的数据库操作Mapper
* @createDate 2023-02-15 00:20:27
* @Entity com.example.demo.model.entity.GameThreadPO
*/
@Mapper
public interface GameThreadMapper extends BaseMapper<GameThreadPO> {

}




