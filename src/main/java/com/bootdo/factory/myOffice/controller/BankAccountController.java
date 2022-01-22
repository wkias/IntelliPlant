package com.bootdo.factory.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bootdo.common.utils.ShiroUtils;
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

import com.bootdo.factory.myOffice.domain.BankAccountDO;
import com.bootdo.factory.service.BankAccountService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author Casper
 * @email 1992lcg@163.com
 * @date 2020-05-25 15:36:18
 */
 
@Controller
@RequestMapping("/factory/bankAccount")
public class BankAccountController {
	@Autowired
	private BankAccountService bankAccountService;
	
	@GetMapping()
	@RequiresPermissions("factory:bankAccount:bankAccount")
	String BankAccount(){
	    return "factory/bankAccount/bankAccount";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("factory:bankAccount:bankAccount")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<BankAccountDO> bankAccountList = bankAccountService.list(query);
		int total = bankAccountService.count(query);
		PageUtils pageUtils = new PageUtils(bankAccountList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("factory:bankAccount:add")
	String add(){
	    return "factory/bankAccount/add";
	}

	@GetMapping("/edit/{bankAccountId}")
	@RequiresPermissions("factory:bankAccount:edit")
	String edit(@PathVariable("bankAccountId") String bankAccountId,Model model){
		BankAccountDO bankAccount = bankAccountService.get(bankAccountId);
		model.addAttribute("bankAccount", bankAccount);
	    return "factory/bankAccount/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("factory:bankAccount:add")
	public R save( BankAccountDO bankAccount){
		Long userId = ShiroUtils.getUserId();
		bankAccount.setCreateUserId(userId + "");
		bankAccount.setCreateTime(new Date());
		bankAccount.setIsDeleted(false);
		if(bankAccountService.save(bankAccount)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("factory:bankAccount:edit")
	public R update( BankAccountDO bankAccount){
		bankAccountService.update(bankAccount);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("factory:bankAccount:remove")
	public R remove( String bankAccountId){
		if(bankAccountService.remove(bankAccountId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("factory:bankAccount:batchRemove")
	public R remove(@RequestParam("ids[]") String[] bankAccountIds){
		bankAccountService.batchRemove(bankAccountIds);
		return R.ok();
	}
	
}
