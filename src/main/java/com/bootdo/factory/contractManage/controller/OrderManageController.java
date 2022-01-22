package com.bootdo.factory.contractManage.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.service.DictService;
import com.bootdo.common.utils.*;
import com.bootdo.factory.contractManage.domain.CustermerInformationDO;
import com.bootdo.factory.contractManage.domain.OrderContentDO;
import com.bootdo.factory.contractManage.service.CustermerInformationService;
import com.bootdo.factory.contractManage.service.OrderContentService;
import com.bootdo.factory.contractManage.vo.ContractManageVO;
import com.bootdo.factory.contractManage.vo.OrderManageVO;
import com.bootdo.factory.productManage.domain.ProductDefinitionDO;
import com.bootdo.factory.productManage.service.ProductDefinitionService;
import com.bootdo.system.service.UserService;
import io.swagger.models.auth.In;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.factory.contractManage.domain.OrderManageDO;
import com.bootdo.factory.contractManage.service.OrderManageService;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * 
 * @author xgq
 * @email 1992lcg@163.com
 * @date 2020-03-11 16:26:25
 */
 
@Controller
@RequestMapping("/factory/orderManage")
public class OrderManageController {
	@Autowired
	private OrderManageService orderManageService;
	@Autowired
	private DictService dictService;
	@Autowired
	private UserService userService;
	@Autowired
	private OrderContentService orderContentService;
	private static String fileRootPath;
	@Autowired
	private BootdoConfig bootdoConfig;
	@Autowired
	private ProductDefinitionService productDefinitionService;
	@Autowired
	private CustermerInformationService custermerInformationService;

	@PostConstruct
	private void init() {        //初始化文件根路径
		fileRootPath = bootdoConfig.getUploadPath();
	}
	@GetMapping()
	@RequiresPermissions("factory:orderManage:orderManage")
	String OrderManage(){
	    return "factory/orderManage/orderManage";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("factory:orderManage:orderManage")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		//List<OrderManageDO> orderManageList = orderManageService.list(query);
		List<OrderManageVO> orderManageVOList = orderManageService.listVO(query);
		if(orderManageVOList.size()!=0){
			for (OrderManageVO order:orderManageVOList) {

				CustermerInformationDO custermerInformationDO =  custermerInformationService.get(order.getDemandId());
				if(custermerInformationDO!=null){
				order.setDemandName(custermerInformationDO.getCustermerName());}
				else order.setDemandName("无");
				order.setOrderType(dictService.getName("order_type",order.getOrderType()));
				order.setState(dictService.getName("order_state",order.getState()));
				order.setOrderManagerName(userService.get(Long.parseLong(order.getOrderManagerId())).getName());
			}
		}
		int total = orderManageService.count(query);
		PageUtils pageUtils = new PageUtils(orderManageVOList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("factory:orderManage:add")
	String add(Model model){
	    model.addAttribute("orderCode",UUID.randomUUID().toString());
		return "factory/orderManage/add";

	}

	@GetMapping("/contractList")
	@RequiresPermissions("factory:orderManage:add")
	String contract(){
		return "factory/orderManage/contractList";
	}
	@GetMapping("/edit/{orderId}")
	@RequiresPermissions("factory:orderManage:edit")
	String edit(@PathVariable("orderId") String orderId,Model model){

		OrderManageVO orderManage = orderManageService.getVO(orderId);
		//orderManage.setOrderType(dictService.getName("order_type",orderManage.getOrderType()));
		//orderManage.setState(dictService.getName("order_state",orderManage.getState()));
		orderManage.setDemandName(custermerInformationService.get(orderManage.getDemandId()).getCustermerName());
		orderManage.setOrderManagerName(userService.get(Long.parseLong(orderManage.getOrderManagerId())).getName());
		orderManage.setContractType(dictService.getName("contract_type",orderManage.getContractType()));
		Map map = new HashMap<String,String >();
		map.put("orderId",orderId);
		List<OrderContentDO> orderList = orderContentService.list(map);
		List<OrderContentDO> batchList = new ArrayList();
		List<OrderContentDO> noBatchList = new ArrayList();
		if(orderList.size()!=0){


			for (OrderContentDO content:orderList
			) {
				content.setProductDefinitionDO(productDefinitionService.get(content.getProductId()));
				System.out.println(content.getIsBatched()+"    batch");
				if (content.getIsBatched().equals("0")){
					System.out.println(noBatchList.size()+"size");
					noBatchList.add(content);
					System.out.println(noBatchList.size()+"size");
				}else {batchList.add(content);}
			}



		}
		model.addAttribute("batchList",batchList);
		model.addAttribute("noBatchList",noBatchList);
		model.addAttribute("orderManage", orderManage);


		model.addAttribute("orderManage", orderManage);

	    return "factory/orderManage/edit";
	}
	@ResponseBody
	@RequestMapping("/getMaxPeriod")
	public String getMaxPeriod(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String contractId = request.getParameter("contractId");
		String m = orderManageService.getMaxPeriodById(contractId);
		if(m==null||m=="NaN"){m="1";}else {
		m = Integer.parseInt(m)+1+"";}
		System.out.println(m);

		return m;
	}
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("factory:orderManage:add")
	public R save(OrderManageVO orderManage, MultipartFile filesFile,HttpServletRequest request) throws ParseException {
		orderManage.setOrderId(UUID.randomUUID().toString());
		String filesFileName = null;
		if(filesFile!=null&&!filesFile.isEmpty()){
			System.out.println(filesFile.getName());
			try {
				 filesFileName = saveFiles(filesFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
			orderManage.setFiles(filesFileName);
		}
		String orderId=orderManageService.save(orderManage);
		if(orderId!=null){
			String []noBatchIds = request.getParameterValues("noBatchId");
			String []noCounts = request.getParameterValues("noCount");
			String []noDestinations = request.getParameterValues("noDestination");
			String []noTotalSums = request.getParameterValues("noTotalSum");
			String  []NoDeadlines = request.getParameterValues("noDeadline");
			String []noStoreHouses = request.getParameterValues("noStoreHouse");
			if (noBatchIds.length!=0){
				for (int i = 0;i<noBatchIds.length;i++){
				if("".equals(noBatchIds[i])){
					break;
				}
					OrderContentDO orderContent = new OrderContentDO();
					orderContent.setProductId(noBatchIds[i]);
					orderContent.setOrderId(orderId);
					orderContent.setContentId(UUID.randomUUID().toString());
					orderContent.setCount(Integer.parseInt(noCounts[i]));
					long time = new SimpleDateFormat("yyyy-MM-dd").parse(NoDeadlines[i]).getTime();
					orderContent.setDeadline(new Date(time));
					orderContent.setDestination(noDestinations[i]);
					orderContent.setTotalSum(Double.parseDouble(noTotalSums[i]));
					orderContent.setStorehouse(noStoreHouses[i]);
					orderContent.setIsBatched("0");
					orderContent.setComplete(0);
					orderContentService.save(orderContent);


				}

			}


//			String []BatchIds = request.getParameterValues("batchId");
//			String []Counts = request.getParameterValues("count");
//			String []Destinations = request.getParameterValues("destination");
//			String []TotalSums = request.getParameterValues("TotalSum");
//			String  []Deadlines = request.getParameterValues("deadline");
//			String []StoreHouses = request.getParameterValues("storeHouse");
//			if (BatchIds.length!=0){
//				for (int i = 0;i<BatchIds.length;i++){
//					if("".equals(BatchIds[i])){
//						break;
//					}
//					OrderContentDO orderContent = new OrderContentDO();
//					orderContent.setProductId(BatchIds[i]);
//					orderContent.setOrderId(orderId);
//					orderContent.setContentId(UUID.randomUUID().toString());
//					orderContent.setCount(Integer.parseInt(Counts[i]));
//					long time = new SimpleDateFormat("yyyy-MM-dd").parse(Deadlines[i]).getTime();
//					orderContent.setDeadline(new Date(time));
//					orderContent.setDestination(Destinations[i]);
//					orderContent.setTotalSum(Double.parseDouble(TotalSums[i]));
//					orderContent.setStorehouse(StoreHouses[i]);
//					orderContent.setIsBatched(i+1+"");
//					orderContent.setComplete(0);
//					orderContentService.save(orderContent);
//
//
//				}
//
//			}



			return R.ok();
		}
		return R.error();
	}
	private String saveFiles(MultipartFile filesFile) throws Exception {
		String filesFileName = filesFile.getOriginalFilename();
		filesFileName = FileUtil.renameToUUID(filesFileName);
		String filesFilePath = fileRootPath + "files/";
		FileUtil.uploadFile(filesFile.getBytes(), filesFilePath, filesFileName);
		return filesFileName;
	}
	/**
	 * 详情
	 */
	@GetMapping("/details/{id}")
	@RequiresPermissions("factory:orderManage:orderManage")
	public String details(@PathVariable String id, Model model) {
		//查询列表数据
		OrderManageVO orderManage = orderManageService.getVO(id);
		orderManage.setDemandName(custermerInformationService.get(orderManage.getDemandId()).getCustermerName());
		orderManage.setOrderType(dictService.getName("order_type",orderManage.getOrderType()));
		orderManage.setState(dictService.getName("order_state",orderManage.getState()));
		orderManage.setOrderManagerId(userService.get(Long.parseLong(orderManage.getOrderManagerId())).getName());
		orderManage.setContractType(dictService.getName("contract_type",orderManage.getContractType()));
		Map map = new HashMap<String,String >();
		map.put("orderId",id);
		List<OrderContentDO> orderList = orderContentService.list(map);
		List<OrderContentDO> batchList = new ArrayList();
		List<OrderContentDO> noBatchList = new ArrayList();
		if(orderList.size()!=0){


			for (OrderContentDO content:orderList
				 ) {
				content.setProductDefinitionDO(productDefinitionService.get(content.getProductId()));

				if (content.getIsBatched().equals("0")){
					System.out.println(noBatchList.size()+"size");
					noBatchList.add(content);
					System.out.println(noBatchList.size()+"size");
				}else {batchList.add(content);}
			}



		}
		model.addAttribute("batchList",batchList);
		model.addAttribute("noBatchList",noBatchList);
		model.addAttribute("orderManage", orderManage);
		return "factory/orderManage/details";
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("factory:orderManage:edit")
	public R update(@RequestBody OrderManageVO orderManage,MultipartFile filesFile){
		String filesFileName = null;
		if(filesFile!=null&&!filesFile.isEmpty()){
			try {
				filesFileName = saveFiles(filesFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
			orderManage.setFiles(filesFileName);
		}
		System.out.println(orderManage.getNoBatchOrderContentS().get(0).getCount());
		orderManageService.update(orderManage);
		return R.ok();
	}

	@GetMapping("/productList/{isBatch}")
	@RequiresPermissions("factory:orderManage:orderManage")
	public String productList(@PathVariable String isBatch, Model model) {
		model.addAttribute("isBatch",isBatch);
		return "factory/orderManage/productList";
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("factory:orderManage:remove")
	public R remove( String orderId){
		if(orderManageService.remove(orderId)>0){
		return R.ok();
		}
		return R.error();
	}
	@RequestMapping(value = "/exportFile")
	@RequiresPermissions("factory:repairHistory:exportFile")
	public void Export(HttpServletResponse response, HttpServletRequest request) throws Exception {

		Map<String, Object> params = new HashMap<>();
		String orderCode = request.getParameter("orderCode");
		String contractId = request.getParameter("contractId");
		String orderName = request.getParameter("orderName");

		params.put("orderCode", orderCode);
		params.put("contractId", contractId);
		params.put("orderName", orderName);

		List<OrderManageVO> orderManageDOList = orderManageService.listVO(params);
		ExportUtils<OrderManageVO> exportUtils = new ExportUtils<OrderManageVO>();
		exportUtils.exportFile(response, request, "详细信息", orderManageDOList);


	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("factory:orderManage:batchRemove")
	public R remove(@RequestParam("ids[]") String[] orderIds){
		orderManageService.batchRemove(orderIds);
		return R.ok();
	}

	/**
	 * 获取客户-订单金额图表
	 */
	@GetMapping("/customerChart")
	@ResponseBody
	public Map customerChart(){
		Map<String,Double> chartMap=new HashMap<>();
		Map<String,String> custermerMap=new HashMap<>();
		List<CustermerInformationDO> custermers=custermerInformationService.list(new HashMap<>());
		for(CustermerInformationDO custermer:custermers){
			custermerMap.put(custermer.getCustermerId(),custermer.getCustermerName());
		}
		for(String customerId:custermerMap.keySet()){
			//客户
			String customerName=custermerMap.get(customerId);
			double totalMoney=0;
			Map<String,Object> orderQuery=new HashMap<>();
			orderQuery.put("demandId",customerId);
			List<OrderManageVO> orderManageDOS=orderManageService.listVO(orderQuery);
			for(OrderManageVO orderManage:orderManageDOS){
				//订单
				totalMoney+=orderManage.getTotalSum();
			}
			chartMap.put(customerName,totalMoney);
		}
		return chartMap;
	}
}
