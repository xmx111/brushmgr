package com.ufo.project.drawing.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufo.core.dao.BaseDao;
import com.ufo.core.service.BaseSpringDataService;
import com.ufo.project.contract.entity.Contract;
import com.ufo.project.drawing.dao.interfaces.DrawingDao;
import com.ufo.project.drawing.entity.Drawing;
import com.ufo.project.drawing.entity.Drawing.DrawingEnum;
import com.ufo.project.drawing.service.interfaces.IDrawingService;

@Service
@Transactional
public class DrawingService extends BaseSpringDataService<Drawing, Integer> implements IDrawingService {
    
    @Autowired
    private DrawingDao drawingDao;

    @Override
    public BaseDao<Drawing, Integer> getEntityDao() {
        return drawingDao;
    }

    @Override
    public Integer getMaxVersion(Contract contract, DrawingEnum type) {
        Integer code = drawingDao.getMaxVersionByContractAndType(contract, type);
        if (code == null)
            code = 1;
        else
            code = code + 1;
        return code;
    }

    @Override
    public Integer getMaxCode(Contract contract, DrawingEnum type, Integer version) {
        Integer code = drawingDao.getMaxCode(contract, type, version);
        if (code == null)
            code = 1;
        else
            code = code + 1;
        return code;
    }

    @Override
    public Drawing findByContractAndVersionAndCode(Contract contract, Integer version, Integer code) {
        return drawingDao.getMaxByContractAndVersionAndCode(contract, version, code);
    }

    @Override
    public Integer findMaxVersionByEngineeringCodeAndType(String engineeringCode, DrawingEnum type) {
        return drawingDao.findMaxVersionByEngineeringCodeAndType(engineeringCode, type);
    }


    @Override
    public List<Drawing> findByEngineeringCodeAndType(String engineeringCode, DrawingEnum type) {
        return drawingDao.findByEngineeringCodeAndType(engineeringCode, type);
    }
    @Override
    public List<Drawing> findByEngineeringCodeAndTypeAndVersion(String engineeringCode, DrawingEnum type, Integer version) {
        return drawingDao.findByEngineeringCodeAndTypeAndVersion(engineeringCode, type, version);
    }

    @Override
    public List<Integer> findVersion(Integer contractId, DrawingEnum type, Pageable page) {
        return drawingDao.findVersion(contractId, type, page);
    }
    
    @Override
    public List<Integer> findDistinctVersion(String engineeringCode, DrawingEnum type) {
        return drawingDao.findDistinctVersion(engineeringCode, type);
    }
}
