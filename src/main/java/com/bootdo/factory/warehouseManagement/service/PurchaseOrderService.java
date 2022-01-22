package com.bootdo.factory.warehouseManagement.service;

import com.bootdo.factory.warehouseManagement.domain.PurchaseOrderDO;
import com.bootdo.factory.warehouseManagement.vo.PurchaseOrderVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author xuhui
 * @email 1992lcg@163.com
 * @date 2020-03-18 18:00:09
 */
public interface PurchaseOrderService {
	
	PurchaseOrderDO get(String purchaseId);
	
	List<PurchaseOrderVO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	String save(PurchaseOrderDO purchaseOrder, String procdefId, Map<String,Object> params, HttpServletRequest request);
	
	int audit(PurchaseOrderDO purchaseOrder,Map<String,Object> params);

	int update(PurchaseOrderDO purchaseOrder,Map<String,Object> params);

	int remove(String purchaseId);
	
	int batchRemove(String[] purchaseIds);
}
