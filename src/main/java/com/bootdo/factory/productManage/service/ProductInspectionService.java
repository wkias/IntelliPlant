package com.bootdo.factory.productManage.service;

import com.bootdo.factory.productManage.domain.ProductInspectionDO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author Casper
 * @email 1992lcg@163.com
 * @date 2020-03-28 20:02:59
 */
public interface ProductInspectionService {

    ProductInspectionDO get(String productInspectionId);

    List<ProductInspectionDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(ProductInspectionDO productInspection, HttpServletRequest request);

    int update(ProductInspectionDO productInspection, HttpServletRequest request);

    int remove(String productInspectionId);

    int batchRemove(String[] productInspectionIds);

    ProductInspectionDO getWithNameType(String productInspectionId);
}
