package com.bootdo.factory.dao;

import com.bootdo.factory.domain.BillContentDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author Casper
 * @email 1992lcg@163.com
 * @date 2020-04-18 11:08:28
 */
@Mapper
public interface BillContentDao {

	BillContentDO get(String id);
	
	List<BillContentDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(BillContentDO billContent);
	
	int update(BillContentDO billContent);

	int removeByInvoiceId(String InvoiceId);

	int remove(String id);
	
	int batchRemove(String[] ids);
}
