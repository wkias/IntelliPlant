package com.bootdo.activiti.service.impl;

import com.bootdo.activiti.dao.FlowConfigDao;
import com.bootdo.activiti.domain.FlowConfigDO;
import com.bootdo.activiti.service.FlowConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FlowConfigServiceImpl implements FlowConfigService {
    @Autowired
    private FlowConfigDao flowConfigDao;
    @Override
    public int insertFlowConfig(FlowConfigDO flowConfigDO) {
        return  flowConfigDao.insertFlowConfig(flowConfigDO);
    }

    @Override
    public List<FlowConfigDO> list(Map<String, Object> map) {
        return flowConfigDao.list(map);
    }
}
