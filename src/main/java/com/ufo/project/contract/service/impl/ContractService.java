package com.ufo.project.contract.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufo.config.sys.entity.Manager;
import com.ufo.config.sys.entity.Manager.ManagerEnum;
import com.ufo.core.dao.BaseDao;
import com.ufo.core.service.BaseSpringDataService;
import com.ufo.core.utils.DateUtils;
import com.ufo.core.utils.StringUtils;
import com.ufo.project.contract.dao.interfaces.ContractDao;
import com.ufo.project.contract.entity.Contract;
import com.ufo.project.contract.service.interfaces.IContractService;

@Service
@Transactional
public class ContractService extends BaseSpringDataService<Contract, Integer> implements IContractService {
    
    @Autowired
    private ContractDao contractDao;

    @Override
    public BaseDao<Contract, Integer> getEntityDao() {
        return contractDao;
    }

    public String genCanUseEngineeringCode() {
        String code;
        String start = String.valueOf(DateUtils.getThisYear()) + (DateUtils.getThisMonth() > 9 ? String.valueOf(DateUtils.getThisMonth()) : "0" + DateUtils.getThisMonth());
        Contract contract = contractDao.getTodayMaxEngineeringCode(start + "%");
        if (contract == null){
            code = start + "0001";
        } else {
            code = start + StringUtils.addZero(Integer.valueOf(contract.getEngineeringCode().substring(6)) + 1, 4);
        }
        return code;
    }
    
    public List<Contract> findByUser(ManagerEnum type, String code) {
        if (type.equals(Manager.ManagerEnum.EMPLOYEE)) {
            return contractDao.findByEmployee(code);
        } else if (type.equals(Manager.ManagerEnum.CUSTOM)) {
            return contractDao.findByCustom(code);
        }
        return null;
    }

    public Contract findByMin() {
        return contractDao.findByMin();
    }

}
