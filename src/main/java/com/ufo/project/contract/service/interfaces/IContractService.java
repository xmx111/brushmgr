package com.ufo.project.contract.service.interfaces;

import java.util.List;

import com.ufo.config.sys.entity.Manager.ManagerEnum;
import com.ufo.core.service.IBaseSpringDataService;
import com.ufo.project.contract.entity.Contract;

public interface IContractService extends IBaseSpringDataService<Contract, Integer> {

    /**
     * 取当前可用的新工程编号
     * @return
     */
    public String genCanUseEngineeringCode();
    
    /**
     * 获取员工、客户合同列表
    * @param type
    * @param uid
    * @return
     */
    public List<Contract> findByUser(ManagerEnum type, String code);

    /**
     * 取最小id合同
     * @return
     */
    public Contract findByMin();

}