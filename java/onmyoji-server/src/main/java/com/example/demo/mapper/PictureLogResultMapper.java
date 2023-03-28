package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.model.entity.PictureLogResultPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
* @author Administrator
* @description 针对表【picture_log_result】的数据库操作Mapper
* @createDate 2023-03-24 17:00:59
* @Entity com.example.demo.model.entity.PictureLogResultPO
*/
@Mapper
public interface PictureLogResultMapper extends BaseMapper<PictureLogResultPO> {
    List<PictureLogResultPO>  findList(@Param ("map")HashMap<?,?> map);
}




