package other.scenario.entity;

import lombok.Data;

import javax.persistence.Id;

@Data
public class OnmyojiInfoPO {
	@Id
	private String userName;  //账号名称
	private String userAccount;  //账号
	private String userType;   //账号类型
	private String userAddress; //账号大区
	private Integer userNum;//账号序号
}
