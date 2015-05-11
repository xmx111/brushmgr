package com.ufo.engineering.order.dao.interfaces;

import org.springframework.stereotype.Repository;

import com.ufo.core.dao.BaseDao;
import com.ufo.engineering.order.entity.OrderDetails;

@Repository
public interface OrderDetailsDao extends BaseDao<OrderDetails, Integer> {
}
