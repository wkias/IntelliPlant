package com.bootdo.factory.productManage.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 工序投入
 * 
 * @author xuhui
 * @email 1992lcg@163.com
 * @date 2020-04-26 18:30:11
 */
public class ProcessInputDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//部件编号
	private String componentCode;
	//生产计划ID
	private String planId;
	//当前工序ID
	private String processId;
	//物料ID
	private String materialId;
	//
	private Integer materialCount;
	//质检中
	private Boolean inspecting;
	//删除标记
	private Boolean isDeleted;
	//创建者
	private String createUserId;
	//创建时间
	private Date createTime;

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
	 * 设置：部件编号
	 */
	public void setComponentCode(String componentCode) {
		this.componentCode = componentCode;
	}
	/**
	 * 获取：部件编号
	 */
	public String getComponentCode() {
		return componentCode;
	}
	/**
	 * 设置：生产计划ID
	 */
	public void setPlanId(String planId) {
		this.planId = planId;
	}
	/**
	 * 获取：生产计划ID
	 */
	public String getPlanId() {
		return planId;
	}
	/**
	 * 设置：当前工序ID
	 */
	public void setProcessId(String processId) {
		this.processId = processId;
	}
	/**
	 * 获取：当前工序ID
	 */
	public String getProcessId() {
		return processId;
	}
	/**
	 * 设置：物料ID
	 */
	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}
	/**
	 * 获取：物料ID
	 */
	public String getMaterialId() {
		return materialId;
	}
	/**
	 * 设置：
	 */
	public void setMaterialCount(Integer materialCount) {
		this.materialCount = materialCount;
	}
	/**
	 * 获取：
	 */
	public Integer getMaterialCount() {
		return materialCount;
	}
	/**
	 * 设置：质检中
	 */
	public void setInspecting(Boolean inspecting) {
		this.inspecting = inspecting;
	}
	/**
	 * 获取：质检中
	 */
	public Boolean getInspecting() {
		return inspecting;
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
