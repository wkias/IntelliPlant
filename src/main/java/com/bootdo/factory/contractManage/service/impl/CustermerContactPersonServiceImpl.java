package com.bootdo.factory.contractManage.service.impl;

import com.bootdo.common.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.bootdo.factory.contractManage.dao.CustermerContactPersonDao;
import com.bootdo.factory.contractManage.domain.CustermerContactPersonDO;
import com.bootdo.factory.contractManage.service.CustermerContactPersonService;



@Service
public class CustermerContactPersonServiceImpl implements CustermerContactPersonService {
	@Autowired
	private CustermerContactPersonDao custermerContactPersonDao;
	@Autowired
	private DictService dictService;
	
	@Override
	public CustermerContactPersonDO get(String contactPersonId){
		return custermerContactPersonDao.get(contactPersonId);
	}
	
	@Override
	public List<CustermerContactPersonDO> list(Map<String, Object> map){
		List<CustermerContactPersonDO> list = custermerContactPersonDao.list(map);
		for (CustermerContactPersonDO historyDO : list) {
			String type = dictService.getName("sex", historyDO.getSex());
			historyDO.setSex(type);

		}
		return list;
	}
	
	@Override
	public int count(Map<String, Object> map){
		return custermerContactPersonDao.count(map);
	}
	
	@Override
	public int save(CustermerContactPersonDO custermerContactPerson){
		Map<String,Object> map=new HashMap<>();
		if(custermerContactPerson.getIsMainPerson()){
			map.put("custermerId",custermerContactPerson.getCustermerId());
			map.put("isMainPerson","1");
			if(count(map)>0){
				return 0;
			}
		}
		custermerContactPerson.setContactPersonId(UUID.randomUUID().toString());
		return custermerContactPersonDao.save(custermerContactPerson);
	}
	
	@Override
	public int update(CustermerContactPersonDO custermerContactPerson){
		return custermerContactPersonDao.update(custermerContactPerson);
	}
	
	@Override
	public int remove(String contactPersonId){
		return custermerContactPersonDao.remove(contactPersonId);
	}
	
	@Override
	public int batchRemove(String[] contactPersonIds){
		return custermerContactPersonDao.batchRemove(contactPersonIds);
	}
	
}
