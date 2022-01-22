package com.bootdo.factory.contractManage.service.impl;

import com.bootdo.common.config.Constant;
import com.bootdo.common.service.DictService;
import com.bootdo.common.utils.DateUtils;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.factory.contractManage.dao.CustermerContactPersonDao;
import com.bootdo.factory.contractManage.domain.CustermerContactPersonDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.bootdo.factory.contractManage.dao.CustermerInformationDao;
import com.bootdo.factory.contractManage.domain.CustermerInformationDO;
import com.bootdo.factory.contractManage.service.CustermerInformationService;



@Service
public class CustermerInformationServiceImpl implements CustermerInformationService {
	@Autowired
	private CustermerInformationDao custermerInformationDao;
	@Autowired
	private DictService dictService;
    @Autowired
    private CustermerContactPersonDao custermerContactPersonDao;
	@Override
	public CustermerInformationDO get(String custermerId){
		CustermerInformationDO custermer = custermerInformationDao.get(custermerId);
		Map<String,Object> personMap=new HashMap<>();
		personMap.put("custermerId",custermerId);
		personMap.put("isMainPerson","1");
		List<CustermerContactPersonDO> mainPersonS=custermerContactPersonDao.list(personMap);
		CustermerContactPersonDO mainPerson;
		if(mainPersonS!=null&&mainPersonS.size()>0){
			mainPerson=mainPersonS.get(0);
			custermer.setMainContactPerson(mainPerson.getContactPersonId());
		}
		return custermer;
	}

	@Override
	public CustermerInformationDO getWithNameType(String custermerId) {
		CustermerInformationDO equipment = custermerInformationDao.get(custermerId);
		String type="";
		if (equipment.getIsSupplier()!=null&&equipment.getIsSupplier()!=true){
			 type = dictService.getName("custermer_type", equipment.getCustermerType());
		}else{
			 type = dictService.getName("supplier_type", equipment.getCustermerType());
		}
		equipment.setCustermerType(type);
		return equipment;
	}

	@Override
	public List<CustermerInformationDO> list(Map<String, Object> map){
		if(map.get("isSupplier")==null){
			map.put("isSupplier",'0');
		}
		List <CustermerInformationDO> list = custermerInformationDao.list(map);
		for (CustermerInformationDO historyDO : list) {
			String type="";
			if (historyDO.getIsSupplier()==null||historyDO.getIsSupplier()!=true){
				type = dictService.getName("custermer_type", historyDO.getCustermerType());
			}else{
				type = dictService.getName("supplier_type", historyDO.getCustermerType());
			}
			historyDO.setCustermerType(type);
			Map<String,Object> personMap=new HashMap<>();
			personMap.put("custermerId",historyDO.getCustermerId());
			personMap.put("isMainPerson","1");
			List<CustermerContactPersonDO> mainPersonS=custermerContactPersonDao.list(personMap);
			CustermerContactPersonDO mainPerson;
			if(mainPersonS!=null&&mainPersonS.size()>0){
				mainPerson=mainPersonS.get(0);
				historyDO.setMainContactPerson(mainPerson.getContactPersonId());
			}
		}
		return list;
	}
	
	@Override
	public int count(Map<String, Object> map){
		return custermerInformationDao.count(map);
	}
	
	@Override
	public int save(CustermerInformationDO custermerInformation){
		custermerInformation.setCustermerId(UUID.randomUUID().toString());
		if(custermerInformation.getIsSupplier()==null){//默认为需求商
			custermerInformation.setIsSupplier(false);
		}
		return custermerInformationDao.save(custermerInformation);
	}
	
	@Override
	public int update(CustermerInformationDO custermerInformation){
		return custermerInformationDao.update(custermerInformation);
	}
	
	@Override
	public int remove(String custermerId){
		return custermerInformationDao.remove(custermerId);
	}
	
	@Override
	public int batchRemove(String[] custermerIds){
		return custermerInformationDao.batchRemove(custermerIds);
	}

    @Override
    public PageUtils plist(Map<String, Object> map) {
        List<CustermerContactPersonDO> rows = custermerContactPersonDao.list(map);

        PageUtils page = new PageUtils(rows, custermerContactPersonDao.count(map));
        return page;
    }
	
}
