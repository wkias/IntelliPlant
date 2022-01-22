package com.bootdo.factory.personManage.dao;

import com.bootdo.factory.personManage.domain.WorkLogDO;

import java.util.List;
import java.util.Map;

import com.bootdo.factory.personManage.vo.WorkLogVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author zrj
 * @email 1992lcg@163.com
 * @date 2020-03-13 17:22:04
 */
@Mapper
public interface WorkLogDao {

	WorkLogDO get(String workLogId);
	
	List<WorkLogVO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(WorkLogDO workLog);
	
	int update(WorkLogDO workLog);
	
	int remove(String work_log_id);
	
	int batchRemove(String[] workLogIds);
}
