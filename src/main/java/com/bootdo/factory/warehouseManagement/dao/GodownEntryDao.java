package com.bootdo.factory.warehouseManagement.dao;

import com.bootdo.factory.warehouseManagement.domain.GodownEntryDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author willice
 * @email 2782546116@qq.com
 * @date 2020-03-19 11:12:54
 */
@Mapper
public interface GodownEntryDao {

    GodownEntryDO get(String godownEntryId);

    List<GodownEntryDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(GodownEntryDO godownEntry);

    int update(GodownEntryDO godownEntry);

    int remove(String godown_entry_id);

    int batchRemove(String[] godownEntryIds);
}
