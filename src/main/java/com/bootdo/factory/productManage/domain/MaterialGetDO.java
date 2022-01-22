package com.bootdo.factory.productManage.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author xuhui
 * @email 1992lcg@163.com
 * @date 2020-04-27 14:06:39
 */
public class MaterialGetDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//
	private String planId;
	//
	private String materalId;
	//
	private Integer count;
	//物料名
	private String materalName;
	//物料规格
	private String materalModel;
	//删除标记；
	private Boolean isDeleted;
	//创建者
	private String createUserId;
	//创建时间
	private Date createTime;


	public String getMateralName() {
		return this.materalName;
	}

	public void setMateralName(final String materalName) {
		this.materalName = materalName;
	}

	public String getMateralModel() {
		return this.materalModel;
	}

	public void setMateralModel(final String materalModel) {
		this.materalModel = materalModel;
	}

	/**
	 * 设置：
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setPlanId(String planId) {
		this.planId = planId;
	}
	/**
	 * 获取：
	 */
	public String getPlanId() {
		return planId;
	}
	/**
	 * 设置：
	 */
	public void setMateralId(String materalId) {
		this.materalId = materalId;
	}
	/**
	 * 获取：
	 */
	public String getMateralId() {
		return materalId;
	}
	/**
	 * 设置：
	 */
	public void setCount(Integer count) {
		this.count = count;
	}
	/**
	 * 获取：
	 */
	public Integer getCount() {
		return count;
	}
	/**
	 * 设置：删除标记；
默认值0 ；
0：未删除
1：已删除
	 */
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	/**
	 * 获取：删除标记；
默认值0 ；
0：未删除
1：已删除
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
