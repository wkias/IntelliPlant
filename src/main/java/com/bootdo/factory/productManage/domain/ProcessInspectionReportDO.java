package com.bootdo.factory.productManage.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * @author willice
 * @email 2782546116@qq.com
 * @date 2020-04-10 13:28:34
 */
public class ProcessInspectionReportDO implements Serializable {
    private static final long serialVersionUID = 1L;

    //质检报告ID
    private String processInspectionReportId;
    //质检日期
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date inspectionDate;
    //质检明细ID
    private String processInspectionDetailId;
    //
    private List<ProcessInspectionDetailDO> processInspectionDetailDOList;
    //
    private String processName;
    //
    private String componentName;
    //
    private String unit;
    //合格数量
    private Integer qualifiedNum;
    //返工数量
    private Integer reworkNum;
    //报废数量
    private Integer scrapNum;
    //备注
    private String remark;
    //创建用户ID
    private Integer createUserId;
    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    //是否删除
    private Boolean isDeleted;

    public Integer getQualifiedNum() {
        return qualifiedNum;
    }

    public void setQualifiedNum(Integer qualifiedNum) {
        this.qualifiedNum = qualifiedNum;
    }

    public Integer getReworkNum() {
        return reworkNum;
    }

    public void setReworkNum(Integer reworkNum) {
        this.reworkNum = reworkNum;
    }

    public Integer getScrapNum() {
        return scrapNum;
    }

    public void setScrapNum(Integer scrapNum) {
        this.scrapNum = scrapNum;
    }

    public List<ProcessInspectionDetailDO> getProcessInspectionDetailDOList() {
        return processInspectionDetailDOList;
    }

    public void setProcessInspectionDetailDOList(List<ProcessInspectionDetailDO> processInspectionDetailDOList) {
        this.processInspectionDetailDOList = processInspectionDetailDOList;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * 获取：质检报告ID
     */
    public String getProcessInspectionReportId() {
        return processInspectionReportId;
    }

    /**
     * 设置：质检报告ID
     */
    public void setProcessInspectionReportId(String processInspectionReportId) {
        this.processInspectionReportId = processInspectionReportId;
    }

    /**
     * 获取：质检日期
     */
    public Date getInspectionDate() {
        return inspectionDate;
    }

    /**
     * 设置：质检日期
     */
    public void setInspectionDate(Date inspectionDate) {
        this.inspectionDate = inspectionDate;
    }

    /**
     * 获取：质检明细ID
     */
    public String getProcessInspectionDetailId() {
        return processInspectionDetailId;
    }

    /**
     * 设置：质检明细ID
     */
    public void setProcessInspectionDetailId(String processInspectionDetailId) {
        this.processInspectionDetailId = processInspectionDetailId;
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
     * 获取：创建用户ID
     */
    public Integer getCreateUserId() {
        return createUserId;
    }

    /**
     * 设置：创建用户ID
     */
    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
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
