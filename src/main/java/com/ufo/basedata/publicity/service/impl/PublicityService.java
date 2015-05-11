package com.ufo.basedata.publicity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufo.basedata.publicity.dao.interfaces.PublicityDao;
import com.ufo.basedata.publicity.entity.Publicity;
import com.ufo.basedata.publicity.entity.Publicity.PublicityEnum;
import com.ufo.basedata.publicity.service.interfaces.IPublicityService;
import com.ufo.core.dao.BaseDao;
import com.ufo.core.service.BaseSpringDataService;

@Service
@Transactional
public class PublicityService extends BaseSpringDataService<Publicity, Integer> implements IPublicityService {
    
    @Autowired
    private PublicityDao publicityDao;

    @Override
    public BaseDao<Publicity, Integer> getEntityDao() {
        return publicityDao;
    }
    
    public Publicity findByPublicityType(PublicityEnum publicityType){
        return publicityDao.findByPublicityType(publicityType);
    }
}
