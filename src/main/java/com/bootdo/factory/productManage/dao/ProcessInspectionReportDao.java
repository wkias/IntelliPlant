package com.bootdo.factory.productManage.dao;

import com.bootdo.factory.productManage.domain.ProcessInspectionReportDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author willice
 * @email 2782546116@qq.com
 * @date 2020-04-10 13:28:34
 */
@Mapper
public interface ProcessInspectionReportDao {

    ProcessInspectionReportDO get(String processInspectionReportId);

    List<ProcessInspectionReportDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(ProcessInspectionReportDO processInspectionReport);

    int update(ProcessInspectionReportDO processInspectionReport);

    int remove(String process_inspection_report_id);

    int batchRemove(String[] processInspectionReportIds);
}
