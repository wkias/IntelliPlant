package com.bootdo.factory.productManage.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Casper
 * @email 1992lcg@163.com
 * @date 2020-03-28 14:55:08
 */
public class InspectionItemsDetailDO implements Serializable {
    private static final long serialVersionUID = 1L;
    //关系表主键ID
    private String id;
    private String productInspectionId;
    private String inspectionItemsId;
    private com.bootdo.factory.productManage.domain.InspectionItemsDO inspectionItems;
    //项目名称
    private String projectName;
    //值类型
    private String valueType;
    //单位
    private String unit;
    //范围阈值
    private String rangeThreshold;
    //说明
    private String description;
    //删除标记；默认值0；0：未删除；1：已删除
    private Boolean isDeleted;
    //创建者
    private String createUserId;
    //创建时间
    private Date createTime;

    public com.bootdo.factory.productManage.domain.InspectionItemsDO getInspectionItems() {
        return this.inspectionItems;
    }

    public void setInspectionItems(final com.bootdo.factory.productManage.domain.InspectionItemsDO inspectionItems) {
        this.inspectionItems = inspectionItems;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setProductInspectionId(String productInspectionId) {
        this.productInspectionId = productInspectionId;
    }

    public String getProductInspectionId() {
        return productInspectionId;
    }

    public void setInspectionItemsId(String inspectionItemsId) {
        this.inspectionItemsId = inspectionItemsId;
    }

    public String getInspectionItemsId() {
        return inspectionItemsId;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    public String getValueType() {
        return valueType;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }

    public void setRangeThreshold(String rangeThreshold) {
        this.rangeThreshold = rangeThreshold;
    }

    public String getRangeThreshold() {
        return rangeThreshold;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }
}
