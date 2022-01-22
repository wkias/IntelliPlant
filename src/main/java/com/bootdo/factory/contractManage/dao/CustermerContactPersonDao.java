package com.bootdo.factory.contractManage.dao;

import com.bootdo.factory.contractManage.domain.CustermerContactPersonDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author zrj
 * @email 1992lcg@163.com
 * @date 2020-03-10 18:39:57
 */
@Mapper
public interface CustermerContactPersonDao {

	CustermerContactPersonDO get(String contactPersonId);
	
	List<CustermerContactPersonDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(CustermerContactPersonDO custermerContactPerson);
	
	int update(CustermerContactPersonDO custermerContactPerson);
	
	int remove(String contact_person_id);
	
	int batchRemove(String[] contactPersonIds);


}
