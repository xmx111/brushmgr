package com.ufo.engineering.special.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufo.core.dao.BaseDao;
import com.ufo.core.service.BaseSpringDataService;
import com.ufo.engineering.special.dao.interfaces.SpecialInfoDao;
import com.ufo.engineering.special.entity.SpecialInfo;
import com.ufo.engineering.special.service.interfaces.ISpecialInfoService;

@Service
@Transactional
public class SpecialInfoService extends BaseSpringDataService<SpecialInfo, Integer> implements ISpecialInfoService {
    
    @Autowired
    private SpecialInfoDao specialInfoDao;

    @Override
    public BaseDao<SpecialInfo, Integer> getEntityDao() {
        return specialInfoDao;
    }
    
    public List<SpecialInfo> findByEngineeringCode(String engineeringCode){
        return specialInfoDao.findByEngineeringCode(engineeringCode);
    }
    
    public Page<SpecialInfo> findPageByEngineeringCode(String engineeringCode,Pageable page){
        return specialInfoDao.findPageByEngineeringCode(engineeringCode,page);
    }
}
