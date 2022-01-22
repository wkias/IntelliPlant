package com.bootdo.factory.contractManage.domain;

import com.bootdo.common.annotation.Log;
import com.bootdo.factory.domain.BillContentDO;
import com.bootdo.factory.productManage.domain.InspectionItemsDO;
import com.bootdo.factory.warehouseManagement.domain.GoodsDetailDO;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 
 * 
 * @author Casper
 * @email 1992lcg@163.com
 * @date 2020-03-08 16:33:30
 */
public class InvoiceRecordDO implements Serializable {

	//开票ID
	@Log(value = "开票ID")
	private String invoiceId;
	//开票名称
	@Log(value = "开票名称")
	private String invoiceName;
	//合同编号
	@Log(value = "合同编号")
	private String contractNumber;
	//合同名称
	@Log(value = "合同名称")
	private String contractName;
	//合同类型
	@Log(value = "合同类型")
	private String contractType;
	//开票金额
	@Log(value = "开票金额")
	private String invoiceMoney;
	//开票日期
	@Log(value = "开票日期")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date invoiceDate;
	//开票类型
	@Log(value = "开票类型")
	private String invoiceType;

	private String invoiceTypeName;

	//发票代码
	@Log(value = "发票代码")
	private String invoiceCode;
	//发票号码
	@Log(value = "发票号码")
	private String invoiceNumber;
	//经手人
	@Log(value = "经手人")
	private String handlerPerson;
	//开票人
	@Log(value = "开票人")
	private String drawer;
	//买方名称
	@Log(value = "买方名称")
	private String buyingName;
	//买方纳税人识别号
	@Log(value = "买方纳税人识别号")
	private String buyingTin;
	//买方地址
	@Log(value = "买方地址")
	private String buyingAddress;
	//买方电话
	@Log(value = "买方电话")
	private String buyingPhoneNumber;
	//买方开户行
	@Log(value = "买方开户行")
	private String buyingBank;
	//买方开户行账号
	@Log(value = "买方开户行账号")
	private String buyingBankNumber;
	//卖方名称
	@Log(value = "卖方名称")
	private String sellingName;
	//卖方纳税人识别号
	@Log(value = "卖方纳税人识别号")
	private String sellingTin;
	//卖方地址
	@Log(value = "卖方地址")
	private String sellingAddress;
	//卖方电话
	@Log(value = "卖方电话")
	private String sellingPhoneNumber;
	//买方开户行
	@Log(value = "买方开户行")
	private String sellingBank;
	//卖方开户行账号
	@Log(value = "卖方开户行账号")
	private String sellingBankNumber;
	//添加人
	@Log(value = "添加人")
	private String addtionPerson;
	//添加时间
	@Log(value = "添加时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date addtionDate;
	//备注
	@Log(value = "备注")
	private String remarks;
	//附件
	private String files;
	//删除标记；默认值0；0：未删除；1：已删除
	private Boolean isDeleted;
	//创建者
	private String createUserId;
	//创建时间
	private Date createTime;

	private BillContentDO billContent;

	public BillContentDO getBillContent() {
		return billContent;
	}

	public void setBillContent(BillContentDO billContent) {
		this.billContent= billContent;
	}

	/**
	 * 设置：开票ID
	 */
	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}

	/**
	 * 获取：开票ID
	 */
	public String getInvoiceId() {
		return invoiceId;
	}

	/**
	 * 设置：开票名称
	 */
	public void setInvoiceName(String invoiceName) {
		this.invoiceName = invoiceName;
	}

	/**
	 * 获取：开票名称
	 */
	public String getInvoiceName() {
		return invoiceName;
	}

	/**
	 * 设置：合同编号
	 */
	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	/**
	 * 获取：合同编号
	 */
	public String getContractNumber() {
		return contractNumber;
	}

	/**
	 * 设置：
	 */
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	/**
	 * 获取：
	 */
	public String getContractName() {
		return contractName;
	}

	/**
	 * 设置：合同类型
	 */
	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	/**
	 * 获取：合同类型
	 */
	public String getContractType() {
		return contractType;
	}

	/**
	 * 设置：开票金额
	 */
	public void setInvoiceMoney(String invoiceMoney) {
		this.invoiceMoney = invoiceMoney;
	}

	/**
	 * 获取：开票金额
	 */
	public String getInvoiceMoney() {
		return invoiceMoney;
	}

	/**
	 * 设置：开票日期
	 */
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	/**
	 * 获取：开票日期
	 */
	public Date getInvoiceDate() {
		return invoiceDate;
	}

	/**
	 * 设置：开票类型
	 */
	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	/**
	 * 获取：开票类型
	 */
	public String getInvoiceType() {
		return invoiceType;
	}

	/**
	 * 获取：开票类型
	 */
	public String getInvoiceTypeName() {
		return invoiceTypeName;
	}

	/**
	 * 设置：开票类型
	 */
	public void setInvoiceTypeName(String invoiceTypeName) {
		this.invoiceTypeName = invoiceTypeName;
	}

	/**
	 * 设置：发票代码
	 */
	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}

	/**
	 * 获取：发票代码
	 */
	public String getInvoiceCode() {
		return invoiceCode;
	}

	/**
	 * 设置：发票号码
	 */
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	/**
	 * 获取：发票号码
	 */
	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	/**
	 * 设置：经手人
	 */
	public void setHandlerPerson(String handlerPerson) {
		this.handlerPerson = handlerPerson;
	}

	/**
	 * 获取：经手人
	 */
	public String getHandlerPerson() {
		return handlerPerson;
	}

	/**
	 * 设置：开票人
	 */
	public void setDrawer(String drawer) {
		this.drawer = drawer;
	}

	/**
	 * 获取：开票人
	 */
	public String getDrawer() {
		return drawer;
	}

	/**
	 * 设置：买方名称
	 */
	public void setBuyingName(String buyingName) {
		this.buyingName = buyingName;
	}

	/**
	 * 获取：买方名称
	 */
	public String getBuyingName() {
		return buyingName;
	}

	/**
	 * 设置：买方纳税人识别号
	 */
	public void setBuyingTin(String buyingTin) {
		this.buyingTin = buyingTin;
	}

	/**
	 * 获取：买方纳税人识别号
	 */
	public String getBuyingTin() {
		return buyingTin;
	}

	/**
	 * 设置：买方地址
	 */
	public void setBuyingAddress(String buyingAddress) {
		this.buyingAddress = buyingAddress;
	}

	/**
	 * 获取：买方地址
	 */
	public String getBuyingAddress() {
		return buyingAddress;
	}

	/**
	 * 设置：买方电话
	 */
	public void setBuyingPhoneNumber(String buyingPhoneNumber) {
		this.buyingPhoneNumber = buyingPhoneNumber;
	}

	/**
	 * 获取：买方电话
	 */
	public String getBuyingPhoneNumber() {
		return buyingPhoneNumber;
	}

	/**
	 * 设置：买方开户行
	 */
	public void setBuyingBank(String buyingBank) {
		this.buyingBank = buyingBank;
	}

	/**
	 * 获取：买方开户行
	 */
	public String getBuyingBank() {
		return buyingBank;
	}

	/**
	 * 设置：买方开户行账号
	 */
	public void setBuyingBankNumber(String buyingBankNumber) {
		this.buyingBankNumber = buyingBankNumber;
	}

	/**
	 * 获取：买方开户行账号
	 */
	public String getBuyingBankNumber() {
		return buyingBankNumber;
	}

	/**
	 * 设置：卖方名称
	 */
	public void setSellingName(String sellingName) {
		this.sellingName = sellingName;
	}

	/**
	 * 获取：卖方名称
	 */
	public String getSellingName() {
		return sellingName;
	}

	/**
	 * 设置：卖方纳税人识别号
	 */
	public void setSellingTin(String sellingTin) {
		this.sellingTin = sellingTin;
	}

	/**
	 * 获取：卖方纳税人识别号
	 */
	public String getSellingTin() {
		return sellingTin;
	}

	/**
	 * 设置：卖方地址
	 */
	public void setSellingAddress(String sellingAddress) {
		this.sellingAddress = sellingAddress;
	}

	/**
	 * 获取：卖方地址
	 */
	public String getSellingAddress() {
		return sellingAddress;
	}

	/**
	 * 设置：卖方电话
	 */
	public void setSellingPhoneNumber(String sellingPhoneNumber) {
		this.sellingPhoneNumber = sellingPhoneNumber;
	}

	/**
	 * 获取：卖方电话
	 */
	public String getSellingPhoneNumber() {
		return sellingPhoneNumber;
	}

	/**
	 * 设置：
	 */
	public void setSellingBank(String sellingBank) {
		this.sellingBank = sellingBank;
	}

	/**
	 * 获取：
	 */
	public String getSellingBank() {
		return sellingBank;
	}

	/**
	 * 设置：卖方开户行账号
	 */
	public void setSellingBankNumber(String sellingBankNumber) {
		this.sellingBankNumber = sellingBankNumber;
	}

	/**
	 * 获取：卖方开户行账号
	 */
	public String getSellingBankNumber() {
		return sellingBankNumber;
	}

	/**
	 * 设置：添加人
	 */
	public void setAddtionPerson(String addtionPerson) {
		this.addtionPerson = addtionPerson;
	}

	/**
	 * 获取：添加人
	 */
	public String getAddtionPerson() {
		return addtionPerson;
	}

	/**
	 * 设置：添加时间
	 */
	public void setAddtionDate(Date addtionDate) {
		this.addtionDate = addtionDate;
	}

	/**
	 * 获取：添加时间
	 */
	public Date getAddtionDate() {
		return addtionDate;
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
	 * 设置：附件
	 */
	public void setFiles(String files) {
		this.files = files;
	}

	/**
	 * 获取：附件
	 */
	public String getFiles() {
		return files;
	}

	/**
	 * 设置：删除标记；默认值0；0：未删除；1：已删除
	 */
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	/**
	 * 获取：删除标记；默认值0；0：未删除；1：已删除
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
