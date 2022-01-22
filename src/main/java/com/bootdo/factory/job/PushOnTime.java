package com.bootdo.factory.job;

import com.bootdo.factory.equipmentManage.dao.EquipmentManageDao;
import com.bootdo.factory.equipmentManage.domain.EquipmentMaintainCycleDO;
import com.bootdo.factory.equipmentManage.domain.EquipmentManageDO;
import com.bootdo.factory.equipmentManage.domain.RepairPushDO;
import com.bootdo.factory.equipmentManage.service.EquipmentMaintainCycleService;
import com.bootdo.factory.equipmentManage.service.RepairPushService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PushOnTime implements Job {
    @Autowired
    private EquipmentManageDao equipmentManageDao;
    @Autowired
    private EquipmentMaintainCycleService equipmentMaintainCycleService;
    @Autowired
    private RepairPushService repairPushService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Map<String, Object> manageParams = new LinkedHashMap<>();
        Map<String, Object> pushParams = new LinkedHashMap<>();
        java.util.Date utilDateNow = new java.util.Date();
        EquipmentMaintainCycleDO equipmentMaintainCycle = null;
        manageParams.put("isDeleted", "0");
        List<EquipmentManageDO> equipmentManageDOList = equipmentManageDao.list(manageParams);
        long duration = 0;
        int unit = 0;
        for (EquipmentManageDO equipmentManageDO : equipmentManageDOList) {
            Map<String, Object> cycleParams = new LinkedHashMap<>();
            cycleParams.put("isDeleted", "0");
            cycleParams.put("equipmentType", equipmentManageDO.getEquipmentType());
            try {
                equipmentMaintainCycle = equipmentMaintainCycleService.list(cycleParams).get(0);
                switch (equipmentMaintainCycle.getUnit()) {
                    case "天":
                        unit = 1;
                        break;
                    case "周":
                        unit = 7;
                        break;
                    case "月":
                        unit = 30;
                        break;
                    case "年":
                        unit = 365;
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + equipmentMaintainCycle.getUnit());
                }
            } catch (IndexOutOfBoundsException e) {
                continue;
            }
            pushParams.put("equipmentId", equipmentManageDO.getEquipmentId());
            try {
                RepairPushDO repairPushDO = repairPushService.list(pushParams).get(0);
                duration = (utilDateNow.getTime() - repairPushDO.getLastRepairDate().getTime()) / (1000 * 60 * 60 * 24);
            } catch (IndexOutOfBoundsException e) {
                createTask(equipmentManageDO);
                continue;
            }
            if (duration >= equipmentMaintainCycle.getMaintainCycle() * unit) {
                createTask(equipmentManageDO);
            }
        }
    }

    public void createTask(EquipmentManageDO equipmentManage) {
        java.util.Date utilDateNow = new java.util.Date();
        Date sqlDateNow = new Date(utilDateNow.getTime());
        RepairPushDO repairPushDO = new RepairPushDO();
        repairPushDO.setEquipmentId(equipmentManage.getEquipmentId());
        repairPushDO.setLastRepairDate(sqlDateNow);
        repairPushDO.setState(0);
        repairPushService.save(repairPushDO);
    }
}
