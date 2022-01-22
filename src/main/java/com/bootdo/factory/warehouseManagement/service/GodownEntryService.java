package com.bootdo.factory.warehouseManagement.service;

import com.bootdo.factory.productManage.domain.ProductDetailDO;
import com.bootdo.factory.warehouseManagement.domain.GodownEntryDO;

import java.util.List;
import java.util.Map;

/**
 * @author willice
 * @email 2782546116@qq.com
 * @date 2020-03-19 11:12:54
 */
public interface GodownEntryService {

    GodownEntryDO get(String godownEntryId);

    List<GodownEntryDO> list(Map<String, Object> map);

    List<ProductDetailDO> listPurchaseDetails(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(GodownEntryDO godownEntry);

    int update(GodownEntryDO godownEntry);

    int remove(String godownEntryId);

    int batchRemove(String[] godownEntryIds);

}
