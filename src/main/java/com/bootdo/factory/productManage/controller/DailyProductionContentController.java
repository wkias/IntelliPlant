package com.bootdo.factory.productManage.controller;

import java.util.List;
import java.util.Map;

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

import com.bootdo.factory.productManage.domain.DailyProductionContentDO;
import com.bootdo.factory.productManage.service.DailyProductionContentService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author xgq
 * @email 1992lcg@163.com
 * @date 2020-04-04 14:47:59
 */
 
@Controller
@RequestMapping("/productManage/dailyProductionContent")
public class DailyProductionContentController {
	@Autowired
	private DailyProductionContentService dailyProductionContentService;
	@Autowired
	private com.bootdo.factory.service.ProcessDefinitionService processDefinitionService;
	@Autowired
	private ProductDetailService productDetailService;


	@GetMapping()
	String DailyProductionContent(){
	    return "productManage/dailyProductionContent/dailyProductionContent";
	}
	
	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<DailyProductionContentDO> dailyProductionContentList = dailyProductionContentService.list(query);
		if(dailyProductionContentList.size()!=0){
			for( DailyProductionContentDO dailyProductionContentDO:dailyProductionContentList) {
				dailyProductionContentDO.setProcessDefinitionDO(processDefinitionService.get(dailyProductionContentDO.getProcessId()));
				dailyProductionContentDO.setProductDetailDO(productDetailService.get(dailyProductionContentDO.getProductId()));
			}
		}

		int total = dailyProductionContentService.count(query);
		PageUtils pageUtils = new PageUtils(dailyProductionContentList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("productManage:dailyProductionContent:add")
	String add(){
	    return "productManage/dailyProductionContent/add";
	}

	@GetMapping("/edit/{dailyProductionContentId}")
	@RequiresPermissions("productManage:dailyProductionContent:edit")
	String edit(@PathVariable("dailyProductionContentId") String dailyProductionContentId,Model model){
		DailyProductionContentDO dailyProductionContent = dailyProductionContentService.get(dailyProductionContentId);
		model.addAttribute("dailyProductionContent", dailyProductionContent);
	    return "productManage/dailyProductionContent/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("productManage:dailyProductionContent:add")
	public R save( DailyProductionContentDO dailyProductionContent){
		if(dailyProductionContentService.save(dailyProductionContent)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("productManage:dailyProductionContent:edit")
	public R update( DailyProductionContentDO dailyProductionContent){
		dailyProductionContentService.update(dailyProductionContent);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("productManage:dailyProductionContent:remove")
	public R remove( String dailyProductionContentId){
		if(dailyProductionContentService.remove(dailyProductionContentId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("productManage:dailyProductionContent:batchRemove")
	public R remove(@RequestParam("ids[]") String[] dailyProductionContentIds){
		dailyProductionContentService.batchRemove(dailyProductionContentIds);
		return R.ok();
	}
	
}
