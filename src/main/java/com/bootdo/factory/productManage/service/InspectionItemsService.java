package com.bootdo.factory.productManage.service;

import com.bootdo.factory.productManage.domain.InspectionItemsDO;

import java.util.List;
import java.util.Map;

/**
 * @author willice
 * @email 2782546116@qq.com
 * @date 2020-03-26 12:19:21
 */
public interface InspectionItemsService {

    InspectionItemsDO get(String inspectionItemsId);

    List<InspectionItemsDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(InspectionItemsDO inspectionItems);

    int update(InspectionItemsDO inspectionItems);

    int switchh(String inspectionItemsId);

    int remove(String inspectionItemsId);

    int batchRemove(String[] inspectionItemsIds);

}
