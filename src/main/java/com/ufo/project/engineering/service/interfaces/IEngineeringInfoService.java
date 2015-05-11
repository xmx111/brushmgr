package com.ufo.project.engineering.service.interfaces;

import com.ufo.project.contract.entity.Contract;
import com.ufo.project.engineering.entity.EngineeringInfo;
import com.ufo.core.service.IBaseSpringDataService;

public interface IEngineeringInfoService extends IBaseSpringDataService<EngineeringInfo, Integer> {

    /**
     * 根据工程编号找工程项目信息
     * @return
     */
    public EngineeringInfo findByEngineeringCode(String engineeringCode);

    /**
     * 根据工程编号找工程项目信息
     * @return
     */
    public EngineeringInfo findByContract(Contract contract);

}