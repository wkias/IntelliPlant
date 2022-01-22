package com.bootdo.factory.equipmentManage.service;

import com.bootdo.factory.equipmentManage.domain.EquipmentCheckSetDO;
import com.bootdo.factory.equipmentManage.vo.EquipmentCheckSetVO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author xuhui
 * @email 1992lcg@163.com
 * @date 2020-02-25 13:46:27
 */
public interface EquipmentCheckSetService {
	
	EquipmentCheckSetDO get(String checkSetId);
	
	List<EquipmentCheckSetVO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(EquipmentCheckSetDO equipmentCheckSet);
	
	int update(EquipmentCheckSetDO equipmentCheckSet);
	
	int remove(String checkSetId);
	
	int batchRemove(String[] checkSetIds);

    EquipmentCheckSetVO getVO(String checkSetId);

    int setChecker(String[] checkSeIds,String checkerId);
}
