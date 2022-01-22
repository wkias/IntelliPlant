package com.bootdo.factory.warehouseManagement.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.bootdo.activiti.domain.Variable;
import com.bootdo.activiti.domain.Variables;
import com.bootdo.activiti.utils.ActivitiUtils;
import com.bootdo.activiti.vo.LeaveBillVO;
import com.bootdo.common.service.DictService;
import com.bootdo.common.utils.*;
import com.bootdo.factory.productManage.domain.ProductDetailDO;
import com.bootdo.factory.productManage.service.ProductDetailService;
import com.bootdo.factory.warehouseManagement.dao.PurchaseDetailDao;
import com.bootdo.factory.warehouseManagement.domain.PurchaseDetailDO;
import com.bootdo.factory.warehouseManagement.vo.PurchaseOrderVO;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.UserService;
import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.persistence.entity.CommentEntity;
import org.activiti.engine.task.Attachment;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.bootdo.factory.warehouseManagement.domain.PurchaseOrderDO;
import com.bootdo.factory.warehouseManagement.service.PurchaseOrderService;

import javax.servlet.http.HttpServletRequest;

/**
 * 采购流程，
 * 附件名放在hi_attachment表中，url表示附件的名
 * 批注有两种（物资用途，审批结果），
 * 物资用途放在了hi_attachment的附件描述中,并且物资和附件的上传者的用户NAME放在了表中的NAME_字段
 * 审批结果放在hi_comment里
 *
 * 
 * @author xuhui
 * @email 1992lcg@163.com
 * @date 2020-03-18 18:00:09
 */
 
@Controller
@RequestMapping("/warehouseManagement/purchaseOrder")
public class PurchaseOrderController {
	@Autowired
	private PurchaseOrderService purchaseOrderService;
	@Autowired
	private FormService formService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private UserService userService;
	@Autowired
	private DictService dictService;
	@Autowired
	private ProductDetailService productDetailService;
	@Autowired
	private PurchaseDetailDao purchaseDetailDao;
	@Autowired
	private HistoryService historyService;
	@Autowired
	private ActivitiUtils activitiUtils;
	@GetMapping()
	@RequiresPermissions("warehouseManagement:purchaseOrder:purchaseOrder")
	String PurchaseOrder(){
	    return "warehouseManagement/purchaseOrder/purchaseOrder";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("warehouseManagement:purchaseOrder:purchaseOrder")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<PurchaseOrderVO> purchaseOrderList = purchaseOrderService.list(query);
		int total = purchaseOrderService.count(query);
		PageUtils pageUtils = new PageUtils(purchaseOrderList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("warehouseManagement:purchaseOrder:add")
	String add(Model model){
		model.addAttribute("orderCode",UUID.randomUUID().toString());
	    return "warehouseManagement/purchaseOrder/add";
	}
	@GetMapping("/details/{purchaseId}")
	@RequiresPermissions("warehouseManagement:purchaseOrder:edit")
	String details(@PathVariable("purchaseId") String purchaseId,Model model){
		Map<String,Object> map = new HashMap<>();
		map.put("purchaseId",purchaseId);
		PurchaseOrderVO purchaseOrder = purchaseOrderService.list(map).get(0);
		model.addAttribute("purchaseOrder", purchaseOrder);
		//使用工具封装的方法，兼容已结束的流程
		//String procInsId=activitiUtils.getProcessInstanceIdByBusinessKey(purchaseId);
		//审批结论
		//List<Comment> comments= taskService.getProcessInstanceComments(procInsId,"comment");
		//comments=handleComments(comments);
		//model.addAttribute("comments",comments);
		//传给前端 附件和物资用途
		//List<Attachment> attachments=taskService.getProcessInstanceAttachments(procInsId);
		//model.addAttribute("attachments",attachments);
		return "warehouseManagement/purchaseOrder/details";
	}

	@GetMapping("/edit/{purchaseId}")
	@RequiresPermissions("warehouseManagement:purchaseOrder:edit")
	String edit(@PathVariable("purchaseId") String purchaseId,Model model){
		Map<String,Object> map = new HashMap<>();
		map.put("purchaseId",purchaseId);
		PurchaseOrderVO purchaseOrder = purchaseOrderService.list(map).get(0);
		model.addAttribute("purchaseOrder", purchaseOrder);
		//使用工具封装的方法，兼容已结束的流程
		//String procInsId=activitiUtils.getProcessInstanceIdByBusinessKey(purchaseId);
		//审批结论
		//List<Comment> comments= taskService.getProcessInstanceComments(procInsId,"comment");
		//comments=handleComments(comments);
		//model.addAttribute("comments",comments);
		//传给前端 附件和物资用途
		//List<Attachment> attachments=taskService.getProcessInstanceAttachments(procInsId);
		//model.addAttribute("attachments",attachments);
	    return "warehouseManagement/purchaseOrder/edit";
	}

	@RequestMapping("/form")
	String add(@RequestAttribute("procdefId") String procdefId, Model model){
		model.addAttribute("procdefId",procdefId);
		model.addAttribute("orderCode",UUID.randomUUID().toString());
		return "warehouseManagement/purchaseOrder/add";
	}
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("warehouseManagement:purchaseOrder:add")
	public R save(PurchaseOrderVO purchaseOrder, String procdefId,
				  @RequestParam Map<String,Object> params,
				  HttpServletRequest request){
		if(purchaseOrderService.save(purchaseOrder,procdefId,params,request)!=null){
			return R.ok();
		}

		return R.error();
	}
	@GetMapping("/form/{taskId}")
	String audit(@PathVariable("taskId") String taskId, Model model) {
		Task task=activitiUtils.getTaskByTaskId(taskId);
		//puchaseOrder
		Map<String,Object> map = new HashMap<>();
		map.put("purchaseId",activitiUtils.getBusinessKeyByTaskId(taskId));
		PurchaseOrderVO purchaseOrder = purchaseOrderService.list(map).get(0);
		purchaseOrder.setTaskId(taskId);
		model.addAttribute("purchaseOrder", purchaseOrder);
		//purchaseDetails
		Map<String,Object> detailMap = new HashMap<>();
		detailMap.put("orderId",purchaseOrder.getPurchaseId());
		List<PurchaseDetailDO> purchaseDetails=purchaseDetailDao.list(detailMap);
		for(PurchaseDetailDO purchaseDetail:purchaseDetails){
			String productId=purchaseDetail.getProductId();
			ProductDetailDO productDetail=productDetailService.get(productId);
			purchaseDetail.setProductDetail(productDetail);
			productDetail.setProductTypeName(dictService.getName("product_type",productDetail.getProductType()));
			productDetail.setWeightUnitName(dictService.getName("weight_unit",productDetail.getWeightUnit()));
			productDetail.setQuantityUnitName(dictService.getName("quantity_unit",productDetail.getQuantityUnit()));
		}
		model.addAttribute("purchaseDetails",purchaseDetails);
		//之前所有的审批结论
		List<Comment> comments=taskService.getProcessInstanceComments(task.getProcessInstanceId(),"comment");
		comments = handleComments(comments);
		model.addAttribute("comments",comments);
		//传给前端 附件和物资用途
		List<Attachment> attachments=taskService.getProcessInstanceAttachments(task.getProcessInstanceId());
		model.addAttribute("attachments",attachments);
		//
		Long curUserId= ShiroUtils.getUserId();
		model.addAttribute("currentUserId",curUserId);
		return "warehouseManagement/purchaseOrder/audit";
	}
	/**
	 * 表单审核
	 */
	@ResponseBody
	@RequestMapping("/audit")
	@RequiresPermissions("warehouseManagement:purchaseOrder:audit")
	public R audit( PurchaseOrderDO purchaseOrder,
					 @RequestParam Map<String,Object> params,
					 HttpServletRequest request){
		purchaseOrderService.audit(purchaseOrder,params);
		return R.ok();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("warehouseManagement:purchaseOrder:edit")
	public R update( PurchaseOrderDO purchaseOrder,
					 @RequestParam Map<String,Object> params,
					 HttpServletRequest request){
		purchaseOrderService.update(purchaseOrder,params);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("warehouseManagement:purchaseOrder:remove")
	public R remove( String purchaseId){
		if(purchaseOrderService.remove(purchaseId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("warehouseManagement:purchaseOrder:batchRemove")
	public R remove(@RequestParam("ids[]") String[] purchaseIds){
		purchaseOrderService.batchRemove(purchaseIds);
		return R.ok();
	}
	@GetMapping("/productSelect")
	public String productSelect(){
		return  "warehouseManagement/purchaseOrder/productSelect";
	}
	/**
	 * 审批结论处理
	 * 		//利用Comment中的userId显示用户名
	 * 		//利用Comment中的action属性显示审批是否通过
	 * */
	public List<Comment> handleComments(List<Comment> comments){

		for(Comment comment:comments){
			String userName=comment.getUserId();
			if(StringUtils.isNotEmpty(userName)){
				Map<String,Object> userMap = new HashMap<>();
				userMap.put("username",userName);
				List<UserDO> users=userService.list(userMap);
				//前端显示的时候用user.name代替user.username
				if(users!=null&&!users.isEmpty()){
					UserDO user=users.get(0);
					((CommentEntity)comment).setUserId(user.getName());
				}
			}else{
				((CommentEntity)comment).setUserId("未知用户");
			}

			//获取当前审批结论的审批是否通过
			HistoricVariableInstance passIns=historyService.createHistoricVariableInstanceQuery().taskId(comment.getTaskId()).variableName("pass").singleResult();
			if(passIns!=null){
				String pass=(String) passIns.getValue();
				if ("0".equals(pass)) {
					((CommentEntity)comment).setAction("回退");
				} else if("1".equals(pass)){
					((CommentEntity)comment).setAction("通过");
				}else if("2".equals(pass)){
					((CommentEntity)comment).setAction("拒绝");
				}
			}
		}
		return comments;
	}
}
