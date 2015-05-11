package com.ufo.project.contract.dao.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ufo.core.dao.BaseDao;
import com.ufo.project.contract.entity.Contract;

@Repository
public interface ContractDao extends BaseDao<Contract, Integer> {

    @Query("select max(c) from Contract c where c.engineeringCode like ?1")
    public Contract getTodayMaxEngineeringCode(String code);
    
    @Query("select c from Contract c where deleted=0 and c.employee.code = ?1 order by id desc")
    public List<Contract> findByEmployee(String code);
    
    @Query("select c from Contract c where deleted=0 and c.custom.code = ?1 order by id desc")
    public List<Contract> findByCustom(String code);

    @Query("select min(c) from Contract c where deleted=0 order by id desc")
    public Contract findByMin();
}
