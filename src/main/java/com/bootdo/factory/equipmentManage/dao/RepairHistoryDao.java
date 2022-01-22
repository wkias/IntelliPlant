package com.bootdo.factory.equipmentManage.dao;

import com.bootdo.factory.equipmentManage.domain.RepairHistoryDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-02-18 14:06:55
 */
@Mapper
public interface RepairHistoryDao {

    RepairHistoryDO get(String repairId);

    List<RepairHistoryDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(RepairHistoryDO repairHistory);

    int update(RepairHistoryDO repairHistory);

    int remove(String repair_id);

    int batchRemove(String[] repairIds);
}
