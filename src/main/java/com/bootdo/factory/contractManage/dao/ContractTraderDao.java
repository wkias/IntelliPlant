package com.bootdo.factory.contractManage.dao;

import com.bootdo.factory.contractManage.domain.ContractTraderDO;

import java.util.List;
import java.util.Map;

import com.bootdo.factory.contractManage.vo.ContractTraderVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author xuhui
 * @email 1992lcg@163.com
 * @date 2020-03-13 19:24:32
 */
@Mapper
public interface ContractTraderDao {

	ContractTraderDO get(String id);
	
	List<ContractTraderVO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ContractTraderDO contractTrader);
	
	int update(ContractTraderDO contractTrader);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
