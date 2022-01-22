package com.bootdo.factory.contractManage.service;

import com.bootdo.factory.contractManage.domain.InvoiceRecordDO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author Casper
 * @email 1992lcg@163.com
 * @date 2020-03-08 16:33:30
 */
public interface InvoiceRecordService {
	
	InvoiceRecordDO get(String invoiceId);
	
	List<InvoiceRecordDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(InvoiceRecordDO invoiceRecord, HttpServletRequest request);
	
	int update(InvoiceRecordDO invoiceRecord, HttpServletRequest request);
	
	int remove(String invoiceId);
	
	int batchRemove(String[] invoiceIds);
}
