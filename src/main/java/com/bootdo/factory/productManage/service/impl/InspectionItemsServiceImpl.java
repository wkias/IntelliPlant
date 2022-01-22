package com.bootdo.factory.productManage.service.impl;

import com.bootdo.common.service.DictService;
import com.bootdo.factory.productManage.dao.InspectionItemsDao;
import com.bootdo.factory.productManage.domain.InspectionItemsDO;
import com.bootdo.factory.productManage.service.InspectionItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;


@Service
public class InspectionItemsServiceImpl implements InspectionItemsService {
	@Autowired
	private InspectionItemsDao inspectionItemsDao;
	@Autowired
	private DictService dictService;

	@Override
	public InspectionItemsDO get(String inspectionItemsId) {
		InspectionItemsDO inspectionItemsDO = inspectionItemsDao.get(inspectionItemsId);
		String str;
		str = inspectionItemsDO.getValueType();
		str = dictService.getName("inspection_items_value_type", str);
		inspectionItemsDO.setValueTypeName(str);

		str = inspectionItemsDO.getUnit();
		str = dictService.getName("inspection_items_unit", str);
		inspectionItemsDO.setUnitName(str);

		str = inspectionItemsDO.getState();
		str = dictService.getName("inspection_items_state", str);
		inspectionItemsDO.setStateName(str);

		return inspectionItemsDO;
	}

	@Override
	public List<InspectionItemsDO> list(Map<String, Object> map) {
		List<InspectionItemsDO> inspectionItemsDOList = inspectionItemsDao.list(map);
		String str;
		for (InspectionItemsDO inspectionItemsDO : inspectionItemsDOList) {
			str = inspectionItemsDO.getValueType();
			str = dictService.getName("inspection_items_value_type", str);
			inspectionItemsDO.setValueTypeName(str);

			str = inspectionItemsDO.getUnit();
			str = dictService.getName("inspection_items_unit", str);
			inspectionItemsDO.setUnitName(str);

			str = inspectionItemsDO.getState();
			str = dictService.getName("inspection_items_state", str);
			inspectionItemsDO.setStateName(str);
		}
		return inspectionItemsDOList;
	}

	@Override
	public int count(Map<String, Object> map) {
		return inspectionItemsDao.count(map);
	}

	@Override
	public int save(InspectionItemsDO inspectionItems) {
		inspectionItems.setInspectionItemsId(UUID.randomUUID().toString());
		return inspectionItemsDao.save(inspectionItems);
	}

	@Override
	public int update(InspectionItemsDO inspectionItems) {
		return inspectionItemsDao.update(inspectionItems);
	}

	@Override
	public int switchh(String inspectionItemsId) {
		InspectionItemsDO inspectionItemsDO = inspectionItemsDao.get(inspectionItemsId);
		if (inspectionItemsDO.getState().equals("0")) {
			inspectionItemsDO.setState("1");
		} else {
			inspectionItemsDO.setState("0");
		}
		return update(inspectionItemsDO);
	}

	@Override
	public int remove(String inspectionItemsId) {
		return inspectionItemsDao.remove(inspectionItemsId);
	}

	@Override
	public int batchRemove(String[] inspectionItemsIds) {
		return inspectionItemsDao.batchRemove(inspectionItemsIds);
	}

}
