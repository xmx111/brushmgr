package com.ufo.project.drawing.service.interfaces;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.ufo.core.service.IBaseSpringDataService;
import com.ufo.project.contract.entity.Contract;
import com.ufo.project.drawing.entity.Drawing;
import com.ufo.project.drawing.entity.Drawing.DrawingEnum;

public interface IDrawingService extends IBaseSpringDataService<Drawing, Integer> {

    public Integer getMaxVersion(Contract contract, DrawingEnum type);

    public Integer getMaxCode(Contract contract, DrawingEnum type, Integer version);

    public Drawing findByContractAndVersionAndCode(Contract contract, Integer version, Integer code);

    public Integer findMaxVersionByEngineeringCodeAndType(String engineeringCode, DrawingEnum type);

    public List<Drawing> findByEngineeringCodeAndType(String engineeringCode, DrawingEnum type);

    public List<Drawing> findByEngineeringCodeAndTypeAndVersion(String engineeringCode, DrawingEnum type, Integer version);

    public List<Integer> findVersion(Integer contractId, DrawingEnum type, Pageable page);
    
    public List<Integer> findDistinctVersion(String engineeringCode, DrawingEnum type);
}