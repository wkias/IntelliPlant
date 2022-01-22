package com.bootdo.factory.contractManage.dao;

import com.bootdo.factory.contractManage.domain.CustermerInformationDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author zrj
 * @email 1992lcg@163.com
 * @date 2020-03-06 10:20:56
 */
@Mapper
public interface CustermerInformationDao {

	CustermerInformationDO get(String custermerId);
	
	List<CustermerInformationDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(CustermerInformationDO custermerInformation);
	
	int update(CustermerInformationDO custermerInformation);
	
	int remove(String custermer_id);
	
	int batchRemove(String[] custermerIds);
}
