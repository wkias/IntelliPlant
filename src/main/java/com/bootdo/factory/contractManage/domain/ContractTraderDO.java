package com.bootdo.factory.contractManage.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author xuhui
 * @email 1992lcg@163.com
 * @date 2020-03-13 19:24:32
 */
public class ContractTraderDO implements Serializable {
	private static final long serialVersionUID = 1L;

	public ContractTraderDO() {
	}

	public ContractTraderDO(final String id, final String contractId, final String traderId, final String traderType, final String contactPersonId, final String deliverAddress) {
		this.id = id;
		this.contractId = contractId;
		this.traderId = traderId;
		this.traderType = traderType;
		this.contactPersonId = contactPersonId;
		this.deliverAddress = deliverAddress;
	}

	//关系ID
	private String id;
	//合同ID
	private String contractId;
	//交易方ID
	private String traderId;
	//交易方类型
	private String traderType;
	//联系人ID
	private String contactPersonId;
	//发货/收货地址
	private String deliverAddress;

	/**
	 * 设置：关系ID
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：关系ID
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：合同ID
	 */
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	/**
	 * 获取：合同ID
	 */
	public String getContractId() {
		return contractId;
	}
	/**
	 * 设置：交易方ID
	 */
	public void setTraderId(String traderId) {
		this.traderId = traderId;
	}
	/**
	 * 获取：交易方ID
	 */
	public String getTraderId() {
		return traderId;
	}
	/**
	 * 设置：交易方类型
	 */
	public void setTraderType(String traderType) {
		this.traderType = traderType;
	}
	/**
	 * 获取：交易方类型
	 */
	public String getTraderType() {
		return traderType;
	}
	/**
	 * 设置：联系人ID
	 */
	public void setContactPersonId(String contactPersonId) {
		this.contactPersonId = contactPersonId;
	}
	/**
	 * 获取：联系人ID
	 */
	public String getContactPersonId() {
		return contactPersonId;
	}
	/**
	 * 设置：发货/收货地址
	 */
	public void setDeliverAddress(String deliverAddress) {
		this.deliverAddress = deliverAddress;
	}
	/**
	 * 获取：发货/收货地址
	 */
	public String getDeliverAddress() {
		return deliverAddress;
	}
}
