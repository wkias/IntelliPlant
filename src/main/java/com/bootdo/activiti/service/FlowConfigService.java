package com.bootdo.activiti.service;

import com.bootdo.activiti.domain.FlowConfigDO;

import java.util.List;
import java.util.Map;

public interface FlowConfigService {
    int insertFlowConfig(FlowConfigDO flowConfigDO);

    List<FlowConfigDO> list(Map<String,Object> map);
}
