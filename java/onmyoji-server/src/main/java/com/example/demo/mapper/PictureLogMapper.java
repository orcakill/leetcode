package com.example.demo.mapper;

import com.example.demo.model.entity.PictureLogPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
* @author Administrator
* @description 针对表【picture_log(图片日志)】的数据库操作Mapper
* @createDate 2023-03-24 10:02:54
* @Entity com.example.demo.model.entity.PictureLogPO
*/
@Mapper
public interface PictureLogMapper extends BaseMapper<PictureLogPO> {
	
	/***
	 * @description: 图片日志查询接口
	 * @param map map集合 日志ID、图片日志类型、图片日志名称、创建开始时间、创建结束时间
	 * @return: boolean
	 * @author: orcakill
	 * @date: 2023/3/27 8:58
	 */
	List<PictureLogPO> findList (@Param ("map") HashMap<?,?> map);
}




