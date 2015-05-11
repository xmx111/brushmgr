package com.ufo.basedata.material.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufo.basedata.material.dao.interfaces.MaterialDao;
import com.ufo.basedata.material.entity.Material;
import com.ufo.basedata.material.service.interfaces.IMaterialService;
import com.ufo.core.dao.BaseDao;
import com.ufo.core.service.BaseSpringDataService;
import com.ufo.core.service.IBaseSpringDataService;

@Service
@Transactional
public class MaterialService extends BaseSpringDataService<Material, Integer> implements IMaterialService {
    
    @Autowired
    private MaterialDao materialDao;

    @Override
    public BaseDao<Material, Integer> getEntityDao() {
        return materialDao;
    }
}
