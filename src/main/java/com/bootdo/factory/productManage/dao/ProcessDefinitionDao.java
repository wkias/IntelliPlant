package com.bootdo.factory.dao;

import com.bootdo.factory.domain.ProcessDefinitionDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author Casper
 * @email 1992lcg@163.com
 * @date 2020-03-24 11:16:31
 */
@Mapper
public interface ProcessDefinitionDao {

    ProcessDefinitionDO get(String processId);

    List<ProcessDefinitionDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(ProcessDefinitionDO processDefinition);

    int update(ProcessDefinitionDO processDefinition);

    int remove(String process_id);

    int batchRemove(String[] processIds);
}
