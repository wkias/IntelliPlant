package com.bootdo.factory.productManage.service.impl;

import com.bootdo.common.service.DictService;
import com.bootdo.factory.productManage.dao.ProductDefinitionDao;
import com.bootdo.factory.productManage.domain.ProductDefinitionDO;
import com.bootdo.factory.productManage.service.ProductDefinitionService;
import com.bootdo.factory.productManage.vo.ProductDefinitionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;


@Service
public class ProductDefinitionServiceImpl implements ProductDefinitionService {
    @Autowired
    private ProductDefinitionDao productDefinitionDao;
    @Autowired
    private DictService dictService;

    @Override
    public ProductDefinitionDO get(String productId) {
        ProductDefinitionDO p = productDefinitionDao.get(productId);
        p.setUnitName(dictService.getName("unit",p.getUnit()));
        return p;
    }

    @Override
    public ProductDefinitionVO getVO(String productId) {
        return productDefinitionDao.getVO(productId);
    }

    @Override
    public List<ProductDefinitionDO> list(Map<String, Object> map) {
        List<ProductDefinitionDO> list = productDefinitionDao.list(map);
        return list;
    }

    @Override
    public int count(Map<String, Object> map) {
        return productDefinitionDao.count(map);
    }

    @Override
    public int save(ProductDefinitionDO productDefinition) {
        productDefinition.setProductId(UUID.randomUUID().toString());
        return productDefinitionDao.save(productDefinition);
    }

    @Override
    public int update(ProductDefinitionDO productDefinition) {
        return productDefinitionDao.update(productDefinition);
    }

    @Override
    public int remove(String productId) {
        return productDefinitionDao.remove(productId);
    }

    @Override
    public int batchRemove(String[] productIds) {
        return productDefinitionDao.batchRemove(productIds);
    }

    @Override
    public void changeState(String productId, String cmd) {
        ProductDefinitionDO productDefinition = get(productId);

        if ("stop".equals(cmd)) {

            productDefinition.setState(ProductDefinitionDO.STATE_NOT_USE);
        } else {
            if (!"start".equals(cmd)) {
            } else {
                productDefinition.setState(ProductDefinitionDO.STATE_USE);

            }
        }
        update(productDefinition);
    }

}
