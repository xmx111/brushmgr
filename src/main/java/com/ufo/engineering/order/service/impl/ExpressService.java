package com.ufo.engineering.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufo.engineering.order.dao.interfaces.ExpressDao;
import com.ufo.engineering.order.entity.Express;
import com.ufo.engineering.order.service.interfaces.IExpressService;
import com.ufo.core.dao.BaseDao;
import com.ufo.core.service.BaseSpringDataService;
import com.ufo.core.service.IBaseSpringDataService;

@Service
@Transactional
public class ExpressService extends BaseSpringDataService<Express, Integer> implements IExpressService {
    
    @Autowired
    private ExpressDao expressDao;

    @Override
    public BaseDao<Express, Integer> getEntityDao() {
        return expressDao;
    }
}
