package com.ufo.engineering.special.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufo.core.dao.BaseDao;
import com.ufo.core.service.BaseSpringDataService;
import com.ufo.engineering.special.dao.interfaces.SpecialImageDao;
import com.ufo.engineering.special.entity.SpecialImage;
import com.ufo.engineering.special.service.interfaces.ISpecialImageService;

@Service
@Transactional
public class SpecialImageService extends BaseSpringDataService<SpecialImage, Integer> implements ISpecialImageService {
    
    @Autowired
    private SpecialImageDao specialImageDao;

    @Override
    public BaseDao<SpecialImage, Integer> getEntityDao() {
        return specialImageDao;
    }
    
    public List<SpecialImage> findBySpecialId(Integer specialId){
        return specialImageDao.findBySpecialId(specialId);
    }
}
