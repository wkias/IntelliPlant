package com.bootdo.factory.productManage.service;

import com.bootdo.factory.productManage.domain.ProcessInspectionDetailDO;

import java.util.List;
import java.util.Map;

/**
 * @author willice
 * @email 2782546116@qq.com
 * @date 2020-04-10 13:28:34
 */
public interface ProcessInspectionDetailService {

    ProcessInspectionDetailDO getOne(String processInspectionDetailId);

    List<ProcessInspectionDetailDO> get(String processInspectionReportId);

    List<ProcessInspectionDetailDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(ProcessInspectionDetailDO processInspectionDetail);

    int update(ProcessInspectionDetailDO processInspectionDetail);

    int remove(String processInspectionDetailId);

    int removeByReport(String processInspectionReportId);

    int batchRemove(String[] processInspectionDetailIds);
}
