package com.ufo.ucenter.custom.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufo.ucenter.custom.dao.interfaces.CustomDao;
import com.ufo.ucenter.custom.entity.Custom;
import com.ufo.ucenter.custom.service.interfaces.ICustomService;
import com.ufo.core.dao.BaseDao;
import com.ufo.core.service.BaseSpringDataService;
import com.ufo.core.service.IBaseSpringDataService;

@Service
@Transactional
public class CustomService extends BaseSpringDataService<Custom, Integer> implements ICustomService {
    
    @Autowired
    private CustomDao customDao;

    @Override
    public BaseDao<Custom, Integer> getEntityDao() {
        return customDao;
    }
}
