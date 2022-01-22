package com.bootdo.factory.equipmentManage.service;

import com.bootdo.factory.equipmentManage.domain.RepairPushDO;

import java.util.List;
import java.util.Map;

/**
 * @author willice
 * @date 2020-02-19 17:15:02
 */
public interface RepairPushService {

    RepairPushDO get(String pushInfoId);

    List<RepairPushDO> list(Map<String, Object> map);

    boolean repair(String pushInfoId);

    int count(Map<String, Object> map);

    int save(RepairPushDO repairPush);

    int update(RepairPushDO repairPush);

    int remove(String pushInfoId);

    int batchRemove(String[] pushInfoIds);
}
