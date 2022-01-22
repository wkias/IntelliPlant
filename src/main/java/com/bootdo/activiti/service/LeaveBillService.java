package com.bootdo.activiti.service;

import com.bootdo.activiti.domain.LeaveBillDO;
import com.bootdo.activiti.vo.LeaveBillVO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author xh
 * @email 1992lcg@163.com
 * @date 2020-03-02 09:38:31
 */
public interface LeaveBillService {
	
	LeaveBillDO get(String billId);
	
	List<LeaveBillVO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(LeaveBillDO leaveBill,String procdefId);
	
	int update(LeaveBillDO leaveBill);
	
	int remove(String billId);
	
	int batchRemove(String[] billIds);
}
