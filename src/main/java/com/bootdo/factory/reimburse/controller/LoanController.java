package com.bootdo.factory.reimburse.controller;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.factory.reimburse.domain.LoanDO;
import com.bootdo.factory.reimburse.service.LoanService;
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
@RequestMapping("/reimburse/loan")
public class LoanController {
	@Autowired
	private LoanService loanService;

	@GetMapping()
	@RequiresPermissions("reimburse:loan:loan")
	String Loan() {
		return "reimburse/loan/loan";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("reimburse:loan:loan")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		//查询列表数据
		Query query = new Query(params);
		List<LoanDO> loanList = loanService.list(query);
		int total = loanService.count(query);
		PageUtils pageUtils = new PageUtils(loanList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("reimburse:loan:add")
	String add() {
		return "reimburse/loan/add";
	}

	@GetMapping("/edit/{loanId}")
	@RequiresPermissions("reimburse:loan:edit")
	String edit(@PathVariable("loanId") String loanId, Model model) {
		LoanDO loan = loanService.get(loanId);
		model.addAttribute("loan", loan);
		return "reimburse/loan/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("reimburse:loan:add")
	public R save(LoanDO loan) {
		if (loanService.save(loan) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("reimburse:loan:edit")
	public R update(LoanDO loan) {
		loanService.update(loan);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("reimburse:loan:remove")
	public R remove(String loanId) {
		if (loanService.remove(loanId) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("reimburse:loan:batchRemove")
	public R remove(@RequestParam("ids[]") String[] loanIds) {
		loanService.batchRemove(loanIds);
		return R.ok();
	}

}
