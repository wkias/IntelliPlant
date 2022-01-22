package com.bootdo.factory.contractManage.service.impl;

import com.bootdo.common.config.Constant;
import com.bootdo.common.service.DictService;
import com.bootdo.factory.contractManage.domain.ContractTraderDO;
import com.bootdo.factory.contractManage.domain.CustermerInformationDO;
import com.bootdo.factory.contractManage.service.ContractTraderService;
import com.bootdo.factory.contractManage.service.CustermerContactPersonService;
import com.bootdo.factory.contractManage.service.CustermerInformationService;
import com.bootdo.factory.contractManage.vo.ContractManageVO;
import com.bootdo.factory.contractManage.vo.ContractTraderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import com.bootdo.factory.contractManage.dao.ContractManageDao;
import com.bootdo.factory.contractManage.domain.ContractManageDO;
import com.bootdo.factory.contractManage.service.ContractManageService;

import javax.naming.event.ObjectChangeListener;


@Service
public class ContractManageServiceImpl implements ContractManageService {
	@Autowired
	private ContractManageDao contractManageDao;
	@Autowired
	private DictService dictService;
	@Autowired
	private CustermerInformationService custermerInformationService;
	@Autowired
	private ContractTraderService contractTraderService;
	@Autowired
	private CustermerContactPersonService  contactPersonService;
	@Override
	public ContractManageDO get(String contractId){
		return contractManageDao.get(contractId);
	}
	@Override
	public ContractManageVO getVO(String contractId){

		ContractManageVO contractManageVO=contractManageDao.getVO(contractId);
		//处理字典值
		try{String typeName=dictService.getName(Constant.CONTRACT_TYPE,contractManageVO.getContractType());
			contractManageVO.setTypeName(typeName);} catch (Exception e) {
			e.printStackTrace();
		}
		try{
			String stateName= dictService.getName(Constant.CONTRACT_STATE,contractManageVO.getState());
			contractManageVO.setStateName(stateName); }
		catch (Exception e) {
			e.printStackTrace();
		}
		//供应商列表
		List<ContractTraderVO> suppliers = new ArrayList<>();
		//需求商列表
		List<ContractTraderVO> demanders = new ArrayList<>();
		//获取该合同所有交易方
		Map<String, Object> conTraderMap=new HashMap<>();
		conTraderMap.put("contractId",contractManageVO.getContractId());
		List<ContractTraderVO> contractTraderS=contractTraderService.list(conTraderMap);
		for(ContractTraderVO contractTrader:contractTraderS){
			String traderId=contractTrader.getTraderId();
			String traderType=contractTrader.getTraderType();
			Map<String, Object> custermerMap=new HashMap<>();
			custermerMap.put("custermerId",traderId);
			custermerMap.put("isSupplier",traderType.equals("Sup")?"1":"0");
			custermerMap.put("getAll","1");
			//当前交易方信息
			List<CustermerInformationDO> custermerS=custermerInformationService.list(custermerMap);
			if(custermerS!=null&&!custermerS.isEmpty()){
				 contractTrader.setTrader(custermerS.get(0));
			}
			//分类
			if("Sup".equals(traderType)){
				suppliers.add(contractTrader);
			}else if("Dem".equals(traderType)){
				demanders.add(contractTrader);
			}
		}
		contractManageVO.setSuppliers(suppliers);
		contractManageVO.setDemanders(demanders);
		return contractManageVO;
	}
	@Override
	public List<ContractManageVO> list(Map<String, Object> map){
		List<ContractManageVO> contracts=contractManageDao.list(map);

		for(ContractManageVO contract:contracts){
			try{String typeName=dictService.getName(Constant.CONTRACT_TYPE,contract.getContractType());
				contract.setTypeName(typeName);} catch (Exception e) {
				e.printStackTrace();
			}
			try{
			String stateName= dictService.getName(Constant.CONTRACT_STATE,contract.getState());
			contract.setStateName(stateName); }
			catch (Exception e) {
				e.printStackTrace();
			}

		}
		return contracts;
	}
	
	@Override
	public int count(Map<String, Object> map){
		return contractManageDao.count(map);
	}
	
	@Override
	public String save(ContractManageDO contractManage){

		String id=UUID.randomUUID().toString();
		contractManage.setContractId(id);
		contractManageDao.save(contractManage);
		return id;
	}
	
	@Override
	public int update(ContractManageVO contractManage){
		contractManageDao.update(contractManage);
		List<ContractTraderVO> suppliers=contractManage.getSuppliers();
		for(ContractTraderVO contractTrader:suppliers){
			contractTrader.setTraderType("Sup");//设置交易方类型
		}
		List<ContractTraderVO> demanders=contractManage.getDemanders();
		for(ContractTraderVO contractTrader:demanders){
			contractTrader.setTraderType("Dem");//设置交易方类型
		}
		List<ContractTraderVO> tempList=new ArrayList<>();
		tempList.addAll(suppliers);
		tempList.addAll(demanders);
		//修改时先删除原来的所有关系
		Map<String,Object> map = new HashMap<>();
		map.put("contractId",contractManage.getContractId());
		List<ContractTraderVO> contractTraderS = contractTraderService.list(map);
		if(contractTraderS!=null&&!contractTraderS.isEmpty()){
			for(ContractTraderVO contractTrader:contractTraderS){
				contractTraderService.remove(contractTrader.getId());
			}
		}
		//处理和合同关系
		for(ContractTraderVO contractTrader:tempList){
			contractTrader.setContractId(contractManage.getContractId());
			contractTrader.setTraderId(contractTrader.getTrader().getCustermerId());
			contractTraderService.save(contractTrader);
		}
		return 0;
	}
	
	@Override
	public int remove(String contractId){
		return contractManageDao.remove(contractId);
	}
	
	@Override
	public int batchRemove(String[] contractIds){
		return contractManageDao.batchRemove(contractIds);
	}
	
}
