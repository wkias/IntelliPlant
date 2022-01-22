package com.bootdo.factory.saleManage.controller;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.bootdo.common.service.DictService;
import com.bootdo.factory.contractManage.domain.CustermerInformationDO;
import com.bootdo.factory.contractManage.domain.OrderContentDO;
import com.bootdo.factory.contractManage.domain.OrderManageDO;
import com.bootdo.factory.contractManage.service.CustermerInformationService;
import com.bootdo.factory.contractManage.service.OrderContentService;
import com.bootdo.factory.contractManage.service.OrderManageService;
import com.bootdo.factory.saleManage.domain.SaleContentDO;
import com.bootdo.factory.saleManage.service.SaleContentService;
import com.bootdo.system.service.UserService;
import io.swagger.models.auth.In;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.factory.saleManage.domain.SaleManageDO;
import com.bootdo.factory.saleManage.service.SaleManageService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * 
 * @author xgq
 * @email 1992lcg@163.com
 * @date 2020-03-25 15:00:46
 */
 
@Controller
@RequestMapping("/saleManage/saleManage")
public class SaleManageController {
	@Autowired
	OrderManageService orderManageService;
	@Autowired
	private SaleManageService saleManageService;
	@Autowired
	private CustermerInformationService custermerInformationService;
	@Autowired
	private DictService dictService;
	@Autowired
	private OrderContentService orderContentService;
	@Autowired
	private SaleContentService saleContentService;
	@Autowired
	private UserService userService;



	@GetMapping()
	@RequiresPermissions("saleManage:saleManage:saleManage")
	String SaleManage(){
	    return "factory/saleManage/saleManage";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("saleManage:saleManage:saleManage")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据

        Query query = new Query(params);
        List<SaleManageDO> saleManageList = saleManageService.list(query);
		if (saleManageList.size()!=0){
			for (SaleManageDO sale:saleManageList) {
				sale.setCustermerName(custermerInformationService.get(sale.getCustermerId()).getCustermerName());
				sale.setSaleStateName(dictService.getName("sale_state",sale.getSaleState().toString()));
			}

		}
		int total = saleManageService.count(query);
		PageUtils pageUtils = new PageUtils(saleManageList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("saleManage:saleManage:add")
	String add(Model model){
	    model.addAttribute("saleCode", UUID.randomUUID().toString());
		return "factory/saleManage/add";
	}
	@GetMapping("/select/{orderId}")
	@RequiresPermissions("saleManage:saleManage:add")
	String select(@PathVariable String orderId, Model model){
		model.addAttribute("orderId",orderId);
		return "factory/saleManage/select";
	}
	@GetMapping("/details/{saleId}")
	@RequiresPermissions("saleManage:saleManage:add")
	String detail(@PathVariable String saleId, Model model){
		SaleManageDO saleManageDO = saleManageService.get(saleId);
		saleManageDO.setSaleStateName(dictService.getName("sale_state",saleManageDO.getSaleState().toString()));
		saleManageDO.setSaleManegerName(userService.get(Long.parseLong(saleManageDO.getSaleManagerId())).getName());

		CustermerInformationDO custermerInformationDO = custermerInformationService.get(saleManageDO.getCustermerId());
		OrderManageDO orderManageDO = orderManageService.get(saleManageDO.getOrderId());
		List<SaleContentDO> list = saleContentService.getBySaleId(saleId);
		if (list.size()!=0){
			for (SaleContentDO s :list) {
//				s.setProductType(dictService.getName("product_type",s.getProductType()));
				s.setQuantityUnitName(dictService.getName("unit",s.getQuantityUnit()));
			}
		}
		model.addAttribute("orderManageDo",orderManageDO);
		model.addAttribute("saleManageDo", saleManageDO);
		model.addAttribute("custermerInformationDo", custermerInformationDO);
		model.addAttribute("list",list);

		return "factory/saleManage/detail";
	}


	@GetMapping("/orderList")
	@RequiresPermissions("saleManage:saleManage:add")
	String orderList(){
		return "factory/saleManage/orderList";
	}
	@GetMapping("/custermerList")
	@RequiresPermissions("saleManage:saleManage:add")
	String custermerList(){
		return "factory/saleManage/custermerList";
	}
	@GetMapping("/edit/{saleId}")
	@RequiresPermissions("saleManage:saleManage:edit")
	String edit(@PathVariable("saleId") String saleId,Model model){
		SaleManageDO saleManage = saleManageService.get(saleId);
		model.addAttribute("saleManage", saleManage);
	    return "factory/saleManage/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("saleManage:saleManage:add")
	public R save(SaleManageDO saleManage, HttpServletRequest request){

		saleManage.setCreatDate(new Date(new java.util.Date().getTime()));
		saleManage.setIsDeleted(0);
		String saleId = saleManageService.save(saleManage);

		if(saleId!=null){
			String []contentIds = request.getParameterValues("contentId");
			String []productIds = request.getParameterValues("contentProductId");
			String []contentNames = request.getParameterValues("contentName");
			String []contentTypes = request.getParameterValues("contentModel");
			String []contentCodes = request.getParameterValues("contentCode");
			String []quantityUnits = request.getParameterValues("quantityUnit");
			String []counts = request.getParameterValues("count");
			String []deadlines = request.getParameterValues("deadline");
			String []storehouses = request.getParameterValues("storehouse");

			if (contentIds.length!=0){
				for (int i = 0;i<contentIds.length;i++){
				OrderContentDO orderContentDO = new OrderContentDO();
				SaleContentDO saleContentDO = new SaleContentDO();
				orderContentDO.setContentId(contentIds[i]);
				orderContentDO.setComplete(Integer.parseInt(counts[i]));
				orderContentService.update(orderContentDO);

				saleContentDO.setSaleId(saleId);
				saleContentDO.setSaleContentId(UUID.randomUUID().toString());
				saleContentDO.setCount(Integer.parseInt(counts[i]));
				saleContentDO.setProductCode(contentCodes[i]);
				saleContentDO.setProductId(productIds[i]);
				saleContentDO.setProductName(contentNames[i]);
				saleContentDO.setQuantityUnit(quantityUnits[i]);
				saleContentDO.setProductType(contentTypes[i]);
				saleContentDO.setDeadline(deadlines[i]);
				saleContentDO.setStorehouse(storehouses[i]);

				saleContentService.save(saleContentDO);

				}
			}

			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("saleManage:saleManage:edit")
	public R update( SaleManageDO saleManage){
		saleManageService.update(saleManage);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("saleManage:saleManage:remove")
	public R remove( String saleId){
		if(saleManageService.remove(saleId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("saleManage:saleManage:batchRemove")
	public R remove(@RequestParam("ids[]") String[] saleIds){
		saleManageService.batchRemove(saleIds);
		return R.ok();
	}
	
}
