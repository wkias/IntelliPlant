package com.bootdo.factory.personManage.vo;

import com.bootdo.common.annotation.Log;
import com.bootdo.factory.personManage.domain.WorkLogDO;

public class WorkLogVO extends WorkLogDO {
    @Log("人员名称")
    private String userName;
    @Log("归属部门")
    private String deptName;


    public String getUserName() {
        return this.userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    public String getDeptName() {
        return this.deptName;
    }

    public void setDeptName(final String deptName) {
        this.deptName = deptName;
    }

}
