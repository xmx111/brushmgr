package com.ufo.basedata.news.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ufo.basedata.news.entity.News;
import com.ufo.basedata.news.entity.News.TypeEnum;
import com.ufo.core.service.IBaseSpringDataService;

public interface INewsService extends IBaseSpringDataService<News, Integer> {

    /**
     * 根据类型分页查询资讯
    * @param type
    * @param page
    * @return
     */
    public Page<News> findPageByType(TypeEnum type,Pageable page);
}