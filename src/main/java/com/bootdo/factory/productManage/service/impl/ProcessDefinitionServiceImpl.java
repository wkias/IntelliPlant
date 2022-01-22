package com.bootdo.factory.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.bootdo.factory.dao.ProcessDefinitionDao;
import com.bootdo.factory.domain.ProcessDefinitionDO;
import com.bootdo.factory.service.ProcessDefinitionService;

@Service
public class ProcessDefinitionServiceImpl implements ProcessDefinitionService {
    @Autowired
    private ProcessDefinitionDao processDefinitionDao;

    @Override
    public ProcessDefinitionDO get(String processId) {
        return processDefinitionDao.get(processId);
    }

    @Override
    public List<ProcessDefinitionDO> list(Map<String, Object> map) {
        return processDefinitionDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return processDefinitionDao.count(map);
    }

    @Override
    public int save(ProcessDefinitionDO processDefinition) {
        processDefinition.setProcessId(UUID.randomUUID().toString());
        return processDefinitionDao.save(processDefinition);
    }

    @Override
    public int update(ProcessDefinitionDO processDefinition) {
        return processDefinitionDao.update(processDefinition);
    }

    @Override
    public void changeState(String processId, String cmd) {
        ProcessDefinitionDO processDefinition = get(processId);
        if ("stop".equals(cmd)) {
            processDefinition.setState(ProcessDefinitionDO.STATE_NOT_USE);
        } else {
            if (!"start".equals(cmd)) {
            } else {
                processDefinition.setState(ProcessDefinitionDO.STATE_USE);
            }
        }
        update(processDefinition);
    }

    @Override
    public int remove(String processId) {
        return processDefinitionDao.remove(processId);
    }

    @Override
    public int batchRemove(String[] processIds) {
        return processDefinitionDao.batchRemove(processIds);
    }
}
