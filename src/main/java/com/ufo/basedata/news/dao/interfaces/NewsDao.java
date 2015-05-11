package com.ufo.basedata.news.dao.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ufo.basedata.news.entity.News;
import com.ufo.basedata.news.entity.News.TypeEnum;
import com.ufo.core.dao.BaseDao;

@Repository
public interface NewsDao extends BaseDao<News, Integer> {

    @Query("select n from News n where n.deleted=0 and n.type = ?1 order by n.id desc")
    public Page<News> findPageByType(TypeEnum type,Pageable page);
}
