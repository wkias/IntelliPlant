package com.bootdo.factory.equipmentManage.service;

import com.bootdo.factory.equipmentManage.domain.RepairHistoryDO;

import java.util.List;
import java.util.Map;

/**
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-02-18 14:06:55
 */
public interface RepairHistoryService {

    RepairHistoryDO get(String repairId);

    List<RepairHistoryDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(RepairHistoryDO repairHistory);

    int update(RepairHistoryDO repairHistory);

    int remove(String repairId);

    int batchRemove(String[] repairIds);
}
