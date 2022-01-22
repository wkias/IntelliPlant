package com.bootdo.factory.saleManage.service;

import com.bootdo.factory.saleManage.domain.SaleContentDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author xgq
 * @email 1992lcg@163.com
 * @date 2020-03-29 23:21:55
 */
public interface SaleContentService {
	
	SaleContentDO get(String saleContentId);
	
	List<SaleContentDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SaleContentDO saleContent);
	
	int update(SaleContentDO saleContent);
	
	int remove(String saleContentId);
	
	int batchRemove(String[] saleContentIds);

	List<SaleContentDO> getBySaleId(String saleId);
}
