package com.bootdo.factory.equipmentManage.service;

import com.bootdo.factory.equipmentManage.domain.EquipmentCheckHistoryDO;

import java.util.List;
import java.util.Map;

/**
 * @author Casper
 * @email 1992lcg@163.com
 * @date 2020-03-01 15:20:46
 */
public interface EquipmentCheckHistoryService {

    EquipmentCheckHistoryDO get(String checkHistoryId);

    List<EquipmentCheckHistoryDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int check(String checkHistoryId);

    int save(EquipmentCheckHistoryDO equipmentCheckHistory);

    int update(EquipmentCheckHistoryDO equipmentCheckHistory);

    int remove(String checkHistoryId);

    int batchRemove(String[] checkHistoryIds);
}
