package com.bootdo.factory.equipmentManage.dao;

import com.bootdo.factory.equipmentManage.domain.EquipmentCheckHistoryDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author Casper
 * @email 1992lcg@163.com
 * @date 2020-03-01 15:20:46
 */
@Mapper
public interface EquipmentCheckHistoryDao {

    EquipmentCheckHistoryDO get(String checkHistoryId);

    List<EquipmentCheckHistoryDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int check(String checkHistoryId);

    int save(EquipmentCheckHistoryDO equipmentCheckHistory);

    int update(EquipmentCheckHistoryDO equipmentCheckHistory);

    int remove(String check_history_id);

    int batchRemove(String[] checkHistoryIds);
}
