package com.ufo.engineering.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufo.engineering.order.dao.interfaces.InstallationImageDao;
import com.ufo.engineering.order.entity.InstallationImage;
import com.ufo.engineering.order.service.interfaces.IInstallationImageService;
import com.ufo.core.dao.BaseDao;
import com.ufo.core.service.BaseSpringDataService;
import com.ufo.core.service.IBaseSpringDataService;

@Service
@Transactional
public class InstallationImageService extends BaseSpringDataService<InstallationImage, Integer> implements IInstallationImageService {
    
    @Autowired
    private InstallationImageDao installationImageDao;

    @Override
    public BaseDao<InstallationImage, Integer> getEntityDao() {
        return installationImageDao;
    }
}
