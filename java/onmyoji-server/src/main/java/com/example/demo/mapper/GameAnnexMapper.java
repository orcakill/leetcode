package com.example.demo.mapper;

import com.example.demo.model.entity.GameAnnexPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
* @author orca
* @description 针对表【game_annex(游戏附录信息)】的数据库操作Mapper
* @createDate 2023-01-30 21:25:19
* @Entity com.example.demo.model.entity.GameAnnexPO
*/
public interface GameAnnexMapper extends BaseMapper<GameAnnexPO> {
	List<GameAnnexPO> findList(Map<String,Object> map);

}




