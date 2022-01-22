package com.bootdo.factory.contractManage.vo;

import com.bootdo.factory.contractManage.domain.ContractTraderDO;
import com.bootdo.factory.contractManage.domain.CustermerInformationDO;

import java.util.List;

public class ContractTraderVO extends ContractTraderDO {
    private CustermerInformationDO trader;
    private String contactPersonName;

    public CustermerInformationDO getTrader() {
        return this.trader;
    }

    public void setTrader(final CustermerInformationDO trader) {
        this.trader = trader;
    }

    public String getContactPersonName() {
        return this.contactPersonName;
    }

    public void setContactPersonName(final String contactPersonName) {
        this.contactPersonName = contactPersonName;
    }
}
