package com.bootdo.factory.equipmentManage.service;

import com.bootdo.factory.equipmentManage.domain.EquipmentManageDO;

import java.util.List;
import java.util.Map;

/**
 * @author xuhui
 * @email 1992lcg@163.com
 * @date 2020-02-18 08:59:15
 */
public interface EquipmentManageService {

    EquipmentManageDO get(String equipmentId);

    EquipmentManageDO getWithNameType(String equipmentId);

    List<EquipmentManageDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(EquipmentManageDO equipmentManage);

    int update(EquipmentManageDO equipmentManage);

    int remove(String equipmentId);

    int batchRemove(String[] equipmentIds);

}
