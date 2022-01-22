package com.bootdo.factory.productManage.service.impl;

import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.factory.productManage.dao.CraftProcessDao;
import com.bootdo.factory.productManage.domain.CraftProcessDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.bootdo.factory.productManage.dao.CraftDefinitionDao;
import com.bootdo.factory.productManage.domain.CraftDefinitionDO;
import com.bootdo.factory.productManage.service.CraftDefinitionService;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;


@Service
public class CraftDefinitionServiceImpl implements CraftDefinitionService {
	@Autowired
	private CraftDefinitionDao craftDefinitionDao;
	@Autowired
	private CraftProcessDao craftProcessDao;
	@Override
	public CraftDefinitionDO get(String craftId){
		return craftDefinitionDao.get(craftId);
	}
	
	@Override
	public List<CraftDefinitionDO> list(Map<String, Object> map){
		return craftDefinitionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return craftDefinitionDao.count(map);
	}
	
	@Override
	@Transactional
	public int save(CraftDefinitionDO craftDefinition, HttpServletRequest request){
		craftDefinition.setCraftId(UUID.randomUUID().toString());
		craftDefinition.setIsDeleted(false);
		craftDefinition.setCreateUserId(ShiroUtils.getUserId()+"");
		craftDefinition.setCreateTime(new Date());
		String[] processIds=request.getParameterValues("processId");
		String[] orders=request.getParameterValues("order");
		String[] processTypes=request.getParameterValues("processType");
		if(craftDefinitionDao.save(craftDefinition)>0){
			//处理Craft-Process关系
			for(int i=0;i<processIds.length;i++){
				CraftProcessDO craftProcess=new CraftProcessDO();
				craftProcess.setId(UUID.randomUUID().toString());
				craftProcess.setCraftId(craftDefinition.getCraftId());
				craftProcess.setProcessId(processIds[i]);
				craftProcess.setOrder(Integer.parseInt(orders[i]));
				craftProcess.setProcessType(processTypes[i]);
				craftProcess.setIsDeleted(false);
				craftProcess.setCreateTime(new Date());
				craftProcess.setCreateUserId(ShiroUtils.getUserId()+"");
				craftProcessDao.save(craftProcess);
			}
		}
		return 1;
	}
	
	@Override
    @Transactional
	public int update(CraftDefinitionDO craftDefinition, HttpServletRequest request) {
        String[] processIds = request.getParameterValues("processId");
        String[] orders = request.getParameterValues("order");
        String[] processTypes = request.getParameterValues("processType");
        if (craftDefinitionDao.update(craftDefinition) > 0&&processIds!=null) {
            //先把关联表所有数据删掉
            craftProcessDao.removeByCraftId(craftDefinition.getCraftId());
            //处理Craft-Process关系
            for (int i = 0; i < processIds.length; i++) {
                CraftProcessDO craftProcess = new CraftProcessDO();
                craftProcess.setId(UUID.randomUUID().toString());
                craftProcess.setCraftId(craftDefinition.getCraftId());
                craftProcess.setProcessId(processIds[i]);
                craftProcess.setOrder(Integer.parseInt(orders[i]));
                craftProcess.setProcessType(processTypes[i]);
                craftProcess.setIsDeleted(false);
                craftProcess.setCreateTime(new Date());
                craftProcess.setCreateUserId(ShiroUtils.getUserId() + "");
                craftProcessDao.save(craftProcess);
            }
            return 1;
        }
        return 0;
    }
	
	@Override
	public int remove(String craftId){
		return craftDefinitionDao.remove(craftId);
	}
	
	@Override
	public int batchRemove(String[] craftIds){
		return craftDefinitionDao.batchRemove(craftIds);
	}
	
}
