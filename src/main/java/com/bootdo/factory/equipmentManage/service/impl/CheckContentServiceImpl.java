package com.bootdo.factory.equipmentManage.service.impl;

import com.bootdo.common.config.Constant;
import com.bootdo.common.dao.DictDao;
import com.bootdo.common.domain.DictDO;
import com.bootdo.common.domain.Tree;
import com.bootdo.common.utils.BuildTree;
import com.bootdo.factory.equipmentManage.dao.EquipmentManageDao;
import com.bootdo.factory.equipmentManage.domain.EquipmentManageDO;
import com.bootdo.system.domain.DeptDO;
import com.bootdo.system.domain.UserDO;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.factory.equipmentManage.dao.CheckContentDao;
import com.bootdo.factory.equipmentManage.domain.CheckContentDO;
import com.bootdo.factory.equipmentManage.service.CheckContentService;



@Service
public class CheckContentServiceImpl implements CheckContentService {
	@Autowired
	EquipmentManageDao manageDao;
	@Autowired
	DictDao dictDao;
	@Autowired
	private CheckContentDao checkContentDao;

	@Override
	public CheckContentDO get(int checkId){
		return checkContentDao.get(checkId);
	}
	
	@Override
	public List<CheckContentDO> list(Map<String, Object> map){
		return checkContentDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return checkContentDao.count(map);
	}
	
	@Override
	public int save(CheckContentDO checkContent){
		return checkContentDao.save(checkContent);
	}
	
	@Override
	public int update(CheckContentDO checkContent){
		return checkContentDao.update(checkContent);
	}
	
	@Override
	public int remove(int checkId){
		return checkContentDao.remove(checkId);
	}
	
	@Override
	public int batchRemove(int[] checkIds){
		return checkContentDao.batchRemove(checkIds);
	}

	@Override
	public Tree<DictDO> getTree() {
		{
			List<Tree<DictDO>> trees = new ArrayList<Tree<DictDO>>();
			HashMap<String,Object> map = new HashMap<>();
			map.put("type","equipment_type");
			List<DictDO> type = dictDao.list(map);
			Long[] parentType = dictDao.listParentType(Constant.EQUIPMENT_TYPE);
			Long[] equipmentType = manageDao.listAllType();
			Long[] allDepts = (Long[]) ArrayUtils.addAll(parentType, equipmentType);
			for (DictDO dictDo : type) {
				Tree<DictDO> tree = new Tree<DictDO>();
				tree.setId(dictDo.getId().toString());
				tree.setParentId("0");
				tree.setText(dictDo.getName());
				Map<String, Object> state = new HashMap<>(16);
				state.put("opened", true);
				state.put("mType", "dict");
				tree.setState(state);
				trees.add(tree);
			}
			// 默认顶级菜单为０，根据数据库实际情况调整
			Tree<DictDO> t = BuildTree.build(trees);
			return t;
		}
	}

}
