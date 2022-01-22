package com.bootdo.factory.warehouseManagement.domain;

import com.bootdo.common.annotation.Log;

import java.io.Serializable;


/**
 * @author zrj
 * @email 1992lcg@163.com
 * @date 2020-03-22 18:08:32
 */
public class StockCheckDO implements Serializable {
    private static final long serialVersionUID = 1L;

    //库存ID

    private String stockCheckId;
    //产品ID

    private String productId;
    //库存名称
    @Log("库存名称")
    private String productName;
    //库存类型名称
    @Log("库存类型名称")
    private String productTypeName;
    //库存数量
    @Log("库存数量")
    private int quantity;
    //库存重量
    @Log("库存重量")
    private Double weight;
    //数量单位名称
    @Log("数量单位名称")
    private String quantityUnitName;
    //重量单位名称
    @Log("重量单位名称")
    private String weightUnitName;

    /**
     * 设置：库存ID
     */
    public void setStockCheckId(String stockCheckId) {
        this.stockCheckId = stockCheckId;
    }
    /**
     * 获取：库存ID
     */
    public String getStockCheckId() {
        return stockCheckId;
    }
    /**
     * 设置：产品ID
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }
    /**
     * 获取：产品ID
     */
    public String getProductId() {
        return productId;
    }
    /**
     * 设置：库存名称
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }
    /**
     * 获取：库存名称
     */
    public String getProductName() {
        return productName;
    }
    /**
     * 设置：库存类型名称
     */
    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }
    /**
     * 获取：库存类型名称
     */
    public String getProductTypeName() {
        return productTypeName;
    }

    /**
     * 获取：库存数量
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * 设置：库存数量
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * 设置：库存重量
     */
    public void setWeight(Double weight) {
        this.weight = weight;
    }

    /**
     * 获取：库存重量
     */
    public Double getWeight() {
        return weight;
    }
    /**
     * 设置：数量单位名称
     */
    public void setQuantityUnitName(String quantityUnitName) {
        this.quantityUnitName = quantityUnitName;
    }
    /**
     * 获取：数量单位名称
     */
    public String getQuantityUnitName() {
        return quantityUnitName;
    }
    /**
     * 设置：重量单位名称
     */
    public void setWeightUnitName(String weightUnitName) {
        this.weightUnitName = weightUnitName;
    }
    /**
     * 获取：重量单位名称
     */
    public String getWeightUnitName() {
        return weightUnitName;
    }
}
