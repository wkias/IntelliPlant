package com.bootdo.factory.saleManage.controller;

import java.util.List;
import java.util.Map;

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

import com.bootdo.factory.saleManage.domain.SaleContentDO;
import com.bootdo.factory.saleManage.service.SaleContentService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author xgq
 * @email 1992lcg@163.com
 * @date 2020-03-29 23:21:55
 */
 
@Controller
@RequestMapping("/saleManage/saleContent")
public class SaleContentController {
	@Autowired
	private SaleContentService saleContentService;
	
	@GetMapping()
	@RequiresPermissions("saleManage:saleContent:saleContent")
	String SaleContent(){
	    return "saleManage/saleContent/saleContent";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("saleManage:saleContent:saleContent")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<SaleContentDO> saleContentList = saleContentService.list(query);
		int total = saleContentService.count(query);
		PageUtils pageUtils = new PageUtils(saleContentList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("saleManage:saleContent:add")
	String add(){
	    return "saleManage/saleContent/add";
	}

	@GetMapping("/edit/{saleContentId}")
	@RequiresPermissions("saleManage:saleContent:edit")
	String edit(@PathVariable("saleContentId") String saleContentId,Model model){
		SaleContentDO saleContent = saleContentService.get(saleContentId);
		model.addAttribute("saleContent", saleContent);
	    return "saleManage/saleContent/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("saleManage:saleContent:add")
	public R save( SaleContentDO saleContent){
		if(saleContentService.save(saleContent)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("saleManage:saleContent:edit")
	public R update( SaleContentDO saleContent){
		saleContentService.update(saleContent);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("saleManage:saleContent:remove")
	public R remove( String saleContentId){
		if(saleContentService.remove(saleContentId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("saleManage:saleContent:batchRemove")
	public R remove(@RequestParam("ids[]") String[] saleContentIds){
		saleContentService.batchRemove(saleContentIds);
		return R.ok();
	}
	
}
