package com.bootdo.factory.warehouseManagement.dao;

import com.bootdo.factory.warehouseManagement.domain.PurchaseDetailDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author xuhui
 * @email 1992lcg@163.com
 * @date 2020-03-18 21:40:50
 */
@Mapper
public interface PurchaseDetailDao {

	PurchaseDetailDO get(String id);
	
	List<PurchaseDetailDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(PurchaseDetailDO purchaseDetail);
	
	int update(PurchaseDetailDO purchaseDetail);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
