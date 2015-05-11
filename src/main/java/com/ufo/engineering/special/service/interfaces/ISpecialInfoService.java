package com.ufo.engineering.special.service.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ufo.core.service.IBaseSpringDataService;
import com.ufo.engineering.special.entity.SpecialInfo;

public interface ISpecialInfoService extends IBaseSpringDataService<SpecialInfo, Integer> {

    /**
     * 根据工程编号查询特殊信息
    * @param engineeringCode
    * @return
     */
    public List<SpecialInfo> findByEngineeringCode(String engineeringCode);
    
    /**
     * 根据工程编号查询特殊信息分页查询
    * @param engineeringCode
    * @param page
    * @return
     */
    public Page<SpecialInfo> findPageByEngineeringCode(String engineeringCode,Pageable page);
}