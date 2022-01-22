package com.bootdo.factory.warehouseManagement.service.impl;

import com.bootdo.common.config.Constant;
import com.bootdo.common.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.bootdo.factory.warehouseManagement.dao.LogisticsAccountDao;
import com.bootdo.factory.warehouseManagement.domain.LogisticsAccountDO;
import com.bootdo.factory.warehouseManagement.service.LogisticsAccountService;

@Service
public class LogisticsAccountServiceImpl implements LogisticsAccountService {
    @Autowired
    private LogisticsAccountDao logisticsAccountDao;
    @Autowired
    private DictService dictService;

    @Override
    public LogisticsAccountDO get(String logisticsId) {
        return logisticsAccountDao.get(logisticsId);
    }

    @Override
    public LogisticsAccountDO getWithNameType(String logisticsId) {
        LogisticsAccountDO equipment = logisticsAccountDao.get(logisticsId);
        String name = dictService.getName(Constant.LOGISTICS_COMPANY, equipment.getLogisticsCompany());
        String unit = dictService.getName(Constant.WEIGHT_UNIT, equipment.getWeightUnit());
        equipment.setLogisticsCompany(name);
        equipment.setWeightUnit(unit);
        return equipment;
    }

    @Override
    public List<LogisticsAccountDO> list(Map<String, Object> map) {
        List<LogisticsAccountDO> list = logisticsAccountDao.list(map);
        for (LogisticsAccountDO accountDO : list) {
            String name = dictService.getName(Constant.LOGISTICS_COMPANY, accountDO.getLogisticsCompany());
            String unit = dictService.getName(Constant.WEIGHT_UNIT, accountDO.getWeightUnit());
            accountDO.setLogisticsCompany(name);
            accountDO.setWeightUnit(unit);
        }
        return list;
    }

    @Override
    public int count(Map<String, Object> map) {
        return logisticsAccountDao.count(map);
    }

    @Override
    public int save(LogisticsAccountDO logisticsAccount) {
        logisticsAccount.setLogisticsId(UUID.randomUUID().toString());
        return logisticsAccountDao.save(logisticsAccount);
    }

    @Override
    public int update(LogisticsAccountDO logisticsAccount) {
        return logisticsAccountDao.update(logisticsAccount);
    }

    @Override
    public int remove(String logisticsId) {
        return logisticsAccountDao.remove(logisticsId);
    }

    @Override
    public int batchRemove(String[] logisticsIds) {
        return logisticsAccountDao.batchRemove(logisticsIds);
    }

}