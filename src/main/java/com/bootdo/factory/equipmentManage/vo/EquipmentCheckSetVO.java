package com.bootdo.factory.equipmentManage.vo;

import com.bootdo.common.annotation.Log;
import com.bootdo.factory.equipmentManage.domain.EquipmentCheckSetDO;
import com.bootdo.factory.equipmentManage.domain.EquipmentManageDO;

public class EquipmentCheckSetVO extends EquipmentCheckSetDO {
    @Log("设备编码")
    private String equipmentCode;
    @Log("设备类型")
    private String equipmentType;
    @Log("设备名称")
    private String equipmentName;
    @Log("点检人姓名")
    private String checkerName;



    public String getEquipmentCode() {
        return equipmentCode;
    }

    public void setEquipmentCode(String equipmentCode) {
        this.equipmentCode = equipmentCode;
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getCheckerName() {
        return this.checkerName;
    }

    public void setCheckerName(final String checkerName) {
        this.checkerName = checkerName;
    }
}
