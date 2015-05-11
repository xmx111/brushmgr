package com.ufo.basedata.news.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufo.basedata.news.dao.interfaces.NewsDao;
import com.ufo.basedata.news.entity.News;
import com.ufo.basedata.news.entity.News.TypeEnum;
import com.ufo.basedata.news.service.interfaces.INewsService;
import com.ufo.core.dao.BaseDao;
import com.ufo.core.service.BaseSpringDataService;

@Service
@Transactional
public class NewsService extends BaseSpringDataService<News, Integer> implements INewsService {
    
    @Autowired
    private NewsDao newsDao;

    @Override
    public BaseDao<News, Integer> getEntityDao() {
        return newsDao;
    }
    
    public Page<News> findPageByType(TypeEnum type,Pageable page){
        return newsDao.findPageByType(type, page);
    }
}
