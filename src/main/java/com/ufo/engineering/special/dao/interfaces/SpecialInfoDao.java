package com.ufo.engineering.special.dao.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ufo.core.dao.BaseDao;
import com.ufo.engineering.special.entity.SpecialInfo;

@Repository
public interface SpecialInfoDao extends BaseDao<SpecialInfo, Integer> {
    
    @Query("select s from SpecialInfo s where s.deleted=0 and s.contract.engineeringCode = ?1 order by id desc")
    public List<SpecialInfo> findByEngineeringCode(String engineeringCode);
    
    @Query("select s from SpecialInfo s where s.deleted=0 and s.contract.engineeringCode = ?1 order by id desc")
    public Page<SpecialInfo> findPageByEngineeringCode(String engineeringCode,Pageable page);
}
