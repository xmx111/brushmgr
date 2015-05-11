package com.ufo.project.quote.service.impl;

import com.ufo.project.contract.entity.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufo.project.quote.dao.interfaces.QuoteDao;
import com.ufo.project.quote.entity.Quote;
import com.ufo.project.quote.service.interfaces.IQuoteService;
import com.ufo.core.dao.BaseDao;
import com.ufo.core.service.BaseSpringDataService;
import com.ufo.core.service.IBaseSpringDataService;

import java.util.List;

@Service
@Transactional
public class QuoteService extends BaseSpringDataService<Quote, Integer> implements IQuoteService {
    
    @Autowired
    private QuoteDao quoteDao;

    @Override
    public BaseDao<Quote, Integer> getEntityDao() {
        return quoteDao;
    }

    @Override
    public Integer getMaxCode(Contract contract) {
        Integer code = quoteDao.getMaxCode(contract);
        if (code == null)
            code = 1;
        else
            code = code + 1;
        return code;
    }

    @Override
    public List<Quote> findByEngineeringCode(String engineeringCode) {
        return quoteDao.findByEngineeringCode(engineeringCode);
    }

    @Override
    public Quote findByEngineeringCodeAndCode(String engineeringCode, Integer code) {
        return quoteDao.findByEngineeringCodeAndCode(engineeringCode, code);
    }

    @Override
    public Quote findByContractAndCode(Contract contract, Integer code) {
        return quoteDao.findByContractAndCode(contract, code);
    }
}
