package com.ufo.ucenter.custom.dao.interfaces;

import org.springframework.stereotype.Repository;

import com.ufo.ucenter.custom.entity.Custom;
import com.ufo.core.dao.BaseDao;

@Repository
public interface CustomDao extends BaseDao<Custom, Integer> {

}
