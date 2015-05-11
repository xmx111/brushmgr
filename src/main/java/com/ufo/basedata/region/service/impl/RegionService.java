package com.ufo.basedata.region.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufo.basedata.region.dao.interfaces.RegionDao;
import com.ufo.basedata.region.entity.Region;
import com.ufo.basedata.region.service.interfaces.IRegionService;
import com.ufo.core.dao.BaseDao;
import com.ufo.core.service.BaseSpringDataService;
import com.ufo.core.service.IBaseSpringDataService;

@Service
@Transactional
public class RegionService extends BaseSpringDataService<Region, Integer> implements IRegionService {
    
    @Autowired
    private RegionDao regionDao;

    @Override
    public BaseDao<Region, Integer> getEntityDao() {
        return regionDao;
    }
}
