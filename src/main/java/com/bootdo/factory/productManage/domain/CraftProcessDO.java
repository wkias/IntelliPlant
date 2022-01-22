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
public class CraftProcessDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//关系表主键ID
	private String id;
	//工艺ID
	private String craftId;
	//工序ID
	private String processId;
	//工序定义
	private com.bootdo.factory.domain.ProcessDefinitionDO processDefinition;
	//加工次序
	private Integer order;
	//工序分类
	private String processType;
	//删除标记
	private Boolean isDeleted;
	//创建者
	private String createUserId;
	//创建时间
	private Date createTime;

	public com.bootdo.factory.domain.ProcessDefinitionDO getProcessDefinition() {
		return this.processDefinition;
	}

	public void setProcessDefinition(final com.bootdo.factory.domain.ProcessDefinitionDO processDefinition) {
		this.processDefinition = processDefinition;
	}

	/**
	 * 设置：关系表主键ID
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：关系表主键ID
	 */
	public String getId() {
		return id;
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
	 * 设置：工序ID
	 */
	public void setProcessId(String processId) {
		this.processId = processId;
	}
	/**
	 * 获取：工序ID
	 */
	public String getProcessId() {
		return processId;
	}
	/**
	 * 设置：加工次序
	 */
	public void setOrder(Integer order) {
		this.order = order;
	}
	/**
	 * 获取：加工次序
	 */
	public Integer getOrder() {
		return order;
	}
	/**
	 * 设置：工序分类
	 */
	public void setProcessType(String processType) {
		this.processType = processType;
	}
	/**
	 * 获取：工序分类
	 */
	public String getProcessType() {
		return processType;
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
