package com.bootdo.factory.saleManage.dao;

import com.bootdo.factory.saleManage.domain.SaleManageDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author xgq
 * @email 1992lcg@163.com
 * @date 2020-03-25 15:00:46
 */
@Mapper
public interface SaleManageDao {

	SaleManageDO get(String saleId);
	
	List<SaleManageDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SaleManageDO saleManage);
	
	int update(SaleManageDO saleManage);
	
	int remove(String sale_id);
	
	int batchRemove(String[] saleIds);
}
