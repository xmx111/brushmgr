package com.ufo.engineering.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufo.engineering.order.dao.interfaces.InstallationInfoDao;
import com.ufo.engineering.order.entity.InstallationInfo;
import com.ufo.engineering.order.service.interfaces.IInstallationInfoService;
import com.ufo.core.dao.BaseDao;
import com.ufo.core.service.BaseSpringDataService;
import com.ufo.core.service.IBaseSpringDataService;

@Service
@Transactional
public class InstallationInfoService extends BaseSpringDataService<InstallationInfo, Integer> implements IInstallationInfoService {
    
    @Autowired
    private InstallationInfoDao installationInfoDao;

    @Override
    public BaseDao<InstallationInfo, Integer> getEntityDao() {
        return installationInfoDao;
    }
}
