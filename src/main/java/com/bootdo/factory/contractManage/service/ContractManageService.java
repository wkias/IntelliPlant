package com.bootdo.factory.contractManage.service;

import com.bootdo.factory.contractManage.domain.ContractManageDO;
import com.bootdo.factory.contractManage.vo.ContractManageVO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author xuhui
 * @email 1992lcg@163.com
 * @date 2020-03-09 15:22:58
 */
public interface ContractManageService {
	
	ContractManageDO get(String contractId);

	ContractManageVO getVO(String contractId);

	List<ContractManageVO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	String save(ContractManageDO contractManage);
	
	int update(ContractManageVO contractManage);
	
	int remove(String contractId);
	
	int batchRemove(String[] contractIds);
}
