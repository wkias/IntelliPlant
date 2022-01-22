package com.bootdo.factory.personManage.service;

import com.bootdo.factory.personManage.domain.WorkLogDO;
import com.bootdo.factory.personManage.vo.WorkLogVO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author zrj
 * @email 1992lcg@163.com
 * @date 2020-03-13 17:22:04
 */
public interface WorkLogService {
	
	WorkLogDO get(String workLogId);
	
	List<WorkLogVO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(WorkLogDO workLog);
	
	int update(WorkLogDO workLog);
	
	int remove(String workLogId);
	
	int batchRemove(String[] workLogIds);
}
