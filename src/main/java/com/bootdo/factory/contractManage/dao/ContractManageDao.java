package com.bootdo.factory.contractManage.dao;

import com.bootdo.factory.contractManage.domain.ContractManageDO;

import java.util.List;
import java.util.Map;

import com.bootdo.factory.contractManage.vo.ContractManageVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author xuhui
 * @email 1992lcg@163.com
 * @date 2020-03-09 15:22:58
 */
@Mapper
public interface ContractManageDao {

	ContractManageDO get(String contractId);

	ContractManageVO getVO(String contractId);

	List<ContractManageVO> list(Map<String,Object> map);

	int count(Map<String,Object> map);

	int save(ContractManageDO contractManage);

	int update(ContractManageDO contractManage);

	int remove(String contract_id);

	int batchRemove(String[] contractIds);
}
