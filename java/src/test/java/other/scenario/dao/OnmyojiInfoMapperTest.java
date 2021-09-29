package other.scenario.dao;

import org.junit.Test;
import other.scenario.entity.OnmyojiInfoPO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class OnmyojiInfoMapperTest {
	@Test
	public void findById() throws SQLException {
		OnmyojiInfoPO onmyojiInfoPO=OnmyojiInfoMapper.findById("逆戟之刃");
		System.out.println(onmyojiInfoPO.getUserName());
		System.out.println(onmyojiInfoPO.getUserAccount());
		System.out.println(onmyojiInfoPO.getUserType());
		System.out.println(onmyojiInfoPO.getUserAddress());
		System.out.println(onmyojiInfoPO.getUserNum());
	}
	
	@Test
	public void findAll() throws SQLException {
		List<OnmyojiInfoPO> onmyojiInfoPOList=OnmyojiInfoMapper.findAll();
		for(int i=0;i<onmyojiInfoPOList.size();i++){
			System.out.println (onmyojiInfoPOList.get(i).getUserName());
			System.out.println (onmyojiInfoPOList.get(i).getUserAccount());
			System.out.println (onmyojiInfoPOList.get(i).getUserType());
			System.out.println (onmyojiInfoPOList.get(i).getUserAddress());
			System.out.println (onmyojiInfoPOList.get(i).getUserNum());
		}
	}
	
	@Test
	public void check() throws SQLException {
		Boolean boolean1=OnmyojiInfoMapper.check("测试");
		System.out.println (boolean1);
	}
	
	@Test
	public void save() throws SQLException {
		OnmyojiInfoPO onmyojiInfoPO=new OnmyojiInfoPO();
		onmyojiInfoPO.setUserName("测试");
		onmyojiInfoPO.setUserAccount("测试");
		onmyojiInfoPO.setUserType("测试");
		onmyojiInfoPO.setUserAddress("测试");
		onmyojiInfoPO.setUserNum(1);
		OnmyojiInfoMapper.save(onmyojiInfoPO);
	}
	
	@Test
	public void deleteAll() throws SQLException {
		OnmyojiInfoMapper.deleteAll();
	}
	
	@Test




	public void deleteById() throws SQLException {
		OnmyojiInfoMapper.deleteById("测试");
	}
}