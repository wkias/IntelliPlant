package com.bootdo.factory.saleManage.domain;

import com.bootdo.common.annotation.Log;
import com.bootdo.factory.contractManage.domain.CustermerInformationDO;
import com.bootdo.factory.contractManage.domain.OrderContentDO;
import org.activiti.engine.test.Deployment;

import java.io.Serializable;
import java.sql.Date;



/**
 * 
 * 
 * @author xgq
 * @email 1992lcg@163.com
 * @date 2020-03-25 15:00:46
 */
public class SaleManageDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//销售单ID
	private String saleId;
	//订单ID
	private String orderId;
	//联系人
	private String contact;
	private String contactTel;
	//订单
	private OrderContentDO order;
	@Log(value = "销售发货单编号")
	//销售发货单编号
	private String saleCode;
	@Log(value = "销售发货日期")
	//销售发货日期
	private Date saleDate;
	//销售发货单状态
	private Integer saleState;
	//销售发货状态名
	@Log(value = "销售发货单状态")
	private String saleStateName;
	//销售发货负责人
	private String saleManagerId;
	//销售发货负责人名
	private String saleManegerName;
	//订货单位ID
	private String custermerId;

	//订货单位名
	@Log(value = "订货单位名")
	private String custermerName;
	@Log(value = "收货地址")
	private String destination;
	//订货单位
	private CustermerInformationDO custermerInformation;
	//备注
	private String remark;
	//删除标记
	private Integer isDeleted;
	//创建时间
	private Date creatDate;

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getCustermerName() {
		return custermerName;
	}

	public void setCustermerName(String custermerName) {
		this.custermerName = custermerName;
	}

	public OrderContentDO getOrder() {
		return order;
	}

	public void setOrder(OrderContentDO order) {
		this.order = order;
	}

	public String getSaleStateName() {
		return saleStateName;
	}

	public void setSaleStateName(String saleStateName) {
		this.saleStateName = saleStateName;
	}

	public String getSaleManegerName() {
		return saleManegerName;
	}

	public void setSaleManegerName(String saleManegerName) {
		this.saleManegerName = saleManegerName;
	}

	public CustermerInformationDO getCustermerInformation() {
		return custermerInformation;
	}

	public void setCustermerInformation(CustermerInformationDO custermerInformation) {
		this.custermerInformation = custermerInformation;
	}
	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getContactTel() {
		return contactTel;
	}

	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}

	/**
	 * 获取：销售单ID
	 */
	public String getSaleId() {
		return saleId;
	}

	/**
	 * 设置：销售单ID
	 */
	public void setSaleId(String saleId) {
		this.saleId = saleId;
	}

	/**
	 * 获取：订单ID
	 */
	public String getOrderId() {
		return orderId;
	}

	/**
	 * 设置：订单ID
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	/**
	 * 获取：销售发货单编号
	 */
	public String getSaleCode() {
		return saleCode;
	}

	/**
	 * 设置：销售发货单编号
	 */
	public void setSaleCode(String saleCode) {
		this.saleCode = saleCode;
	}

	/**
	 * 获取：销售发货日期
	 */
	public Date getSaleDate() {
		return saleDate;
	}

	/**
	 * 设置：销售发货日期
	 */
	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}

	/**
	 * 获取：销售发货单状态
	 */
	public Integer getSaleState() {
		return saleState;
	}

	/**
	 * 设置：销售发货单状态
	 */
	public void setSaleState(Integer saleState) {
		this.saleState = saleState;
	}

	/**
	 * 获取：销售发货负责人
	 */
	public String getSaleManagerId() {
		return saleManagerId;
	}

	/**
	 * 设置：销售发货负责人
	 */
	public void setSaleManagerId(String saleManagerId) {
		this.saleManagerId = saleManagerId;
	}

	/**
	 * 获取：订货单位ID
	 */
	public String getCustermerId() {
		return custermerId;
	}

	/**
	 * 设置：订货单位ID
	 */
	public void setCustermerId(String custermerId) {
		this.custermerId = custermerId;
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
