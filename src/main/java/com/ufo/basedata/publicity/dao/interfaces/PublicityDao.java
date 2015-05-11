package com.ufo.basedata.publicity.dao.interfaces;

import org.springframework.stereotype.Repository;

import com.ufo.basedata.publicity.entity.Publicity;
import com.ufo.basedata.publicity.entity.Publicity.PublicityEnum;
import com.ufo.core.dao.BaseDao;

@Repository
public interface PublicityDao extends BaseDao<Publicity, Integer> {
    public Publicity findByPublicityType(PublicityEnum publicityType);
}
