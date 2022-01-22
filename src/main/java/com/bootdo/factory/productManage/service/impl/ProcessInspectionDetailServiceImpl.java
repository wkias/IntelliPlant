package com.bootdo.factory.productManage.service.impl;

import com.bootdo.factory.productManage.dao.ProcessInspectionDetailDao;
import com.bootdo.factory.productManage.domain.ProcessInspectionDetailDO;
import com.bootdo.factory.productManage.service.ProcessInspectionDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;


@Service
public class ProcessInspectionDetailServiceImpl implements ProcessInspectionDetailService {
    @Autowired
    private ProcessInspectionDetailDao processInspectionDetailDao;

    @Override
    public ProcessInspectionDetailDO getOne(String processInspectionDetailId) {
        return processInspectionDetailDao.getOne(processInspectionDetailId);
    }

    @Override
    public List<ProcessInspectionDetailDO> get(String processInspectionReportId) {
        List<ProcessInspectionDetailDO> processInspectionDetailDOList = processInspectionDetailDao.get(processInspectionReportId);
        for (ProcessInspectionDetailDO processInspectionDetailDO : processInspectionDetailDOList) {
            processInspectionDetailDO.setNum(processInspectionDetailDO.getQualifiedNum() + processInspectionDetailDO.getScrapNum() + processInspectionDetailDO.getReworkNum());
        }
        return processInspectionDetailDOList;
    }

    @Override
    public List<ProcessInspectionDetailDO> list(Map<String, Object> map) {
        List<ProcessInspectionDetailDO> processInspectionDetailDOList = processInspectionDetailDao.list(map);
        for (ProcessInspectionDetailDO processInspectionDetailDO : processInspectionDetailDOList) {
            processInspectionDetailDO.setNum(processInspectionDetailDO.getQualifiedNum() + processInspectionDetailDO.getScrapNum() + processInspectionDetailDO.getReworkNum());
        }
        return processInspectionDetailDOList;
    }

    @Override
    public int count(Map<String, Object> map) {
        return processInspectionDetailDao.count(map);
    }

    @Override
    public int save(ProcessInspectionDetailDO processInspectionDetail) {
        processInspectionDetail.setProcessInspectionDetailId(UUID.randomUUID().toString());
        return processInspectionDetailDao.save(processInspectionDetail);
    }

    @Override
    public int update(ProcessInspectionDetailDO processInspectionDetail) {
        return processInspectionDetailDao.update(processInspectionDetail);
    }

    @Override
    public int remove(String processInspectionDetailId) {
        return processInspectionDetailDao.remove(processInspectionDetailId);
    }

    @Override
    public int removeByReport(String processInspectionReportId) {
        return processInspectionDetailDao.removeByReport(processInspectionReportId);
    }

    @Override
    public int batchRemove(String[] processInspectionDetailIds) {
        return processInspectionDetailDao.batchRemove(processInspectionDetailIds);
    }

}
