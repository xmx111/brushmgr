package com.ufo.basedata.publicity.service.interfaces;

import com.ufo.basedata.publicity.entity.Publicity;
import com.ufo.basedata.publicity.entity.Publicity.PublicityEnum;
import com.ufo.core.service.IBaseSpringDataService;

public interface IPublicityService extends IBaseSpringDataService<Publicity, Integer> {
    public Publicity findByPublicityType(PublicityEnum publicityType);
}