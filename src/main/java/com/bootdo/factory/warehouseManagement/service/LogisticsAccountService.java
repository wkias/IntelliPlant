package com.bootdo.factory.warehouseManagement.service;

import com.bootdo.factory.warehouseManagement.domain.LogisticsAccountDO;

import java.util.List;
import java.util.Map;

/**
 * @author Casper
 * @email 1992lcg@163.com
 * @date 2020-03-17 15:48:07
 */
public interface LogisticsAccountService {

    LogisticsAccountDO get(String logisticsId);

    List<LogisticsAccountDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(LogisticsAccountDO logisticsAccount);

    int update(LogisticsAccountDO logisticsAccount);

    int remove(String logisticsId);

    int batchRemove(String[] logisticsIds);

    LogisticsAccountDO getWithNameType(String logisticsId);
}
