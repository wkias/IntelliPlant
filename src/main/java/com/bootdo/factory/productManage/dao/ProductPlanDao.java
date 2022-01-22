package com.bootdo.factory.productManage.dao;

import com.bootdo.factory.productManage.domain.ProductPlanDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author xuhui
 * @email 1992lcg@163.com
 * @date 2020-04-07 20:22:08
 */
@Mapper
public interface ProductPlanDao {

	ProductPlanDO get(String planId);
	
	List<ProductPlanDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ProductPlanDO productPlan);
	
	int update(ProductPlanDO productPlan);
	
	int remove(String plan_id);
	
	int batchRemove(String[] planIds);
}
