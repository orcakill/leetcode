package com.example.model.enums;

/**
 * @Classname SoulEnums
 * @Description TODO
 * @Date 2022/8/28 20:24
 * @Created by orcakill
 */
public enum SoulEnums {
	
	//加成
	addition("scenario/御魂/加成"),
	//宠物奖励
	pet("scenario/御魂/宠物奖励"),
	//御魂图标
	icon("scenario/御魂/御魂图标"),
	//御魂加成
	soul_addition("scenario/御魂/御魂加成"),
	//拒接协战
	refuse_XZ("scenario/御魂/拒接协战"),
	//挑战
	fight("scenario/御魂/挑战"),
	//胜利
	win("scenario/御魂/胜利"),
	//角色头像
	soul_JSTX("scenario/御魂/胜利"),
	//退出挑战
	back_fight("scenario/御魂/退出挑战"),
	
	//御魂类型-八岐大蛇
	type_BQDS("scenario/御魂/御魂类型/八岐大蛇"),
	//御魂类型-业原火
	type_YYH("scenario/御魂/御魂类型/业原火"),
	//御魂类型-日轮之陨
	type_RLZY("scenario/御魂/御魂类型/日轮之陨"),
	//御魂类型-永生之海
	type_YSZH("scenario/御魂/御魂类型/日轮之陨"),
	
	//层数-魂十一
	type_HSY("scenario/御魂/层数/魂十一"),
	//层数-魂十
	type_HS("scenario/御魂/层数/魂十"),
	//层数-业原火三层
	type_YYHSC("scenario/御魂/层数/业原火三层"),
	//层数-日轮之陨三层
	type_RLZYSC("scenario/御魂/层数/日轮之陨三层"),
	//层数-永生之海四层
	type_YSZHSC("scenario/御魂/层数/永生之海四层"),
	;
	
	SoulEnums(String value) {
		this.value = value;
	}
	
	private final String value;
	
	public String getValue() {
		return value;
	}
}
