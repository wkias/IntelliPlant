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
public class CraftDefinitionDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//工艺主键ID
	private String craftId;
	//工艺编号
	private String craftCode;
	//工艺名称
	private String craftName;
	//状态 0停用 1启用
	private String state;
	//描述
	private String description;
	//删除标记
	private Boolean isDeleted;
	//创建者
	private String createUserId;
	//创建时间
	private Date createTime;

	/**
	 * 设置：工艺主键ID
	 */
	public void setCraftId(String craftId) {
		this.craftId = craftId;
	}
	/**
	 * 获取：工艺主键ID
	 */
	public String getCraftId() {
		return craftId;
	}
	/**
	 * 设置：工艺编号
	 */
	public void setCraftCode(String craftCode) {
		this.craftCode = craftCode;
	}
	/**
	 * 获取：工艺编号
	 */
	public String getCraftCode() {
		return craftCode;
	}
	/**
	 * 设置：工艺名称
	 */
	public void setCraftName(String craftName) {
		this.craftName = craftName;
	}
	/**
	 * 获取：工艺名称
	 */
	public String getCraftName() {
		return craftName;
	}
	/**
	 * 设置：状态 0停用 1启用
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * 获取：状态 0停用 1启用
	 */
	public String getState() {
		return state;
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
