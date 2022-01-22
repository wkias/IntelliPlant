package com.bootdo.factory.productManage.service.impl;

import com.bootdo.common.utils.CodeGenerator;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.factory.productManage.dao.MaterialGetDao;
import com.bootdo.factory.productManage.domain.MaterialGetDO;
import com.bootdo.factory.productManage.service.MaterialGetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.bootdo.factory.productManage.dao.ProcessInputDao;
import com.bootdo.factory.productManage.domain.ProcessInputDO;
import com.bootdo.factory.productManage.service.ProcessInputService;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ProcessInputServiceImpl implements ProcessInputService {
	@Autowired
	private ProcessInputDao processInputDao;
	@Autowired
	private MaterialGetDao materialGetDao;

	@Override
	public ProcessInputDO get(String id){
		return processInputDao.get(id);
	}
	
	@Override
	public List<ProcessInputDO> list(Map<String, Object> map){
		return processInputDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return processInputDao.count(map);
	}

	/**
	 * 生成部件（半成品）
	 * */
	@Override
	@Transactional
	public int save(ProcessInputDO processInput){
		processInput.setId(UUID.randomUUID().toString());
		processInput.setInspecting(false);
		processInput.setIsDeleted(false);
		processInput.setCreateUserId(ShiroUtils.getUserId()+"");
		processInput.setCreateTime(new Date());
		//更新已领取物料数量
		MaterialGetDO materialGet=new MaterialGetDO();
		materialGet.setPlanId(processInput.getPlanId());
		materialGet.setMateralId(processInput.getMaterialId());
		materialGet.setCount(processInput.getMaterialCount());
		materialGetDao.useMaterial(materialGet);
		return processInputDao.save(processInput);
	}
	
	@Override
	public int update(ProcessInputDO processInput){
		return processInputDao.update(processInput);
	}
	
	@Override
	public int remove(String id){
		return processInputDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return processInputDao.batchRemove(ids);
	}
	
}
