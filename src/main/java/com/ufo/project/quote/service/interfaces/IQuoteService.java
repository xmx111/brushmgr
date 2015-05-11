package com.ufo.project.quote.service.interfaces;

import com.ufo.project.contract.entity.Contract;
import com.ufo.project.quote.entity.Quote;
import com.ufo.core.service.IBaseSpringDataService;

import java.util.List;

public interface IQuoteService extends IBaseSpringDataService<Quote, Integer> {

    /**
     * 取最大编号
     * @param contract
     * @return
     */
    public Integer getMaxCode(Contract contract);

    public List<Quote> findByEngineeringCode(String engineeringCode);

    public Quote findByEngineeringCodeAndCode(String engineeringCode, Integer code);

    public Quote findByContractAndCode(Contract contract, Integer code);
}