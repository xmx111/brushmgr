package com.ufo.basedata.material.dao.interfaces;

import org.springframework.stereotype.Repository;

import com.ufo.basedata.material.entity.Material;
import com.ufo.core.dao.BaseDao;

@Repository
public interface MaterialDao extends BaseDao<Material, Integer> {

}
