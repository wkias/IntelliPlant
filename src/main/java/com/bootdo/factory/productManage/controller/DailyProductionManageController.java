package com.bootdo.factory.productManage.controller;

import java.text.SimpleDateFormat;
import java.util.*;

import com.bootdo.common.controller.BaseController;
import com.bootdo.factory.productManage.domain.DailyProductionContentDO;
import com.bootdo.factory.productManage.service.DailyProductionContentService;
import com.bootdo.factory.productManage.service.ProductDetailService;
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

import com.bootdo.factory.productManage.domain.DailyProductionManageDO;
import com.bootdo.factory.productManage.service.DailyProductionManageService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * 
 * @author xgq
 * @email 1992lcg@163.com
 * @date 2020-04-04 14:47:59
 */
 
@Controller
@RequestMapping("/productManage/dailyProductionManage")
public class DailyProductionManageController extends BaseController {
	@Autowired
	private DailyProductionManageService dailyProductionManageService;

	@Autowired
	private DailyProductionContentService dailyProductionContentService;
    @Autowired
	private com.bootdo.factory.service.ProcessDefinitionService processDefinitionService;
    @Autowired
	private ProductDetailService productDetailService;

	@GetMapping()
	@RequiresPermissions("productManage:dailyProductionManage:dailyProductionManage")
	String DailyProductionManage(){
	    return "factory/productManage/dailyProductionManage/dailyProductionManage";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("productManage:dailyProductionManage:dailyProductionManage")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<DailyProductionManageDO> dailyProductionManageList = dailyProductionManageService.list(query);
		int total = dailyProductionManageService.count(query);
		PageUtils pageUtils = new PageUtils(dailyProductionManageList, total);
		return pageUtils;
	}
	
	@GetMapping("/processList")
	@RequiresPermissions("productManage:dailyProductionManage:add")
	String getProcess(){
	    return "factory/productManage/dailyProductionManage/processList";
	}

	@GetMapping("/productList")
	@RequiresPermissions("productManage:dailyProductionManage:add")
	String getProduct(){
		return "factory/productManage/dailyProductionManage/productList";
	}


	@GetMapping("/add")
	@RequiresPermissions("productManage:dailyProductionManage:add")
	String add(){
		return "factory/productManage/dailyProductionManage/add";
	}

	@GetMapping("/edit/{dailyProductionId}")
	@RequiresPermissions("productManage:dailyProductionManage:edit")
	String edit(@PathVariable("dailyProductionId") String dailyProductionId,Model model){
		DailyProductionManageDO dailyProductionManage = dailyProductionManageService.get(dailyProductionId);
		Map map = new HashMap();
		map.put("dailyProductionId",dailyProductionId);
		List<DailyProductionContentDO> list = dailyProductionContentService.list(map);
		if(list.size()!=0){
			for (DailyProductionContentDO d:list) {
				d.setProductDetailDO(productDetailService.get(d.getProductId()));
				d.setProcessDefinitionDO(processDefinitionService.get(d.getProcessId()));
			}



		}
		model.addAttribute("dailyProductionManage", dailyProductionManage);
		model.addAttribute("contentList",list);
	    return "factory/productManage/dailyProductionManage/edit";
	}

	@GetMapping("/detail/{id}")
	@RequiresPermissions("productManage:dailyProductionManage:edit")
	String detail(@PathVariable("id") String dailyProductionId,Model model){
		DailyProductionManageDO dailyProductionManage = dailyProductionManageService.get(dailyProductionId);
		Map map = new HashMap();
		map.put("dailyProductionId",dailyProductionId);
		List<DailyProductionContentDO> list = dailyProductionContentService.list(map);
		if(list.size()!=0){
			for (DailyProductionContentDO d:list) {
				d.setProductDetailDO(productDetailService.get(d.getProductId()));
				d.setProcessDefinitionDO(processDefinitionService.get(d.getProcessId()));
			}



		}
		model.addAttribute("dailyProductionManage", dailyProductionManage);
		model.addAttribute("contentList",list);
		return "factory/productManage/dailyProductionManage/detail";
	}


	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("productManage:dailyProductionManage:add")
	public R save(DailyProductionManageDO dailyProductionManage, HttpServletRequest request)throws Exception{

		String date = request.getParameter("period");
		System.out.println(date);
		long time = new SimpleDateFormat("yyyy-MM-dd").parse(date).getTime();
		dailyProductionManage.setDailyProductionDate(new Date(time));
		dailyProductionManage.setCreateDate(new Date(time));
		dailyProductionManage.setIsDeleted(0);
		dailyProductionManage.setCreateUserId(getUserId().toString());
		String []processIds = request.getParameterValues("processId");
		String []productIds = request.getParameterValues("productId");
		String []counts = request.getParameterValues("count");
		String dailyId = "";
		try {
			dailyId = dailyProductionManageService.save(dailyProductionManage);
		}catch (Exception e){
			return R.error(1,"已有生产日报");
		}


		DailyProductionContentDO dailyProductionContentDO = new DailyProductionContentDO();
		for(int i = 0;i<processIds.length;i++){
			dailyProductionContentDO.setDailyProductionContentId(UUID.randomUUID().toString());
			dailyProductionContentDO.setCount(Integer.parseInt(counts[i]));
			dailyProductionContentDO.setDailyProductionId(dailyId);
			dailyProductionContentDO.setProcessId(processIds[i]);
			dailyProductionContentDO.setProductId(productIds[i]);
			dailyProductionContentService.save(dailyProductionContentDO);
		}


		if(dailyId!=null){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("productManage:dailyProductionManage:edit")
	public R update(@RequestBody DailyProductionManageDO dailyProductionManage){
		dailyProductionManageService.update(dailyProductionManage);
		System.out.println(dailyProductionManage.getContentList().get(0).getProcessId());
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("productManage:dailyProductionManage:remove")
	public R remove( String dailyProductionId){
		if(dailyProductionManageService.remove(dailyProductionId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("productManage:dailyProductionManage:batchRemove")
	public R remove(@RequestParam("ids[]") String[] dailyProductionIds){
		dailyProductionManageService.batchRemove(dailyProductionIds);
		return R.ok();
	}
	
}
