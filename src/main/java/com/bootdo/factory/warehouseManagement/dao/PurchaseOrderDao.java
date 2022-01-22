package com.bootdo.factory.warehouseManagement.dao;

import com.bootdo.factory.warehouseManagement.domain.PurchaseOrderDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author xuhui
 * @email 1992lcg@163.com
 * @date 2020-03-18 18:00:09
 */
@Mapper
public interface PurchaseOrderDao {

	PurchaseOrderDO get(String purchaseId);
	
	List<PurchaseOrderDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(PurchaseOrderDO purchaseOrder);
	
	int update(PurchaseOrderDO purchaseOrder);
	
	int remove(String purchase_id);
	
	int batchRemove(String[] purchaseIds);
}
