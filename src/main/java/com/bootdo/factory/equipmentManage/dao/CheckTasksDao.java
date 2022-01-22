package com.bootdo.factory.equipmentManage.dao;

import com.bootdo.factory.equipmentManage.domain.CheckTasksDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author willice
 */
@Mapper
public interface CheckTasksDao {

    CheckTasksDO get(String checkTaskId);

    List<CheckTasksDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int check(String checkTaskId);

    int save(CheckTasksDO checkTasks);

    int update(CheckTasksDO checkTasks);

    int remove(String checkTaskId);

    int batchRemove(String[] checkTaskIds);
}
