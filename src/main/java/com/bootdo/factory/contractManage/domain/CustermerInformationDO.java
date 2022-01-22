package com.bootdo.factory.contractManage.domain;

import com.bootdo.common.annotation.Log;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;



/**
 * 
 * 
 * @author zrj
 * @email 1992lcg@163.com
 * @date 2020-03-06 10:20:56
 */
public class CustermerInformationDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//客户ID
    @Log(value = "客户ID")
	private String custermerId;
	//客户名称（全称）
    @Log(value = "客户名称（全称）")
	private String custermerName;
	//客户类型
    @Log(value = "客户类型")
	private String custermerType;
    //是否是供应商
    private Boolean isSupplier;



	//纳税人识别号
    @Log(value = "纳税人识别号")
	private String taxpayerIdNumbers;
	//统一社会信用代码
    @Log(value = "统一社会信用代码")
	private String socialCreditCode;
	//地址
    @Log(value = "地址")
	private String address;
	//客户电话
    @Log(value = "客户电话")
	private String custermerTel;
	//开户行
    @Log(value = "开户行")
	private String bank;
	//开户行账号
    @Log(value = "开户行账号")
	private String bankAccount;
    //电子邮箱
    @Log(value = "电子邮箱")
    private String email;
    //传真
    @Log(value = "传真")
    private String fax;
    //主联系人
    /*@Log(value = "主联系人")*/
    private String mainContactPerson;
    //主联系人电话
    /*@Log(value = "主联系人电话")*/
    private String mainContactTel;
    //备注信息
	@Log(value = "备注信息")
	private String remarks;
	//删除标记

	private Boolean isDeleted;
	//创建者

	private String createUserId;
	//创建时间

	private Timestamp createTime;
    //姓名
    @Log(value = "主联系人姓名")
    private String name;
    //联系电话
    @Log(value = "主联系人电话")
    private String contactPersonTel;
    //是否主要联系人；0不是；1是；默认为0

    private Boolean isMainPerson;

	/**
	 * 设置：客户ID
	 */
	public void setCustermerId(String custermerId) {
		this.custermerId = custermerId;
	}
	/**
	 * 获取：客户ID
	 */
	public String getCustermerId() {
		return custermerId;
	}
	/**
	 * 设置：客户名称（全称）
	 */
	public void setCustermerName(String custermerName) {
		this.custermerName = custermerName;
	}
	/**
	 * 获取：客户名称（全称）
	 */
	public String getCustermerName() {
		return custermerName;
	}
	/**
	 * 设置：客户类型
	 */
	public void setCustermerType(String custermerType) {
		this.custermerType = custermerType;
	}
	/**
	 * 获取：客户类型
	 */
	public String getCustermerType() {
		return custermerType;
	}


	public Boolean getIsSupplier() {
		return this.isSupplier;
	}

	public void setIsSupplier(final Boolean supplier) {
		this.isSupplier = supplier;
	}


	/**
	 * 设置：纳税人识别号
	 */
	public void setTaxpayerIdNumbers(String taxpayerIdNumbers) {
		this.taxpayerIdNumbers = taxpayerIdNumbers;
	}
	/**
	 * 获取：纳税人识别号
	 */
	public String getTaxpayerIdNumbers() {
		return taxpayerIdNumbers;
	}
	/**
	 * 设置：统一社会信用代码
	 */
	public void setSocialCreditCode(String socialCreditCode) {
		this.socialCreditCode = socialCreditCode;
	}
	/**
	 * 获取：统一社会信用代码
	 */
	public String getSocialCreditCode() {
		return socialCreditCode;
	}
	/**
	 * 设置：地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：地址
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 设置：联系电话
	 */
	public void setCustermerTel(String custermerTel) {
		this.custermerTel = custermerTel;
	}
	/**
	 * 获取：联系电话
	 */
	public String getCustermerTel() {
		return custermerTel;
	}
	/**
	 * 设置：开户行
	 */
	public void setBank(String bank) {
		this.bank = bank;
	}
	/**
	 * 获取：开户行
	 */
	public String getBank() {
		return bank;
	}
	/**
	 * 设置：开户行账号
	 */
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	/**
	 * 获取：开户行账号
	 */
	public String getBankAccount() {
		return bankAccount;
	}
    /**
     * 设置：电子邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * 获取：电子邮箱
     */
    public String getEmail() {
        return email;
    }
    /**
     * 设置：传真
     */
    public void setFax(String fax) {
        this.fax = fax;
    }
    /**
     * 获取：传真
     */
    public String getFax() {
        return fax;
    }
    /**
     * 设置：主联系人
     */
    public void setMainContactPerson(String mainContactPerson) {
        this.mainContactPerson = mainContactPerson;
    }
    /**
     * 设置：主联系人
     */
    public String getMainContactPerson() {
        return mainContactPerson;
    }
    /**
     * 设置：主联系人
     */
    public void setMainContactTel(String mainContactTel) {
        this.mainContactTel = mainContactTel;
    }
    /**
     * 设置：主联系人
     */
    public String getMainContactTel() {
        return mainContactTel;
    }

    /**
     * 获取：备注信息
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置：备注信息
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
	/**
	 * 设置：删除标记；0表示未被删除，1表示已被删除，默认为0
	 */
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	/**
	 * 获取：删除标记；0表示未被删除，1表示已被删除，默认为0
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
	public void setCreateTime(long createTime) {
		this.createTime = new Timestamp(createTime);
	}
	/**
	 * 获取：创建时间
	 */
	public Timestamp getCreateTime() {
		return createTime;
	}
    /**
     * 设置：姓名
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * 获取：姓名
     */
    public String getName() {
        return name;
    }
    /**
     * 设置：联系电话
     */
    public void setContactPersonTel(String contactPersonTel) {
        this.contactPersonTel = contactPersonTel;
    }
    /**
     * 获取：联系电话
     */
    public String getContactPersonTel() {
        return contactPersonTel;
    }
    /**
     * 设置：主要联系人；0不是；1是；默认为0
     */
    public void setIsMainPerson(Boolean isMainPerson) {
        this.isMainPerson = isMainPerson;
    }
    /**
     * 获取：主要联系人；0不是；1是；默认为0
     */
    public Boolean getIsMainPerson() {
        return isMainPerson;
    }

	/**
	 * 设置：创建时间（方法重载，供Excel导出功能读取）
	 */
	public void setCreateTime(Timestamp timestamp) {
		this.createTime = timestamp;
	}
}
