package com.bootdo.factory.warehouseManagement.domain;


import java.math.BigDecimal;


/**
 * @author willice
 * @email 2782546116@qq.com
 * @date 2020-04-16 16:17:26
 */
public class GoodsDetailDO {
    private static final long serialVersionUID = 1L;

    //出入库明细ID
    private String goodsId;
    //订单ID
    private String orderId;
    //产品ID
    private String productId;
    //产品名称
    private String productName;
    //产品类型名称
    private String productTypeName;
    //数量
    private int quantity;
    //数量单位名称
    private String quantityUnitName;
    //重量单位名称
    private String weightUnitName;
    //重量
    private Double totalWeight;
    //总金额
    private BigDecimal amount;
    //
    private int isProduct;

    public int getIsProduct() {
        return isProduct;
    }

    public void setIsProduct(int isProduct) {
        this.isProduct = isProduct;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public String getQuantityUnitName() {
        return quantityUnitName;
    }

    public void setQuantityUnitName(String quantityUnitName) {
        this.quantityUnitName = quantityUnitName;
    }

    public String getWeightUnitName() {
        return weightUnitName;
    }

    public void setWeightUnitName(String weightUnitName) {
        this.weightUnitName = weightUnitName;
    }

    public Double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Double totalWeight) {
        this.totalWeight = totalWeight;
    }

    /**
     * 获取：出入库明细ID
     */
    public String getGoodsId() {
        return goodsId;
    }

    /**
     * 设置：出入库明细ID
     */
    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
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
     * 获取：产品ID
     */
    public String getProductId() {
        return productId;
    }

    /**
     * 设置：产品ID
     */
    public void setProductId(String productId) {
        this.productId = productId;
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
}
