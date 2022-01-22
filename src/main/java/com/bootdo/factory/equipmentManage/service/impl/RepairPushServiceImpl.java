package com.bootdo.factory.equipmentManage.service.impl;

import com.bootdo.common.config.Constant;
import com.bootdo.common.service.DictService;
import com.bootdo.factory.equipmentManage.dao.RepairPushDao;
import com.bootdo.factory.equipmentManage.domain.RepairPushDO;
import com.bootdo.factory.equipmentManage.service.RepairPushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.UUID;


@Service
public class RepairPushServiceImpl implements RepairPushService {
    @Autowired
    private RepairPushDao repairPushDao;
    @Autowired
    private DictService dictService;

    @Override
    public RepairPushDO get(String pushInfoId) {
        return repairPushDao.get(pushInfoId);
    }

    @Override
    public List<RepairPushDO> list(Map<String, Object> map) {
        List<RepairPushDO> repairPushList = repairPushDao.list(map);
        ListIterator<RepairPushDO> iterator = repairPushList.listIterator();
        while (iterator.hasNext()) {
            RepairPushDO repairPush = iterator.next();
            try {
                String type = dictService.getName(Constant.EQUIPMENT_TYPE, repairPush.getEquipmentType());
                repairPush.setEquipmentType(type);
            } catch (Exception e) {
                //iterator.remove();
            }
        }
        return repairPushList;
    }

    @Override
    public boolean repair(String pushInfoId) {
        return repairPushDao.repair(pushInfoId);
    }

    @Override
    public int count(Map<String, Object> map) {
        return repairPushDao.count(map);
    }

    @Override
    public int save(RepairPushDO repairPush) {
        repairPush.setPushInfoId(UUID.randomUUID().toString());
        return repairPushDao.save(repairPush);
    }

    @Override
    public int update(RepairPushDO repairPush) {
        return repairPushDao.update(repairPush);
    }

    @Override
    public int remove(String pushInfoId) {
        return repairPushDao.remove(pushInfoId);
    }

    @Override
    public int batchRemove(String[] pushInfoIds) {
        return repairPushDao.batchRemove(pushInfoIds);
    }

}
