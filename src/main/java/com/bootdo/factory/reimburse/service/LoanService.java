package com.bootdo.factory.reimburse.service;

import com.bootdo.factory.reimburse.domain.LoanDO;

import java.util.List;
import java.util.Map;

/**
 * @author willice
 * @email willicen@qq.com
 * @date 2020-05-25 22:39:15
 */
public interface LoanService {

    LoanDO get(String loanId);

    List<LoanDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(LoanDO loan);

    int update(LoanDO loan);

    int remove(String loanId);

    int batchRemove(String[] loanIds);
}
