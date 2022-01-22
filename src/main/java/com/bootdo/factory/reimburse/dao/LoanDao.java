package com.bootdo.factory.reimburse.dao;

import com.bootdo.factory.reimburse.domain.LoanDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author willice
 * @email willicen@qq.com
 * @date 2020-05-25 22:39:15
 */
@Mapper
public interface LoanDao {

	LoanDO get(String loanId);

	List<LoanDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(LoanDO loan);

	int update(LoanDO loan);

	int remove(String loan_id);

	int batchRemove(String[] loanIds);
}
