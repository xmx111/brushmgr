package com.ufo.engineering.special.dao.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ufo.core.dao.BaseDao;
import com.ufo.engineering.special.entity.SpecialImage;

@Repository
public interface SpecialImageDao extends BaseDao<SpecialImage, Integer> {
    
    @Query("select s from SpecialImage s where s.specialInfo.id = ?1")
    public List<SpecialImage> findBySpecialId(Integer specialId);
}
