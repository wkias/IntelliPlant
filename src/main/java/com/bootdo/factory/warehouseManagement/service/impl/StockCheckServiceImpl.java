package com.bootdo.factory.warehouseManagement.service.impl;

import com.bootdo.factory.warehouseManagement.dao.StockCheckDao;
import com.bootdo.factory.warehouseManagement.domain.StockCheckDO;
import com.bootdo.factory.warehouseManagement.service.StockCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class StockCheckServiceImpl implements StockCheckService {
	@Autowired
	private StockCheckDao stockCheckDao;
    @Autowired
    private StockCheckService stockCheckService;
	
	@Override
	public StockCheckDO get(String stockCheckId){
		return stockCheckDao.get(stockCheckId);
	}
	
	@Override
	public List<StockCheckDO> list(Map<String, Object> map){
		return stockCheckDao.list(map);
	}

	@Override
	public int count(Map<String, Object> map){
		return stockCheckDao.count(map);
	}

	@Override
	public int save(StockCheckDO stockCheck){
        List<StockCheckDO> stockCheckDOS =stockCheckService.list(new HashMap());
        for(StockCheckDO stock : stockCheckDOS ){
           if((stock.getProductId()).equals(stockCheck.getProductId())){
               stockCheck.setStockCheckId(stock.getStockCheckId());
               stockCheck.setQuantity(stockCheck.getQuantity()+stock.getQuantity());
               stockCheck.setWeight(stockCheck.getWeight()+stock.getWeight());
               return stockCheckDao.update(stockCheck);
           }
        };
        stockCheck.setStockCheckId(UUID.randomUUID().toString());
        return stockCheckDao.save(stockCheck);
	}

	@Override
	public int update(StockCheckDO stockCheck) {
		return stockCheckDao.update(stockCheck);
	}

	@Override
	public int remove(String stockCheckId) {
		return stockCheckDao.remove(stockCheckId);
	}
    @Override
	public int removeByProduct(StockCheckDO stockCheck) {
        List<StockCheckDO> stockCheckDOS =stockCheckService.list(new HashMap());
        for(StockCheckDO stock : stockCheckDOS ){
            if((stock.getProductId()).equals(stockCheck.getProductId())){
                stockCheck.setStockCheckId(stock.getStockCheckId());
                stockCheck.setQuantity(stock.getQuantity()-stockCheck.getQuantity());
                stockCheck.setWeight(stock.getWeight()-stockCheck.getWeight());
            }
        };
        return stockCheckDao.update(stockCheck);
	}


	@Override
	public int batchRemove(String[] stockCheckIds) {
		return stockCheckDao.batchRemove(stockCheckIds);
	}

}
