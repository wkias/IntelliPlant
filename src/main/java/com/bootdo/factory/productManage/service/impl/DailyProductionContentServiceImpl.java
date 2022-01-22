package com.bootdo.factory.productManage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.factory.productManage.dao.DailyProductionContentDao;
import com.bootdo.factory.productManage.domain.DailyProductionContentDO;
import com.bootdo.factory.productManage.service.DailyProductionContentService;



@Service
public class DailyProductionContentServiceImpl implements DailyProductionContentService {
	@Autowired
	private DailyProductionContentDao dailyProductionContentDao;
	
	@Override
	public DailyProductionContentDO get(String dailyProductionContentId){
		return dailyProductionContentDao.get(dailyProductionContentId);
	}
	
	@Override
	public List<DailyProductionContentDO> list(Map<String, Object> map){
		return dailyProductionContentDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return dailyProductionContentDao.count(map);
	}
	
	@Override
	public int save(DailyProductionContentDO dailyProductionContent){
		return dailyProductionContentDao.save(dailyProductionContent);
	}
	
	@Override
	public int update(DailyProductionContentDO dailyProductionContent){
		return dailyProductionContentDao.update(dailyProductionContent);
	}
	
	@Override
	public int remove(String dailyProductionContentId){
		return dailyProductionContentDao.remove(dailyProductionContentId);
	}
	
	@Override
	public int batchRemove(String[] dailyProductionContentIds){
		return dailyProductionContentDao.batchRemove(dailyProductionContentIds);
	}
	
}
