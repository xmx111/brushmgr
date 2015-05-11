package com.ufo.engineering.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufo.engineering.order.dao.interfaces.ExpressWayDao;
import com.ufo.engineering.order.entity.ExpressWay;
import com.ufo.engineering.order.service.interfaces.IExpressWayService;
import com.ufo.core.dao.BaseDao;
import com.ufo.core.service.BaseSpringDataService;
import com.ufo.core.service.IBaseSpringDataService;

@Service
@Transactional
public class ExpressWayService extends BaseSpringDataService<ExpressWay, Integer> implements IExpressWayService {
    
    @Autowired
    private ExpressWayDao expressWayDao;

    @Override
    public BaseDao<ExpressWay, Integer> getEntityDao() {
        return expressWayDao;
    }
}
