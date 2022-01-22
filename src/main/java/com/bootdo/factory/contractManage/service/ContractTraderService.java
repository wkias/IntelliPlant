package com.bootdo.factory.contractManage.service;

import com.bootdo.factory.contractManage.domain.ContractTraderDO;
import com.bootdo.factory.contractManage.vo.ContractTraderVO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author xuhui
 * @email 1992lcg@163.com
 * @date 2020-03-13 19:24:32
 */
public interface ContractTraderService {
	
	ContractTraderDO get(String id);
	
	List<ContractTraderVO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ContractTraderDO contractTrader);
	
	int update(ContractTraderDO contractTrader);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
