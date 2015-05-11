package com.ufo.engineering.order.dao.interfaces;

import org.springframework.stereotype.Repository;

import com.ufo.engineering.order.entity.InstallationInfo;
import com.ufo.core.dao.BaseDao;

@Repository
public interface InstallationInfoDao extends BaseDao<InstallationInfo, Integer> {

}
