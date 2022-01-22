package com.bootdo.factory.equipmentManage.service.impl;

import com.bootdo.common.config.Constant;
import com.bootdo.common.service.DictService;
import com.bootdo.factory.equipmentManage.dao.RepairHistoryDao;
import com.bootdo.factory.equipmentManage.domain.RepairHistoryDO;
import com.bootdo.factory.equipmentManage.service.RepairHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class RepairHistoryServiceImpl implements RepairHistoryService {
    @Autowired
    private RepairHistoryDao repairHistoryDao;
    @Autowired
    private DictService dictService;

    @Override
    public RepairHistoryDO get(String repairId) {
        return repairHistoryDao.get(repairId);
    }

    @Override
    public List<RepairHistoryDO> list(Map<String, Object> map) {
        List<RepairHistoryDO> list = repairHistoryDao.list(map);
        for (RepairHistoryDO historyDO : list) {
            String type = dictService.getName(Constant.EQUIPMENT_TYPE, historyDO.getEquipmentType());

            String state = dictService.getName(Constant.REPAIR_STATE, historyDO.getState());

            historyDO.setEquipmentType(type);
            historyDO.setState(state);
        }
        return list;
    }

    @Override
    public int count(Map<String, Object> map) {
        return repairHistoryDao.count(map);
    }

    @Override
    public int save(RepairHistoryDO repairHistory) {
        return repairHistoryDao.save(repairHistory);
    }

    @Override
    public int update(RepairHistoryDO repairHistory) {
        return repairHistoryDao.update(repairHistory);
    }

    @Override
    public int remove(String repairId) {
        return repairHistoryDao.remove(repairId);
    }

    @Override
    public int batchRemove(String[] repairIds) {
        return repairHistoryDao.batchRemove(repairIds);
    }

}
