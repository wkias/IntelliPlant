package com.bootdo.activiti.dao;

import com.bootdo.activiti.domain.FlowConfigDO;

import java.util.List;
import java.util.Map;

public interface FlowConfigDao {
    int insertFlowConfig(FlowConfigDO flowConfigDO);

    List<FlowConfigDO> list(Map<String, Object> map);
}
