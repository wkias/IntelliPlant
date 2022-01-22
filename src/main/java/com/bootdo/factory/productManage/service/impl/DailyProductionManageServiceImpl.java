package com.bootdo.factory.productManage.service.impl;

import com.bootdo.factory.productManage.domain.DailyProductionContentDO;
import com.bootdo.factory.productManage.service.DailyProductionContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.bootdo.factory.productManage.dao.DailyProductionManageDao;
import com.bootdo.factory.productManage.domain.DailyProductionManageDO;
import com.bootdo.factory.productManage.service.DailyProductionManageService;



@Service
public class DailyProductionManageServiceImpl implements DailyProductionManageService {
	@Autowired
	private DailyProductionManageDao dailyProductionManageDao;
	@Autowired
	private DailyProductionContentService dailyProductionContentService;

	@Override
	public DailyProductionManageDO get(String dailyProductionId){
		return dailyProductionManageDao.get(dailyProductionId);
	}
	
	@Override
	public List<DailyProductionManageDO> list(Map<String, Object> map){
		return dailyProductionManageDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return dailyProductionManageDao.count(map);
	}
	
	@Override
	public String save(DailyProductionManageDO dailyProductionManage){
		String id = UUID.randomUUID().toString();
		dailyProductionManage.setDailyProductionId(id);
		dailyProductionManageDao.save(dailyProductionManage);
		return id;
	}
	
	@Override
	public int update(DailyProductionManageDO dailyProductionManage){
		if (dailyProductionManage.getContentList().size()!=0){
			dailyProductionContentService.remove(dailyProductionManage.getDailyProductionId());
			for(DailyProductionContentDO d:dailyProductionManage.getContentList()) {
				d.setDailyProductionContentId(UUID.randomUUID().toString());
				d.setDailyProductionId(dailyProductionManage.getDailyProductionId());
				dailyProductionContentService.save(d);
			}
		}
		return dailyProductionManageDao.update(dailyProductionManage);
	}
	
	@Override
	public int remove(String dailyProductionId){
		return dailyProductionManageDao.remove(dailyProductionId);
	}
	
	@Override
	public int batchRemove(String[] dailyProductionIds){
		return dailyProductionManageDao.batchRemove(dailyProductionIds);
	}
	
}
