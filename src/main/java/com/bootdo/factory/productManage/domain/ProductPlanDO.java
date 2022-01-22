package com.bootdo.factory.productManage.domain;

import com.bootdo.factory.contractManage.domain.OrderContentDO;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author xuhui
 * @email 1992lcg@163.com
 * @date 2020-04-07 20:22:08
 */
public class ProductPlanDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//生产计划主键ID
	private String planId;
	//计划编号
	private String planCode;
	//订单内容id
	private String orderContentId;
	//订单名称
	private String orderName;
	//订单内容
	private OrderContentDO orderContentDO;
	//产品
	private ProductDefinitionDO productDefinitionDO;
	//优先级
	private String priority;
	//计划负责人ID
	private String dutyPersonId;
	//计划负责人姓名
	private String dutyPersonName;
	//计划开工时间
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	//计划完工时间
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date endDate;
	//完成数量
	private Integer completedQuantity;
	//计划状态
	private String planState;
	//备注
	private String remarks;
	//删除标记
	private Boolean isDeleted;
	//创建人
	private String creatUserId;
	//创建时间
	private Date creatTime;

	public String getOrderName() {
		return this.orderName;
	}

	public void setOrderName(final String orderName) {
		this.orderName = orderName;
	}

	public String getDutyPersonName() {
		return this.dutyPersonName;
	}

	public void setDutyPersonName(final String dutyPersonName) {
		this.dutyPersonName = dutyPersonName;
	}

	public ProductDefinitionDO getProductDefinitionDO() {
		return this.productDefinitionDO;
	}

	public void setProductDefinitionDO(final ProductDefinitionDO productDefinitionDO) {
		this.productDefinitionDO = productDefinitionDO;
	}

	public OrderContentDO getOrderContentDO() {
		return this.orderContentDO;
	}

	public void setOrderContentDO(final OrderContentDO orderContentDO) {
		this.orderContentDO = orderContentDO;
	}

	/**
	 * 设置：生产计划主键ID
	 */
	public void setPlanId(String planId) {
		this.planId = planId;
	}
	/**
	 * 获取：生产计划主键ID
	 */
	public String getPlanId() {
		return planId;
	}
	/**
	 * 设置：计划编号
	 */
	public void setPlanCode(String planCode) {
		this.planCode = planCode;
	}
	/**
	 * 获取：计划编号
	 */
	public String getPlanCode() {
		return planCode;
	}
	/**
	 * 设置：订单内容id
	 */
	public void setOrderContentId(String orderContentId) {
		this.orderContentId = orderContentId;
	}
	/**
	 * 获取：订单内容id
	 */
	public String getOrderContentId() {
		return orderContentId;
	}
	/**
	 * 设置：优先级
	 */
	public void setPriority(String priority) {
		this.priority = priority;
	}
	/**
	 * 获取：优先级
	 */
	public String getPriority() {
		return priority;
	}
	/**
	 * 设置：计划负责人ID
	 */
	public void setDutyPersonId(String dutyPersonId) {
		this.dutyPersonId = dutyPersonId;
	}
	/**
	 * 获取：计划负责人ID
	 */
	public String getDutyPersonId() {
		return dutyPersonId;
	}
	/**
	 * 设置：计划开工时间
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * 获取：计划开工时间
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * 设置：计划完工时间
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * 获取：计划完工时间
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * 设置：完成数量
	 */
	public void setCompletedQuantity(Integer completedQuantity) {
		this.completedQuantity = completedQuantity;
	}
	/**
	 * 获取：完成数量
	 */
	public Integer getCompletedQuantity() {
		return completedQuantity;
	}
	/**
	 * 设置：计划状态
	 */
	public void setPlanState(String planState) {
		this.planState = planState;
	}
	/**
	 * 获取：计划状态
	 */
	public String getPlanState() {
		return planState;
	}
	/**
	 * 设置：备注
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * 获取：备注
	 */
	public String getRemarks() {
		return remarks;
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
	 * 设置：创建人
	 */
	public void setCreatUserId(String creatUserId) {
		this.creatUserId = creatUserId;
	}
	/**
	 * 获取：创建人
	 */
	public String getCreatUserId() {
		return creatUserId;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreatTime() {
		return creatTime;
	}
}
