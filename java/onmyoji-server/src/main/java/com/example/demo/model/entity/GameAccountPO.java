package com.example.demo.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Classname GameAccountPO
 * @Description 账号信息
 * @Date 2023/1/29 0:16
 * @Created by orcakill
 */
@Data
@TableName ("game_account")
public class GameAccountPO {
	@TableId
	private String id; /*ID*/
	private String accountName; /*账号名称*/
	private String accountPassword; /*账号密码*/
	private String gameRegion; /*游戏服务器*/
	private String gameName; /*角色名称*/
	private String accountType; /*账号类型*/
	private String accountClass; /*账号等级*/
	private String accountNumber; /*账号序号*/
}
