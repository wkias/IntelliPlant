package com.bootdo.factory.equipmentManage.job;

import com.bootdo.factory.equipmentManage.domain.CheckTasksDO;
import com.bootdo.factory.equipmentManage.service.CheckTasksService;
import com.bootdo.factory.equipmentManage.service.EquipmentCheckSetService;
import com.bootdo.factory.equipmentManage.vo.EquipmentCheckSetVO;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.List;

public class CheckOnTime implements Job {
    @Autowired
    private EquipmentCheckSetService checkSetService;
    @Autowired
    private CheckTasksService checkTasksService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LinkedHashMap<String, Object> setParams = new LinkedHashMap<>();
        LinkedHashMap<String, Object> taskParams = new LinkedHashMap<>();
        java.util.Date utilDateNow = new java.util.Date();
        Date sqlDateNow = new Date(utilDateNow.getTime());
        List<EquipmentCheckSetVO> checkSetVOList = checkSetService.list(setParams);
        taskParams.put("equipmentId", "");
        int duration = 0;
        int unit = 0;
        for (EquipmentCheckSetVO checkSetVO : checkSetVOList) {
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
            taskParams.replace("equipmentId", checkSetVO.getEquipmentId());
            List<CheckTasksDO> checkTasksDOList = checkTasksService.list(taskParams);
            try {
                CheckTasksDO checkTasksDO = checkTasksDOList.get(0);
                duration = calculateTimeDifferenceBySimpleDateFormat(checkTasksDO.getDate(), sqlDateNow);
            } catch (NullPointerException e) {
                createTask(checkSetVO);
                continue;
            } catch (ParseException e) {
            }
            if (duration == checkSetVO.getCheckCycle() * unit) {
                createTask(checkSetVO);
            }
        }
    }

    public void createTask(EquipmentCheckSetVO checkSetVO) {
        java.util.Date utilDateNow = new java.util.Date();
        Date sqlDateNow = new Date(utilDateNow.getTime());
        CheckTasksDO checkTasksDO = new CheckTasksDO();
        checkTasksDO.setCheckerName(checkSetVO.getCheckerName());
        checkTasksDO.setEquipmentType(checkSetVO.getEquipmentType());
        checkTasksDO.setCode(checkSetVO.getEquipmentCode());
        checkTasksDO.setDate(sqlDateNow);
        checkTasksDO.setDeadline(checkSetVO.getDeadline());
        checkTasksDO.setState(0);
        checkTasksService.save(checkTasksDO);
    }

    public int calculateTimeDifferenceBySimpleDateFormat(Date startDate, Date stopDate) throws ParseException {
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        /*天数差*/
        java.util.Date start = simpleFormat.parse(startDate.toString());
        java.util.Date stop = simpleFormat.parse(stopDate.toString());
        long from = start.getTime();
        long to = stop.getTime();
        return (int) ((to - from) / (1000 * 60 * 60 * 24));
    }
}
