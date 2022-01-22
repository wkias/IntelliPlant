package com.bootdo.factory.contractManage.controller;

import java.io.IOException;
import java.util.*;

import com.bootdo.common.utils.*;
import com.bootdo.factory.contractManage.domain.ContractTraderDO;
import com.bootdo.factory.equipmentManage.domain.EquipmentManageDO;
import com.bootdo.factory.contractManage.service.ContractTraderService;
import com.bootdo.factory.contractManage.vo.ContractManageVO;
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

import com.bootdo.factory.contractManage.domain.ContractManageDO;
import com.bootdo.factory.contractManage.service.ContractManageService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * 
 * @author xuhui
 * @email 1992lcg@163.com
 * @date 2020-03-09 15:22:58
 */
 
@Controller
@RequestMapping("/factory/contractManage")
public class ContractManageController {
	@Autowired
	private ContractManageService contractManageService;
	@Autowired
	private ContractTraderService contractTraderService;
	@GetMapping()
	@RequiresPermissions("factory:contractManage:contractManage")
	String ContractManage(){
	    return "factory/contractManage/contractManage";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("factory:contractManage:contractManage")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据

			Query query = new Query(params);
			List<ContractManageVO> contractManageList = contractManageService.list(query);
			int total = contractManageService.count(query);
			PageUtils pageUtils = new PageUtils(contractManageList, total);
			return pageUtils;
	}
	@ResponseBody
	@GetMapping("getVO/{id}")
	public ContractManageVO getVO(@PathVariable String id, Model model) {
		//查询列表数据
		ContractManageVO contractManage = contractManageService.getVO(id);
		return contractManage;
	}



	@ResponseBody
	@GetMapping("/ajaxList")
	@RequiresPermissions("factory:contractManage:contractManage")
	public List<ContractManageVO> ajaxList(@RequestParam Map<String, Object> params){
		//查询列表数据
			List<ContractManageVO> contractManageList = contractManageService.list(params);
			return contractManageList;
	}
	@GetMapping("/traderList/{traderType}")
	public String traderList(@PathVariable("traderType")String traderType, Model model){
		if("Sup".equals(traderType)){
			model.addAttribute("isSupplier",true);
			return "factory/contractManage/traderSelect";
		}
		else if("Dem".equals(traderType)){
			model.addAttribute("isSupplier",false);
			return "factory/contractManage/traderSelect";
		}
		return "factory/contractManage/traderSelect";
	}
	/**
	 * 详情
	 */
	@GetMapping("/details/{id}")
	@RequiresPermissions("factory:contractManage:contractManage")
	public String details(@PathVariable String id, Model model) {
		//查询列表数据
		ContractManageVO contractManage = contractManageService.getVO(id);
		model.addAttribute("contractManage", contractManage);
		return "factory/contractManage/details";
	}
	@GetMapping("/add")
	@RequiresPermissions("factory:contractManage:add")
	String add(){
	    return "factory/contractManage/add";
	}

	@GetMapping("/edit/{contractId}")
	@RequiresPermissions("factory:contractManage:edit")
	String edit(@PathVariable("contractId") String contractId,Model model){
		ContractManageVO contractManage = contractManageService.getVO(contractId);
		model.addAttribute("contractManage", contractManage);
	    return "factory/contractManage/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("factory:contractManage:add")
	public R save(ContractManageDO contractManage, MultipartFile filesFile,HttpServletRequest request) throws Exception{
		if (filesFile != null && !filesFile.isEmpty()) {
			String filesFileName = FileUtil.saveFiles(filesFile);
			contractManage.setFileName(filesFileName);
		}
		String contractId=contractManageService.save(contractManage);
		if(contractId!=null){
			String[] supIds=request.getParameterValues("supplierId");
			String[] supSendAddr=request.getParameterValues("supSendAddr");
			String[] supContactPersonId=request.getParameterValues("supplierContactPersonId");
			for(int i=0;i<supIds.length;i++){
				if("".equals(supIds[i])){
					break;
				}
				ContractTraderDO contractTrader=new ContractTraderDO();
				contractTrader.setTraderId(supIds[i]);
				contractTrader.setDeliverAddress(supSendAddr[i]);
				contractTrader.setContractId(contractId);
				contractTrader.setContactPersonId(supContactPersonId[i]);
				contractTrader.setTraderType("Sup");
				contractTraderService.save(contractTrader);
			}
			String[] demIds=request.getParameterValues("demanderId");
			String[] demSendAddr=request.getParameterValues("demSendAddr");
			String[] demContactPersonId=request.getParameterValues("demanderContactPersonId");
			for(int i=0;i<demIds.length;i++){
				if("".equals(demIds[i])){
					break;
				}
				ContractTraderDO contractTrader=new ContractTraderDO();
				contractTrader.setTraderId(demIds[i]);
				contractTrader.setDeliverAddress(demSendAddr[i]);
				contractTrader.setContractId(contractId);
				contractTrader.setId(UUID.randomUUID().toString());
				contractTrader.setContactPersonId(demContactPersonId[i]);
				contractTrader.setTraderType("Dem");
				contractTraderService.save(contractTrader);
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
	@RequiresPermissions("factory:contractManage:edit")
	public R update(@RequestBody ContractManageVO contractManage) throws Exception{
		contractManageService.update(contractManage);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("factory:contractManage:remove")
	public R remove( String contractId){
		if(contractManageService.remove(contractId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("factory:contractManage:batchRemove")
	public R remove(@RequestParam("ids[]") String[] contractIds){
		contractManageService.batchRemove(contractIds);
		return R.ok();
	}
	/**
	 * 附件下载
	 */
	@GetMapping("/files/{filesName}")
	public void filesDownload(@PathVariable("filesName") String filesName, HttpServletResponse response) throws Exception {

		try {
			if (filesName == null || filesName.equals("")) {
				return;
			}
			Map<String, Object> map = new HashMap<>();
			map.put("fileName", filesName);
			ContractManageDO contract = contractManageService.list(map).get(0);
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; filename=" + filesName);
			response.getOutputStream().write(FileUtil.getFilesBytes(filesName));
			response.getOutputStream().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 导出为excel
	 */
	@GetMapping(value = "/export")
	public void Export(@RequestParam Map<String, Object> params, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ExportUtils<ContractManageDO> exportUtils = new ExportUtils<>();
//        Map<String,Object> query=new HashMap<>();
//        query.put("code",request.getParameter("code"));
//        query.put("name",request.getParameter("name"));
		List<ContractManageVO> contracts = contractManageService.list(params);
//		System.out.println("params:");
//		for (Map.Entry<String, Object> param : params.entrySet()) {
//			System.out.println(param.getKey() + "-------" + param.getValue());
//		}
//		for (ContractManageDO contract : contracts) {
//			System.out.println("equipment:" + contract);
//		}
		exportUtils.exportFile(response, request, "bootdo导出文档", contracts);
	}
	@GetMapping("/contactPerson/{custermerId}")
	public String selectContactPerson(@PathVariable("custermerId")String custermerId,Model model){
		model.addAttribute("custermerId",custermerId);
		return "factory/contractManage/contactPersonSelect";
	}
}
