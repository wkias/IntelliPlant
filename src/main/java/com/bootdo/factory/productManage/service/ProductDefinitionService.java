package com.bootdo.factory.productManage.service;

import com.bootdo.factory.productManage.domain.ProductDefinitionDO;
import com.bootdo.factory.productManage.vo.ProductDefinitionVO;

import java.util.List;
import java.util.Map;

/**
 * @author zrj
 * @email 1992lcg@163.com
 * @date 2020-03-24 11:12:52
 */
public interface ProductDefinitionService {

    ProductDefinitionDO get(String productId);

    ProductDefinitionVO getVO(String productId);

    List<ProductDefinitionDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(ProductDefinitionDO productDefinition);

    int update(ProductDefinitionDO productDefinition);

    int remove(String productId);

    int batchRemove(String[] productIds);

    void changeState(String productId, String cmd);
}
