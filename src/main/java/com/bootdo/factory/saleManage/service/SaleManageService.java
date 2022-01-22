package com.bootdo.factory.saleManage.service;

import com.bootdo.factory.saleManage.domain.SaleManageDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author xgq
 * @email 1992lcg@163.com
 * @date 2020-03-25 15:00:46
 */
public interface SaleManageService {
	
	SaleManageDO get(String saleId);
	
	List<SaleManageDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	String save(SaleManageDO saleManage);
	
	int update(SaleManageDO saleManage);
	
	int remove(String saleId);
	
	int batchRemove(String[] saleIds);
}
