package com.bootdo.factory.productManage.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Casper
 * @email 1992lcg@163.com
 * @date 2020-03-27 10:41:48
 */
public class ProductInspectionDO implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String STATE_USE = "1";
    public static final String STATE_NOT_USE = "0";
    private com.bootdo.factory.productManage.domain.ProductDefinitionDO product;
    //质检项目
    private InspectionItemsDO inspectionItems;
    //工序质检ID
    private String productInspectionId;
    //关联产品名称
    private String associatedProduct;
    //产品编号
    private String productCode;
    //状态；0停用；1启用
    private String state;
    //质检结果；0报废；1返工；2合格
    private String result;
    //描述
    private String represent;
    //删除标记；默认值0；0：未删除；1：已删除
    private Boolean isDeleted;
    //创建者
    private String createUserId;
    //创建时间
    private Date createTime;

    public InspectionItemsDO getInspectionItems() {
        return this.inspectionItems;
    }

    public void setInspectionItems(final InspectionItemsDO inspectionItems) {
        this.inspectionItems = inspectionItems;
    }

    public com.bootdo.factory.productManage.domain.ProductDefinitionDO getProduct() {
        return this.product;
    }

    public void setProduct(final com.bootdo.factory.productManage.domain.ProductDefinitionDO product) {
        this.product = product;
    }

    public void setProductInspectionId(String productInspectionId) {
        this.productInspectionId = productInspectionId;
    }

    public String getProductInspectionId() {
        return productInspectionId;
    }

    public void setAssociatedProduct(String associatedProduct) {
        this.associatedProduct = associatedProduct;
    }

    public String getAssociatedProduct() {
        return associatedProduct;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setRepresent(String represent) {
        this.represent = represent;
    }

    public String getRepresent() {
        return represent;
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
