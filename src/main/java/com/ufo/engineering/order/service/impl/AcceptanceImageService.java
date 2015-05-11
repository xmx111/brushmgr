package com.ufo.engineering.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufo.engineering.order.dao.interfaces.AcceptanceImageDao;
import com.ufo.engineering.order.entity.AcceptanceImage;
import com.ufo.engineering.order.service.interfaces.IAcceptanceImageService;
import com.ufo.core.dao.BaseDao;
import com.ufo.core.service.BaseSpringDataService;
import com.ufo.core.service.IBaseSpringDataService;

@Service
@Transactional
public class AcceptanceImageService extends BaseSpringDataService<AcceptanceImage, Integer> implements IAcceptanceImageService {
    
    @Autowired
    private AcceptanceImageDao acceptanceImageDao;

    @Override
    public BaseDao<AcceptanceImage, Integer> getEntityDao() {
        return acceptanceImageDao;
    }
}
