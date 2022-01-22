package com.bootdo.factory.warehouseManagement.dao;

import com.bootdo.factory.warehouseManagement.domain.LogisticsAccountDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author Casper
 * @email 1992lcg@163.com
 * @date 2020-03-17 15:48:07
 */
@Mapper
public interface LogisticsAccountDao {

    LogisticsAccountDO get(String logisticsId);

    List<LogisticsAccountDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(LogisticsAccountDO logisticsAccount);

    int update(LogisticsAccountDO logisticsAccount);

    int remove(String logistics_id);

    int batchRemove(String[] logisticsIds);
}
