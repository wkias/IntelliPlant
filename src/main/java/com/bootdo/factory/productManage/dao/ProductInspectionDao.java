package com.bootdo.factory.productManage.dao;

import com.bootdo.factory.productManage.domain.ProductInspectionDO;

import java.util.List;
import java.util.Map;

import com.bootdo.factory.productManage.domain.ProductInspectionDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Casper
 * @email 1992lcg@163.com
 * @date 2020-03-27 10:41:48
 */
@Mapper
public interface ProductInspectionDao {

    ProductInspectionDO get(String productInspectionId);

    List<ProductInspectionDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(ProductInspectionDO productInspection);

    int update(ProductInspectionDO productInspection);

    int remove(String product_inspection_id);

    int batchRemove(String[] productInspectionIds);
}
