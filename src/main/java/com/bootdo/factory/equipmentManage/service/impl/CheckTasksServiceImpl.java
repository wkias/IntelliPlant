package com.bootdo.factory.equipmentManage.service.impl;

import com.bootdo.common.config.Constant;
import com.bootdo.common.service.DictService;
import com.bootdo.factory.equipmentManage.dao.CheckTasksDao;
import com.bootdo.factory.equipmentManage.domain.CheckTasksDO;
import com.bootdo.factory.equipmentManage.service.CheckTasksService;
import com.bootdo.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.UUID;


@Service
public class CheckTasksServiceImpl implements CheckTasksService {
    @Autowired
    private CheckTasksDao checkTasksDao;
    @Autowired
    private DictService dictService;
    @Autowired
    private UserService userService;

    @Override
    public CheckTasksDO get(String checkTaskId) {
        CheckTasksDO checkTasksDO = checkTasksDao.get(checkTaskId);
        try {
            String type = dictService.getName(Constant.EQUIPMENT_TYPE, checkTasksDO.getEquipmentType());
            String checkerName = userService.get(Long.parseLong(checkTasksDO.getCheckerId())).getName();
            checkTasksDO.setEquipmentType(type);
            checkTasksDO.setCheckerName(checkerName);
        } catch (Exception e) {
            //iterator.remove();
        }
        return checkTasksDO;
    }

    @Override
    public List<CheckTasksDO> list(Map<String, Object> map) {
        List<CheckTasksDO> checkTaskDOs = checkTasksDao.list(map);
        ListIterator<CheckTasksDO> iterator = checkTaskDOs.listIterator();
        while (iterator.hasNext()) {
            CheckTasksDO checkTaskDO = iterator.next();
            try {
                String type = dictService.getName(Constant.EQUIPMENT_TYPE, checkTaskDO.getEquipmentType());
                String checkerName = userService.get(Long.parseLong(checkTaskDO.getCheckerId())).getName();
                checkTaskDO.setEquipmentType(type);
                checkTaskDO.setCheckerName(checkerName);
            } catch (Exception e) {
                //iterator.remove();
            }
        }
        return checkTaskDOs;
    }

    @Override
    public int count(Map<String, Object> map) {
        return checkTasksDao.count(map);
    }

    @Override
    public int check(String checkTaskId) {
        return checkTasksDao.check(checkTaskId);
    }

    @Override
    public int save(CheckTasksDO checkTask) {
        checkTask.setCheckTaskId(UUID.randomUUID().toString());
        return checkTasksDao.save(checkTask);
    }

    @Override
    public int update(CheckTasksDO checkTasks) {
        return checkTasksDao.update(checkTasks);
    }

    @Override
    public int remove(String checkTaskId) {
        return checkTasksDao.remove(checkTaskId);
    }

    @Override
    public int batchRemove(String[] checkTaskIds) {
        return checkTasksDao.batchRemove(checkTaskIds);
    }

}
