package com.example.model.entity;

import lombok.Data;

/**
 * @Classname FightResultPO
 * @Description 战斗结果
 * @Date 2023/1/8 21:47
 * @Created by orcakill
 */
@Data
public class FightResultPO {
	private String fightName;/*战斗类型名称 如御魂-魂十一*/
	private boolean fightWin; /*本次战斗是否胜利*/
	private double fightTime;/*本次战斗用时 如 20（秒）*/
	private int thisRound;/*本轮轮次  如第 1（轮）*/
	private int thisRoundNumber;/*本轮战斗次数  如 第 20（次）*/
	
	public double getFightTime (FightResultPO fightResultPO) {
		return fightResultPO.fightTime;
	}
}
