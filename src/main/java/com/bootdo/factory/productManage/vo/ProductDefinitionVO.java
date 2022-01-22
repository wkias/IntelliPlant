package com.bootdo.factory.productManage.vo;

import com.bootdo.factory.productManage.domain.ProductDefinitionDO;

public class ProductDefinitionVO extends ProductDefinitionDO {
    private int count;
    private String productType;
    private String productTypeName;
    private String quantityUnit;
    private String quantityUnitName;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public String getQuantityUnit() {
        return quantityUnit;
    }

    public void setQuantityUnit(String quantityUnit) {
        this.quantityUnit = quantityUnit;
    }

    public String getQuantityUnitName() {
        return quantityUnitName;
    }

    public void setQuantityUnitName(String quantityUnitName) {
        this.quantityUnitName = quantityUnitName;
    }
}
