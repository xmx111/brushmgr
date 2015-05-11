package com.ufo.project.engineering.service.interfaces;

import com.ufo.project.engineering.entity.RelatedPersonal;
import com.ufo.core.service.IBaseSpringDataService;

import java.util.List;

public interface IRelatedPersonalService extends IBaseSpringDataService<RelatedPersonal, Integer> {

    /**
     * 根据工程编号查找关联信息
     * @return
     */
    public List<RelatedPersonal> findByEngineeringCode(String engineeringCode);

}