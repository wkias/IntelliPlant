package com.bootdo.factory.productManage.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author xuhui
 * @email 1992lcg@163.com
 * @date 2020-03-26 11:53:33
 */
public class ProductCraftDO implements Serializable {
	private static final long serialVersionUID = 1L;

	//产品
	private ProductDefinitionDO product;
	//工艺
	private CraftDefinitionDO craft;
	//产品-工艺主键ID
	private String productCraftId;
	//产品ID
	private String productId;
	//工艺ID
	private String craftId;
	//描述
	private String description;
	//删除标记
	private Boolean isDeleted;
	//创建者
	private String createUserId;
	//创建时间
	private Date createTime;

	public CraftDefinitionDO getCraft() {
		return this.craft;
	}

	public void setCraft(final CraftDefinitionDO craft) {
		this.craft = craft;
	}

	public ProductDefinitionDO getProduct() {
		return this.product;
	}

	public void setProduct(final ProductDefinitionDO product) {
		this.product = product;
	}

	/**
	 * 设置：产品-工艺主键ID
	 */
	public void setProductCraftId(String productCraftId) {
		this.productCraftId = productCraftId;
	}
	/**
	 * 获取：产品-工艺主键ID
	 */
	public String getProductCraftId() {
		return productCraftId;
	}
	/**
	 * 设置：产品ID
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}
	/**
	 * 获取：产品ID
	 */
	public String getProductId() {
		return productId;
	}
	/**
	 * 设置：工艺ID
	 */
	public void setCraftId(String craftId) {
		this.craftId = craftId;
	}
	/**
	 * 获取：工艺ID
	 */
	public String getCraftId() {
		return craftId;
	}
	/**
	 * 设置：描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * 获取：描述
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * 设置：删除标记
	 */
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	/**
	 * 获取：删除标记
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
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
}
