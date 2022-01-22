package com.bootdo.common.dao;

import com.bootdo.common.domain.CodeNumberDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author xuhui
 * @email 1992lcg@163.com
 * @date 2020-04-28 10:50:15
 */
@Mapper
public interface CodeNumberDao {

	CodeNumberDO get(String id);
	
	List<CodeNumberDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(CodeNumberDO codeNumber);
	
	int update(CodeNumberDO codeNumber);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
