package com.bootdo.factory.equipmentManage.service.impl;

import com.bootdo.common.config.Constant;
import com.bootdo.common.service.DictService;
import com.bootdo.factory.equipmentManage.dao.EquipmentManageDao;
import com.bootdo.factory.equipmentManage.domain.EquipmentManageDO;
import com.bootdo.factory.equipmentManage.service.EquipmentManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.UUID;


@Service
public class EquipmentManageServiceImpl implements EquipmentManageService {
    @Autowired
    private EquipmentManageDao equipmentManageDao;
    @Autowired
    private DictService dictService;

    @Override
    public EquipmentManageDO get(String equipmentId) {
        EquipmentManageDO equipment = equipmentManageDao.get(equipmentId);
        return equipment;
    }

    @Override
    public EquipmentManageDO getWithNameType(String equipmentId) {
        EquipmentManageDO equipment = equipmentManageDao.get(equipmentId);
        String type = dictService.getName(Constant.EQUIPMENT_TYPE, equipment.getEquipmentType());
        equipment.setEquipmentType(type);
        return equipment;
    }

    @Override
    public List<EquipmentManageDO> list(Map<String, Object> map) {
        List<EquipmentManageDO> equipmentList = equipmentManageDao.list(map);
        ListIterator<EquipmentManageDO> iterator = equipmentList.listIterator();
        while (iterator.hasNext()) {
            EquipmentManageDO equipment = iterator.next();
            //if(equipment.getIsDeleted()){iterator.remove();continue;}
            try {
                String type = dictService.getName(Constant.EQUIPMENT_TYPE, equipment.getEquipmentType());
                equipment.setEquipmentType(type);
            } catch (Exception e) {
                //iterator.remove();
            }
        }
        return equipmentList;
    }

    @Override
    public int count(Map<String, Object> map) {
        return equipmentManageDao.count(map);
    }

    @Override
    public int save(EquipmentManageDO equipmentManage) {
        equipmentManage.setEquipmentId(UUID.randomUUID().toString());
        return equipmentManageDao.save(equipmentManage);
    }

    @Override
    public int update(EquipmentManageDO equipmentManage) {
        return equipmentManageDao.update(equipmentManage);
    }

    @Override
    public int remove(String equipmentId) {
        return equipmentManageDao.remove(equipmentId);
    }

    @Override
    public int batchRemove(String[] equipmentIds) {
        return equipmentManageDao.batchRemove(equipmentIds);
    }
    /**
     * sql.Date->util.Date
     * */
//    public void dateConvert(EquipmentManageDO equipment){
//        java.util.Date dateU = equipment.getProductionDate();
//        java.sql.Date dateS =new java.sql.Date(dateU.getTime());
//        SimpleDateFormat sdf=new SimpleDateFormat();
//        sdf.
//    }
}
