package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.model.entity.PictureLogResultPO;

import java.util.HashMap;
import java.util.List;

/**
* @author Administrator
* @description 针对表【picture_log_result】的数据库操作Service
* @createDate 2023-03-24 17:00:59
*/
public interface PictureLogResultService extends IService<PictureLogResultPO> {
	/***
	 * @description: 图片识别结果保存服务
	 * @param pictureLogResultPO   图片识别结果
	 * @return: boolean
	 * @author: orcakill
	 * @date: 2023/3/28 8:37
	 */
	boolean save(PictureLogResultPO pictureLogResultPO);
	/*** 
	 * @description: 图片识别结果查询服务
	 * @param map   参数集合
	 * @return: java.util.List<com.example.demo.model.entity.PictureLogResultPO>
	 * @author: orcakill
	 * @date: 2023/3/28 16:25
	 */
	
	List<PictureLogResultPO> findList(HashMap<?,?> map);
	
	/***
	 * @description: 图片识别删除服务
	 * @param resultId  主键ID
	 * @return: void
	 * @author: orcakill
	 * @date: 2023/3/28 16:49
	 */
	
	void  deleteById(String resultId);

}
