package com.bootdo.factory.productManage.service.impl;

import com.bootdo.common.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.factory.productManage.dao.ProductDetailDao;
import com.bootdo.factory.productManage.domain.ProductDetailDO;
import com.bootdo.factory.productManage.service.ProductDetailService;



@Service
public class ProductDetailServiceImpl implements ProductDetailService {
	@Autowired
	private ProductDetailDao productDetailDao;
	@Autowired
	private DictService dictService;


	@Override
	public ProductDetailDO get(String productId){
		ProductDetailDO product = productDetailDao.get(productId);
		product.setQuantityUnitName(dictService.getName("quantity_unit",product.getQuantityUnit()));
		return product;
	}
	
	@Override
	public List<ProductDetailDO> list(Map<String, Object> map){
		return productDetailDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return productDetailDao.count(map);
	}
	
	@Override
	public int save(ProductDetailDO productDetail){
		return productDetailDao.save(productDetail);
	}
	
	@Override
	public int update(ProductDetailDO productDetail){
		return productDetailDao.update(productDetail);
	}
	
	@Override
	public int remove(String productId){
		return productDetailDao.remove(productId);
	}
	
	@Override
	public int batchRemove(String[] productIds){
		return productDetailDao.batchRemove(productIds);
	}
	
}
