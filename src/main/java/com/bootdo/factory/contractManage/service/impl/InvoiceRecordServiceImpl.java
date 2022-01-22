package com.bootdo.factory.contractManage.service.impl;

import com.bootdo.common.config.Constant;
import com.bootdo.common.service.DictService;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.factory.contractManage.domain.ReceiveRecordDO;
import com.bootdo.factory.dao.BillContentDao;
import com.bootdo.factory.domain.BillContentDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import com.bootdo.factory.contractManage.dao.InvoiceRecordDao;
import com.bootdo.factory.contractManage.domain.InvoiceRecordDO;
import com.bootdo.factory.contractManage.service.InvoiceRecordService;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;


@Service
public class InvoiceRecordServiceImpl implements InvoiceRecordService {
    @Autowired
    private InvoiceRecordDao invoiceRecordDao;
    @Autowired
    private DictService dictService;
    @Autowired
    private BillContentDao billContentDao;

    @Override
    public InvoiceRecordDO get(String invoiceId) {
        InvoiceRecordDO invoiceRecordDO = invoiceRecordDao.get(invoiceId);
        String invoiceTypeName = dictService.getName("invoice_type", invoiceRecordDO.getInvoiceTypeName());
        invoiceRecordDO.setInvoiceTypeName(invoiceTypeName);
        return invoiceRecordDO;
    }

    @Override
    public List<InvoiceRecordDO> list(Map<String, Object> map) {
        List<InvoiceRecordDO> list = invoiceRecordDao.list(map);
        for (InvoiceRecordDO invoiceRecordDO : list) {
            String type = dictService.getName(Constant.INVOICE_TYPE, invoiceRecordDO.getInvoiceType());
            invoiceRecordDO.setInvoiceType(type);
            String invoiceTypeName = dictService.getName("invoice_type", invoiceRecordDO.getInvoiceTypeName());
            invoiceRecordDO.setInvoiceTypeName(invoiceTypeName);
        }
        return list;
    }

    @Override
    public int count(Map<String, Object> map) {
        return invoiceRecordDao.count(map);
    }

    @Override
    @Transactional
    public int save(InvoiceRecordDO invoiceRecord, HttpServletRequest request) {
        invoiceRecord.setInvoiceId(UUID.randomUUID().toString());
        invoiceRecord.setIsDeleted(false);
        invoiceRecord.setCreateUserId(ShiroUtils.getUserId() + "");
        invoiceRecord.setCreateTime(new Date());
        String[] projectNames = request.getParameterValues("productName");
        String[] specifications = request.getParameterValues("specification");
        String[] units = request.getParameterValues("unit");
        String[] quantitys = request.getParameterValues("quantity");
        String[] unitPrices = request.getParameterValues("unitPrice");
        String[] amounts = request.getParameterValues("amount");
        String[] taxRates = request.getParameterValues("taxRate");
        String[] taxAmounts = request.getParameterValues("taxAmount");
        if (invoiceRecordDao.save(invoiceRecord) > 0) {
            for (int i = 0; i <projectNames.length; i++) {
                BillContentDO billContent =  new BillContentDO();
                billContent.setId(UUID.randomUUID().toString());
                billContent.setInvoiceId(invoiceRecord.getInvoiceId());
                billContent.setProductName(projectNames[i]);
                billContent.setSpecification(specifications[i]);
                billContent.setUnit(units[i]);
                billContent.setQuantity(quantitys[i]);
                billContent.setUnitPrice(unitPrices[i]);
                billContent.setAmount(amounts[i]);
                billContent.setTaxRate(taxRates[i]);
                billContent.setTaxAmount(taxAmounts[i]);
                billContent.setIsDeleted(false);
                billContent.setCreateTime(new Date());
                billContent.setCreateUserId(ShiroUtils.getUserId() + "");
                billContentDao.save(billContent);
            }
        }
        return 1;
    }

    @Override
    @Transactional
    public int update(InvoiceRecordDO invoiceRecord, HttpServletRequest request) {
        String[] projectNames = request.getParameterValues("productName");
        String[] specifications = request.getParameterValues("specification");
        String[] units = request.getParameterValues("unit");
        String[] quantitys = request.getParameterValues("quantity");
        String[] unitPrices = request.getParameterValues("unitPrice");
        String[] amounts = request.getParameterValues("amount");
        String[] taxRates = request.getParameterValues("taxRate");
        String[] taxAmounts = request.getParameterValues("taxAmount");
        if (invoiceRecordDao.update(invoiceRecord) > 0) {
            billContentDao.removeByInvoiceId(invoiceRecord.getInvoiceId());
            for (int i = 0; i < projectNames.length; i++) {
                BillContentDO billContent =  new BillContentDO();
                billContent.setId(UUID.randomUUID().toString());
                billContent.setInvoiceId(invoiceRecord.getInvoiceId());
                billContent.setProductName(projectNames[i]);
                billContent.setSpecification(specifications[i]);
                billContent.setUnit(units[i]);
                billContent.setQuantity(quantitys[i]);
                billContent.setUnitPrice(unitPrices[i]);
                billContent.setAmount(amounts[i]);
                billContent.setTaxRate(taxRates[i]);
                billContent.setTaxAmount(taxAmounts[i]);
                billContent.setIsDeleted(false);
                billContent.setCreateTime(new Date());
                billContent.setCreateUserId(ShiroUtils.getUserId() + "");
                billContentDao.save(billContent);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int remove(String invoiceId) {
        return invoiceRecordDao.remove(invoiceId);
    }

    @Override
    public int batchRemove(String[] invoiceIds) {
        return invoiceRecordDao.batchRemove(invoiceIds);
    }

}
