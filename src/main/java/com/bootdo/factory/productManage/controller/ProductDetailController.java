package com.bootdo.factory.productManage.controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.service.DictService;
import com.bootdo.common.utils.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.factory.productManage.domain.ProductDetailDO;
import com.bootdo.factory.productManage.service.ProductDetailService;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * 
 * @author xgq
 * @email 1992lcg@163.com
 * @date 2020-03-18 21:49:08
 */
 
@Controller
@RequestMapping("/productManage/productDetail")
public class ProductDetailController extends BaseController {
	private static String fileRootPath;
	@Autowired
	private ProductDetailService productDetailService;
	@Autowired
	private DictService dictService;
	@Autowired
	private BootdoConfig bootdoConfig;

	@PostConstruct
	private void init() {        //初始化文件根路径
		fileRootPath = bootdoConfig.getUploadPath();
	}
	@GetMapping()
	@RequiresPermissions("productManage:productDetail:productDetail")
	String ProductDetail(){
	    return "factory/productManage/productDetail/productDetail";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("productManage:productDetail:productDetail")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
     	List<ProductDetailDO> productDetailList = productDetailService.list(query);
		if(productDetailList.size()!=0){
			for (ProductDetailDO product:productDetailList) {
				product.setProductTypeName(dictService.getName("product_type",product.getProductType()));
				product.setWeightUnitName(dictService.getName("weight_unit",product.getWeightUnit()));
				product.setQuantityUnitName(dictService.getName("quantity_unit",product.getQuantityUnit()));
			}
		}
		int total = productDetailService.count(query);
		PageUtils pageUtils = new PageUtils(productDetailList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("productManage:productDetail:add")
	String add(){
	    return "factory/productManage/productDetail/add";
	}

	@GetMapping("/edit/{productId}")
	@RequiresPermissions("productManage:productDetail:edit")
	String edit(@PathVariable("productId") String productId,Model model){
		ProductDetailDO productDetail = productDetailService.get(productId);
		model.addAttribute("productDetail", productDetail);
	    return "factory/productManage/productDetail/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("productManage:productDetail:add")
	public R save(ProductDetailDO productDetail, MultipartFile filesFile){
		productDetail.setProductId(UUID.randomUUID().toString());
		String date = DateUtils.format(new java.util.Date());
		productDetail.setCreatDate(new Date(new java.util.Date().getTime()));
		productDetail.setCreatUserId(getUserId().toString());
		productDetail.setIsDeleted(0);
		String filesFileName = null;
		if(filesFile!=null&&!filesFile.isEmpty()){
			System.out.println(filesFile.getName());
			try {
				filesFileName = saveFiles(filesFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
			productDetail.setFiles(filesFileName);
		}
		if(productDetailService.save(productDetail)>0){
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
		ProductDetailDO product = productDetailService.get(id);
		product.setProductTypeName(dictService.getName("product_type",product.getProductType()));
		product.setWeightUnitName(dictService.getName("weight_unit",product.getWeightUnit()));
		product.setQuantityUnitName(dictService.getName("quantity_unit",product.getQuantityUnit()));

		model.addAttribute("productDetail", product);
		return "factory/productManage/productDetail/detail";
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("productManage:productDetail:edit")
	public R update( ProductDetailDO productDetail){
		productDetailService.update(productDetail);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("productManage:productDetail:remove")
	public R remove( String productId){
		if(productDetailService.remove(productId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("productManage:productDetail:batchRemove")
	public R remove(@RequestParam("ids[]") String[] productIds){
		productDetailService.batchRemove(productIds);
		return R.ok();
	}
	@RequestMapping(value = "/exportFile")
	@RequiresPermissions("factory:repairHistory:exportFile")
	public void Export(HttpServletResponse response, HttpServletRequest request) throws Exception {

		Map<String, Object> params = new HashMap<>();
		String productName = request.getParameter("searchProductName");
		String productType = request.getParameter("searchType");
		String model = request.getParameter("searchModel");
		System.out.println("pro"+productName+"typpe"+productType+"model"+model);
		params.put("productName", productName);
		params.put("productType", productType);
		params.put("model", model);

		List<ProductDetailDO> productDetailDOList = productDetailService.list(params);
		if(productDetailDOList.size()!=0){
			for (ProductDetailDO product:productDetailDOList) {
				product.setProductTypeName(dictService.getName("product_type",product.getProductType()));
				product.setWeightUnitName(dictService.getName("weight_unit",product.getWeightUnit()));
				product.setQuantityUnitName(dictService.getName("quantity_unit",product.getQuantityUnit()));
			}
		}
		ExportUtils<ProductDetailDO> exportUtils = new ExportUtils<ProductDetailDO>();
		exportUtils.exportFile(response, request, "详细信息", productDetailDOList);


	}
	
}
