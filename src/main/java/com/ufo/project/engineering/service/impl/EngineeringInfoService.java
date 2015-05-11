package com.ufo.project.engineering.service.impl;

import com.ufo.project.contract.entity.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufo.project.engineering.dao.interfaces.EngineeringInfoDao;
import com.ufo.project.engineering.entity.EngineeringInfo;
import com.ufo.project.engineering.service.interfaces.IEngineeringInfoService;
import com.ufo.core.dao.BaseDao;
import com.ufo.core.service.BaseSpringDataService;
import com.ufo.core.service.IBaseSpringDataService;

@Service
@Transactional
public class EngineeringInfoService extends BaseSpringDataService<EngineeringInfo, Integer> implements IEngineeringInfoService {
    
    @Autowired
    private EngineeringInfoDao engineeringInfoDao;

    @Override
    public BaseDao<EngineeringInfo, Integer> getEntityDao() {
        return engineeringInfoDao;
    }

    @Override
    public EngineeringInfo findByEngineeringCode(String engineeringCode) {
        return engineeringInfoDao.findByEngineeringCode(engineeringCode);
    }

    @Override
    public EngineeringInfo findByContract(Contract contract) {
        return engineeringInfoDao.findByContract(contract);
    }
}
