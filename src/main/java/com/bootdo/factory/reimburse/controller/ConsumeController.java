package com.bootdo.factory.reimburse.controller;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.factory.reimburse.domain.ConsumeDO;
import com.bootdo.factory.reimburse.service.ConsumeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author willice
 * @email willicen@qq.com
 * @date 2020-05-25 22:39:15
 */

@Controller
@RequestMapping("/reimburse/consume")
public class ConsumeController {
	@Autowired
	private ConsumeService consumeService;

	@GetMapping()
	@RequiresPermissions("reimburse:consume:consume")
	String Consume() {
		return "reimburse/consume/consume";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("reimburse:consume:consume")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		//查询列表数据
		Query query = new Query(params);
		List<ConsumeDO> consumeList = consumeService.list(query);
		int total = consumeService.count(query);
		PageUtils pageUtils = new PageUtils(consumeList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("reimburse:consume:add")
	String add() {
		return "reimburse/consume/add";
	}

	@GetMapping("/edit/{consumeId}")
	@RequiresPermissions("reimburse:consume:edit")
	String edit(@PathVariable("consumeId") String consumeId, Model model) {
		ConsumeDO consume = consumeService.get(consumeId);
		model.addAttribute("consume", consume);
		return "reimburse/consume/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("reimburse:consume:add")
	public R save(ConsumeDO consume) {
		if (consumeService.save(consume) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("reimburse:consume:edit")
	public R update(ConsumeDO consume) {
		consumeService.update(consume);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("reimburse:consume:remove")
	public R remove(String consumeId) {
		if (consumeService.remove(consumeId) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("reimburse:consume:batchRemove")
	public R remove(@RequestParam("ids[]") String[] consumeIds) {
		consumeService.batchRemove(consumeIds);
		return R.ok();
	}

}
