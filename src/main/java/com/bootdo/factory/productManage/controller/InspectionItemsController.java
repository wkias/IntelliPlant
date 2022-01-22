package com.bootdo.factory.productManage.controller;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.factory.productManage.domain.InspectionItemsDO;
import com.bootdo.factory.productManage.service.InspectionItemsService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author willice
 * @email 2782546116@qq.com
 * @date 2020-03-26 12:19:21
 */

@Controller
@RequestMapping("/factory/inspectionItems")
public class InspectionItemsController {
	@Autowired
	private InspectionItemsService inspectionItemsService;

	@GetMapping()
	@RequiresPermissions("factory:inspectionItems:inspectionItems")
	String InspectionItems() {
		return "factory/inspectionItems/inspectionItems";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("factory:inspectionItems:inspectionItems")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		List<InspectionItemsDO> inspectionItemsList = inspectionItemsService.list(query);
		int total = inspectionItemsService.count(query);
		PageUtils pageUtils = new PageUtils(inspectionItemsList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("factory:inspectionItems:add")
	String add() {
		return "factory/inspectionItems/add";
	}

	@GetMapping("/process")
	@RequiresPermissions("factory:inspectionItems:add")
	String process() {
		return "factory/inspectionItems/process";
	}

	@GetMapping("/edit/{inspectionItemsId}")
	@RequiresPermissions("factory:inspectionItems:edit")
	String edit(@PathVariable("inspectionItemsId") String inspectionItemsId, Model model) {
		InspectionItemsDO inspectionItems = inspectionItemsService.get(inspectionItemsId);
		model.addAttribute("inspectionItems", inspectionItems);
		return "factory/inspectionItems/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("factory:inspectionItems:add")
	public R save(InspectionItemsDO inspectionItems) {
		inspectionItems.setCreateUserId(ShiroUtils.getUserId());
		inspectionItems.setCreateTime(new Timestamp(new Date().getTime()));
		inspectionItems.setIsDeleted(false);
		if (inspectionItemsService.save(inspectionItems) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("factory:inspectionItems:edit")
	public R update(InspectionItemsDO inspectionItems) {
		inspectionItemsService.update(inspectionItems);
		return R.ok();
	}

	/**
	 * 停用
	 */
	@ResponseBody
	@PostMapping("/switch")
	@RequiresPermissions("factory:inspectionItems:edit")
	public R switchh(String inspectionItemsId) {
		if (inspectionItemsService.switchh(inspectionItemsId) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("factory:inspectionItems:remove")
	public R remove(String inspectionItemsId) {
		if (inspectionItemsService.remove(inspectionItemsId) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("factory:inspectionItems:batchRemove")
	public R remove(@RequestParam("ids[]") String[] inspectionItemsIds) {
		inspectionItemsService.batchRemove(inspectionItemsIds);
		return R.ok();
	}

}
