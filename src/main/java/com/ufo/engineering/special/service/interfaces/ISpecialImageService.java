package com.ufo.engineering.special.service.interfaces;

import java.util.List;

import com.ufo.core.service.IBaseSpringDataService;
import com.ufo.engineering.special.entity.SpecialImage;

public interface ISpecialImageService extends IBaseSpringDataService<SpecialImage, Integer> {

    /**
     * 根据特殊信息id查询特殊采集图片
    * @param specialId
    * @return
     */
    public List<SpecialImage> findBySpecialId(Integer specialId);
}