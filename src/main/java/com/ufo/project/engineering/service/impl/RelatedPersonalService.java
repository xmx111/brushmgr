package com.ufo.project.engineering.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufo.project.engineering.dao.interfaces.RelatedPersonalDao;
import com.ufo.project.engineering.entity.RelatedPersonal;
import com.ufo.project.engineering.service.interfaces.IRelatedPersonalService;
import com.ufo.core.dao.BaseDao;
import com.ufo.core.service.BaseSpringDataService;
import com.ufo.core.service.IBaseSpringDataService;

import java.util.List;

@Service
@Transactional
public class RelatedPersonalService extends BaseSpringDataService<RelatedPersonal, Integer> implements IRelatedPersonalService {
    
    @Autowired
    private RelatedPersonalDao relatedPersonalDao;

    @Override
    public BaseDao<RelatedPersonal, Integer> getEntityDao() {
        return relatedPersonalDao;
    }

    @Override
    public List<RelatedPersonal> findByEngineeringCode(String engineeringCode) {
        return relatedPersonalDao.findByEngineeringCode(engineeringCode);
    }
}
