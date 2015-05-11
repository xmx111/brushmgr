package com.ufo.engineering.order.dao.interfaces;

import org.springframework.stereotype.Repository;

import com.ufo.engineering.order.entity.Express;
import com.ufo.core.dao.BaseDao;

@Repository
public interface ExpressDao extends BaseDao<Express, Integer> {

}
