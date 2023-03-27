package com.example.demo.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.mapper.PictureLogMapper;
import com.example.demo.model.entity.PictureLogPO;
import com.example.demo.service.PictureLogService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
* @author Administrator
* @description 针对表【picture_log(图片日志)】的数据库操作Service实现
* @createDate 2023-03-24 10:02:55
*/
@Service
public class PictureLogServiceImpl extends ServiceImpl<PictureLogMapper, PictureLogPO>
    implements PictureLogService{
	private final PictureLogMapper pictureLogMapper;
	
	public PictureLogServiceImpl (PictureLogMapper pictureLogMapper) {
		this.pictureLogMapper = pictureLogMapper;
	}
	
	/***
	 * @description: 图片日志保存接口
	 * @param pictureLogPO  图片日志对象
	 * @return: boolean
	 * @author: orcakill
	 * @date: 2023/3/27 8:58
	 */
	@Override
	public boolean save (PictureLogPO pictureLogPO) {
		Date date = new Date();
		date.setTime(System.currentTimeMillis());
		PictureLogPO pictureLogPO1 = pictureLogMapper.selectById (pictureLogPO.getLogId ());
		//    插入
		if (Objects.isNull (pictureLogPO1)) {
			if(pictureLogPO.getLogId ()==null){
				String id = IdUtil.objectId ();
				pictureLogPO.setLogId (id);
			}
			pictureLogPO.setCreateTime(date);
			pictureLogMapper.insert(pictureLogPO);
		}
		//   更新
		else{
			pictureLogPO.setUpdateTime(date);
			pictureLogMapper.updateById(pictureLogPO);
		}
		return  true;
	}
	
	/***
	 * @description: 图片日志查询接口
	 * @param map map集合 日志ID、图片日志类型、图片日志名称、创建开始时间、创建结束时间
	 * @return: boolean
	 * @author: orcakill
	 * @date: 2023/3/27 8:58
	 */
	@Override
	public List<PictureLogPO> findList (HashMap<?, ?> map) {
		return null;
	}
}




