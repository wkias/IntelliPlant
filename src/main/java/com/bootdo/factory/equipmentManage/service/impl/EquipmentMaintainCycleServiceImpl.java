package com.bootdo.factory.equipmentManage.service.impl;

import com.bootdo.common.service.DictService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.factory.equipmentManage.dao.EquipmentMaintainCycleDao;
import com.bootdo.factory.equipmentManage.domain.EquipmentMaintainCycleDO;
import com.bootdo.factory.equipmentManage.service.EquipmentMaintainCycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;


@Service
public class EquipmentMaintainCycleServiceImpl implements EquipmentMaintainCycleService {
    @Autowired
    private EquipmentMaintainCycleDao equipmentMaintainCycleDao;
    @Autowired
    private DictService dictService;


    @Override
    public EquipmentMaintainCycleDO get(String maintainCycleId) {
        EquipmentMaintainCycleDO equipment = equipmentMaintainCycleDao.get(maintainCycleId);
        return equipment;
    }

    @Override
    public List<EquipmentMaintainCycleDO> list(Map<String, Object> map) {
        List<EquipmentMaintainCycleDO> list = equipmentMaintainCycleDao.list(map);
        for (EquipmentMaintainCycleDO historyDO : list) {
            String type = dictService.getName("equipment_type", historyDO.getEquipmentType());
            historyDO.setEquipmentType(type);

        }
        return list;
    }

    @Override
    public int count(Map<String, Object> map) {
        return equipmentMaintainCycleDao.count(map);
    }

    @Override
    public int save(EquipmentMaintainCycleDO equipmentMaintainCycle) {
        equipmentMaintainCycle.setMaintainCycleId(UUID.randomUUID().toString());
        return equipmentMaintainCycleDao.save(equipmentMaintainCycle);
    }

    @Override
    public int update(EquipmentMaintainCycleDO equipmentMaintainCycle) {
        return equipmentMaintainCycleDao.update(equipmentMaintainCycle);
    }

    @Override
    public int remove(String maintainCycleId) {
        return equipmentMaintainCycleDao.remove(maintainCycleId);
    }

    @Override
    public int batchRemove(String[] maintainCycleIds) {
        return equipmentMaintainCycleDao.batchRemove(maintainCycleIds);
    }


}
