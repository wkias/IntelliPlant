package com.bootdo.factory.productManage.service.impl;

import com.bootdo.common.service.DictService;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.factory.productManage.dao.ProcessRelationshipDao;
import com.bootdo.factory.productManage.domain.InspectionItemsDO;
import com.bootdo.factory.productManage.domain.ProcessRelationshipDO;
import com.bootdo.factory.productManage.service.InspectionItemsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import com.bootdo.factory.productManage.dao.ProcessInspectionDao;
import com.bootdo.factory.productManage.domain.ProcessInspectionDO;
import com.bootdo.factory.productManage.service.ProcessInspectionService;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;


@Service
public class ProcessInspectionServiceImpl implements ProcessInspectionService {
	@Autowired
	private ProcessInspectionDao processInspectionDao;
	@Autowired
	private ProcessRelationshipDao processRelationshipDao;
	
	@Override
	public ProcessInspectionDO get(String processInspectionId){

	    return processInspectionDao.get(processInspectionId);
	}
	
	@Override
	public List<ProcessInspectionDO> list(Map<String, Object> map){
		return processInspectionDao.list(map);
	}

	@Override
	public int count(Map<String, Object> map){
		return processInspectionDao.count(map);
	}

    @Override
	@Transactional
	public int save(ProcessInspectionDO processInspection, HttpServletRequest request){
		processInspection.setProcessInspectionId(UUID.randomUUID().toString());
        Long userId = ShiroUtils.getUserId();
        processInspection.setCreateUserId(userId + "");
        processInspection.setCreateDate(new Date().getTime());
        String[] inspectionItemsIds=request.getParameterValues("inspectionItemsId");
        if(processInspectionDao.save(processInspection)>0){
            for(int i=0;i<inspectionItemsIds.length;i++){
				ProcessRelationshipDO processRelationship=new ProcessRelationshipDO();
				processRelationship.setId(UUID.randomUUID().toString());
				processRelationship.setProcessInspectionId(processInspection.getProcessInspectionId());
				processRelationship.setInspectionItemsId(inspectionItemsIds[i]);
				processRelationship.setIsDeleted(false);
				processRelationship.setCreateDate(new Date().getTime());
				processRelationship.setCreateUserId(ShiroUtils.getUserId()+"");
				processRelationshipDao.save(processRelationship);
			}
		}
		return 1;
	}

    @Override
    @Transactional
    public int update(ProcessInspectionDO processInspection, HttpServletRequest request) {
        String[] inspectionItemsIds = request.getParameterValues("inspectionItemsId");


        if (processInspectionDao.update(processInspection) > 0&&inspectionItemsIds!=null) {

            processRelationshipDao.removeByProcessInspectionId(processInspection.getProcessInspectionId());

            for (int i = 0; i < inspectionItemsIds.length; i++) {
                ProcessRelationshipDO processRelationship = new ProcessRelationshipDO();
                processRelationship.setId(UUID.randomUUID().toString());
                processRelationship.setProcessInspectionId(processInspection.getProcessInspectionId());
                processRelationship.setInspectionItemsId(inspectionItemsIds[i]);
                processRelationship.setIsDeleted(false);
                processRelationship.setCreateDate(new Date().getTime());
                processRelationship.setCreateUserId(ShiroUtils.getUserId()+"");
                processRelationshipDao.save(processRelationship);
            }
            return 1;
        }
        return 0;
    }
	
	@Override
	public int remove(String processInspectionId){
		return processInspectionDao.remove(processInspectionId);
	}
	
	@Override
	public int batchRemove(String[] processInspectionIds){
		return processInspectionDao.batchRemove(processInspectionIds);
	}
	
}
