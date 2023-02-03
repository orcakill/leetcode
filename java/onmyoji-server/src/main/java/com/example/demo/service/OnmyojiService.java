package com.example.demo.service;

import com.example.demo.model.entity.PictureCollectionPO;
import org.springframework.scheduling.annotation.Async;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Classname OnmyojiService
 * @Description 阴阳师自动战斗
 * @Date 2023/1/25 20:56
 * @Created by orcakill
 */
public interface OnmyojiService {
	/***
	 * @description: 阴阳师自动战斗
	 * @param type  类型
	 * @param round   轮次
	 * @return: java.lang.String
	 * @author: orcakill
	 * @date: 2023/1/25 20:58
	 */
	@Async
	void onmyojiService (Integer type, Integer round) throws InterruptedException;
	
	/***
	 * @description: 开启自动游戏
	 * @param type  类型
	 * @param round   轮次
	 * @return: void
	 * @author: orcakill
	 * @date: 2023/1/28 23:42
	 */
	
	void autoActivity (Integer type, Integer round);
	
	/***
	 * @description: 阴阳师游戏角色ID登录，默认登录大号角色
	 * @param gameUserId 阴阳师游戏角色ID
	 * @return: void
	 * @author: orcakill
	 * @date: 2023/1/28 23:21
	 */
	void login (String gameUserId);
}
