package com.bootdo.factory.productManage.service;

import com.bootdo.factory.productManage.domain.ProductDetailDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author xgq
 * @email 1992lcg@163.com
 * @date 2020-03-18 21:49:08
 */
public interface ProductDetailService {
	
	ProductDetailDO get(String productId);
	
	List<ProductDetailDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ProductDetailDO productDetail);
	
	int update(ProductDetailDO productDetail);
	
	int remove(String productId);
	
	int batchRemove(String[] productIds);
}
