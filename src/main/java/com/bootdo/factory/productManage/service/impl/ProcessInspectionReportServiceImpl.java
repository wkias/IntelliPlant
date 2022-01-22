package com.bootdo.factory.productManage.service.impl;

import com.bootdo.factory.productManage.dao.ProcessInspectionReportDao;
import com.bootdo.factory.productManage.domain.ProcessInspectionDetailDO;
import com.bootdo.factory.productManage.domain.ProcessInspectionReportDO;
import com.bootdo.factory.productManage.service.ProcessInspectionDetailService;
import com.bootdo.factory.productManage.service.ProcessInspectionReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;


@Service
public class ProcessInspectionReportServiceImpl implements ProcessInspectionReportService {
    @Autowired
    private ProcessInspectionReportDao processInspectionReportDao;
    @Autowired
    private ProcessInspectionDetailService processInspectionDetailService;

    @Override
    public ProcessInspectionReportDO get(String processInspectionReportId) {
        return processInspectionReportDao.get(processInspectionReportId);
    }

    @Override
    public List<ProcessInspectionReportDO> list(Map<String, Object> map) {
        return processInspectionReportDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return processInspectionReportDao.count(map);
    }

    @Override
    public int save(ProcessInspectionReportDO processInspectionReport) {
        StringBuilder processName = new StringBuilder();
        StringBuilder componentName = new StringBuilder();
        StringBuilder unit = new StringBuilder();
        int qualified = 0;
        int rework = 0;
        int scrap = 0;
        processInspectionReport.setProcessInspectionReportId(UUID.randomUUID().toString());
        for (ProcessInspectionDetailDO processInspectionDetailDO : processInspectionReport.getProcessInspectionDetailDOList()) {
            processInspectionDetailDO.setProcessInspectionDetailId(UUID.randomUUID().toString());
            processInspectionDetailDO.setProcessInspectionReportId(processInspectionReport.getProcessInspectionReportId());
            processInspectionDetailService.save(processInspectionDetailDO);
            processName.append(processInspectionDetailDO.getProcessName()).append(";");
            componentName.append(processInspectionDetailDO.getComponentName()).append(";");
            unit.append(processInspectionDetailDO.getUnit()).append(";");
            qualified += processInspectionDetailDO.getQualifiedNum();
            rework += processInspectionDetailDO.getReworkNum();
            scrap += processInspectionDetailDO.getScrapNum();
        }
        processInspectionReport.setProcessName(processName.toString());
        processInspectionReport.setComponentName(componentName.toString());
        processInspectionReport.setUnit(unit.toString());
        processInspectionReport.setQualifiedNum(qualified);
        processInspectionReport.setReworkNum(rework);
        processInspectionReport.setScrapNum(scrap);
        return processInspectionReportDao.save(processInspectionReport);
    }

    @Override
    public int update(ProcessInspectionReportDO processInspectionReport) {
        processInspectionReport.setProcessInspectionDetailDOList(processInspectionDetailService.get(processInspectionReport.getProcessInspectionReportId()));
        StringBuilder processName = new StringBuilder();
        StringBuilder componentName = new StringBuilder();
        StringBuilder unit = new StringBuilder();
        int qualified = 0;
        int rework = 0;
        int scrap = 0;
        for (ProcessInspectionDetailDO processInspectionDetailDO : processInspectionReport.getProcessInspectionDetailDOList()) {
            processInspectionDetailDO.setProcessInspectionDetailId(UUID.randomUUID().toString());
            processInspectionDetailDO.setProcessInspectionReportId(processInspectionReport.getProcessInspectionReportId());
            processName.append(processInspectionDetailDO.getProcessName()).append(";");
            componentName.append(processInspectionDetailDO.getComponentName()).append(";");
            unit.append(processInspectionDetailDO.getUnit()).append(";");
            qualified += processInspectionDetailDO.getQualifiedNum();
            rework += processInspectionDetailDO.getReworkNum();
            scrap += processInspectionDetailDO.getScrapNum();
        }
        processInspectionReport.setProcessName(processName.toString());
        processInspectionReport.setComponentName(componentName.toString());
        processInspectionReport.setUnit(unit.toString());
        processInspectionReport.setQualifiedNum(qualified);
        processInspectionReport.setReworkNum(rework);
        processInspectionReport.setScrapNum(scrap);
        return processInspectionReportDao.update(processInspectionReport);
    }

    @Override
    public int remove(String processInspectionReportId) {
        processInspectionDetailService.removeByReport(processInspectionReportId);
        return processInspectionReportDao.remove(processInspectionReportId);
    }

    @Override
    public int batchRemove(String[] processInspectionReportIds) {
        for (String id : processInspectionReportIds) {
            processInspectionDetailService.removeByReport(id);
        }
        return processInspectionReportDao.batchRemove(processInspectionReportIds);
    }

}
