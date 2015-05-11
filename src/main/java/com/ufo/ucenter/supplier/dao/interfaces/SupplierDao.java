package com.ufo.ucenter.supplier.dao.interfaces;

import org.springframework.stereotype.Repository;

import com.ufo.ucenter.supplier.entity.Supplier;
import com.ufo.core.dao.BaseDao;

@Repository
public interface SupplierDao extends BaseDao<Supplier, Integer> {

}
