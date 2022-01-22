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
			//ID-》部门名
			String deptId=purchaseOrderVO.getApplyDeptId();
			if(StringUtils.isNotBlank(deptId)){
				try{
					DeptDO dept=deptService.get(Long.valueOf(deptId));
					purchaseOrderVO.setApplyDeptName(dept.getName());
				}catch (Exception e){
					System.err.println("PurchaseOrderService:list:部门ID异常");
				}
			}
			//ID-》用户名
			String userId=purchaseOrderVO.getDutyPersonId();
			if(StringUtils.isNotBlank(userId)){
				try {
					UserDO user=userService.get(Long.valueOf(userId));
					purchaseOrderVO.setDutyPersonName(user.getName());
				}catch (Exception e){
					System.err.println("PurchaseOrderService:list:用户ID异常");
				}
			}
			//字典值 value->name
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

		/*流程相关*/
	//	String procIns=actTaskService.startProcessWithProcDefId(procdefId,purchaseOrder.getPurchaseId(),"",new HashMap<>());
	//	String purpose=(String) params.get("purpose");
	//	String fileName= (String) params.get("fileName");
		//Authentication.setAuthenticatedUserId(ShiroUtils.getUser().getUsername());
	//	String taskId=taskService.createTaskQuery().processInstanceId(procIns).singleResult().getId();
		//存储附件
	//	taskService.createAttachment("file_name",taskId,procIns,user.getName(),purpose,fileName);
		/*流程相关*/

		purchaseOrder.setCreateTime(new Date());
		purchaseOrder.setIsDeleted(false);
		purchaseOrder.setAuditState("审批中");
		purchaseOrder.setCreateUserId(user.getUserId()+"");
		purchaseOrder.setPurchaseState("not_done");
		if(purchaseOrderDao.save(purchaseOrder)>0){
			//
			//在这里处理PurchaseDetail
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
		//领取任务
		taskService.claim(purchaseOrder.getTaskId(), currentUser.getUsername());
		//存储附件（物资用途+附件）
		String taskId=purchaseOrder.getTaskId();
		String procIns=taskService.createTaskQuery().taskId(taskId).singleResult().getProcessInstanceId();
		String purpose=(String) params.get("purpose");
		String fileName= (String) params.get("fileName");
		taskService.createAttachment("file_name",taskId,procIns,currentUser.getName(),purpose,fileName);
		//存储审批结论
		Authentication.setAuthenticatedUserId(currentUser.getUsername());
		String comment= (String) params.get("auditComment");
		taskService.addComment(taskId,procIns,comment);




		boolean hasBranch=activitiUtils.hasBranch(purchaseOrder.getTaskId());
		//没有分支的情况,拒绝将会直接销毁流程
		if(!hasBranch){
			String pass=purchaseOrder.getTaskPass();
			//确认，流程前进
			if("1".equals(pass)){
				actTaskService.complete(purchaseOrder.getTaskId(), vars);
			}else if("2".equals(pass)){//拒绝，流程销毁
				runtimeService.deleteProcessInstance(purchaseOrder.getTaskId(),currentUser.getName()+"拒绝了采购请求");
			}

		}else{//有分支的情况，pass作为分支的判断条件
			actTaskService.complete(purchaseOrder.getTaskId(), vars);
		}
		//判断是否是最后节点，如果是，则结束时变更表单状态
		boolean isLastTask=activitiUtils.isLashTask(purchaseOrder.getPurchaseId());

		if(isLastTask){
			if("1".equals(purchaseOrder.getTaskPass())){
				purchaseOrder.setAuditState("已通过");//已通过
			}else{
				purchaseOrder.setAuditState("已拒绝");//已拒绝/已撤销
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
