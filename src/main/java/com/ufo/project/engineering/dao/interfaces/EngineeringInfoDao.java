package com.ufo.project.engineering.dao.interfaces;

import com.ufo.project.contract.entity.Contract;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ufo.project.engineering.entity.EngineeringInfo;
import com.ufo.core.dao.BaseDao;

@Repository
public interface EngineeringInfoDao extends BaseDao<EngineeringInfo, Integer> {

    @Query("select e from EngineeringInfo e where e.contract.engineeringCode = ?1 and deleted=0")
    public EngineeringInfo findByEngineeringCode(String engineeringCode);

    @Query("select e from EngineeringInfo e where e.contract = ?1 and deleted=0")
    public EngineeringInfo findByContract(Contract contract);

}
