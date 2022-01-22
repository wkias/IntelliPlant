package com.bootdo.factory.productManage.domain;

import com.bootdo.common.annotation.Log;

import java.io.Serializable;
import java.sql.Date;



/**
 * 
 * 
 * @author xgq
 * @email 1992lcg@163.com
 * @date 2020-03-18 21:49:08
 */
public class ProductDetailDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//产品ID
	private String productId;
	//产品名称
	@Log(value = "产品名称")
	private String productName;
	//产品类型
	private String productType;
	//规值
	@Log(value = "规值")
	private String gaugeValue;
	//材料
	@Log(value = "材料")
	private String material;
	//尺寸
	@Log(value = "尺寸")
	private String size;
	//规格
	@Log(value = "规格")
	private String model;
	//数量单位
	private String quantityUnit;
	//重量单位
	private String weightUnit;
	//备注
	private String remark;
	//单重
	@Log(value = "单重")
	private String weight;
	//单价
	@Log(value = "单价")
	private Double price;
	//附件
	private String files;
	//删除标记
	private Integer isDeleted;
	//创建人
	private String creatUserId;
	//创建时间
	private Date creatDate;
	//类型名
	@Log(value = "产品类型")
	private String productTypeName;
	@Log(value = "数量单位")
	private String quantityUnitName;
	@Log(value = "重量单位")
	private String weightUnitName;

	public String getQuantityUnitName() {
		return quantityUnitName;
	}

	public void setQuantityUnitName(String quantityUnitName) {
		this.quantityUnitName = quantityUnitName;
	}

	public String getWeightUnitName() {
		return weightUnitName;
	}

	public void setWeightUnitName(String weightUnitName) {
		this.weightUnitName = weightUnitName;
	}

	public String getProductTypeName() {
		return productTypeName;
	}

	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}

	/**
	 * 获取：产品ID
	 */
	public String getProductId() {
		return productId;
	}

	/**
	 * 设置：产品ID
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}

	/**
	 * 获取：产品名称
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * 设置：产品名称
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * 获取：产品类型
	 */
	public String getProductType() {
		return productType;
	}

	/**
	 * 设置：产品类型
	 */
	public void setProductType(String productType) {
		this.productType = productType;
	}

	/**
	 * 获取：规值
	 */
	public String getGaugeValue() {
		return gaugeValue;
	}

	/**
	 * 设置：规值
	 */
	public void setGaugeValue(String gaugeValue) {
		this.gaugeValue = gaugeValue;
	}

	/**
	 * 获取：材料
	 */
	public String getMaterial() {
		return material;
	}

	/**
	 * 设置：材料
	 */
	public void setMaterial(String material) {
		this.material = material;
	}

	/**
	 * 获取：尺寸
	 */
	public String getSize() {
		return size;
	}

	/**
	 * 设置：尺寸
	 */
	public void setSize(String size) {
		this.size = size;
	}

	/**
	 * 获取：尺寸
	 */
	public String getModel() {
		return model;
	}

	/**
	 * 设置：尺寸
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * 获取：数量单位
	 */
	public String getQuantityUnit() {
		return quantityUnit;
	}

	/**
	 * 设置：数量单位
	 */
	public void setQuantityUnit(String quantityUnit) {
		this.quantityUnit = quantityUnit;
	}

	/**
	 * 获取：重量单位
	 */
	public String getWeightUnit() {
		return weightUnit;
	}

	/**
	 * 设置：重量单位
	 */
	public void setWeightUnit(String weightUnit) {
		this.weightUnit = weightUnit;
	}

	/**
	 * 获取：备注
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置：备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 获取：单重
	 */
	public String getWeight() {
		return weight;
	}

	/**
	 * 设置：单重
	 */
	public void setWeight(String weight) {
		this.weight = weight;
	}

	/**
	 * 获取：单价
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * 设置：单价
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * 获取：附件
	 */
	public String getFiles() {
		return files;
	}

	/**
	 * 设置：附件
	 */
	public void setFiles(String files) {
		this.files = files;
	}

	/**
	 * 获取：删除标记
	 */
	public Integer getIsDeleted() {
		return isDeleted;
	}

	/**
	 * 设置：删除标记
	 */
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	/**
	 * 获取：创建人
	 */
	public String getCreatUserId() {
		return creatUserId;
	}

	/**
	 * 设置：创建人
	 */
	public void setCreatUserId(String creatUserId) {
		this.creatUserId = creatUserId;
	}

	/**
	 * 获取：创建时间
	 */
	public Date getCreatDate() {
		return creatDate;
	}

	/**
	 * 设置：创建时间
	 */
	public void setCreatDate(Date creatDate) {
		this.creatDate = creatDate;
	}
}
