package com.bootdo.factory.contractManage.service;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.factory.contractManage.domain.CustermerInformationDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author zrj
 * @email 1992lcg@163.com
 * @date 2020-03-06 10:20:56
 */
public interface CustermerInformationService {
	
	CustermerInformationDO get(String custermerId);

	CustermerInformationDO getWithNameType(String custermerId);

	List<CustermerInformationDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CustermerInformationDO custermerInformation);
	
	int update(CustermerInformationDO custermerInformation);
	
	int remove(String custermerId);
	
	int batchRemove(String[] custermerIds);

    PageUtils plist(Map<String, Object> map);
}
