package com.ufo.project.engineering.dao.interfaces;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ufo.project.engineering.entity.RelatedPersonal;
import com.ufo.core.dao.BaseDao;

import java.util.List;

@Repository
public interface RelatedPersonalDao extends BaseDao<RelatedPersonal, Integer> {

    @Query("select r from RelatedPersonal r where r.deleted=0 and r.engineeringInfo.contract.engineeringCode = ?1 order by type")
    public List<RelatedPersonal> findByEngineeringCode(String engineeringCode);

}
