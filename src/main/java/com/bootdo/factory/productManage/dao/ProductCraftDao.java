package com.bootdo.factory.productManage.dao;

import com.bootdo.factory.productManage.domain.ProductCraftDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author xuhui
 * @email 1992lcg@163.com
 * @date 2020-03-26 11:53:33
 */
@Mapper
public interface ProductCraftDao {

	ProductCraftDO get(String productCraftId);
	
	List<ProductCraftDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ProductCraftDO productCraft);
	
	int update(ProductCraftDO productCraft);
	
	int remove(String product_craft_id);
	
	int batchRemove(String[] productCraftIds);
}
