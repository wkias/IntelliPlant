package com.bootdo.factory.equipmentManage.domain;

import com.bootdo.common.annotation.Log;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;


/**
 * @author xuhui
 * @email 1992lcg@163.com
 * @date 2020-02-18 08:59:15
 */

public class EquipmentManageDO implements Serializable {

    //设备ID
    @Log("设备ID")
    private String equipmentId;
    //设备类型
    @Log("设备类型")
    private String equipmentType;
    //设备编码
    @Log("设备编码")
    private String code;
    //设备名称
    @Log("设备名称")
    private String name;
    //设备图片
    @Log("设备图片")
    private String photo;
    //供应商
    @Log("供应商")
    private String provider;
    //设备型号
    @Log("设备型号")
    private String model;
    //出厂编号
    @Log("出厂编号")
    private String factoryNumber;
    //出厂日期
    @Log("出厂日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date productionDate;
    //购买人
    @Log("购买人")
    private String purchaser;
    //价值
    @Log("价值")
    private Double cost;
    //购买日期
    @Log("购买日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date purchaseDate;
    //负责人
    @Log("负责人")
    private String dutyUserId;
    //备注
    @Log("备注")
    private String remarks;
    //附件
    @Log("附件")
    private String files;
    //删除标记
    @Log("删除标记")
    private Boolean isDeleted;
    //创建者
    @Log("创建者")
    private String createUserId;
    //创建时间
    @Log("创建时间")
    private Timestamp createTime;

    /**
     * 获取：设备ID
     */

    public String getEquipmentId() {
        return equipmentId;
    }

    /**
     * 设置：设备ID
     */
    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    /**
     * 获取：设备类型
     */
    public String getEquipmentType() {
        return equipmentType;
    }

    /**
     * 设置：设备类型
     */
    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    /**
     * 获取：设备编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置：设备编码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取：设备名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置：设备名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：设备图片
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * 设置：设备图片
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    /**
     * 获取：供应商
     */
    public String getProvider() {
        return provider;
    }

    /**
     * 设置：供应商
     */
    public void setProvider(String provider) {
        this.provider = provider;
    }

    /**
     * 获取：设备型号
     */
    public String getModel() {
        return model;
    }

    /**
     * 设置：设备型号
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * 获取：出厂编号
     */
    public String getFactoryNumber() {
        return factoryNumber;
    }

    /**
     * 设置：出厂编号
     */
    public void setFactoryNumber(String factoryNumber) {
        this.factoryNumber = factoryNumber;
    }

    /**
     * 获取：出厂日期
     */
    public Date getProductionDate() {
        return productionDate;
    }

    /**
     * 设置：出厂日期
     */
    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    /**
     * 获取：购买人
     */
    public String getPurchaser() {
        return purchaser;
    }

    /**
     * 设置：购买人
     */
    public void setPurchaser(String purchaser) {
        this.purchaser = purchaser;
    }

    /**
     * 获取：价值
     */
    public Double getCost() {
        return cost;
    }

    /**
     * 设置：价值
     */
    public void setCost(Double cost) {
        this.cost = cost;
    }

    /**
     * 获取：购买日期
     */
    public Date getPurchaseDate() {
        return purchaseDate;
    }

    /**
     * 设置：购买日期
     */
    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    /**
     * 获取：负责人
     */
    public String getDutyUserId() {
        return dutyUserId;
    }

    /**
     * 设置：负责人
     */
    public void setDutyUserId(String dutyUserId) {
        this.dutyUserId = dutyUserId;
    }

    /**
     * 获取：备注
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置：备注
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * 获取：附件
     */
    public String getFiles() {
        return files;
    }

    /**
     * 设置：附件
     */
    public void setFiles(String files) {
        this.files = files;
    }

    /**
     * 获取：删除标记
     */
    public Boolean getIsDeleted() {
        return isDeleted;
    }

    /**
     * 设置：删除标记
     */
    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * 获取：创建者
     */
    public String getCreateUserId() {
        return createUserId;
    }

    /**
     * 设置：创建者
     */
    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 获取：创建时间
     */
    public Timestamp getCreateTime() {
        return createTime;
    }

    /**
     * 设置：创建时间
     */
    public void setCreateTime(long createTime) {

        this.createTime = new Timestamp(createTime);
    }

    /**
     * 设置：创建时间（方法重载，供Excel导出功能读取）
     */
    public void setCreateTime(Timestamp timestamp) {
        this.createTime = timestamp;
    }

    @Override
    public String toString() {
        return "EquipmentManageDO{" +
                "equipmentId='" + equipmentId + '\'' +
                ", equipmentType='" + equipmentType + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", photo='" + photo + '\'' +
                ", provider='" + provider + '\'' +
                ", model='" + model + '\'' +
                ", factoryNumber='" + factoryNumber + '\'' +
                ", productionDate=" + productionDate +
                ", purchaser='" + purchaser + '\'' +
                ", cost=" + cost +
                ", purchaseDate=" + purchaseDate +
                ", dutyUserId='" + dutyUserId + '\'' +
                ", remarks='" + remarks + '\'' +
                ", files='" + files + '\'' +
                ", isDeleted=" + isDeleted +
                ", createUserId='" + createUserId + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
