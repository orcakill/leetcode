package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.model.entity.PictureLogPO;

import java.util.HashMap;
import java.util.List;

/**
* @author Administrator
* @description 针对表【picture_log(图片日志)】的数据库操作Service
* @createDate 2023-03-24 10:02:55
*/
public interface PictureLogService extends IService<PictureLogPO> {
	/***
	 * @description: 图片日志保存接口
	 * @param pictureLogPO  图片日志对象
	 * @return: boolean
	 * @author: orcakill
	 * @date: 2023/3/27 8:58
	 */
	boolean save (PictureLogPO pictureLogPO);
	
	/***
	 * @description: 图片日志查询接口
	 * @param map map集合 日志ID、图片日志类型、图片日志名称、创建开始时间、创建结束时间
	 * @return: boolean
	 * @author: orcakill
	 * @date: 2023/3/27 8:58
	 */
	List<PictureLogPO> findList (HashMap<?,?> map);
}
