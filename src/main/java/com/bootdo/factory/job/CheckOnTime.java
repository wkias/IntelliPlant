package com.bootdo.factory.job;

import com.bootdo.factory.equipmentManage.dao.EquipmentCheckSetDao;
import com.bootdo.factory.equipmentManage.dao.EquipmentManageDao;
import com.bootdo.factory.equipmentManage.domain.CheckTasksDO;
import com.bootdo.factory.equipmentManage.domain.EquipmentManageDO;
import com.bootdo.factory.equipmentManage.service.CheckTasksService;
import com.bootdo.factory.equipmentManage.vo.EquipmentCheckSetVO;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CheckOnTime implements Job {
    @Autowired
    private EquipmentCheckSetDao checkSetDao;
    @Autowired
    private CheckTasksService checkTasksService;
    @Autowired
    private EquipmentManageDao equipmentManageDao;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Map<String, Object> manageParams = new LinkedHashMap<>();
        Map<String, Object> checkParams = new LinkedHashMap<>();
        java.util.Date utilDateNow = new java.util.Date();
        EquipmentCheckSetVO checkSetVO = null;
        manageParams.put("isDeleted", "0");
        List<EquipmentManageDO> equipmentManageDOList = equipmentManageDao.list(manageParams);
        long duration = 0;
        int unit = 0;
        for (EquipmentManageDO equipmentManageDO : equipmentManageDOList) {
            Map<String, Object> setParams = new LinkedHashMap<>();
            setParams.put("isDeleted", "0");
            setParams.put("equipmentId", equipmentManageDO.getEquipmentId());
            try {
                checkSetVO = checkSetDao.list(setParams).get(0);
                switch (checkSetVO.getUnit()) {
                    case "day":
                        unit = 1;
                        break;
                    case "week":
                        unit = 7;
                        break;
                    case "month":
                        unit = 30;
                        break;
                    case "year":
                        unit = 365;
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + checkSetVO.getUnit());
                }
            } catch (IndexOutOfBoundsException e) {
                continue;
            }
            checkParams.put("equipmentId", checkSetVO.getEquipmentId());
            try {
                CheckTasksDO checkTasksDO = checkTasksService.list(checkParams).get(0);
                duration = (utilDateNow.getTime() - checkTasksDO.getDate().getTime()) / (1000 * 60 * 60 * 24);
            } catch (IndexOutOfBoundsException e) {
                createTask(equipmentManageDO, checkSetVO);
                continue;
            }
            if (duration >= checkSetVO.getCheckCycle() * unit) {
                createTask(equipmentManageDO, checkSetVO);
            }
        }
    }

    public void createTask(EquipmentManageDO equipmentManageDO, EquipmentCheckSetVO checkSetVO) {
        java.util.Date utilDateNow = new java.util.Date();
        Date sqlDateNow = new Date(utilDateNow.getTime());
        CheckTasksDO checkTasksDO = new CheckTasksDO();
        checkTasksDO.setEquipmentId(equipmentManageDO.getEquipmentId());
        checkTasksDO.setEquipmentType(equipmentManageDO.getEquipmentType());
        checkTasksDO.setCode(equipmentManageDO.getCode());
        checkTasksDO.setName(equipmentManageDO.getName());
        checkTasksDO.setCheckerId(checkSetVO.getCheckerId());
        checkTasksDO.setDate(sqlDateNow);
        checkTasksDO.setDeadline(checkSetVO.getDeadline());
        checkTasksDO.setState(0);
        checkTasksService.save(checkTasksDO);
    }
}
