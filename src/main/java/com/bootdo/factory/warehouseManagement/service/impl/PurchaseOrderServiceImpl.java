package com.bootdo.factory.warehouseManagement.service.impl;

import com.bootdo.activiti.service.impl.ActTaskServiceImpl;
import com.bootdo.activiti.utils.ActivitiUtils;
import com.bootdo.common.config.Constant;
import com.bootdo.common.service.DictService;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.factory.productManage.domain.ProductDetailDO;
import com.bootdo.factory.productManage.service.ProductDetailService;
import com.bootdo.factory.warehouseManagement.dao.PurchaseDetailDao;
import com.bootdo.factory.warehouseManagement.domain.PurchaseDetailDO;
import com.bootdo.factory.warehouseManagement.vo.PurchaseOrderVO;
import com.bootdo.system.domain.DeptDO;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.DeptService;
import com.bootdo.system.service.UserService;
import org.activiti.engine.FormService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.catalina.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import com.bootdo.factory.warehouseManagement.dao.PurchaseOrderDao;
import com.bootdo.factory.warehouseManagement.domain.PurchaseOrderDO;
import com.bootdo.factory.warehouseManagement.service.PurchaseOrderService;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;


@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {
	@Autowired
	private PurchaseOrderDao purchaseOrderDao;
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
	private PurchaseDetailDao purchaseDetailDao;
	@Autowired
	private ProductDetailService productDetailService;
	@Autowired
	private ActivitiUtils activitiUtils;
	@Autowired
	private DeptService deptService;
	@Autowired
	private UserService userService;
	@Override
	public PurchaseOrderDO get(String purchaseId){
		return purchaseOrderDao.get(purchaseId);
	}
	
	@Override
	public List<PurchaseOrderVO> list(Map<String, Object> map){
		List<PurchaseOrderDO> purchaseOrders= purchaseOrderDao.list(map);
		List<PurchaseOrderVO> purchaseOrderVOS=new ArrayList<>();
		for(PurchaseOrderDO purchaseOrder:purchaseOrders){
			PurchaseOrderVO purchaseOrderVO=new PurchaseOrderVO();
			BeanUtils.copyProperties(purchaseOrder,purchaseOrderVO);
			//ID-????????????
			String deptId=purchaseOrderVO.getApplyDeptId();
			if(StringUtils.isNotBlank(deptId)){
				try{
					DeptDO dept=deptService.get(Long.valueOf(deptId));
					purchaseOrderVO.setApplyDeptName(dept.getName());
				}catch (Exception e){
					System.err.println("PurchaseOrderService:list:??????ID??????");
				}
			}
			//ID-????????????
			String userId=purchaseOrderVO.getDutyPersonId();
			if(StringUtils.isNotBlank(userId)){
				try {
					UserDO user=userService.get(Long.valueOf(userId));
					purchaseOrderVO.setDutyPersonName(user.getName());
				}catch (Exception e){
					System.err.println("PurchaseOrderService:list:??????ID??????");
				}
			}
			//????????? value->name
			String businessType=purchaseOrderVO.getBusinessType();
			String purchaseState=purchaseOrderVO.getPurchaseState();
			String businessTypeName=dictService.getName(Constant.PURCHASE_BUSINESS_TYPE,businessType);
			purchaseOrderVO.setBusinessTypeName(businessTypeName);
			String purchaseStateName=dictService.getName(Constant.PURCHASE_STATE,purchaseState);
			purchaseOrderVO.setPurchaseStateName(purchaseStateName);
			//purchaseDetail
			Map<String,Object> detailMap=new HashMap<>();
			detailMap.put("orderId",purchaseOrderVO.getPurchaseId());
			List<PurchaseDetailDO> purchaseDetails=purchaseDetailDao.list(detailMap);
			Iterator<PurchaseDetailDO> iterator=purchaseDetails.iterator();
			while(iterator.hasNext()){
				PurchaseDetailDO purchaseDetail=iterator.next();
				String productId=purchaseDetail.getProductId();
				ProductDetailDO productDetail=productDetailService.get(productId);
				if(productDetail==null){
					iterator.remove();
				}else{
					productDetail.setProductTypeName(dictService.getName("product_type",productDetail.getProductType()));
					productDetail.setWeightUnitName(dictService.getName("weight_unit",productDetail.getWeightUnit()));
					productDetail.setQuantityUnitName(dictService.getName("quantity_unit",productDetail.getQuantityUnit()));
					purchaseDetail.setProductDetail(productDetail);
				}
			}
			purchaseOrderVO.setDetails(purchaseDetails);
			purchaseOrderVOS.add(purchaseOrderVO);
		}
		return purchaseOrderVOS;
	}
	
	@Override
	public int count(Map<String, Object> map){
		return purchaseOrderDao.count(map);
	}
	
	@Override
	@Transactional
	public String save(PurchaseOrderDO purchaseOrder, String procdefId, Map<String,Object> params, HttpServletRequest request){
		purchaseOrder.setPurchaseId(UUID.randomUUID().toString());
		UserDO user=ShiroUtils.getUser();

		/*????????????*/
	//	String procIns=actTaskService.startProcessWithProcDefId(procdefId,purchaseOrder.getPurchaseId(),"",new HashMap<>());
	//	String purpose=(String) params.get("purpose");
	//	String fileName= (String) params.get("fileName");
		//Authentication.setAuthenticatedUserId(ShiroUtils.getUser().getUsername());
	//	String taskId=taskService.createTaskQuery().processInstanceId(procIns).singleResult().getId();
		//????????????
	//	taskService.createAttachment("file_name",taskId,procIns,user.getName(),purpose,fileName);
		/*????????????*/

		purchaseOrder.setCreateTime(new Date());
		purchaseOrder.setIsDeleted(false);
		purchaseOrder.setAuditState("?????????");
		purchaseOrder.setCreateUserId(user.getUserId()+"");
		purchaseOrder.setPurchaseState("not_done");
		if(purchaseOrderDao.save(purchaseOrder)>0){
			//
			//???????????????PurchaseDetail
			String[] productIds=request.getParameterValues("productId");
			String[] numbers=request.getParameterValues("number");
			String[] totalMoneys=request.getParameterValues("money");
			for(int i=0;i<productIds.length;i++){
				PurchaseDetailDO purchaseDetail=new PurchaseDetailDO();
				purchaseDetail.setId(UUID.randomUUID().toString());
				purchaseDetail.setOrderId(purchaseOrder.getPurchaseId());
				purchaseDetail.setNumber(Double.parseDouble(numbers[i]));
				purchaseDetail.setTotalMoney(Double.parseDouble(totalMoneys[i]));
				purchaseDetail.setProductId( productIds[i]);
				purchaseDetail.setCreateTime(new Date());
				purchaseDetail.setCreateUserId(user.getUserId()+"");
				purchaseDetail.setIsDeleted(false);
				purchaseDetailDao.save(purchaseDetail);
			}
			//
			return purchaseOrder.getPurchaseId();
		}
		return null;
	}
	
	@Override
	public int audit(PurchaseOrderDO purchaseOrder,Map<String,Object> params){
		Map<String, Object> vars = new HashMap<>(16);
		taskService.setVariableLocal(purchaseOrder.getTaskId(),"pass",purchaseOrder.getTaskPass());
		vars.put("pass", purchaseOrder.getTaskPass());
		vars.put("title", "");
		UserDO currentUser=ShiroUtils.getUser();
		//????????????
		taskService.claim(purchaseOrder.getTaskId(), currentUser.getUsername());
		//???????????????????????????+?????????
		String taskId=purchaseOrder.getTaskId();
		String procIns=taskService.createTaskQuery().taskId(taskId).singleResult().getProcessInstanceId();
		String purpose=(String) params.get("purpose");
		String fileName= (String) params.get("fileName");
		taskService.createAttachment("file_name",taskId,procIns,currentUser.getName(),purpose,fileName);
		//??????????????????
		Authentication.setAuthenticatedUserId(currentUser.getUsername());
		String comment= (String) params.get("auditComment");
		taskService.addComment(taskId,procIns,comment);




		boolean hasBranch=activitiUtils.hasBranch(purchaseOrder.getTaskId());
		//?????????????????????,??????????????????????????????
		if(!hasBranch){
			String pass=purchaseOrder.getTaskPass();
			//?????????????????????
			if("1".equals(pass)){
				actTaskService.complete(purchaseOrder.getTaskId(), vars);
			}else if("2".equals(pass)){//?????????????????????
				runtimeService.deleteProcessInstance(purchaseOrder.getTaskId(),currentUser.getName()+"?????????????????????");
			}

		}else{//?????????????????????pass???????????????????????????
			actTaskService.complete(purchaseOrder.getTaskId(), vars);
		}
		//????????????????????????????????????????????????????????????????????????
		boolean isLastTask=activitiUtils.isLashTask(purchaseOrder.getPurchaseId());

		if(isLastTask){
			if("1".equals(purchaseOrder.getTaskPass())){
				purchaseOrder.setAuditState("?????????");//?????????
			}else{
				purchaseOrder.setAuditState("?????????");//?????????/?????????
			}
		}
		return purchaseOrderDao.update(purchaseOrder);
	}

	@Override
	public int update(PurchaseOrderDO purchaseOrder, Map<String, Object> params) {
		return purchaseOrderDao.update(purchaseOrder);
	}

	@Override
	public int remove(String purchaseId){
		return purchaseOrderDao.remove(purchaseId);
	}
	
	@Override
	public int batchRemove(String[] purchaseIds){
		return purchaseOrderDao.batchRemove(purchaseIds);
	}
	
}
