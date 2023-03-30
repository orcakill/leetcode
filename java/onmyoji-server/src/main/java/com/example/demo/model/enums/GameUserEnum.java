package com.example.demo.model.enums;

/**
 * @author orcakill
 * @version 1.0.0
 * @ClassName GameUserEnum.java
 * @Description 账号信息
 * @createTime 2023年03月30日 09:35:00
 */
public enum GameUserEnum {
	account_NJZR("逆戟之刃","缥缈之旅","大号","手机号1"),//手机号1
	account_ORCAKILL("orcakill","缥缈之旅","协战号","邮箱号1"), //邮箱号1
	account_CRDCCWY("炽热的惆怅物语","两情相悦","协战号","邮箱号1"), //邮箱号1
	account_HHDXL("洪荒的修罗","桃映春馨","协战号","邮箱号1"), //邮箱号1
	;
	
	private final String Name;
	private final String region;
	private  final String type;
	private  final String account;
	
	public String getName () {
		return Name;
	}
	
	public String getRegion () {
		return region;
	}
	
	public String getType () {
		return type;
	}
	
	public String getAccount () {
		return account;
	}
	
	GameUserEnum (String name, String region, String type, String account) {
		Name = name;
		this.region = region;
		this.type = type;
		this.account = account;
	}
}
