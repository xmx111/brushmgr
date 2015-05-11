package com.ufo.project.quote.dao.interfaces;

import com.ufo.project.contract.entity.Contract;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ufo.project.quote.entity.Quote;
import com.ufo.core.dao.BaseDao;

import java.util.List;

@Repository
public interface QuoteDao extends BaseDao<Quote, Integer> {

    @Query("select max(q.code) from Quote q where q.contract=?1 and deleted=0")
    public Integer getMaxCode(Contract contract);

    @Query("select q from Quote q where q.contract.engineeringCode = ?1 and deleted=0")
    public List<Quote> findByEngineeringCode(String engineeringCode);

    @Query("select q from Quote q where q.contract.engineeringCode = ?1 and q.code = ?2 and deleted=0")
    public Quote findByEngineeringCodeAndCode(String engineeringCode, Integer code);

    @Query("select q from Quote q where q.contract = ?1 and q.code = ?2 and deleted=0")
    public Quote findByContractAndCode(Contract contract, Integer code);
}
