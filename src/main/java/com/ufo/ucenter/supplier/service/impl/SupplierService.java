package com.ufo.ucenter.supplier.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufo.ucenter.supplier.dao.interfaces.SupplierDao;
import com.ufo.ucenter.supplier.entity.Supplier;
import com.ufo.ucenter.supplier.service.interfaces.ISupplierService;
import com.ufo.core.dao.BaseDao;
import com.ufo.core.service.BaseSpringDataService;
import com.ufo.core.service.IBaseSpringDataService;

@Service
@Transactional
public class SupplierService extends BaseSpringDataService<Supplier, Integer> implements ISupplierService {
    
    @Autowired
    private SupplierDao supplierDao;

    @Override
    public BaseDao<Supplier, Integer> getEntityDao() {
        return supplierDao;
    }
}
