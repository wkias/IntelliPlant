package com.bootdo.factory.productManage.service;

import com.bootdo.factory.productManage.domain.ProductPlanDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author xuhui
 * @email 1992lcg@163.com
 * @date 2020-04-07 20:22:08
 */
public interface ProductPlanService {
	
	ProductPlanDO get(String planId);
	
	List<ProductPlanDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ProductPlanDO productPlan);
	
	int update(ProductPlanDO productPlan);
	
	int remove(String planId);
	
	int batchRemove(String[] planIds);
}
