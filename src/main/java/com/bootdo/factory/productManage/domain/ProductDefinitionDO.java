package com.bootdo.factory.productManage.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;



/**
 * 
 * 
 * @author zrj
 * @email 1992lcg@163.com
 * @date 2020-03-24 11:12:52
 */
public class ProductDefinitionDO implements Serializable {
	private static final long serialVersionUID = 1L;
    public static final String STATE_USE = "1";
    public static final String STATE_NOT_USE = "0";
	//产品定义ID
	private String productId;
	//产品编号
	private String productCode;
	//产品名称
	private String productName;
	//规格尺寸
	private String productSize;
	//产品型号
	private String productModel;
	//单位
	private String unit;
	//产品描述
	private String description;
	//产品单重
	private String weight;
	//重量单位
	private String weightUnit;
	//状态 0停用； 1启用
	private String state;
	//单价（元）
	private Double price;
	//删除标记：0未删除；1已删除；默认0
	private Boolean isDeleted;
	//创建者
	private String createUserId;
	//创建日期
	private Timestamp createDate;
	//类型名
	private String unitName;
	private String weightUnitName;

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getWeightUnitName() {
		return weightUnitName;
	}

	public void setWeightUnitName(String weightUnitName) {
		this.weightUnitName = weightUnitName;
	}



	/**
	 * 设置：产品定义ID
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}
	/**
	 * 获取：产品定义ID
	 */
	public String getProductId() {
		return productId;
	}
	/**
	 * 设置：产品编号
	 */
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	/**
	 * 获取：产品编号
	 */
	public String getProductCode() {
		return productCode;
	}
	/**
	 * 设置：产品名称
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
	 * 获取：产品名称
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * 设置：规格尺寸
	 */
	public void setProductSize(String productSize) {
		this.productSize = productSize;
	}
	/**
	 * 获取：规格尺寸
	 */
	public String getProductSize() {
		return productSize;
	}
	/**
	 * 设置：产品型号
	 */
	public void setProductModel(String productModel) {
		this.productModel = productModel;
	}
	/**
	 * 获取：产品型号
	 */
	public String getProductModel() {
		return productModel;
	}
	/**
	 * 设置：单位
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}
	/**
	 * 获取：单位
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * 设置：产品描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * 获取：产品描述
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 获取：产品单重
	 */
	public String getWeight() {
		return weight;
	}

	/**
	 * 设置：产品单重
	 */
	public void setWeight(String weight) {
		this.weight = weight;
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
	 * 设置：状态 0停用； 1启用
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * 获取：状态 0停用； 1启用
	 */
	public String getState() {
		return state;
	}

	/**
	 * 设置：金额
	 */
	public void setPrice(Double price) {
		this.price = price;
	}
	/**
	 * 获取：金额
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * 设置：删除标记：0未删除；1已删除；默认0
	 */
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	/**
	 * 获取：删除标记：0未删除；1已删除；默认0
	 */
	public Boolean getIsDeleted() {
		return isDeleted;
	}
	/**
	 * 设置：创建者
	 */
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	/**
	 * 获取：创建者
	 */
	public String getCreateUserId() {
		return createUserId;
	}
	/**
	 * 设置：创建日期
	 */
	public void setCreateDate(long createDate) {
		this.createDate = new Timestamp (createDate);
	}
	/**
	 * 获取：创建日期
	 */
	public Timestamp getCreateDate() {
		return createDate;
	}
}
