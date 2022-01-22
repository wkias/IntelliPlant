package com.bootdo.factory.personManage.service.impl;

import com.bootdo.common.config.Constant;
import com.bootdo.common.service.DictService;
import com.bootdo.factory.personManage.vo.WorkLogVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.bootdo.factory.personManage.dao.WorkLogDao;
import com.bootdo.factory.personManage.domain.WorkLogDO;
import com.bootdo.factory.personManage.service.WorkLogService;



@Service
public class WorkLogServiceImpl implements WorkLogService {
	@Autowired
	private WorkLogDao workLogDao;
    @Autowired
    private DictService dictService;
	
	@Override
	public WorkLogDO get(String workLogId){
		return workLogDao.get(workLogId);
	}



    @Override
    public List<WorkLogVO> list(Map<String, Object> map) {
        List<WorkLogVO> list = workLogDao.list(map);
        for (WorkLogVO historyDO : list) {
            String workTimeType = dictService.getName(Constant.WORK_TIME_TYPE, historyDO.getWorkTimeType());

            String hourPeriod = dictService.getName(Constant.HOUR_PERIOD, historyDO.getHourPeriod());

            historyDO.setWorkTimeType(workTimeType);
            historyDO.setHourPeriod(hourPeriod);
        }
        return list;
    }


	
	@Override
	public int count(Map<String, Object> map){
		return workLogDao.count(map);
	}
	
	@Override
	public int save(WorkLogDO workLog){
		workLog.setWorkLogId(UUID.randomUUID().toString());
		return workLogDao.save(workLog);
	}
	
	@Override
	public int update(WorkLogDO workLog){
		return workLogDao.update(workLog);
	}
	
	@Override
	public int remove(String workLogId){
		return workLogDao.remove(workLogId);
	}
	
	@Override
	public int batchRemove(String[] workLogIds){
		return workLogDao.batchRemove(workLogIds);
	}
	
}
