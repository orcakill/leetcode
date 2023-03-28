package com.example.demo.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.mapper.PictureLogResultMapper;
import com.example.demo.model.entity.PictureLogResultPO;
import com.example.demo.service.PictureLogResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
* @author Administrator
* @description 针对表【picture_log_result】的数据库操作Service实现
* @createDate 2023-03-24 17:00:59
*/
@Service
public class PictureLogResultServiceImpl extends ServiceImpl<PictureLogResultMapper, PictureLogResultPO>
    implements PictureLogResultService{
	@Autowired
	private  PictureLogResultMapper pictureLogResultMapper;
	
	/***
	 * @description: 图片识别结果保存服务
	 * @param pictureLogResultPO   图片识别结果
	 * @return: boolean
	 * @author: orcakill
	 * @date: 2023/3/28 8:37
	 */
	@Override
	public boolean save (PictureLogResultPO pictureLogResultPO) {
		Date date = new Date();
		date.setTime(System.currentTimeMillis());
		PictureLogResultPO pictureLogResultPO1 = pictureLogResultMapper.selectById (pictureLogResultPO.getResultId ());
		//    插入
		if (Objects.isNull (pictureLogResultPO1)) {
			if(pictureLogResultPO.getLogId ()==null){
				String id = IdUtil.objectId ();
				pictureLogResultPO.setResultId (id);
			}
			pictureLogResultPO.setCreateTime(date);
			pictureLogResultPO.setCreateUser ("逆戟之刃");
			pictureLogResultMapper.insert(pictureLogResultPO);
		}
		//   更新
		else{
			pictureLogResultPO.setUpdateTime(date);
			pictureLogResultPO.setUpdateUser ("逆戟之刃");
			pictureLogResultMapper.updateById(pictureLogResultPO);
		}
		return  true;
	}
	
	/***
	 * @description: 图片识别结果查询服务
	 * @param map  参数集合
	 * @return: java.util.List<com.example.demo.model.entity.PictureLogResultPO>
	 * @author: orcakill
	 * @date: 2023/3/28 16:25
	 */
	@Override
	public List<PictureLogResultPO> findList (HashMap<?, ?> map) {
		return pictureLogResultMapper.findList (map);
	}
	
	/***
	 * @description: 图片识别删除服务
	 * @param resultId  主键ID
	 * @return: void
	 * @author: orcakill
	 * @date: 2023/3/28 16:49
	 */
	@Override
	public void deleteById (String resultId) {
		pictureLogResultMapper.deleteById (resultId);
	}
}




