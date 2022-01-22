package com.bootdo.factory.productManage.domain;

import java.io.Serializable;


/**
 * @author willice
 * @email 2782546116@qq.com
 * @date 2020-04-10 13:28:34
 */
public class ProcessInspectionDetailDO implements Serializable {
    private static final long serialVersionUID = 1L;

    //质检明细ID
    private String processInspectionDetailId;
    //质检报告ID
    private String processInspectionReportId;
    //工序ID
    private String processId;
    //工序名称
    private String processName;
    //部件ID
    private String componentId;
    //部件名称
    private String componentName;
    //规格
    private String format;
    //数量单位
    private String unit;
    //合格数量
    private Integer qualifiedNum;
    //返工数量
    private Integer reworkNum;
    //报废数量
    private Integer scrapNum;
    //
    private Integer num;

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getComponentId() {
        return componentId;
    }

    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }

    public String getProcessInspectionReportId() {
        return processInspectionReportId;
    }

    public void setProcessInspectionReportId(String processInspectionReportId) {
        this.processInspectionReportId = processInspectionReportId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
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
     * 获取：工序名称
     */
    public String getProcessName() {
        return processName;
    }

    /**
     * 设置：工序名称
     */
    public void setProcessName(String processName) {
        this.processName = processName;
    }

    /**
     * 获取：部件名称
     */
    public String getComponentName() {
        return componentName;
    }

    /**
     * 设置：部件名称
     */
    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    /**
     * 获取：规格
     */
    public String getFormat() {
        return format;
    }

    /**
     * 设置：规格
     */
    public void setFormat(String format) {
        this.format = format;
    }

    /**
     * 获取：数量单位
     */
    public String getUnit() {
        return unit;
    }

    /**
     * 设置：数量单位
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * 获取：合格数量
     */
    public Integer getQualifiedNum() {
        return qualifiedNum;
    }

    /**
     * 设置：合格数量
     */
    public void setQualifiedNum(Integer qualifiedNum) {
        this.qualifiedNum = qualifiedNum;
    }

    /**
     * 获取：返工数量
     */
    public Integer getReworkNum() {
        return reworkNum;
    }

    /**
     * 设置：返工数量
     */
    public void setReworkNum(Integer reworkNum) {
        this.reworkNum = reworkNum;
    }

    /**
     * 获取：报废数量
     */
    public Integer getScrapNum() {
        return scrapNum;
    }

    /**
     * 设置：报废数量
     */
    public void setScrapNum(Integer scrapNum) {
        this.scrapNum = scrapNum;
    }
}
