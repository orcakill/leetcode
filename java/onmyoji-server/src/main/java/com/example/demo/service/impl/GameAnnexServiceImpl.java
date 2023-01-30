package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.mapper.GameAnnexMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【game_annex(游戏附录信息)】的数据库操作Service实现
* @createDate 2023-01-30 14:29:06
*/
@Service
public class GameAnnexServiceImpl extends ServiceImpl<GameAnnexMapper, GameAnnex>
    implements IService<GameAnnex> {

}




