package com.bootdo.activiti.vo;

import com.bootdo.activiti.domain.LeaveBillDO;
import com.bootdo.common.annotation.Log;

public class LeaveBillVO extends LeaveBillDO {
    @Log("申请人")
    private String applyUserName;
    @Log("归属部门")
    private String deptName;
    @Log("状态")
    private String stateName;

    public String getApplyUserName() {
        return this.applyUserName;
    }

    public void setApplyUserName(final String applyUserName) {
        this.applyUserName = applyUserName;
    }

    public String getDeptName() {
        return this.deptName;
    }

    public void setDeptName(final String deptName) {
        this.deptName = deptName;
    }

    public String getStateName() {
        return this.stateName;
    }

    public void setStateName(final String stateName) {
        this.stateName = stateName;
    }
}
