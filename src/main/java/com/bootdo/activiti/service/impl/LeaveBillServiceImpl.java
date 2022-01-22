package com.bootdo.activiti.service.impl;

import com.bootdo.activiti.config.ActivitiConstant;
import com.bootdo.activiti.utils.ActivitiUtils;
import com.bootdo.activiti.vo.LeaveBillVO;
import com.bootdo.common.config.Constant;
import com.bootdo.common.service.DictService;
import com.bootdo.common.utils.ShiroUtils;
import org.activiti.engine.FormService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import com.bootdo.activiti.dao.LeaveBillDao;
import com.bootdo.activiti.domain.LeaveBillDO;
import com.bootdo.activiti.service.LeaveBillService;



@Service
public class LeaveBillServiceImpl implements LeaveBillService {
	@Autowired
	private LeaveBillDao leaveBillDao;
	@Autowired
	private ActTaskServiceImpl actTaskService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private FormService formService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private DictService dictService;
	@Autowired
	private ActivitiUtils activitiUtils;

	@Override
	public LeaveBillDO get(String billId){
		return leaveBillDao.get(billId);
	}
	
	@Override
	public List<LeaveBillVO> list(Map<String, Object> map){
        List<LeaveBillVO> leaveBillVOS=leaveBillDao.list(map);
		for(LeaveBillVO item:leaveBillVOS){
			if(item.getState()!=null){
				item.setStateName(Constant.FLOW_STATE[item.getState()]);
			}
		}
		return leaveBillVOS;
	}
	
	@Override
	public int count(Map<String, Object> map){
		return leaveBillDao.count(map);
	}
	
	@Override
	public int save(LeaveBillDO leaveBill,String procdefId){
		leaveBill.setBillId(UUID.randomUUID().toString());
		leaveBill.setIsDeleted(false);
		String procInsId=actTaskService.startProcessWithProcDefId(procdefId, leaveBill.getBillId(), leaveBill.getContent(), new HashMap<>());
		leaveBill.setProcInsId(procInsId);
		leaveBill.setUserId( ShiroUtils.getUserId()+"");
		leaveBill.setOfficeId(ShiroUtils.getUser().getDeptId()+"");
		leaveBill.setState(0);//开始流程，状态变为审批中
		return leaveBillDao.save(leaveBill);
	}
	
	@Override
	public int update(LeaveBillDO leaveBill){
		Map<String, Object> vars = new HashMap<>(16);
		taskService.setVariableLocal(leaveBill.getTaskId(),"pass",leaveBill.getTaskPass());
		vars.put("pass", leaveBill.getTaskPass());
		vars.put("title", "");
		List<PvmActivity> activities=activitiUtils.getNextNodesByTaskId(leaveBill.getTaskId());
		boolean hasBranch=false;
		for(PvmActivity activity:activities){
			if("exclusiveGateway".equals(activity.getProperty("type"))){
				hasBranch=true;
			}
		}
		taskService.claim(leaveBill.getTaskId(),ShiroUtils.getUser().getUsername());
		//没有分支的情况
		if(!hasBranch){
			String pass=leaveBill.getTaskPass();
			//确认，流程前进
			if("1".equals(pass)){
				actTaskService.complete(leaveBill.getTaskId(), vars);
			}else if("2".equals(pass)){//拒绝，流程销毁
			}

		}else{//有分支的情况，pass作为分支的判断条件
			actTaskService.complete(leaveBill.getTaskId(), vars);
		}
		//判断是否是最后节点，如果是，则结束时变更表单状态
		ProcessInstance procIns=runtimeService.createProcessInstanceQuery().processInstanceBusinessKey(leaveBill.getBillId()).singleResult();

		if(procIns==null){
			if("1".equals(leaveBill.getTaskPass())){
				leaveBill.setState(1);//已通过
			}else{
				leaveBill.setState(2);//已拒绝/已撤销
			}
		}
		return leaveBillDao.update(leaveBill);
	}
	
	@Override
	public int remove(String billId){
		return leaveBillDao.remove(billId);
	}
	
	@Override
	public int batchRemove(String[] billIds){
		return leaveBillDao.batchRemove(billIds);
	}
	
}
