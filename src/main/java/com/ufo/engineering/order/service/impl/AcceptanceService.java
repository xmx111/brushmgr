package com.ufo.engineering.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufo.engineering.order.dao.interfaces.AcceptanceDao;
import com.ufo.engineering.order.entity.Acceptance;
import com.ufo.engineering.order.service.interfaces.IAcceptanceService;
import com.ufo.core.dao.BaseDao;
import com.ufo.core.service.BaseSpringDataService;
import com.ufo.core.service.IBaseSpringDataService;

@Service
@Transactional
public class AcceptanceService extends BaseSpringDataService<Acceptance, Integer> implements IAcceptanceService {
    
    @Autowired
    private AcceptanceDao acceptanceDao;

    @Override
    public BaseDao<Acceptance, Integer> getEntityDao() {
        return acceptanceDao;
    }
}
