package com.bootdo.factory.productManage.dao;

import com.bootdo.factory.productManage.domain.ProcessInputDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 工序投入
 * @author xuhui
 * @email 1992lcg@163.com
 * @date 2020-04-26 18:30:11
 */
@Mapper
public interface ProcessInputDao {

	ProcessInputDO get(String id);
	
	List<ProcessInputDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ProcessInputDO processInput);
	
	int update(ProcessInputDO processInput);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
