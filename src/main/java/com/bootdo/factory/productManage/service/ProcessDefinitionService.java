package com.bootdo.factory.service;

import com.bootdo.factory.domain.ProcessDefinitionDO;

import java.util.List;
import java.util.Map;

/**
 * @author Casper
 * @email 1992lcg@163.com
 * @date 2020-03-24 11:16:31
 */
public interface ProcessDefinitionService {

    ProcessDefinitionDO get(String processId);

    List<ProcessDefinitionDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(ProcessDefinitionDO processDefinition);

    int update(ProcessDefinitionDO processDefinition);

    void changeState(String processId, String cmd);

    int remove(String processId);

    int batchRemove(String[] processIds);
}
