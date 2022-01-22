package com.bootdo.factory.warehouseManagement.domain;

import com.bootdo.common.annotation.Log;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * @author willice
 * @email 2782546116@qq.com
 * @date 2020-03-19 11:13:59
 */
public class OutboundOrderDO implements Serializable {
    private static final long serialVersionUID = 1L;

    //出库单ID
    @Log("出库单ID")
    private String outboundId;
    //关联表ID
    @Log("关联表ID")
    private String associatedTableId;
    //关联表编号
    @Log("关联表编号")
    private String associatedTableCode;
    //关联表类型
    private String associatedTableType;
    //关联表类型
    @Log("关联表类型")
    private String associatedTableTypeName;
    //出库单编号
    @Log("出库单编号")
    private String outboundCode;
    //收货单位Id
    private String consigneeId;
    //收货单位
    @Log("收货单位")
    private String consignee;
    //业务类型
    private String businessType;
    //业务类型
    @Log("业务类型")
    private String businessTypeName;
    //出库状态
    private String outboundState;
    //出库状态
    @Log("出库状态")
    private String outboundStateName;
    //数量
    @Log("数量")
    private int quantity;
    //重量
    @Log("重量")
    private Double weight;
    //金额
    @Log("金额")
    private BigDecimal amount;
    //仓库经办人
    private Long manager;
    //仓库经办人
    @Log("仓库经办人")
    private String managerName;
    //出库日期
    @Log("出库日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date outboundDate;
    //
    private List<GoodsDetailDO> goodsDetail;
    //文件
    private String file;
    //备注
    @Log("备注")
    private String remark;
    //仓库
    private String repository;
    //仓库
    @Log("仓库")
    private String repositoryName;
    //创建者ID
    private Long createUserId;
    //创建者ID
    @Log("创建者")
    private String createUserName;
    //创建时间
    @Log("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    //是否删除
    @Log("是否删除")
    private Boolean isDeleted;

    public List<GoodsDetailDO> getGoodsDetail() {
        return goodsDetail;
    }

    public void setGoodsDetail(List<GoodsDetailDO> goodsDetail) {
        this.goodsDetail = goodsDetail;
    }

    /**
     * 获取：出库单ID
     */
    public String getOutboundId() {
        return outboundId;
    }

    /**
     * 设置：出库单ID
     */
    public void setOutboundId(String outboundId) {
        this.outboundId = outboundId;
    }

    /**
     * 获取：关联表ID
     */
    public String getAssociatedTableId() {
        return associatedTableId;
    }

    /**
     * 设置：关联表ID
     */
    public void setAssociatedTableId(String associatedTableId) {
        this.associatedTableId = associatedTableId;
    }

    /**
     * 获取：关联表编号
     */
    public String getAssociatedTableCode() {
        return associatedTableCode;
    }

    /**
     * 设置：关联表编号
     */
    public void setAssociatedTableCode(String associatedTableCode) {
        this.associatedTableCode = associatedTableCode;
    }

    /**
     * 获取：关联表类型
     */
    public String getAssociatedTableType() {
        return associatedTableType;
    }

    /**
     * 设置：关联表类型
     */
    public void setAssociatedTableType(String associatedTableType) {
        this.associatedTableType = associatedTableType;
    }

    /**
     * 获取：关联表类型
     */
    public String getAssociatedTableTypeName() {
        return associatedTableTypeName;
    }

    /**
     * 设置：关联表类型
     */
    public void setAssociatedTableTypeName(String associatedTableTypeName) {
        this.associatedTableTypeName = associatedTableTypeName;
    }

    /**
     * 获取：出库单编号
     */
    public String getOutboundCode() {
        return outboundCode;
    }

    /**
     * 设置：出库单编号
     */
    public void setOutboundCode(String outboundCode) {
        this.outboundCode = outboundCode;
    }

    /**
     * 获取：收货单位ID
     */
    public String getConsigneeId() {
        return consigneeId;
    }

    /**
     * 设置：收货单位ID
     */
    public void setConsigneeId(String consigneeId) {
        this.consigneeId = consigneeId;
    }

    /**
     * 获取：收货单位
     */
    public String getConsignee() {
        return consignee;
    }

    /**
     * 设置：收货单位
     */
    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    /**
     * 获取：业务类型
     */
    public String getBusinessType() {
        return businessType;
    }

    /**
     * 设置：业务类型
     */
    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    /**
     * 获取：业务类型
     */
    public String getBusinessTypeName() {
        return businessTypeName;
    }

    /**
     * 设置：业务类型
     */
    public void setBusinessTypeName(String businessTypeName) {
        this.businessTypeName = businessTypeName;
    }

    /**
     * 获取：出库状态
     */
    public String getOutboundState() {
        return outboundState;
    }

    /**
     * 设置：出库状态
     */
    public void setOutboundState(String outboundState) {
        this.outboundState = outboundState;
    }

    /**
     * 获取：出库状态
     */
    public String getOutboundStateName() {
        return outboundStateName;
    }

    /**
     * 设置：出库状态
     */
    public void setOutboundStateName(String outboundStateName) {
        this.outboundStateName = outboundStateName;
    }

    /**
     * 获取：数量
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * 设置：数量
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * 获取：重量
     */
    public Double getWeight() {
        return weight;
    }

    /**
     * 设置：重量
     */
    public void setWeight(Double weight) {
        this.weight = weight;
    }

    /**
     * 获取：金额
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置：金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 获取：仓库经办人
     */
    public Long getManager() {
        return manager;
    }

    /**
     * 设置：仓库经办人
     */
    public void setManager(Long manager) {
        this.manager = manager;
    }

    /**
     * 获取：仓库经办人
     */
    public String getManagerName() {
        return managerName;
    }

    /**
     * 设置：仓库经办人
     */
    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    /**
     * 获取：出库日期
     */
    public Date getOutboundDate() {
        return outboundDate;
    }

    /**
     * 设置：出库日期
     */
    public void setOutboundDate(Date outboundDate) {
        this.outboundDate = outboundDate;
    }

    /**
     * 获取：文件
     */
    public String getFile() {
        return file;
    }

    /**
     * 设置：文件
     */
    public void setFile(String file) {
        this.file = file;
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
     * 获取：仓库
     */
    public String getRepository() {
        return repository;
    }

    /**
     * 设置：仓库
     */
    public void setRepository(String repository) {
        this.repository = repository;
    }

    /**
     * 获取：仓库
     */
    public String getRepositoryName() {
        return repositoryName;
    }

    /**
     * 设置：仓库
     */
    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }

    /**
     * 获取：创建者ID
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     * 设置：创建者ID
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 获取：创建者
     */
    public String getCreateUserName() {
        return createUserName;
    }

    /**
     * 设置：创建者
     */
    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    /**
     * 获取：创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置：创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取：是否删除
     */
    public Boolean getIsDeleted() {
        return isDeleted;
    }

    /**
     * 设置：是否删除
     */
    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}
