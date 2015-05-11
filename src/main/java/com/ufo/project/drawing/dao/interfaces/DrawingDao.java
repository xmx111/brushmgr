package com.ufo.project.drawing.dao.interfaces;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ufo.core.dao.BaseDao;
import com.ufo.project.contract.entity.Contract;
import com.ufo.project.drawing.entity.Drawing;
import com.ufo.project.drawing.entity.Drawing.DrawingEnum;

@Repository
public interface DrawingDao extends BaseDao<Drawing, Integer> {

    @Query("select max(d.code) from Drawing d where d.contract=?1 and type=?2 and version=?3 and deleted=0")
    public Integer getMaxCode(Contract contract, DrawingEnum type, Integer version);

    @Query("select max(d.version) from Drawing d where d.contract=?1 and type = ?2 and deleted=0")
    public Integer getMaxVersionByContractAndType(Contract contract, DrawingEnum type);

    @Query("select max(d) from Drawing d where d.contract=?1 and d.version = ?2 and d.code = ?3 and deleted=0")
    public Drawing getMaxByContractAndVersionAndCode(Contract contract, Integer version, Integer code);

    @Query("select max(d.version) from Drawing d where d.contract.engineeringCode = ?1 and d.type = ?2 and deleted=0")
    public Integer findMaxVersionByEngineeringCodeAndType(String engineeringCode, DrawingEnum type);

    @Query("select d from Drawing d where d.contract.engineeringCode = ?1 and d.type = ?2 and deleted=0 order by d.version desc")
    public List<Drawing> findByEngineeringCodeAndType(String engineeringCode, DrawingEnum type);

    @Query("select d from Drawing d where d.contract.engineeringCode = ?1 and d.type = ?2 and d.version = ?3 and deleted=0 order by d.version desc")
    public List<Drawing> findByEngineeringCodeAndTypeAndVersion(String engineeringCode, DrawingEnum type, Integer version);

    @Query("select distinct d.version from Drawing d where d.contract.id = ?1 and d.type = ?2 and deleted=0")
    public List<Integer> findVersion(Integer contractId, DrawingEnum type, Pageable page);
    
    @Query("select distinct d.version from Drawing d where d.contract.engineeringCode = ?1 and d.type = ?2 and deleted=0")
    public List<Integer> findDistinctVersion(String engineeringCode, DrawingEnum type);

}
