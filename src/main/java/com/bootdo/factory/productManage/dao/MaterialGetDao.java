package com.bootdo.factory.productManage.dao;

import com.bootdo.factory.productManage.domain.MaterialGetDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author xuhui
 * @email 1992lcg@163.com
 * @date 2020-04-27 14:06:39
 */
@Mapper
public interface MaterialGetDao {

	MaterialGetDO get(String id);
	
	List<MaterialGetDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(MaterialGetDO materialGet);

	int useMaterial(MaterialGetDO materialGet);

	int update(MaterialGetDO materialGet);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
