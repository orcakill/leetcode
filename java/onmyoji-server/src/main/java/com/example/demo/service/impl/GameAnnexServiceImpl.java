package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.model.entity.GameAnnexPO;
import com.example.demo.service.GameAnnexService;
import com.example.demo.mapper.GameAnnexMapper;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import org.springframework.stereotype.Service;

/**
* @author orca
* @description 针对表【game_annex(游戏附录信息)】的数据库操作Service实现
* @createDate 2023-01-30 21:25:19
*/
@Service
public class GameAnnexServiceImpl extends MppServiceImpl<GameAnnexMapper, GameAnnexPO>
    implements GameAnnexService{

}




