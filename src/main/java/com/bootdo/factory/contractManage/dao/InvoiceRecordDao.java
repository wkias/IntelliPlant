package com.bootdo.factory.contractManage.dao;

import com.bootdo.factory.contractManage.domain.InvoiceRecordDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author Casper
 * @email 1992lcg@163.com
 * @date 2020-03-08 16:33:30
 */
@Mapper
public interface InvoiceRecordDao {

	InvoiceRecordDO get(String invoiceId);
	
	List<InvoiceRecordDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(InvoiceRecordDO invoiceRecord);
	
	int update(InvoiceRecordDO invoiceRecord);
	
	int remove(String invoice_id);
	
	int batchRemove(String[] invoiceIds);
}
