package com.bootdo.factory.productManage.dao;

import com.bootdo.factory.productManage.domain.ProductDetailDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author xgq
 * @email 1992lcg@163.com
 * @date 2020-03-18 21:49:08
 */
@Mapper
public interface ProductDetailDao {

	ProductDetailDO get(String productId);
	
	List<ProductDetailDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ProductDetailDO productDetail);
	
	int update(ProductDetailDO productDetail);
	
	int remove(String product_id);
	
	int batchRemove(String[] productIds);
}
