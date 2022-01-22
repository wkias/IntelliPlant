package com.bootdo.factory.equipmentManage.dao;

import com.bootdo.factory.equipmentManage.domain.EquipmentCheckSetDO;

import java.util.List;
import java.util.Map;

import com.bootdo.factory.equipmentManage.vo.EquipmentCheckSetVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * @author xuhui
 * @email 1992lcg@163.com
 * @date 2020-02-25 13:46:27
 */
@Mapper
public interface EquipmentCheckSetDao {

	EquipmentCheckSetDO get(String checkSetId);
	
	List<EquipmentCheckSetVO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(EquipmentCheckSetDO equipmentCheckSet);
	
	int update(EquipmentCheckSetDO equipmentCheckSet);
	
	int remove(String check_set_id);
	
	int batchRemove(String[] checkSetIds);

    EquipmentCheckSetVO getVO(String checkSetId);

	int setChecker(@Param("checkSetIds") String[] checkSetIds, @Param("checkerId") String checkerId);
}
