package com.bootdo.activiti.dao;

import com.bootdo.activiti.domain.LeaveBillDO;

import java.util.List;
import java.util.Map;

import com.bootdo.activiti.vo.LeaveBillVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author xh
 * @email 1992lcg@163.com
 * @date 2020-03-02 09:38:31
 */
@Mapper
public interface LeaveBillDao {

	LeaveBillDO get(String billId);
	
	List<LeaveBillVO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(LeaveBillDO leaveBill);
	
	int update(LeaveBillDO leaveBill);
	
	int remove(String bill_id);
	
	int batchRemove(String[] billIds);
}
