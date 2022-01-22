package com.bootdo.factory.warehouseManagement.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.common.utils.ExportUtils;
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

import com.bootdo.factory.warehouseManagement.domain.StockCheckDO;
import com.bootdo.factory.warehouseManagement.service.StockCheckService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * 
 * @author zrj
 * @email 1992lcg@163.com
 * @date 2020-03-22 18:08:32
 */
 
@Controller
@RequestMapping("/factory/stockCheck")
public class StockCheckController {
	@Autowired
	private StockCheckService stockCheckService;
	
	@GetMapping()
	@RequiresPermissions("factory:stockCheck:stockCheck")
	String StockCheck(){
	    return "factory/stockCheck/stockCheck";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("factory:stockCheck:stockCheck")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StockCheckDO> stockCheckList = stockCheckService.list(query);
		int total = stockCheckService.count(query);
		PageUtils pageUtils = new PageUtils(stockCheckList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("factory:stockCheck:add")
	String add(){
	    return "factory/stockCheck/add";
	}

	@GetMapping("/edit/{stockCheckId}")
	@RequiresPermissions("factory:stockCheck:edit")
	String edit(@PathVariable("stockCheckId") String stockCheckId,Model model){
		StockCheckDO stockCheck = stockCheckService.get(stockCheckId);
		model.addAttribute("stockCheck", stockCheck);
	    return "factory/stockCheck/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("factory:stockCheck:add")
	public R save( StockCheckDO stockCheck){
		if(stockCheckService.save(stockCheck)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("factory:stockCheck:edit")
	public R update( StockCheckDO stockCheck){
		stockCheckService.update(stockCheck);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("factory:stockCheck:remove")
	public R remove( String stockCheckId){
		if(stockCheckService.remove(stockCheckId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("factory:stockCheck:batchRemove")
	public R remove(@RequestParam("ids[]") String[] stockCheckIds){
		stockCheckService.batchRemove(stockCheckIds);
		return R.ok();
	}
	@GetMapping(value = "/exportFile")
	public void Export(@RequestParam Map<String, Object> params, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ExportUtils<StockCheckDO> exportUtils = new ExportUtils<>();
//        Map<String,Object> query=new HashMap<>();
//        query.put("code",request.getParameter("code"));
//        query.put("name",request.getParameter("name"));
		List<StockCheckDO> stockCheckList = stockCheckService.list(params);
		System.out.println("params:");
		for (Map.Entry<String, Object> param : params.entrySet()) {
			System.out.println(param.getKey() + "-------" + param.getValue());
		}
		for (StockCheckDO stock : stockCheckList) {
			System.out.println("stock:" + stock);
		}
		exportUtils.exportFile(response, request, "bootdo导出文档", stockCheckList);
	}
	
}
