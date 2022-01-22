package com.bootdo.factory.equipmentManage.service.impl;

import com.bootdo.common.config.Constant;
import com.bootdo.common.service.DictService;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.factory.equipmentManage.dao.EquipmentCheckHistoryDao;
import com.bootdo.factory.equipmentManage.domain.EquipmentCheckHistoryDO;
import com.bootdo.factory.equipmentManage.service.EquipmentCheckHistoryService;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class EquipmentCheckHistoryServiceImpl implements EquipmentCheckHistoryService {
    @Autowired
    private EquipmentCheckHistoryDao equipmentCheckHistoryDao;
    @Autowired
    private DictService dictService;
    @Autowired
    private UserService userService;

    @Override
    public EquipmentCheckHistoryDO get(String checkHistoryId) {
        return equipmentCheckHistoryDao.get(checkHistoryId);
    }

    @Override
    public List<EquipmentCheckHistoryDO> list(Map<String, Object> map) {
        List<EquipmentCheckHistoryDO> list = equipmentCheckHistoryDao.list(map);
        for (EquipmentCheckHistoryDO historyDO : list) {
            String type = dictService.getName(Constant.EQUIPMENT_TYPE, historyDO.getEquipmentType());
            historyDO.setEquipmentType(type);
            String userId = historyDO.getCheckerId();
                    UserDO user=userService.get(Long.valueOf(userId));
            historyDO.setCheckerId(user.getName());
        }
        return list;
    }

    @Override
    public int count(Map<String, Object> map) {
        return equipmentCheckHistoryDao.count(map);
    }

    @Override
    public int save(EquipmentCheckHistoryDO equipmentCheckHistory) {
        java.util.Date now = new java.util.Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(now);
        equipmentCheckHistory.setCheckDate(java.sql.Date.valueOf(date));
        return equipmentCheckHistoryDao.save(equipmentCheckHistory);
    }

    @Override
    public int update(EquipmentCheckHistoryDO equipmentCheckHistory) {
        return equipmentCheckHistoryDao.update(equipmentCheckHistory);
    }

    public int check(String checkHistoryId) {
        return equipmentCheckHistoryDao.check(checkHistoryId);
    }

    @Override
    public int remove(String checkHistoryId) {
        return equipmentCheckHistoryDao.remove(checkHistoryId);
    }

    @Override
    public int batchRemove(String[] checkHistoryIds) {
        return equipmentCheckHistoryDao.batchRemove(checkHistoryIds);
    }

}
