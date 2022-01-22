package com.bootdo.factory.productManage.service;

import com.bootdo.factory.productManage.domain.ProcessInspectionReportDO;

import java.util.List;
import java.util.Map;

/**
 * @author willice
 * @email 2782546116@qq.com
 * @date 2020-04-10 13:28:34
 */
public interface ProcessInspectionReportService {

    ProcessInspectionReportDO get(String processInspectionReportId);

    List<ProcessInspectionReportDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(ProcessInspectionReportDO processInspectionReport);

    int update(ProcessInspectionReportDO processInspectionReport);

    int remove(String processInspectionReportId);

    int batchRemove(String[] processInspectionReportIds);
}
