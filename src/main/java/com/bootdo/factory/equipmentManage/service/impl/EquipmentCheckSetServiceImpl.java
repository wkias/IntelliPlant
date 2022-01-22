package com.bootdo.factory.equipmentManage.service.impl;

import com.bootdo.common.config.Constant;
import com.bootdo.common.service.DictService;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.factory.equipmentManage.domain.EquipmentManageDO;
import com.bootdo.factory.equipmentManage.service.EquipmentManageService;
import com.bootdo.factory.equipmentManage.vo.EquipmentCheckSetVO;
import com.bootdo.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import com.bootdo.factory.equipmentManage.dao.EquipmentCheckSetDao;
import com.bootdo.factory.equipmentManage.domain.EquipmentCheckSetDO;
import com.bootdo.factory.equipmentManage.service.EquipmentCheckSetService;



@Service
public class EquipmentCheckSetServiceImpl implements EquipmentCheckSetService {
	@Autowired
	private EquipmentCheckSetDao equipmentCheckSetDao;
	@Autowired
	private DictService dictService;
	@Autowired
	private UserService userService;

	@Override
	public EquipmentCheckSetDO get(String checkSetId){
		return equipmentCheckSetDao.get(checkSetId);
	}
	@Override
	public EquipmentCheckSetVO getVO(String checkSetId) {
		EquipmentCheckSetVO checkSetVO=equipmentCheckSetDao.getVO(checkSetId);
		String type = dictService.getName(Constant.EQUIPMENT_TYPE, checkSetVO.getEquipmentType());
		checkSetVO.setEquipmentType(type);
		return checkSetVO;
	}



	@Override
	public List<EquipmentCheckSetVO> list(Map<String, Object> map){
		List<EquipmentCheckSetVO> list = equipmentCheckSetDao.list(map);
		ListIterator<EquipmentCheckSetVO> iterator = list.listIterator();
		while (iterator.hasNext()) {
			EquipmentCheckSetVO item = iterator.next();
			//if(equipment.getIsDeleted()){iterator.remove();continue;}
			try {
				String type = dictService.getName(Constant.EQUIPMENT_TYPE, item.getEquipmentType());
				item.setEquipmentType(type);
				String timeUnit = dictService.getName(Constant.CHECK_CYCLE,item.getUnit());
				item.setUnit(timeUnit);
				String checkerName =null;
				if(item.getCheckerId()!=null){
					 checkerName =userService.get(Long.valueOf(item.getCheckerId())).getName();
					item.setCheckerName(checkerName);
				}




			} catch (Exception e) {
				//iterator.remove();
				e.printStackTrace();
			}
		}
		return list;
	}
	
	@Override
	public int count(Map<String, Object> map){
		return equipmentCheckSetDao.count(map);
	}
	
	@Override
	public int save(EquipmentCheckSetDO equipmentCheckSet){
		Map<String,Object> map = new HashMap<>();
		map.put("equipmentId",equipmentCheckSet.getEquipmentId());
		List<EquipmentCheckSetVO> origEquipmentCheckSetList=list(map);
		if(origEquipmentCheckSetList.size()!=0){
			equipmentCheckSet.setCheckSetId(origEquipmentCheckSetList.get(0).getCheckSetId());
			return update(equipmentCheckSet);
		};
		equipmentCheckSet.setCheckSetId(UUID.randomUUID().toString());
		equipmentCheckSet.setIsDeleted(false);
		equipmentCheckSet.setCreateTime(new Date());
		equipmentCheckSet.setCreateUserId(ShiroUtils.getUserId()+"");
		return equipmentCheckSetDao.save(equipmentCheckSet);
	}
	
	@Override
	public int update(EquipmentCheckSetDO equipmentCheckSet){
		return equipmentCheckSetDao.update(equipmentCheckSet);
	}
	
	@Override
	public int remove(String checkSetId){
		return equipmentCheckSetDao.remove(checkSetId);
	}
	
	@Override
	public int batchRemove(String[] checkSetIds){
		return equipmentCheckSetDao.batchRemove(checkSetIds);
	}


	@Override
	public int setChecker(String[] checkSeIds, String checkerId) {
		return equipmentCheckSetDao.setChecker(checkSeIds,checkerId);
	}
}
