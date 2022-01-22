package com.bootdo.factory.equipmentManage.service;

import com.bootdo.factory.equipmentManage.domain.EquipmentMaintainCycleDO;

import java.util.List;
import java.util.Map;

/**
 * @author zrj
 * @email 1992lcg@163.com
 * @date 2020-02-19 21:11:33
 */
public interface EquipmentMaintainCycleService {

    EquipmentMaintainCycleDO get(String maintainCycleId);

    List<EquipmentMaintainCycleDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(EquipmentMaintainCycleDO equipmentMaintainCycle);

    int update(EquipmentMaintainCycleDO equipmentMaintainCycle);

    int remove(String maintainCycleId);

    int batchRemove(String[] maintainCycleIds);
}
