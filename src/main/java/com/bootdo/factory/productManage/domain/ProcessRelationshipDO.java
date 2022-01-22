package com.bootdo.factory.productManage.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;



/**
 * 
 * 
 * @author zrj
 * @email 1992lcg@163.com
 * @date 2020-03-27 10:52:40
 */
public class ProcessRelationshipDO implements Serializable {
	private static final long serialVersionUID = 1L;

	//质检项目
	private InspectionItemsDO items;
	//关系表ID
	private String id;
	//工序质检ID
	private String processInspectionId;
	//质检项目ID
	private String inspectionItemsId;

	/*private Integer order;*/
	private Boolean isDeleted;
	//创建者ID
	private String createUserId;
	//创建日期
	private Timestamp createDate;

	public InspectionItemsDO getItems() {
		return this.items;
	}

	public void setItems(final InspectionItemsDO items) {
		this.items = items;
	}
	/**
	 * 设置：关系表ID
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：关系表ID
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：工序质检ID
	 */
	public void setProcessInspectionId(String processInspectionId) {
		this.processInspectionId = processInspectionId;
	}
	/**
	 * 获取：工序质检ID
	 */
	public String getProcessInspectionId() {
		return processInspectionId;
	}
	/**
	 * 设置：质检项目ID
	 */
	public void setInspectionItemsId(String inspectionItemsId) {
		this.inspectionItemsId = inspectionItemsId;
	}
	/**
	 * 获取：质检项目ID
	 */
	public String getInspectionItemsId() {
		return inspectionItemsId;
	}
	/**
	 * 设置：删除标记0未删除1已删除默认为0
	 */
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	/**
	 * 获取：删除标记0未删除1已删除默认为0
	 */
	public Boolean getIsDeleted() {
		return isDeleted;
	}
	/**
	 * 设置：创建者ID
	 */
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	/**
	 * 获取：创建者ID
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
	/**
	 * 设置：加工次序
	 */
	/*public void setOrder(Integer order) {
		this.order = order;
	}*/
	/**
	 * 获取：加工次序
	 */
	/*public Integer getOrder() {
		return order;
	}*/
}
