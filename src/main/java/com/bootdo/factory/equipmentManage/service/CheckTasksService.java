package com.bootdo.factory.equipmentManage.service;

import com.bootdo.factory.equipmentManage.domain.CheckTasksDO;

import java.util.List;
import java.util.Map;

/**
 * @author willice
 * @email 1992lcg@163.com
 * @date 2020-02-25 13:11:24
 */
public interface CheckTasksService {

    CheckTasksDO get(String checkTaskId);

    List<CheckTasksDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int check(String checkTaskId);

    int save(CheckTasksDO checkTasks);

    int update(CheckTasksDO checkTasks);

    int remove(String checkTaskId);

    int batchRemove(String[] checkTaskIds);

}
