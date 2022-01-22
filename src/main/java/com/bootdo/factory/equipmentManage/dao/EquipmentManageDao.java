package com.bootdo.factory.equipmentManage.dao;

import com.bootdo.factory.equipmentManage.domain.EquipmentManageDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author xuhui
 * @email 1992lcg@163.com
 * @date 2020-02-18 08:59:15
 */
@Mapper
public interface EquipmentManageDao {

    EquipmentManageDO get(String equipmentId);

    List<EquipmentManageDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(EquipmentManageDO equipmentManage);

    int update(EquipmentManageDO equipmentManage);

    int remove(String equipment_id);

    int batchRemove(String[] equipmentIds);

    Long[] listAllType();
}
