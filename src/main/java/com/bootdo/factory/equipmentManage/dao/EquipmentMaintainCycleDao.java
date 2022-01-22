package com.bootdo.factory.equipmentManage.dao;

import com.bootdo.factory.equipmentManage.domain.EquipmentMaintainCycleDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author zrj
 * @email 1992lcg@163.com
 * @date 2020-02-19 21:11:33
 */
@Mapper
public interface EquipmentMaintainCycleDao {

    EquipmentMaintainCycleDO get(String maintainCycleId);

    List<EquipmentMaintainCycleDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(EquipmentMaintainCycleDO equipmentMaintainCycle);

    int update(EquipmentMaintainCycleDO equipmentMaintainCycle);

    int remove(String maintain_cycle_id);

    int batchRemove(String[] maintainCycleIds);
}
